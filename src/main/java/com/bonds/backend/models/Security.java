package com.bonds.backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Security {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String isin;
    private String cusip;
    private String issuer;
    private Date maturityDate;
    private int coupon;
    private String securityType;
    private int faceValue;
    private String status;

    @OneToMany(mappedBy = "security")
    private List<Trade> tradeList;
}
