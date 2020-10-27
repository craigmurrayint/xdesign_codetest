package com.xdesigncodetest.service;

import com.xdesigncodetest.data.InMemoryMunroDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
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
        assertThat(munroService.sortMunrosBySize(null, true).get(0).getRunningNo()).isEqualTo(376);
        assertThat(munroService.sortMunrosBySize(null, false).get(0).getRunningNo()).isEqualTo(133);
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
}
