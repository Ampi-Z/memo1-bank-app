package com.aninfo.model;

import javax.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long cbu;
    private Double amount;
    private String type;

    public Transaction(){
    }

    public Transaction(Double amount,String type, Long cbu){
        this.cbu = cbu;
        this.amount = amount;
        this.type = type;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getCbu(){
        return cbu;
    }

    public void setCbu(Long cbu){
        this.cbu = cbu;
    }
    
    public Double getAmount(){
        return amount;
    }

    public void setAmount(Double amount){
        this.amount = amount;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

}