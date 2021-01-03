package xyz.zerxoi.webserver.core;

import java.util.HashSet;
import java.util.Set;

public class ServletMappingEntity {
    private String name;
    private Set<String> set;

    public ServletMappingEntity() {
        set = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setName(String name) {
        this.name = name;
    }
}
