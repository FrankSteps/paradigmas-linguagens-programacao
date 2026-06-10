package li1.plp.imperative1;

import li1.plp.expressions2.expression.ExpEquals;
import li1.plp.expressions2.expression.ExpNot;
import li1.plp.expressions2.expression.ExpSoma;
import li1.plp.expressions2.expression.Id;
import li1.plp.expressions2.expression.ValorInteiro;
import li1.plp.expressions2.expression.ValorString;
import li1.plp.imperative1.command.Atribuicao;
import li1.plp.imperative1.command.Comando;
import li1.plp.imperative1.command.ComandoDeclaracao;
import li1.plp.imperative1.command.SequenciaComando;
import li1.plp.imperative1.command.While;
import li1.plp.imperative1.command.Write;
import li1.plp.imperative1.declaration.DeclaracaoVariavel;
import li1.plp.imperative1.memory.ContextoCompilacaoImperativa;
import li1.plp.imperative1.memory.ContextoExecucaoImperativa;
import li1.plp.imperative1.memory.ListaValor;

/**
 * Exemplo 3: Executa o programa:
 * { var i = 0;
 *   while not (i == 3) do
 *     i := i + 1;
 *   write("Hello World")
 * }
 *
 * Saida esperada: "Hello World"
 */
public class Exemplo3 {

    private Comando comando;

    public Exemplo3() {
        this.comando = construirComando();
    }

    private Comando construirComando() {
        // var i = 0
        DeclaracaoVariavel decI = new DeclaracaoVariavel(
                new Id("i"),
                new ValorInteiro(0)
        );

        // not (i == 3)
        ExpNot condicao = new ExpNot(
                new ExpEquals(new Id("i"), new ValorInteiro(3))
        );

        // i := i + 1
        Atribuicao incremento = new Atribuicao(
                new Id("i"),
                new ExpSoma(new Id("i"), new ValorInteiro(1))
        );

        // while not (i == 3) do i := i + 1
        While whileLoop = new While(condicao, incremento);

        // write("Hello World")
        Write writeHello = new Write(new ValorString("Hello World"));

        // while ...; write("Hello World")
        SequenciaComando comandos = new SequenciaComando(whileLoop, writeHello);

        // { var i = 0; while ...; write("Hello World") }
        return new ComandoDeclaracao(decI, comandos);
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
            Exemplo3 exemplo = new Exemplo3();
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
