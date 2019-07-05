import exception.InvalidOptionError;
import org.bson.Document;
import service.MongoService;

import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        Scanner kb = new Scanner(System.in);

        MongoService m = new MongoService();

        m.createDb();

        while(true) {
            System.out.println("insira o nome");
            String nome = kb.nextLine();
            System.out.println("insira a cidade");
            String cidade = kb.nextLine();

            Document doc = m.createDocument(nome, cidade);

            m.addDocument(doc);

            System.out.println("add outro documento?");
            String resposta = kb.nextLine();
            resposta = resposta.toUpperCase();
            if (resposta.equals("N")) {
                break;
            }

            InvalidOptionError.invalidOptionError(resposta);
            continue;
        }

        m.insertMany(m.getDocumentos());

        m.dbInfoToConsole();

        System.out.println("");
        System.out.println("Fazendo update por nome...");
        System.out.println("nome antigo");
        String oldName = kb.nextLine();
        System.out.println("nome novo");
        String newName = kb.nextLine();

        m.updateByName(oldName, newName);

        m.dbInfoToConsole();

        System.out.println("");
        System.out.println("deletando o nome...");
        System.out.println("Escolha o nome para deletar");
        String name = kb.nextLine();

        m.deleteByName(name);

        m.dbInfoToConsole();

    }

}
