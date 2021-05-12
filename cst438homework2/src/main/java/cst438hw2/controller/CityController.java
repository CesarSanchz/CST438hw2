package cst438hw2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cst438hw2.service.CityService;
import cst438hw2.domain.*;

@Controller
public class CityController {

    @Autowired
    CityService cityService;

    @GetMapping("/cities/{city}")
    public String getCityInfo(@PathVariable("city") String cityName, Model model) {

        ResponseEntity<CityInfo> info = cityService.getCityInfo(cityName);
        if (info.getStatusCode() == HttpStatus.NOT_FOUND) {
            return "city_error"; // invalid city name
        }
        CityInfo city = info.getBody();
        model.addAttribute("city", city);
        return "city_info";
    }
}
