package com.bonds.backend.models;

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
@Entity

public class Trade {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private int quantity;
    private String status;
    private int price;
    private String buyOrSell;
    private Date tradeDate;
    private Date settlementDate;


    @ManyToOne
    @JoinColumn(name = "security_id")
    private Security security;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;


    @ManyToOne
    @JoinColumn(name = "counterparty_id")
    private Counterparty counterparty;

}
