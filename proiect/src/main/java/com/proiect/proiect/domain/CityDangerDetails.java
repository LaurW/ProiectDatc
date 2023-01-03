package com.proiect.proiect.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityDangerDetails {
    private int uid;
    private String rowKey;
    private String date;
    private String type;
    private String status;
    private String adresa;
    private String nameOfTable;
}

