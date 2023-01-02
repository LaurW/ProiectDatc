package com.proiect.proiect.adaptors.rest;

import com.azure.data.tables.TableClient;
import com.azure.data.tables.TableServiceClient;
import com.azure.data.tables.TableServiceClientBuilder;
import com.proiect.proiect.domain.TableDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TablesClient {

    @Autowired
    private RestTemplate restTemplate;
    public final String connectionString ="DefaultEndpointsProtocol=https;AccountName=proiectdatc;AccountKey=4rAGkkF2JqgpqQK9kguKr9K/uxPLG+vQmJgGsxDTB/DlWX1SEGxDFVSgqqInzcpoj+i/1TXaC8ad+ASt4i6FKw==;EndpointSuffix=core.windows.net";
    public ResponseEntity<String> createAzureTable(TableDetails tableDetails) {
        final String tableName = tableDetails.getTableName();

        // Create a TableServiceClient with a connection string.
        TableServiceClient tableServiceClient = new TableServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();

        // Create the table if it not exists.
        TableClient tableClient = tableServiceClient.createTableIfNotExists(tableName);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> updateAzureTable(TableDetails tableDetails)
    {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    public ResponseEntity<String> deleteAzureTable(TableDetails tableDetails)
    {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
