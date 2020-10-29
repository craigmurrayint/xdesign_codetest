package com.xdesigncodetest.data;

import com.opencsv.bean.BeanVerifier;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.xdesigncodetest.model.Munro;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryMunroDAO implements MunroDAO{

    private final List<Munro> munros = new ArrayList<>();

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) throws FileNotFoundException {

        File file = ResourceUtils.getFile("classpath:munrotab_v6.2.csv");
        // parse CSV file to create a list of `User` objects
        try (Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {

            // create csv bean reader
            CsvToBean<Munro> csvToBean = new CsvToBeanBuilder<Munro>(reader)
                .withType(Munro.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withVerifier(new MunroVerifier())
                .build();

            // convert `CsvToBean` object to list of users
            munros.addAll(csvToBean.parse());
        }
        catch (IOException e ) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Munro> getMunros() {
        return new ArrayList<>(munros);
    }

    private static class MunroVerifier implements BeanVerifier<Munro> {
        @Override
        public boolean verifyBean(Munro munro) throws CsvConstraintViolationException {
            return munro.getRunningNo() != 0;
        }
    }
}
