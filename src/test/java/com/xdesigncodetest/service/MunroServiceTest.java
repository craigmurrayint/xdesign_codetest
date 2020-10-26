package com.xdesigncodetest.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InMemoryMunroService.class)
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
}
