package com.proiect.proiect.adaptors.rest.handler.controler;

import com.proiect.proiect.adaptors.rest.CityDangerClient;
import com.proiect.proiect.domain.CityDangerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class GeneralCityDangerController {

    @Autowired
    private CityDangerClient cityDangerClient;

    @RequestMapping(value = "/danger", method = RequestMethod.POST)
    private void createCityDanger(@RequestBody CityDangerDetails cityDangerDetails)
    {
        cityDangerClient.createDanger(cityDangerDetails);
    }
    @RequestMapping(value = "/danger", method = RequestMethod.PUT)
    private void updateCityDanger(@RequestBody CityDangerDetails cityDangerDetails)
    {
        cityDangerClient.updateDanger(cityDangerDetails);
    }
    @RequestMapping(value = "/danger", method = RequestMethod.DELETE)
    private void deleteCityDanger(@RequestBody CityDangerDetails cityDangerDetails)
    {
        cityDangerClient.deleteDanger(cityDangerDetails);
    }
    @RequestMapping(value = "/danger", method = RequestMethod.GET)
    private void displayCityDanger(@RequestBody CityDangerDetails cityDangerDetails)
    {
        cityDangerClient.getDanger(cityDangerDetails);
    }
}
