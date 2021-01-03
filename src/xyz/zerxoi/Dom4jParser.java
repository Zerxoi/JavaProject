package xyz.zerxoi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4jParser {
    public static void main(String[] args) throws DocumentException {
        SAXReader reader = new SAXReader();
        // class.getResourceAsStream 的相对路径是 class对象所在目录
        Document document = reader.read(Dom4jParser.class.getResourceAsStream("books.xml"));
        Element root = document.getRootElement();
        List<Book> books = new ArrayList<>();

        for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
            Element e = it.next();
            String isbn = e.attributeValue("isbn");
            String name = e.elementText("name");
            List<Author> authors = new ArrayList<>();
            for (Iterator<Element> authorIt = e.element("authors").elementIterator(); authorIt.hasNext();) {
                authors.add(new Author(authorIt.next().getText()));
            }
            double price = Double.parseDouble(e.elementText("price"));
            books.add(new Book(isbn, name, authors, price));
        }
        System.out.println(books);
    }
}

class Book {
    private String isbn;
    private String name;
    private List<Author> authors;
    private double price;

    public Book(String isbn, String name, List<Author> authors, double price) {
        {
            this.isbn = isbn;
            this.name = name;
            this.authors = authors;
            this.price = price;
        }
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book{").append("isbn=").append(isbn).append(", name=").append(name).append(", authors=")
                .append(authors).append(", price=").append(price).append("}");
        return sb.toString();
    }
}

class Author {
    public String name;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}