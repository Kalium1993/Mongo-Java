package exception;

public class InvalidOptionError extends Exception {

    public static void invalidOptionError(String resposta) throws Exception {
        if(!resposta.equals("S")) {
            throw new Exception("opção inválida");
        }
    }

}
