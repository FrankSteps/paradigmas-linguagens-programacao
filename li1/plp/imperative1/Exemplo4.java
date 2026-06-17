package li1.plp.imperative1;

import li1.plp.expressions2.expression.ExpEquals;
import li1.plp.expressions2.expression.Id;
import li1.plp.expressions2.expression.ValorInteiro;
import li1.plp.expressions2.expression.ValorString;
import li1.plp.imperative1.command.Atribuicao;
import li1.plp.imperative1.command.Comando;
import li1.plp.imperative1.command.ComandoDeclaracao;
import li1.plp.imperative1.command.IfThenElse;
import li1.plp.imperative1.command.SequenciaComando;
import li1.plp.imperative1.command.Write;
import li1.plp.imperative1.declaration.DeclaracaoComposta;
import li1.plp.imperative1.declaration.DeclaracaoVariavel;
import li1.plp.imperative1.memory.ContextoCompilacaoImperativa;
import li1.plp.imperative1.memory.ContextoExecucaoImperativa;
import li1.plp.imperative1.memory.ListaValor;

/**
 * Exemplo 4: Executa o programa:
 * { var n = 0, var m = 0;
 *   n := 2; m := 3;
 *   if (m == n) then
 *     write("valores de entrada iguais")
 *   else
 *     write("valores de entrada diferentes")
 * }
 *
 * Saida esperada: "valores de entrada diferentes"
 */
public class Exemplo4 {

    private Comando comando;

    public Exemplo4() {
        this.comando = construirComando();
    }

    private Comando construirComando() {
        // var n = 0, var m = 0
        DeclaracaoComposta decNM = new DeclaracaoComposta(
                new DeclaracaoVariavel(new Id("n"), new ValorInteiro(0)),
                new DeclaracaoVariavel(new Id("m"), new ValorInteiro(0))
        );

        // n := 2
        Atribuicao atribN = new Atribuicao(new Id("n"), new ValorInteiro(2));

        // m := 3
        Atribuicao atribM = new Atribuicao(new Id("m"), new ValorInteiro(3));

        // if (m == n) then write("valores de entrada iguais")
        //             else write("valores de entrada diferentes")
        IfThenElse ifCmd = new IfThenElse(
                new ExpEquals(new Id("m"), new Id("n")),
                new Write(new ValorString("valores de entrada iguais")),
                new Write(new ValorString("valores de entrada diferentes"))
        );

        // n := 2; m := 3; if (m == n) then ... else ...
        SequenciaComando comandos = new SequenciaComando(
                atribN,
                new SequenciaComando(atribM, ifCmd)
        );

        // { var n = 0, var m = 0; n := 2; m := 3; if ... }
        return new ComandoDeclaracao(decNM, comandos);
    }

    public boolean checaTipo() throws Exception {
        Programa programa = new Programa(comando);
        return programa.checaTipo(new ContextoCompilacaoImperativa(new ListaValor()));
    }

    public ListaValor executar() throws Exception {
        Programa programa = new Programa(comando);
        return programa.executar(new ContextoExecucaoImperativa(new ListaValor()));
    }

    public static void main(String[] args) {
        try {
            Exemplo4 exemplo = new Exemplo4();
            if (exemplo.checaTipo()) {
                ListaValor saida = exemplo.executar();
                System.out.println("Resultado: " + saida.getHead());
            } else {
                System.out.println("Erro de tipo na verificação");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
