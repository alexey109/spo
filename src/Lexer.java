import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Pattern;

class Lexer {
    private static HashMap<String, String> terms = new HashMap<>();

    Lexer() {
        // определяем регулярные выражения для терминалов
        terms.put("OP", "^[-|+|/|*]$");
        terms.put("ASSIGN_OP", "^=$");
        terms.put("LOG_OP", "^[==|<|>|>=|<=|!=]$");
        terms.put("OPEN_BR", "^[/(]$");
        terms.put("CLOSE_BR", "^[/)]$");
        terms.put("OPEN_BRACE", "^[/{]$");
        terms.put("CLOSE_BRACE", "^[/}]$");
        terms.put("SYS", "^(type|put|pop|set)$");
        terms.put("WHILE", "^while$");
        terms.put("END", "^[;]$");
        terms.put("VAR", "^[a-z]+$");
        terms.put("NUM", "^0|[1-9][0-9]*$");

    }

    void get_terms(String test, Queue<Token> tokens) {
        // определяем терминалы из которых состоит строка
        StringBuilder cache = new StringBuilder();
        for (Character chr: test.toCharArray()) {
            if (chr == ' ') {
                continue;
            }

            for (Map.Entry term : terms.entrySet()) {
                Pattern p = Pattern.compile(term.getValue().toString());

                if (p.matcher(cache.toString()).find() && !p.matcher(cache.toString() + chr).find()) {
                    tokens.add(new Token(term.getKey().toString(), cache.toString()));
                    cache.delete(0, cache.length());
                    break;
                }
            }
            cache.append(chr);
        }
    }
}
