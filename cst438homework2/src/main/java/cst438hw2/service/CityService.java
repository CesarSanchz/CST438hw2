package cst438hw2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import cst438hw2.domain.*;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherService weatherService;

    public CityService(CityRepository mockRepository, WeatherService mockWeather) {
        this.cityRepository = mockRepository;
        this.weatherService = mockWeather;
    }

    // return city in JSON format.
    public ResponseEntity<CityInfo> getCityInfo(String cityName) {

        List<City> cities = cityRepository.findByName(cityName);
        if (cities.size() == 0) {
            //Send 404 return code if city not found
            return new ResponseEntity<CityInfo>(HttpStatus.NOT_FOUND);

        } else {
            City city = cities.get(0);

            //get temperature/time
            TempAndTime weather = weatherService.getTempAndTime(city.getName());
            city.setWeather(weather);
            CityInfo cityInfo = new CityInfo(city);
            return new ResponseEntity<CityInfo>(cityInfo, HttpStatus.OK);
        }

    }
}
