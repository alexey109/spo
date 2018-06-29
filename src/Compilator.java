import java.util.*;

public class Compilator {
    public Compilator() {

    }

    public static void main(String[] args) throws Exception {
        Lexer lexer_obj = new Lexer();
        LinkedList<Token> tokens = new LinkedList<>();
        Queue<Token> parsered_tokens;
        List<Token> polis;


        String target_code =   "x=100; y=30+50;";  // while (x<y) {x=x+5};";
        System.out.println("Исходная строка: \n" + target_code);

        System.out.println("\nЛексер:");
        lexer_obj.get_terms(target_code, tokens);
        for (Token item : tokens) {
            System.out.println(item.type + " " + item.token);
        }

        System.out.println("\nПарсер:");
        Parser parser = new Parser();
        parsered_tokens = parser.parse(tokens);


        System.out.println("\nСоздание ПОЛИЗ:");
        Polis p = new Polis(parsered_tokens);
        polis = p.set_polis();
        for (int i = 0; i<polis.size(); i++){
            Token item = polis.get(i);
            System.out.println(i + " " + item.type + " " + item.token);
        }
        System.out.println("Получившаяся ПОЛИЗ: " + p.line );
    }
}
