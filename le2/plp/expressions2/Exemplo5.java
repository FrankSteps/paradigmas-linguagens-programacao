package le2.plp.expressions2;

import java.util.Arrays;
import le2.plp.expressions2.declaration.DecVariavel;
import le2.plp.expressions2.expression.ExpDeclaracao;
import le2.plp.expressions2.expression.ExpSoma;
import le2.plp.expressions2.expression.Id;
import le2.plp.expressions2.expression.ValorInteiro;

// let var x = 3 in
//   let var x = x + 1 in   <- declaracao colateral: x+1 usa o x=3 do bloco anterior
//     let var y = x in
//       x + y
// Resultado esperado: 8  (x=4, y=4, logo 4+4=8)
public class Exemplo5 {

    public static void main(String[] args) {
        Id idMaisInterno = new Id("y");
        DecVariavel decVariavelMaisInterno = new DecVariavel(idMaisInterno, new Id("x"));
        Arrays listDecVariavelMaisInterno = Arrays.asList(decVariavelMaisInterno);
        ExpDeclaracao blocoMaisInterno = new ExpDeclaracao(
            listDecVariavelMaisInterno,
            new ExpSoma(new Id("x"), new Id("y"))
        );

        Id idIntermediario = new Id("x");
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
            System.out.println(programa.executar()); // 8
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
