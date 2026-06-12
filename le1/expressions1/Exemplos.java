package le1.plp.expressions1;

import le1.plp.expressions1.expression.*;

public class Exemplos {

    public static void main(String[] args) {

        // -4 + 12 - 3
        executar(
            new ExpSub(
                new ExpSoma(
                    new ExpMenos(new ValorInteiro(4)),
                    new ValorInteiro(12)
                ),
                new ValorInteiro(3)
            )
        );

        // length("abc") + 3
        executar(
            new ExpSoma(
                new ExpLength(new ValorString("abc")),
                new ValorInteiro(3)
            )
        );

        // true and false
        executar(
            new ExpAnd(
                new ValorBooleano(true),
                new ValorBooleano(false)
            )
        );

        // "curso" ++ " de " ++ " paradigmas"
        executar(
            new ExpConcat(
                new ExpConcat(
                    new ValorString("curso"),
                    new ValorString(" de ")
                ),
                new ValorString("paradigmas")
            )
        );

        // 1 + true
        executar(
            new ExpSoma(
                new ValorInteiro(1),
                new ValorBooleano(true)
            )
        );
    }

    public static void executar(Expressao exp) {
        Programa p = new Programa(exp);

        if (p.checaTipo()) {
            p.executar();
        } else {
            System.out.println("Erro de tipo!");
        }
    }
}