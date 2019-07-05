package service;

import com.mongodb.client.FindIterable;
import dao.MongoDAO;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MongoService {
    MongoDAO dao = new MongoDAO();
    private List<Document> documentos = new ArrayList<>();
    FindIterable<Document> documents;

    public void createDb() {
        dao.createDb();
    }

    public Document createDocument(String nome, String cidade) {
        return dao.createDocument(nome, cidade);
    }

    public void  addDocument(Document doc) {
        documentos.add(doc);
    }

    public List<Document> getDocumentos() {
        return documentos;
    }

    public void insertMany(List<Document> documentos) {
        dao.insertMany(documentos);
    }

    public void updateByName(String oldName, String newName) {
        dao.updateByName(oldName, newName);
    }

    public void deleteByName(String name) {
        dao.deleteByName(name);
    }


    public void dbInfoToConsole() {
        documents = dao.findAll();

        documents.forEach((Consumer<? super Document>) d ->
                System.out.println(String.format("Nome = %s, Cidade = %s", d.getString("name"), d.getString("cidade"))));
    }


}
