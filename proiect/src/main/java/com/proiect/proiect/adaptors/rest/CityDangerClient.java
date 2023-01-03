package com.proiect.proiect.adaptors.rest;

import com.azure.data.tables.TableClient;
import com.azure.data.tables.TableClientBuilder;
import com.azure.data.tables.TableServiceClient;
import com.azure.data.tables.TableServiceClientBuilder;
import com.azure.data.tables.models.TableEntity;
import com.azure.data.tables.models.TableEntityUpdateMode;
import com.proiect.proiect.domain.CityDangerDetails;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CityDangerClient {
    public final String connectionString = "DefaultEndpointsProtocol=https;AccountName=proiectdatc;AccountKey=4rAGkkF2JqgpqQK9kguKr9K/uxPLG+vQmJgGsxDTB/DlWX1SEGxDFVSgqqInzcpoj+i/1TXaC8ad+ASt4i6FKw==;EndpointSuffix=core.windows.net";
    public final TableServiceClient tableServiceClient = new TableServiceClientBuilder()
            .connectionString(connectionString)
            .buildClient();

    public ResponseEntity<String> createDanger(CityDangerDetails cityDangerDetails) {
        try {
            int uid = cityDangerDetails.getUid();
            String partitionKey = Integer.toString(uid);
            String rowKey = cityDangerDetails.getRowKey();
            Map<String, Object> dangerInfo = new HashMap<>();
            dangerInfo.put("Date", cityDangerDetails.getDate());
            dangerInfo.put("Adresa", cityDangerDetails.getAdresa());
            dangerInfo.put("Tip", cityDangerDetails.getType());
            dangerInfo.put("Status", cityDangerDetails.getStatus());

            TableEntity cityDanger = new TableEntity(partitionKey, rowKey).setProperties(dangerInfo);

            TableClient tableClient = tableServiceClient.getTableClient(cityDangerDetails.getNameOfTable());
            tableClient.createEntity(cityDanger);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<String> updateDanger(CityDangerDetails cityDangerDetails) {
        try {
            TableClient tableClient = new TableClientBuilder()
                    .connectionString(connectionString)
                    .tableName(cityDangerDetails.getNameOfTable())
                    .buildClient();
            String partitionKey = Integer.toString(cityDangerDetails.getUid());
            TableEntity specificEntity = tableClient.getEntity(partitionKey, cityDangerDetails.getRowKey());
            specificEntity.getProperties().put("Date", cityDangerDetails.getDate());
            specificEntity.getProperties().put("Adresa", cityDangerDetails.getAdresa());
            specificEntity.getProperties().put("Tip", cityDangerDetails.getType());
            specificEntity.getProperties().put("Status", cityDangerDetails.getStatus());
            tableClient.updateEntity(specificEntity, TableEntityUpdateMode.REPLACE);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<String> deleteDanger(CityDangerDetails cityDangerDetails) {
        try {
            TableClient tableClient = new TableClientBuilder()
                    .connectionString(connectionString)
                    .tableName(cityDangerDetails.getNameOfTable())
                    .buildClient();
            tableClient.deleteEntity(Integer
                            .toString(
                                    cityDangerDetails.getUid()),
                    cityDangerDetails.getRowKey());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<JSONObject> getDanger(CityDangerDetails cityDangerDetails) {
        try {
            TableClient tableClient = new TableClientBuilder()
                    .connectionString(connectionString)
                    .tableName(cityDangerDetails.getNameOfTable())
                    .buildClient();
            TableEntity specificEntity = tableClient.getEntity(Integer.toString(cityDangerDetails.getUid()), cityDangerDetails.getRowKey());
            if (!specificEntity.getProperties().isEmpty()) {
                JSONObject specificEntityJson = new JSONObject(specificEntity.getProperties());
                return new ResponseEntity<JSONObject>(specificEntityJson, HttpStatus.OK);
            }
        } catch (Exception e) {
            Map<String, Object> errorMessage = new HashMap<>();
            errorMessage.put("Message", e.getMessage());
            JSONObject specificEntityJson = new JSONObject(errorMessage);
            return new ResponseEntity<>(specificEntityJson, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
