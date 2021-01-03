package xyz.zerxoi.webserver.exercise;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParserDemo {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        PersonHandler handler = new PersonHandler();
        parser.parse(
                Thread.currentThread().getContextClassLoader().getResourceAsStream("xyz/zerxoi/webserver/example.xml"),
                handler);
        List<Person> list = handler.getList();
        for (Person p : list) {
            System.out.println(p.getName());
            System.out.println(p.getAge());
        }
    }
}

class PersonHandler extends DefaultHandler {
    private List<Person> list;
    private Person person;
    private String tag;

    @Override
    public void startDocument() throws SAXException {
        list = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName != null)
            tag = qName;
        if (qName.equals("person"))
            person = new Person();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch, start, length).trim();
        if (tag != null) {
            if (tag.equals("name"))
                person.setName(str);
            else if (tag.equals("age"))
                person.setAge(Integer.parseInt(str));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName != null)
            if (qName.equals("person"))
                list.add(person);
        tag = null;
    }

    public List<Person> getList() {
        return list;
    }
}

class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}