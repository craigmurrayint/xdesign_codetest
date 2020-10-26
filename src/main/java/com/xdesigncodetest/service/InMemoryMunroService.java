package com.xdesigncodetest.service;

import com.opencsv.bean.BeanVerifier;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.xdesigncodetest.model.Munro;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service 
public class InMemoryMunroService implements MunroService {
    
    private List<Munro> munros = new ArrayList<>();

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) throws FileNotFoundException {

        File file = ResourceUtils.getFile("classpath:munrotab_v6.2.csv");
        // parse CSV file to create a list of `User` objects
        try (Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {

            // create csv bean reader
            CsvToBean<Munro> csvToBean = new CsvToBeanBuilder(reader)
                .withType(Munro.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withVerifier(new MunroVerifier())
                .build();

            // convert `CsvToBean` object to list of users
            munros = csvToBean.parse();

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCountOfMunros() {
        return munros.size();
    }
   
    @Override
    public Optional<Munro> getMunroById(int runningNo) {
        return munros.stream().filter(munro -> munro.getRunningNo() == runningNo).findFirst();
    }
    
    private class MunroVerifier implements  BeanVerifier<Munro> {

        @Override 
        public boolean verifyBean(Munro munro) throws CsvConstraintViolationException {
          if(munro.getRunningNo() == 0) {
              return false;
          }
          return true;
        }
    }
}
