package dao.xml;

import java.util.ArrayList;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import model.Amount;
import model.Product;

public class SaxReader extends DefaultHandler {
    ArrayList<Product> inventory;
    Product product;
    String value;
    String parsedElement;

    // devolver los Productos
    public ArrayList<Product> getInventory() {
        return inventory;
    }

    // Añadir los productos
    public void setInventory(ArrayList<Product> inventory) {
        this.inventory = inventory;
    }

    @Override
    public void startDocument() throws SAXException {
        this.inventory = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "product":
                if (qName.equals("product")) {
                    this.product = new Product(
                        attributes.getValue("name") != null ? attributes.getValue("name") : "empty",
                        new Amount(0), // Crear un objeto Amount con el valor predeterminado
                        true, // Disponibilidad
                        0 // Stock inicial
                    );                
                }
                break;
            }
        this.parsedElement = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        value = new String(ch, start, length);
        switch(parsedElement) {
            case "product" :
                break;
            case "price":
                this.product.setWholesalerPrice(new Amount(Double.valueOf(value)));
                break;
            case "stock":
            this.product.setStock(Integer.valueOf(value));
            break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // Añadimos el producto dentro del ArrayList
        if(qName.equals("products")){
            this.inventory.add(this.product);
        }
        this.parsedElement = "";
    }

    @Override
    public void endDocument() throws SAXException {
        printDocument();
    }

    private void printDocument() {
        for (Product p : inventory) {
            System.out.println(p.toString());
        }
    }
}
