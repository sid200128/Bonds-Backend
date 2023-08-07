package com.bonds.backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Security {

    @Id
    private int id;
    private String isin;
    private String cusip;
    private String issuer;
    private Date maturityDate;
    private int coupon;
    private int securityType;
    private int faceValue;
    private int status;

    @OneToMany(mappedBy = "security")
    private List<Trade> tradeList;
}
