package dao;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;

import model.Amount;
import model.Employee;
import model.Product;

public class DaoImplMongoDB implements Dao {

    MongoCollection<Document> collection;
    MongoClient mongoClient;
	MongoDatabase mongoDatabase;
    ObjectId id;

    @Override
	public void connect() {
		String uri = "mongodb://localhost:27017";
		MongoClientURI mongoClientURI = new MongoClientURI(uri);
		mongoClient = new MongoClient(mongoClientURI);

		mongoDatabase = mongoClient.getDatabase("JAVA-SHOP-M06");
	}

    @Override
    public void disconnect() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexión cerrada correctamente.");
        }
    }

    @Override
    public Employee getEmployee(int employeeId, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEmployee'");
    }

    @Override
    public ArrayList<Product> getInventory() {

        ArrayList<Product> inventory = new ArrayList<>();
        
        connect();
        
        collection = mongoDatabase.getCollection("inventory");
        
        Iterable<Document> documents = collection.find();
        
        for (Document document : documents) {
             // Extraer unicamente el precio del wholesalerPrice
            System.out.println("aqui");

            Document priceDoc = (Document) document.get("wholesalerPrice");
            double priceValue = priceDoc != null ? priceDoc.getDouble("value") : 0.0;

            Amount wholesalerPrice = new Amount(priceValue);

            Product product = new Product (
                document.getString("name"),
                wholesalerPrice,
                document.getBoolean("available", false),
                document.getInteger("stock")
            );

            inventory.add(product);
        }

        for (Product product : inventory) {
            System.out.println(product);
        };

        return inventory;
    }

    @Override
    public boolean writeInventory(ArrayList<Product> products) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeInventory'");
    }
    
}
