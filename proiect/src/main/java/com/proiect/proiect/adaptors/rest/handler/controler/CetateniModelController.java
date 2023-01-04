package com.proiect.proiect.adaptors.rest.handler.controler;

import com.proiect.proiect.adaptors.rest.CetateniModelClient;
import com.proiect.proiect.domain.CetateniModelDetails;
import com.proiect.proiect.domain.CityDangerDetails;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CetateniModelController {
    @Autowired
    private CetateniModelClient cetateniModelClient;
    @RequestMapping(value = "/cetateni", method = RequestMethod.POST)
    private ResponseEntity<String> createCityDanger(@RequestBody CetateniModelDetails cetateniModelDetails) {
        return cetateniModelClient.createCetateniModel(cetateniModelDetails);
    }

    @RequestMapping(value = "/cetateni", method = RequestMethod.GET)
    private ResponseEntity<JSONObject>  displayCityDanger(@RequestBody CetateniModelDetails cetateniModelDetails) {
        return cetateniModelClient.getCetateniModel(cetateniModelDetails);
    }
}
