package com.proiect.proiect.adaptors.rest.handler.controler;


import com.proiect.proiect.adaptors.rest.TablesClient;
import com.proiect.proiect.domain.TableDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TableController {
    @Autowired
    private TablesClient tablesClient;
    @RequestMapping(value = "/tables", method = RequestMethod.POST)
    public void createTable(@RequestBody TableDetails tableDetails) {
        tablesClient.createAzureTable(tableDetails);
    }
    @RequestMapping(value = "/tables", method = RequestMethod.DELETE)
    public void deleteTable(@RequestBody TableDetails tableDetails) {
        tablesClient.deleteAzureTable(tableDetails);
    }
}
