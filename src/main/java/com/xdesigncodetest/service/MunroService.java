package com.xdesigncodetest.service;

import com.xdesigncodetest.model.Munro;

import java.util.Optional;

public interface MunroService {
    
    int getCountOfMunros();
    Optional<Munro> getMunroById(int runningNo);
}
