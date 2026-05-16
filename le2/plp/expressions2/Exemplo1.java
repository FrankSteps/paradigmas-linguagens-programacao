package le2.plp.expressions2;

import java.util.Arrays;
import le2.plp.expressions2.declaration.DecVariavel;
import le2.plp.expressions2.expression.ExpDeclaracao;
import le2.plp.expressions2.expression.ExpSoma;
import le2.plp.expressions2.expression.Id;
import le2.plp.expressions2.expression.ValorInteiro;

// let var x = 1 in x + 1
// Resultado esperado: 2
public class Exemplo1 {

    public static void main(String[] args) {
        Id id = new Id("x");
        DecVariavel decVariavel = new DecVariavel(id, new ValorInteiro(1));
        Arrays listDecVariavel = Arrays.asList(decVariavel);
        ExpDeclaracao expDeclaracao = new ExpDeclaracao(
            listDecVariavel,
            new ExpSoma(new Id("x"), new ValorInteiro(1))
        );

        try {
            Programa programa = new Programa(expDeclaracao);
            programa.checaTipo();
            System.out.println(programa.executar()); // 2
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
