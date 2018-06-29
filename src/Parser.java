//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.LinkedList;
import java.util.Queue;

class Parser {
    private Token token;
    private Queue<Token> tokens = new LinkedList<>();
    private Queue<Token> processed_tokens = new LinkedList<>();

    Parser() {
    }

    Queue<Token> parse(LinkedList<Token> income_tokens) throws Exception {
        tokens = income_tokens;

        while (!tokens.isEmpty()) {
            define_token();
        }
        System.out.println("Парсинг завершен");

        return processed_tokens;
    }

    private void exec() {
        processed_tokens.add(token);

        assert token != null;
        System.out.println("обработали "+ token.type + " " + token.token);
    }

    private void define_token() throws Exception {
        Boolean defined = Boolean.FALSE;

        try {
            assign_expr();
            defined = Boolean.TRUE;
        } catch (Exception ignored) {}

        try {
            while_expr();
            defined = Boolean.TRUE;
        } catch (Exception ignored) {}

        try {
            mystr();
            defined = Boolean.TRUE;
        } catch (Exception ignored) {}

        if (!defined && token != null) {
            throw new Exception("token " + token.type + " not defined!");
        }
    }

    private void mystr() throws Exception {
        my_str();
        var();
        try {
            value();
        } catch (Exception ignored) {
        }
        end();
    }

    private void assign_expr() throws Exception {
        var();
        assignOp();
        valueExpr();
        end();
    }

    private void valueExpr() throws Exception {

        value();

        while (true) {
            try {
                op();
                value();
            } catch (Exception var2) {
                return;
            }
        }
    }

    private void while_expr() throws Exception {
        my_while();
        open_br();
        log_expr();
        close_br();
        open_brace();
        define_token();
        close_brace();
    }

    private void log_expr() throws Exception {
        value();
        log_op();
        value();
    }

    private void value() throws Exception {
        token = tokens.peek();
        if (!token.type.equals("VAR") && !token.type.equals("NUM")) {
            throw new Exception("ошибка в методе value на токене " + token.type + " " + token.token);
        }
        exec();
        token = tokens.poll();
    }

    private void var() throws Exception {
        token = tokens.peek();
        if (!token.type.equals("VAR")) {
            throw new Exception("ошибка в методе VAR на токене " + token.type + " " + token.token);
        }
        exec();
        token = tokens.poll();
    }

    private void assignOp() throws Exception {
        token = tokens.peek();
        if (!token.type.equals("ASSIGN_OP")) {
            throw new Exception("ошибка в методе assignOp на токене " + token.type + " " + token.token);
        }
        exec();
        token = tokens.poll();
    }

    private void op() throws Exception {
        token = tokens.peek();
        if (!token.type.equals("OP")) {
            throw new Exception("ошибка в методе op на токене " + token.type + " " + token.token);
        }
        exec();
        token = tokens.poll();
    }

    private void end() throws Exception {
        token = tokens.peek();
        if (!token.type.equals("END")) {
            throw new Exception("ошибка в методе end на токене " + token.type + " " + token.token);
        }
        exec();
        token = tokens.poll();
    }

    private void my_while() throws Exception {
        token = tokens.peek();
        if (!token.type.equals("WHILE")) {
            throw new Exception("ошибка в методе while на токене " + token.type + " " + token.token);
        }
        exec();
        token = tokens.poll();
    }

    private void open_br() throws Exception {
        token = tokens.peek();
        if (!token.type.equals("OPEN_BR")) {
            throw new Exception("ошибка в методе open_br на токене " + token.type + " " + token.token);
        }
        exec();
        token = tokens.poll();
    }

    private void close_br() throws Exception {
        token = tokens.peek();
        if (!token.type.equals("CLOSE_BR")) {
            throw new Exception("ошибка в методе close_br на токене " + token.type + " " + token.token);
        }
        exec();
        token = tokens.poll();
    }

    private void open_brace() throws Exception {
        token = tokens.peek();
        if (!token.type.equals("OPEN_BRACE")) {
            throw new Exception("ошибка в методе open_brace на токене " + token.type + " " + token.token);
        }
        exec();
        token = tokens.poll();
    }

    private void close_brace() throws Exception {
        token = tokens.peek();
        if (!token.type.equals("CLOSE_BRACE")) {
            throw new Exception("ошибка в методе close_brace на токене " + token.type + " " + token.token);
        }
        exec();
        token = tokens.poll();
    }

    private void log_op() throws Exception {
        token = tokens.peek();
        if (!token.type.equals("LOG_OP")) {
            throw new Exception("ошибка в методе log_op на токене " + token.type + " " + token.token);
        }
        exec();
        token = tokens.poll();
    }

    private void my_str() throws Exception {
        token = tokens.peek();
        if (!token.type.equals("MYST")) {
            throw new Exception("ошибка в методе my_str на токене " + token.type + " " + token.token);
        }
        exec();
        token = tokens.poll();
    }

}
















