package com.proiect.proiect.adaptors.rest;

import com.azure.data.tables.TableClient;
import com.azure.data.tables.TableClientBuilder;
import com.azure.data.tables.TableServiceClient;
import com.azure.data.tables.TableServiceClientBuilder;
import com.azure.data.tables.models.ListEntitiesOptions;
import com.azure.data.tables.models.TableEntity;
import com.proiect.proiect.domain.CetateniModelDetails;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CetateniModelClient {
    public final String connectionString = "DefaultEndpointsProtocol=https;AccountName=proiectdatc;AccountKey=4rAGkkF2JqgpqQK9kguKr9K/uxPLG+vQmJgGsxDTB/DlWX1SEGxDFVSgqqInzcpoj+i/1TXaC8ad+ASt4i6FKw==;EndpointSuffix=core.windows.net";
    private final String numeTabelaCetateni = "CetateniModel";
    private final String numeComplet = "NumeComplet";
    public final TableServiceClient tableServiceClient = new TableServiceClientBuilder()
            .connectionString(connectionString)
            .buildClient();

    public ResponseEntity<String> createCetateniModel(CetateniModelDetails cetateniModelDetails) {
        try {
            TableClient tableClient = new TableClientBuilder()
                    .connectionString(connectionString)
                    .tableName(cetateniModelDetails.getNumeTabela())
                    .buildClient();
            String partitionKey = Integer.toString(cetateniModelDetails.getUid());
            List<Object> points = new ArrayList<>();
            ListEntitiesOptions options = new ListEntitiesOptions().setFilter(numeComplet + " eq \'" + cetateniModelDetails.getNumeComplet() + "\' ");

            tableClient.listEntities(options, null, null).forEach(
                    tableEntity -> {
                        points.add(tableEntity.getProperties().get("Puncte"));
                    }
            );
            int sumOfPoints = 0;
            for(Object eachPoint : points)
            {
                sumOfPoints += (int)eachPoint;
            }
            System.out.println(sumOfPoints);
            TableClient tableClient2 = tableServiceClient.createTableIfNotExists(numeTabelaCetateni);

            int uid = cetateniModelDetails.getUid();
            String partitionKeyForCetatean = Integer.toString(uid);
            String rowKey = cetateniModelDetails.getRowKey();
            Map<String, Object> cetatean = new HashMap<>();
            cetatean.put("NumeComplet", cetateniModelDetails.getNumeComplet());
            cetatean.put("PuncteAdunate", sumOfPoints);

            TableEntity tableEntity = new TableEntity(partitionKeyForCetatean,rowKey).setProperties(cetatean);
            tableClient2.upsertEntity(tableEntity);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    public ResponseEntity<JSONObject> getCetateniModel(CetateniModelDetails cetateniModelDetails) {
        try {
            TableClient tableClient = new TableClientBuilder()
                    .connectionString(connectionString)
                    .tableName(cetateniModelDetails.getNumeTabela())
                    .buildClient();
            TableEntity specificEntity = tableClient.getEntity(Integer.toString(cetateniModelDetails.getUid()), cetateniModelDetails.getRowKey());
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
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
