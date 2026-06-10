package le2.plp.expressions2;

import java.lang.reflect.Array;
import java.util.Arrays;
import le2.plp.expressions2.declaration.DecVariavel;
import le2.plp.expressions2.expression.ExpDeclaracao;
import le2.plp.expressions2.expression.ExpSoma;
import le2.plp.expressions2.expression.Id;
import le2.plp.expressions2.expression.ValorInteiro;

// let var x = 3 in
//   let var y = x + 1 in
//     let var x = 5 in
//       y
// Resultado esperado: 4  (y foi calculado como 3+1=4, x=5 nao afeta y)
public class Exemplo4 {

    public static void main(String[] args) {
        Id idMaisInterno = new Id("x");
        DecVariavel decVariavelMaisInterno = new DecVariavel(idMaisInterno, new ValorInteiro(5));
        Arrays listDecVariavelMaisInterno = Arrays.asList(decVariavelMaisInterno);
        ExpDeclaracao blocoMaisInterno = new ExpDeclaracao(
            listDecVariavelMaisInterno,
            new Id("y")
        );

        Id idIntermediario = new Id("y");
        DecVariavel decVariavelIntermediario = new DecVariavel(idIntermediario, new ExpSoma(new Id("x"), new ValorInteiro(1)));
        Arrays listDecVariavelIntermediario = Arrays.asList(decVariavelIntermediario);
        ExpDeclaracao blocoIntermediario = new ExpDeclaracao(
            listDecVariavelIntermediario,
            blocoMaisInterno
        );

        Id idExterno = new Id("x");
        DecVariavel decVariavelExterno = new DecVariavel(idExterno, new ValorInteiro(3));
        Arrays listDecVariavelExterno = Arrays.asList(decVariavelExterno);
        ExpDeclaracao expDeclaracao = new ExpDeclaracao(
            listDecVariavelExterno,
            blocoIntermediario
        );

        try {
            Programa programa = new Programa(expDeclaracao);
            programa.checaTipo();
            System.out.println(programa.executar()); // 4
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}