package le1.plp.expressions1;

import le1.plp.expressions1.expression.*;

public class Exemplo1 {

    public static void main(String[] args) {
        // Atividade 1: 4 + 12 - 3
        Expressao exp = new ExpSub(
            new ExpSoma(
                new ValorInteiro(4),
                new ValorInteiro(12)
            ),
            new ValorInteiro(3)
        );

        Programa p = new Programa(exp);
        if (p.checaTipo()) {
            p.executar();
        } else {
            System.err.println("Erro de Tipo!");
        }
    }
}