package xyz.zerxoi.webserver.core;

// import java.io.IOException;
// import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

// import javax.xml.parsers.ParserConfigurationException;
// import javax.xml.parsers.SAXParser;
// import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ServletParseHandler extends DefaultHandler {
    private List<ServletEntity> servletList;
    private List<ServletMappingEntity> servletMappingList;
    private ServletEntity servletEntity;
    private ServletMappingEntity servletMappingEntity;
    private String tag;

    @Override
    public void startDocument() throws SAXException {
        servletList = new ArrayList<>();
        servletMappingList = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName != null) {
            tag = qName;
            if (qName.equals("servlet"))
                servletEntity = new ServletEntity();
            else if (qName.equals("servlet-mapping")) {
                servletMappingEntity = new ServletMappingEntity();
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch, start, length).trim();
        if (tag != null) {
            if (servletEntity != null) {
                if (tag.equals("servlet-name"))
                    servletEntity.setName(str);
                else if (tag.equals("servlet-class"))
                    servletEntity.setClz(str);
            }
            if (servletMappingEntity != null) {
                if (tag.equals("servlet-name"))
                    servletMappingEntity.setName(str);
                else if (tag.equals("url-pattern"))
                    servletMappingEntity.getSet().add(str);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName != null) {
            tag = null;
            if (qName.equals("servlet")) {
                servletList.add(servletEntity);
                servletEntity = null;
            } else if (qName.equals("servlet-mapping")) {
                servletMappingList.add(servletMappingEntity);
                servletMappingEntity = null;
            }
        }
    }

    public List<ServletEntity> getServletList() {
        return servletList;
    }

    public List<ServletMappingEntity> getServletMappingList() {
        return servletMappingList;
    }
}

// class ServletParserDemo {
//     public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException,
//             InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
//             NoSuchMethodException, SecurityException, ClassNotFoundException {
//         SAXParserFactory factory = SAXParserFactory.newInstance();
//         SAXParser parser = factory.newSAXParser();
//         ServletParseHandler handler = new ServletParseHandler();
//         parser.parse(
//                 Thread.currentThread().getContextClassLoader().getResourceAsStream("xyz/zerxoi/webserver/servlet.xml"),
//                 handler);
//         List<ServletEntity> servletList = handler.getServletList();
//         List<ServletMappingEntity> servletMappingList = handler.getServletMappingList();

//         ServletContext context = new ServletContext(servletList, servletMappingList);
//         Servlet servlet = (Servlet)Class.forName(context.getClz("/reg")).getConstructor().newInstance();
//         // servlet.service();
//         System.out.println(servlet);
//     }
// }