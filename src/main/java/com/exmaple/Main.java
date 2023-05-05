package com.exmaple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    static ArrayList<Order> orders = new ArrayList<>(Arrays.asList(new Order(1, "Computer"), new Order(2, "Television"),
            new Order(3, "Sport car")));

    public static void main(String[] args) {
        System.out.println("letter o");
        getByQuery("o").forEach(o -> System.out.format("{ id:%d , name:%s }\n", o.id, o.customerName));
        System.out.println("letter v");
        getByQuery("v").forEach(o -> System.out.format("{ id:%d , name:%s }\n", o.id, o.customerName));
    }

    static List<Order> getByQuery() {
        return getByQuery(-1, "incognito");
    }

    static List<Order> getByQuery(Integer id) {
        return getByQuery(id, "incognito");
    }

    static List<Order> getByQuery(String name) {
        return getByQuery(-1, name);
    }

    static List<Order> getByQuery(Integer id  , String name) {
        // @RequestParam(value="id", defaultValue = "-1")
        // @RequestParam(value="name", defaultValue = "incognito") String name

        var result = orders.stream().filter(
                order -> (id == -1 ? true : order.id == id) && (name.equals("incognito") ? true : order.customerName.contains(name))).
                collect(Collectors.toList());
        return result;
    }
}
