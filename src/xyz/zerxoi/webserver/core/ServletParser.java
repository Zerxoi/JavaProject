package xyz.zerxoi.webserver.core;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class ServletParser {
    public static ServletContext parse(String file) throws ParserConfigurationException, SAXException, IOException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
            SecurityException, ClassNotFoundException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        ServletParseHandler handler = new ServletParseHandler();
        parser.parse(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(file),
                handler);
        List<ServletEntity> servletList = handler.getServletList();
        List<ServletMappingEntity> servletMappingList = handler.getServletMappingList();
        return new ServletContext(servletList, servletMappingList);
    }
}
