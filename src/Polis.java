import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Polis {
    String line = "";

    private List<Token> polis = new LinkedList<>();
    private Queue<Token> tokens;
    private Token token;

    Polis(Queue<Token> _tokens) {
        tokens = _tokens;
    }

    List<Token> set_polis() {
        while (!tokens.isEmpty()) {
            expr();
        }

        return polis;
    }

    // обработчик выражения
    private void expr() {
        token = tokens.poll();
        if (token != null && token.type.equals("VAR")) {
            var();
        }
        if (token != null && token.type.equals("WHILE")) {
            loop();
        }
        if (token != null && token.type.equals("MYST")) {
            mystr();
        }
    }

    // обработчик переменной/числа
    private void var() {
        String cache = "";

        line += token.token;
        polis.add(token);

        token = tokens.peek();
        cache = token.token;
        polis.add(tokens.poll());

        token = tokens.peek();
        line += token.token + cache;
        polis.add(tokens.poll());

        token = tokens.peek();
        while (token != null && !token.type.equals("END")) {
            token = tokens.poll();

            line += tokens.peek().token;
            polis.add(tokens.poll());
            line += token.token;
            polis.add(token);

            if (token == null ) {
                continue;
            }
            token = tokens.peek();

        }

        token = tokens.poll();
    }

    // обработчик цикла
    private void loop() {

        int loop_start = polis.size();
        log_op();
        token = tokens.poll();
        int p = p();
        polis.add(new Token("JUMP", "" + (p - 1)));
        polis.add(new Token("IF", "!F"));

        expr();

        polis.add(new Token("JUMP", "" + (loop_start - 1)));
        polis.add(new Token("IF", "!F"));
        token = tokens.poll();
    }

    // обработчик логической операции
    private void log_op() {
        token = tokens.poll();
        polis.add(tokens.poll());
        token = tokens.poll();
        polis.add(tokens.poll());
        polis.add(token);

        token = tokens.peek();
        while (!token.type.equals("CLOSE_BR")) {
            token = tokens.poll();
            polis.add(tokens.poll());
            polis.add(token);
            token = tokens.peek();
        }
        token = tokens.poll();
    }

    // моя структура данных
    private void mystr() {

        if (token.token.equals("set")) {
            polis.add(token);
            polis.add(tokens.poll());
            polis.add(tokens.poll());
        } else {
            polis.add(token);
            polis.add(tokens.poll());
            polis.add(tokens.poll());
            polis.add(tokens.poll());
        }
    }

    // индекс перехода в цикле
    private int p() {
        int p = polis.size();
        Queue<Token> newtokens = new LinkedList<>(tokens);
        Token newtoken = newtokens.poll();

        while (!newtoken.type.equals("CLOSE_BRACE")) {
            newtoken = newtokens.poll();
            p++;
        }
        p++;

        return p;
    }

}
