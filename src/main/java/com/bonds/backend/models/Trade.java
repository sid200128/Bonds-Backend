package com.bonds.backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    private String status;
    private int price;
    private String buyOrSell;
    private Date tradeDate;
    private Date settlementDate;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "security_id")
    private Security security;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "book_id")
    private Book book;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "counterparty_id")
    private Counterparty counterparty;

}
