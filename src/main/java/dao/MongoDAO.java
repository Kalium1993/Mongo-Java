package dao;

import com.mongodb.MongoClient;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


import java.util.List;

public class MongoDAO {
    private MongoClient mongo;
    private MongoDatabase db;
    private MongoCollection<Document> collec;
    private Document doc;



    public void createDb() {
        mongo = new MongoClient("localhost", 27017);

        db = mongo.getDatabase("testemongodb");

        collec = db.getCollection("dados");
    }

    public Document createDocument(String nome, String cidade) {
        doc = new Document().
                append("name", nome).
                append("cidade", cidade);

        return doc;
    }

    public void insertMany(List<Document> documentos) {
        collec.insertMany(documentos);
    }

    public void updateByName(String oldName, String newName) {
        Document query =  new Document();
        query.append("name", oldName);

        Document update = new Document();
        update.append("name", newName);

        Document set = new Document();
        set.append("$set", update);

        collec.updateOne(query, set);
    }

    public void deleteByName(String name) {
        collec.deleteOne(new Document("name", name));
    }

    public FindIterable<Document> findAll() {
       return collec.find();
    }
}
