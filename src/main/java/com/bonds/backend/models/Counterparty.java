package com.bonds.backend.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Counterparty {

    @Id
    private int id;
    private String counterpartyName;


    @OneToMany(mappedBy = "counterparty")
    private List<Trade> tradeList;

}
