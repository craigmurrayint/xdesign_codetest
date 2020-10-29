package com.xdesigncodetest.service;

import com.xdesigncodetest.data.InMemoryMunroDAO;
import com.xdesigncodetest.model.Munro;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service 
public class InMemoryMunroService implements MunroService {
    
    @Autowired 
    private InMemoryMunroDAO munroDAO;

    //Next two methods were only added to test the loading of the munro csv
    @Override
    public int getCountOfMunros() {
        return munroDAO.getMunros().size();
    }
   
    @Override
    public Optional<Munro> getMunroById(int runningNo) {
        return munroDAO.getMunros().stream().filter(munro -> munro.getRunningNo() == runningNo).findFirst();
    }
    
    @Override
    public List<Munro> filterMunroListByCategory(final List<Munro> inMunros, final String category) {
        List<Munro> munros = getMunrosFromDAOIfEmptyOrNull(inMunros);
        if("EITHER".equalsIgnoreCase(category)) {
            return munros.stream().filter(munro -> Strings.isNotEmpty(munro.getPost1997())).collect(Collectors.toList());
        } else {
            return munros.stream().filter(m -> category.equalsIgnoreCase(m.getPost1997())).collect(Collectors.toList()); 
        }
    }

    @Override 
    public List<Munro> filterMunroListByHeight(List<Munro> inMunros, Double minHeight, Double maxHeight) {
        List<Munro> munros = getMunrosFromDAOIfEmptyOrNull(inMunros);
        
        return munros.stream().filter(
            munro -> ((minHeight == null || munro.getHeightInMetre() >= minHeight) && (maxHeight == null || munro.getHeightInMetre() <= maxHeight)))
            .collect(Collectors.toList());
    }

    @Override 
    public List<Munro> sortMunrosByName(final List<Munro> inMunros, final boolean sortAscending) {
        List<Munro> munros = getMunrosFromDAOIfEmptyOrNull(inMunros);
        if(sortAscending) {
            munros.sort(Comparator.comparing(Munro::getName));
        } else {
            munros.sort(Comparator.comparing(Munro::getName).reversed());
        }
        return munros;
    }

    @Override
    public List<Munro> sortMunrosByHeight(final List<Munro> inMunros, final boolean sortAscending) {
        List<Munro> munros = getMunrosFromDAOIfEmptyOrNull(inMunros);
        if(sortAscending) {
            munros.sort(Comparator.comparing(Munro::getHeightInMetre));
        } else {
            munros.sort(Comparator.comparing(Munro::getHeightInFeet).reversed());
        }
        return munros;
    }

    private List<Munro> getMunrosFromDAOIfEmptyOrNull(List<Munro> munros) {
        if(munros == null || munros.isEmpty()) {
            munros = munroDAO.getMunros();
        }
        return munros;
    }
}
