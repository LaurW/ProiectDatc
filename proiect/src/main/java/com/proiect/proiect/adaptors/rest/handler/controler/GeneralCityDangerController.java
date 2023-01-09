package com.proiect.proiect.adaptors.rest.handler.controler;

import com.proiect.proiect.adaptors.rest.CityDangerClient;
import com.proiect.proiect.domain.CityDangerDetails;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralCityDangerController {

    @Autowired
    private CityDangerClient cityDangerClient;

    @RequestMapping(value = "/danger", method = RequestMethod.POST)
    private ResponseEntity<String> createCityDanger(@RequestBody CityDangerDetails cityDangerDetails) {
        return cityDangerClient.createDanger(cityDangerDetails);
    }

    @RequestMapping(value = "/danger", method = RequestMethod.PUT)
    private ResponseEntity<String>  updateCityDanger(@RequestBody CityDangerDetails cityDangerDetails) {
       return cityDangerClient.updateDanger(cityDangerDetails);
    }

    @RequestMapping(value = "/danger", method = RequestMethod.DELETE)
    private ResponseEntity<String>  deleteCityDanger(@RequestBody CityDangerDetails cityDangerDetails) {
        return cityDangerClient.deleteDanger(cityDangerDetails);
    }

    @RequestMapping(value = "/danger", method = RequestMethod.GET)
    private ResponseEntity<JSONObject>  displayCityDanger(@RequestBody CityDangerDetails cityDangerDetails) {
        return cityDangerClient.getDanger(cityDangerDetails);
    }
}
