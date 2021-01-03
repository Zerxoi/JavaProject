package xyz.zerxoi.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FormDataStore {
    public static void main(String[] args) {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1001);
        map.put("name", "Alice");
        map.put("salary", 5000);
        list.add(map);
        
        map = new HashMap<>();
        map.put("id", 1002);
        map.put("name", "Bob");
        map.put("salary", 4000);
        list.add(map);

        map = new HashMap<>();
        map.put("id", 1003);
        map.put("name", "Chris");
        map.put("salary", 7000);
        list.add(map);

        map = new HashMap<>();
        map.put("id", 1004);
        map.put("name", "David");
        map.put("salary", 5000);
        list.add(map);

        for (Map<String, Object> m:list) {
            Set<String> keys = m.keySet();
            for (String key: keys) {
                System.out.print(key+":" + m.get(key)+"\t");
            }
            System.out.println();
        }
    }
}

class Employee {
    String id;
    String name;
    int salary;

    public Employee(String id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
}