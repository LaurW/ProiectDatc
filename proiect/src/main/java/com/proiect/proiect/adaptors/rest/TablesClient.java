package com.proiect.proiect.adaptors.rest;

import com.azure.data.tables.TableClient;
import com.azure.data.tables.TableServiceClient;
import com.azure.data.tables.TableServiceClientBuilder;
import com.proiect.proiect.domain.TableDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TablesClient {

    public final String connectionString = "DefaultEndpointsProtocol=https;AccountName=proiectdatc;AccountKey=4rAGkkF2JqgpqQK9kguKr9K/uxPLG+vQmJgGsxDTB/DlWX1SEGxDFVSgqqInzcpoj+i/1TXaC8ad+ASt4i6FKw==;EndpointSuffix=core.windows.net";
    public final TableServiceClient tableServiceClient = new TableServiceClientBuilder()
            .connectionString(connectionString)
            .buildClient();

    public ResponseEntity<String> createAzureTable(TableDetails tableDetails) {
        final String tableName = tableDetails.getTableName();

        TableClient tableClient = tableServiceClient.createTableIfNotExists(tableName);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> deleteAzureTable(TableDetails tableDetails) {
        tableServiceClient.deleteTable(tableDetails.getTableName());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
