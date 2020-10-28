package com.xdesigncodetest.controller;

import com.xdesigncodetest.model.Munro;
import com.xdesigncodetest.service.MunroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MunroController {

    private static final String CATEGORY_EITHER = "EITHER";
    private static final String CATEGORY_TOP = "TOP";
    private static final String CATEGORY_MUNRO = "MUN";
    @Autowired
    private MunroService munroService;
    
    @GetMapping("/munros")
    public ResponseEntity<List<Munro>> getAllMunros(@RequestParam(required = false) String category, @RequestParam(required = false) Double minHeight, 
        @RequestParam(required = false) Double maxHeight, @RequestParam(required = false) String sortField, 
        @RequestParam(required = false) String sortType, @RequestParam(required = false) Integer limitResults) {
        
        if(isNotValidCategory(category) || isNotValidHeight(minHeight, maxHeight) || invalidSortField(sortField) || invalidResultsLimit(limitResults)) {
            return ResponseEntity.badRequest().build();
        }
        
        //Default list is filtered to have munro & tops
        List<Munro> munros = munroService.filterMunroListByCategory(null, CATEGORY_EITHER);
        if(filtersToBeApplied(category, minHeight, maxHeight)) {
            if(StringUtils.hasLength(category) && !CATEGORY_EITHER.equalsIgnoreCase(category)) {
                munros = munroService.filterMunroListByCategory(munros, category);
            }
            if(maxHeight != null || minHeight != null) {
                munros = munroService.filterMunroListByHeight(munros, minHeight, maxHeight);
            }
        } 
        if(StringUtils.hasLength(sortField)) {
            if("NAME".equalsIgnoreCase(sortField)) {
                munros = munroService.sortMunrosByName(munros, "ASC".equalsIgnoreCase(sortType));
            } else if("HEIGHT".equalsIgnoreCase(sortField)) {
                munros = munroService.sortMunrosByHeight(munros, "ASC".equalsIgnoreCase(sortType));
            }
        }
        if(limitResults != null) {
            munros = munros.stream().limit(limitResults).collect(Collectors.toList());
        }
        return ResponseEntity.accepted().body(munros);
    }

    private boolean invalidResultsLimit(Integer limitResults) {
        return limitResults != null && limitResults <= 0;
    }

    private boolean invalidSortField(String sortField) {
        return StringUtils.hasLength(sortField) && (!"NAME".equalsIgnoreCase(sortField) && !"HEIGHT".equalsIgnoreCase(sortField));
    }

    private boolean isNotValidHeight(Double minHeight, Double maxHeight) {
        return (minHeight != null && minHeight < 0) || (maxHeight != null && maxHeight < 0) || (minHeight != null && maxHeight != null && maxHeight < minHeight);
    }

    private boolean isNotValidCategory(String category) {
        return StringUtils.hasLength(category) && !CATEGORY_EITHER.equalsIgnoreCase(category) && !CATEGORY_TOP.equalsIgnoreCase(category) &&
            !CATEGORY_MUNRO.equalsIgnoreCase(category);
    }

    private boolean filtersToBeApplied(String category, Double minHeight, Double maxHeight) {
        return !CATEGORY_EITHER.equalsIgnoreCase(category) || minHeight != null || maxHeight != null;
    }
}
