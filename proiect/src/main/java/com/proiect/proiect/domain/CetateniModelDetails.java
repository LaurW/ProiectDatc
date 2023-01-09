package com.proiect.proiect.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CetateniModelDetails {
    private String numeComplet;
    private String numeTabela;
    private int uid;
    private String rowKey;

}
