package cst438hw2.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cst438hw2.domain.City;
import cst438hw2.domain.CityInfo;
import cst438hw2.domain.CityRepository;
import cst438hw2.domain.Country;
import cst438hw2.domain.TempAndTime;

@SpringBootTest
public class CityServiceTest {

    @MockBean
    private WeatherService mockWeatherService;

    @MockBean
    private CityRepository mockCityRepository;

    @MockBean
    private CityService cs;

    // method is executed before each test.
    @BeforeEach
    public void setUpEach() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void test1() throws Exception {

        Country country1 = new Country("C1", "Country 1");
        Country country2 = new Country("C2", "Country 2");
        Country country3 = new Country("C3", "Country 3");
        City city1 = new City(1, "LA", "District 1", 100000, country1);
        City city2 = new City(2, "LA", "District 2", 200000, country2);
        City city3 = new City(3, "LA", "District 3", 300000, country3);
        List<City> cities = new ArrayList<City>();
        cities.add(city1);
        cities.add(city2);
        cities.add(city3);

        cs = new CityService(mockCityRepository, mockWeatherService);
        given(mockWeatherService.getTempAndTime("LA"))
                .willReturn(new TempAndTime(294.8, 1620602021, -25200));

        given(mockCityRepository.findByName("LA")).willReturn(cities);

        ResponseEntity<CityInfo> cityResponse = cs.getCityInfo("LA");

        assertThat(cityResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        CityInfo cityResult = cityResponse.getBody();

        CityInfo expectedResult = new CityInfo(1, "LA", "C1", "Country 1", "District 1", 100000,
                71.0, "4:13 PM");
        
        assertThat(cityResult).isEqualTo(expectedResult);
    }

    // test CityService city name is invalid
    @Test
    public void test2() throws Exception {

        List<City> cities = new ArrayList<City>();

        cs = new CityService(mockCityRepository, mockWeatherService);

        given(mockWeatherService.getTempAndTime("InvalidCity"))
                .willReturn(new TempAndTime(0, 0, 0));
        given(mockCityRepository.findByName("InvalidCity")).willReturn(cities);

        ResponseEntity<CityInfo> cityResponse = cs.getCityInfo("InvalidCity");

        // verify result
        assertThat(cityResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }
}
