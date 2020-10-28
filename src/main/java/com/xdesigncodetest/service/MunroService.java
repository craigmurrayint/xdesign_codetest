package com.xdesigncodetest.service;

import com.xdesigncodetest.model.Munro;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MunroService {
    
    int getCountOfMunros();
    Optional<Munro> getMunroById(int runningNo);
    List<Munro> filterMunroListByCategory(final List<Munro> munros, String category);
    List<Munro> filterMunroListByHeight(List<Munro> munros, Double minHeight, Double maxHeight);
    List<Munro> sortMunrosByHeight(final List<Munro> munros, final boolean sortAscending);
    List<Munro> sortMunrosByName(final List<Munro> munros, final boolean sortAscending);
}
