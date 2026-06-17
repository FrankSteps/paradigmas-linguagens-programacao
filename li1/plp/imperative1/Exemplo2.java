package li1.plp.imperative1;

import li1.plp.expressions2.expression.ExpSoma;
import li1.plp.expressions2.expression.Id;
import li1.plp.expressions2.expression.ValorInteiro;
import li1.plp.imperative1.command.Comando;
import li1.plp.imperative1.command.ComandoDeclaracao;
import li1.plp.imperative1.command.SequenciaComando;
import li1.plp.imperative1.command.Write;
import li1.plp.imperative1.declaration.DeclaracaoComposta;
import li1.plp.imperative1.declaration.DeclaracaoVariavel;
import li1.plp.imperative1.memory.ContextoCompilacaoImperativa;
import li1.plp.imperative1.memory.ContextoExecucaoImperativa;
import li1.plp.imperative1.memory.ListaValor;

/**
 * Exemplo 2: Executa o programa:
 * { var a = 3; write(a);
 *   { var a = 2, var b = 5; write(a); write(b+a) };
 *   write(a)
 * }
 *
 * Saida esperada: 3 2 7 3
 */
public class Exemplo2 {

    private Comando comando;

    public Exemplo2() {
        this.comando = construirComando();
    }

    private Comando construirComando() {
        // --- Bloco interno: { var a = 2, var b = 5; write(a); write(b+a) } ---
        DeclaracaoComposta decInternaAB = new DeclaracaoComposta(
                new DeclaracaoVariavel(new Id("a"), new ValorInteiro(2)),
                new DeclaracaoVariavel(new Id("b"), new ValorInteiro(5))
        );

        SequenciaComando comandosInternos = new SequenciaComando(
                new Write(new Id("a")),
                new Write(new ExpSoma(new Id("b"), new Id("a")))
        );

        ComandoDeclaracao blocoInterno = new ComandoDeclaracao(decInternaAB, comandosInternos);

        // --- Bloco externo: { var a = 3; write(a); <blocoInterno>; write(a) } ---
        DeclaracaoVariavel decExternaA = new DeclaracaoVariavel(
                new Id("a"),
                new ValorInteiro(3)
        );

        SequenciaComando comandosExternos = new SequenciaComando(
                new Write(new Id("a")),
                new SequenciaComando(
                        blocoInterno,
                        new Write(new Id("a"))
                )
        );

        return new ComandoDeclaracao(decExternaA, comandosExternos);
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
            Exemplo2 exemplo = new Exemplo2();
            if (exemplo.checaTipo()) {
                ListaValor saida = exemplo.executar();
                System.out.print("Resultado: ");
                ListaValor atual = saida;
                while (atual != null && atual.getHead() != null) {
                    System.out.print(atual.getHead() + " ");
                    atual = (ListaValor) atual.getTail();
                }
                System.out.println();
            } else {
                System.out.println("Erro de tipo na verificação");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
