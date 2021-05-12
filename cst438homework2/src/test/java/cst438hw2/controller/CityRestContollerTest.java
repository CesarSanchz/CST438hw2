package cst438hw2.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import cst438hw2.domain.CityInfo;
import cst438hw2.service.CityService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(CityRestController.class)
public class CityRestContollerTest {

    // declare @MockBean
    @MockBean
    private CityService cityService;
    // simulated HTTP requests to the class being tested.
    @Autowired
    private MockMvc mvc;
    private JacksonTester<CityInfo> jsonCityAttempt;
    //executed before each Test.
    @BeforeEach
    public void setUpEach() {
        MockitoAnnotations.initMocks(this);
        JacksonTester.initFields(this, new ObjectMapper());
    }
    //Check valid city 
    @Test
    public void test1() throws Exception {
    	
        CityInfo cityInfo = new CityInfo(1, "RandomCity", "TST", "Random Country", "Test District",
                100000, 80.0, "12:00 AM");
        given(cityService.getCityInfo("RandomCity"))
                .willReturn(new ResponseEntity<CityInfo>(cityInfo, HttpStatus.OK));

        // perform the test by making simulated HTTP get using URL of
        // "/api/city/RandomCity"
        MockHttpServletResponse response = mvc.perform(get("/api/cities/RandomCity")).andReturn()
                .getResponse();

        // verify result
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        System.out.println("Mock Response:" + response.getContentAsString());

        // convert returned data from JSON string format to CityInfo object
        CityInfo cityResult = jsonCityAttempt.parseObject(response.getContentAsString());
        CityInfo expectedResult = new CityInfo(1, "RandomCity", "TST", "Random Country",
                "Test District", 100000, 80.0, "12:00 AM");
        // compare return data with expected data
        assertThat(cityResult).isEqualTo(expectedResult);

    }
     //Check invalid city 
    @Test
    public void test2() throws Exception {
        given(cityService.getCityInfo("UnknownCity"))
                .willReturn(new ResponseEntity<CityInfo>(HttpStatus.NOT_FOUND));
        // perform the test by making simulated HTTP get using URL of "/api/city/UnknownCity"
        MockHttpServletResponse response = mvc.perform(get("/api/cities/UnknownCity")).andReturn()
                .getResponse();
        // verify result
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

    }

}