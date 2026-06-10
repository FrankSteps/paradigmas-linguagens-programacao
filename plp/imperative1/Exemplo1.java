package li1.plp.imperative1;

import li1.plp.expressions2.expression.Id;
import li1.plp.expressions2.expression.ValorInteiro;
import li1.plp.imperative1.command.Comando;
import li1.plp.imperative1.command.ComandoDeclaracao;
import li1.plp.imperative1.command.Write;
import li1.plp.imperative1.declaration.DeclaracaoVariavel;
import li1.plp.imperative1.memory.ContextoCompilacaoImperativa;
import li1.plp.imperative1.memory.ContextoExecucaoImperativa;
import li1.plp.imperative1.memory.ListaValor;

/**
 * Exemplo 1: Executa o programa:
 * { var a = 3; write(a) }
 *
 * Saida esperada: 3
 */
public class Exemplo1 {

    private Comando comando;

    public Exemplo1() {
        this.comando = construirComando();
    }

    private Comando construirComando() {
        // var a = 3
        DeclaracaoVariavel decA = new DeclaracaoVariavel(
                new Id("a"),
                new ValorInteiro(3)
        );

        // write(a)
        Write writeA = new Write(new Id("a"));

        // { var a = 3; write(a) }
        return new ComandoDeclaracao(decA, writeA);
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
            Exemplo1 exemplo = new Exemplo1();
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
