package com.xdesigncodetest.service;

import com.xdesigncodetest.data.InMemoryMunroDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {InMemoryMunroService.class, InMemoryMunroDAO.class})
public class MunroServiceTest {
    
    @Autowired
    private InMemoryMunroService munroService;
    
    @Test
    public void munroServiceSetUpTest() {
        assertThat(munroService.getCountOfMunros()).isEqualTo(602);
    }
    
    @Test
    public void getByRunningNoTest() {
        assertThat(munroService.getMunroById(1).get().getRunningNo()).isEqualTo(1);
        assertThat(munroService.getMunroById(602).get().getRunningNo()).isEqualTo(602);
        assertThat(munroService.getMunroById(1000).isPresent()).isFalse();
    }
    
    @Test
    public void sortByHeightInMetresTest() {
        assertThat(munroService.sortMunrosByHeight(null, true).get(0).getRunningNo()).isEqualTo(376);
        assertThat(munroService.sortMunrosByHeight(null, false).get(0).getRunningNo()).isEqualTo(133);
    }

    @Test
    public void sortByNameTest() {
        assertThat(munroService.sortMunrosByName(null, true).get(0).getName()).isEqualTo("A' Bhuidheanach Bheag");
        assertThat(munroService.sortMunrosByName(null, false).get(0).getName()).isEqualTo("Tom a' Choinich - Tom a' Choinich Beag");
    }

    @Test
    public void filterByCategoryTest() {
        assertThat(munroService.filterMunroListByCategory(null, "EITHER").size()).isEqualTo(509);
        assertThat(munroService.filterMunroListByCategory(null, "MUN").size()).isEqualTo(282);
        assertThat(munroService.filterMunroListByCategory(null, "TOP").size()).isEqualTo(227);
    }

    @Test
    public void filterByHeightTest() {
        assertThat(munroService.filterMunroListByHeight(null, null, null).size()).isEqualTo(602);
        assertThat(munroService.filterMunroListByHeight(null, 970d, null).size()).isEqualTo(360);
        assertThat(munroService.filterMunroListByHeight(null, null, 960d).size()).isEqualTo(212);
        assertThat(munroService.filterMunroListByHeight(null, 973d, 1001d).size()).isEqualTo(102);
    }
}
