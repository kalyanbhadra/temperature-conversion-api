package com.kals.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kals.model.TemperatureModel;

@RestController
@RequestMapping("/temperature")
public class TemperatureController {
  
	private final String CELSIUS = "C";
	private final String FAHRENHEIT = "F";
	
    @RequestMapping(value = "/convert", method = RequestMethod.POST)
    public TemperatureModel convert(@RequestParam(value = "unit") String unit, @RequestBody TemperatureModel request) {
         
    	TemperatureModel response = new TemperatureModel();
    	double celsius = 0.0;
        double fahrenheit = 0.0;
        
        if(CELSIUS.equalsIgnoreCase(unit)) { //Convert to fahrenheit
        	celsius = request.getCelsius();
            fahrenheit = (celsius * (9/5)) + 32;
        }
        else if(FAHRENHEIT.equalsIgnoreCase(unit)) { //Convert to celsius
        	fahrenheit = request.getFahrenheit();
        	celsius = (fahrenheit-32) * (5/9);
        }
        response.setCelsius(celsius);
        response.setFahrenheit(fahrenheit);
        
        return response;
    }
    
    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    public String sayHello() {        
        return "Hello Sir...";
    }
}
