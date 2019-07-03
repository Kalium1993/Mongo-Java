import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MongoJava {

    public static void main(String[] args) {

        MongoClient mongo = new MongoClient("localhost", 27017);

        MongoDatabase db = mongo.getDatabase("testemongodb");

        Document doc = new Document("username", "higormed").
                append("name",   "Joaozinho").
                append("cidade", "Porto Alegre");

        Document doc2 = new Document("username", "higormed").
                append("name",   "jao").
                append("cidade", "Porto Alegre");

        MongoCollection<Document> collec = db.getCollection("dados");

        //o que to buscando
        Document query =  new Document();
        query.append("name", "jao");

        //o que quero mudar
        Document update = new Document();
        update.append("name", "astrogildo");

        //fazendo o set
        Document set = new Document();
        set.append("$set", update);

        List<Document> documentos = new ArrayList<>();
        documentos.add(doc);
        documentos.add(doc2);

        collec.insertMany(documentos);

        collec.updateOne(query, set);

        collec.deleteOne(new Document("name", "Joaozinho"));

        FindIterable<Document> documents = collec.find();

        documents.forEach((Consumer<? super Document>) d ->
                System.out.println(String.format("Nome = %s, Cidade = %s", d.getString("name"), d.getString("cidade"))));
    }

}
