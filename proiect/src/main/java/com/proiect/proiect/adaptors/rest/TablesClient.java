package com.proiect.proiect.adaptors.rest;

import com.proiect.proiect.domain.TableDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TablesClient {

    @Autowired
    private RestTemplate restTemplate;
    private static final String storageAccountName = "proiectdatc";

    public ResponseEntity<String> createAzureTable(TableDetails tableDetails) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","SharedKey ");
        headers.add("proiectdatc","4rAGkkF2JqgpqQK9kguKr9K/uxPLG+vQmJgGsxDTB/DlWX1SEGxDFVSgqqInzcpoj+i/1TXaC8ad+ASt4i6FKw==");
        headers.add("Content-Type","application/json");
        headers.add("Content-Length","0");
        HttpEntity httpEntity = new HttpEntity(headers);
        restTemplate.exchange("https://proiectdatc.table.core.windows.net/Tables", HttpMethod.POST,httpEntity,String.class);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
