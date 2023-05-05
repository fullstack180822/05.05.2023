package com.exmaple;

import java.io.Serializable;

public class Order implements Serializable {

    public Order() {

    }

    public Order(Integer id, String name) {
        this.id = id;
        this.customerName = name;
    }

    public Integer id;

    public String customerName;

}

