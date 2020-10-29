package com.xdesigncodetest.controller;

import com.xdesigncodetest.model.Munro;
import com.xdesigncodetest.service.InMemoryMunroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class MunroControllerTest {

    @MockBean 
    private InMemoryMunroService munroService;
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    List<Munro> munros;
    
    @BeforeEach
    void setUp() {
        munros = new ArrayList<>();
        Munro munro1 = simpleMunroBuilder("TOP", 1000, "ABC123", "Munro 1");
        Munro munro2 = simpleMunroBuilder("MUN", 1100, "123ABC", "Test Munro");
        Munro munro3 = simpleMunroBuilder(null, 1150, "GRIDREF1", "Test Munro2");
        Munro munro4 = simpleMunroBuilder("MUN", 1200, "GRIDREF2", "Final Munro");
        munros.add(munro1);
        munros.add(munro2);
        munros.add(munro3);
        munros.add(munro4);
        Mockito.when(munroService.filterMunroListByCategory(null, "EITHER")).thenReturn(munros);
    }

    @Test
    void getAllMunrosTest() {
        ResponseEntity<Munro[]> response = this.restTemplate.getForEntity("http://localhost:" + port + "/munros", 
           Munro[].class);
        Munro[] munroArray = response.getBody();
        List<Munro> returnedMunros = Arrays.asList(munroArray);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(returnedMunros.size()).isEqualTo(munros.size());
        assertThat(returnedMunros.get(0).getName()).isEqualTo("Munro 1");
    }

    @Test
    void validCategoryFilterTest() {
        ResponseEntity<Munro[]> response = this.restTemplate.getForEntity("http://localhost:" + port + "/munros?category=NONSENSE",
            Munro[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

       response = this.restTemplate.getForEntity("http://localhost:" + port + "/munros?category=TOP",
            Munro[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);

    }

    @Test
    void validHeightFilterTest() {
        ResponseEntity<Munro[]> response = this.restTemplate.getForEntity("http://localhost:" + port + "/munros?maxHeight=-1",
            Munro[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        response = this.restTemplate.getForEntity("http://localhost:" + port + "/munros?minHeight=-1",
            Munro[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        response = this.restTemplate.getForEntity("http://localhost:" + port + "/munros?minHeight=100&maxHeight=50",
            Munro[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        response = this.restTemplate.getForEntity("http://localhost:" + port + "/munros?minHeight=50&maxHeight=100",
            Munro[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
    }

    @Test
    void validSortFieldTest() {
        ResponseEntity<Munro[]> response = this.restTemplate.getForEntity("http://localhost:" + port + "/munros?sortField=nonsense",
            Munro[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        response = this.restTemplate.getForEntity("http://localhost:" + port + "/munros?sortField=height",
            Munro[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);

        response = this.restTemplate.getForEntity("http://localhost:" + port + "/munros?sortField=name",
            Munro[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
    }

    @Test
    void validLimitTest() {
        ResponseEntity<Munro[]> response = this.restTemplate.getForEntity("http://localhost:" + port + "/munros?limitResults=0",
            Munro[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        response = this.restTemplate.getForEntity("http://localhost:" + port + "/munros?limitResults=10",
            Munro[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
    }
    
    private Munro simpleMunroBuilder(String category, double height, String gridRef, String name) {
        Munro munro = new Munro();
        munro.setPost1997(category);
        munro.setHeightInMetre(height);
        munro.setGridRef(gridRef);
        munro.setName(name);
        return munro;
    }
    
}
