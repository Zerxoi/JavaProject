package xyz.zerxoi.webserver.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ServletContext {
    private Map<String, String> nameClz = new HashMap<>();
    private Map<String, String> patternName = new HashMap<>();
    public ServletContext(List<ServletEntity> servletList, List<ServletMappingEntity> servletMappingList) {
        for (ServletEntity e: servletList) {
            nameClz.put(e.getName(), e.getClz());
        }
        for (ServletMappingEntity e: servletMappingList) {
            Set<String> set = e.getSet();
            for (String pattern: set) {
                patternName.put(pattern, e.getName());
            }
        }
    }

    public String getClz(String pattern) {
        return nameClz.get(patternName.get(pattern));
    }
}
