package le2.plp.expressions2;

import java.util.Arrays;
import le2.plp.expressions2.declaration.DecVariavel;
import le2.plp.expressions2.expression.ExpDeclaracao;
import le2.plp.expressions2.expression.ExpSoma;
import le2.plp.expressions2.expression.Id;
import le2.plp.expressions2.expression.ValorInteiro;

// let var x = 1 in
//   let var x = 2 in x + 1
// Resultado esperado: 3
public class Exemplo2 {

    public static void main(String[] args) {
         ExpDeclaracao blocoInterno = new ExpDeclaracao(
            Arrays.asList(new DecVariavel(new Id("x"), new ValorInteiro(2))),
            new ExpSoma(new Id("x"), new ValorInteiro(1))
        );

        ExpDeclaracao exp = new ExpDeclaracao(
            Arrays.asList(new DecVariavel(new Id("x"), new ValorInteiro(1))),
            blocoInterno
        );

        try {
            Programa programa = new Programa(exp);
            System.out.println(programa.executar()); // 3
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
