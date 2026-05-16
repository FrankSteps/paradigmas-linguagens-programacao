package le2.plp.expressions2;

import java.util.Arrays;
import le2.plp.expressions2.declaration.DecVariavel;
import le2.plp.expressions2.expression.ExpDeclaracao;
import le2.plp.expressions2.expression.ExpSoma;
import le2.plp.expressions2.expression.Id;
import le2.plp.expressions2.expression.ValorInteiro;

// let var x = 3 in
//   let var y = x + 1 in
//     let var x = 2 in
//       x + y
// Resultado esperado: 6  (x=2, y=4, logo 2+4=6)
public class Exemplo3 {

    public static void main(String[] args) {
        ExpDeclaracao blocoMaisInterno = new ExpDeclaracao(
            Arrays.asList(new DecVariavel(new Id("x"), new ValorInteiro(2))),
            new ExpSoma(new Id("x"), new Id("y"))
        );

        ExpDeclaracao blocoIntermediario = new ExpDeclaracao(
            Arrays.asList(new DecVariavel(new Id("y"), new ExpSoma(new Id("x"), new ValorInteiro(1)))),
            blocoMaisInterno
        );

        ExpDeclaracao exp = new ExpDeclaracao(
            Arrays.asList(new DecVariavel(new Id("x"), new ValorInteiro(3))),
            blocoIntermediario
        );

        try {
            Programa programa = new Programa(exp);
            System.out.println(programa.executar()); // 6
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
