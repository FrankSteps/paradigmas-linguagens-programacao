package li2.plp.imperative2;

import li2.plp.expressions1.util.TipoPrimitivo;
import li2.plp.expressions2.expression.ExpSoma;
import li2.plp.expressions2.expression.Id;
import li2.plp.expressions2.expression.ValorInteiro;
import li2.plp.imperative1.command.Atribuicao;
import li2.plp.imperative1.command.Comando;
import li2.plp.imperative1.command.ComandoDeclaracao;
import li2.plp.imperative1.command.SequenciaComando;
import li2.plp.imperative1.command.Write;
import li2.plp.imperative1.declaration.DeclaracaoComposta;
import li2.plp.imperative1.declaration.DeclaracaoVariavel;
import li2.plp.imperative1.memory.ContextoCompilacaoImperativa;
import li2.plp.imperative1.memory.ListaValor;
import li2.plp.imperative2.command.ChamadaProcedimento;
import li2.plp.imperative2.command.ListaExpressao;
import li2.plp.imperative2.declaration.DeclaracaoParametro;
import li2.plp.imperative2.declaration.DeclaracaoProcedimento;
import li2.plp.imperative2.declaration.DefProcedimento;
import li2.plp.imperative2.declaration.ListaDeclaracaoParametro;
import li2.plp.imperative2.memory.ContextoExecucaoImperativa2;

/**
 * Exemplo 2: Executa o programa:
 * { var x = 0, proc p (int y) {x := x + y};
 *   { var x = 1; call p(3); write(x) };
 *   call p(4); write(x)
 * }
 *
 * Saida esperada: 4 4
 * (escopo dinamico: p modifica o x mais interno na 1a chamada,
 *  e o x externo na 2a chamada)
 */
public class Exemplo2 {

    private Comando comando;

    public Exemplo2() {
        this.comando = construirComando();
    }

    private Comando construirComando() {
        Atribuicao corpoP = new Atribuicao(
                new Id("x"),
                new ExpSoma(new Id("x"), new Id("y"))
        );

        ListaDeclaracaoParametro params = new ListaDeclaracaoParametro(
                new DeclaracaoParametro(new Id("y"), TipoPrimitivo.INTEIRO)
        );

        DefProcedimento defP = new DefProcedimento(params, corpoP);

        DeclaracaoComposta decExterna = new DeclaracaoComposta(
                new DeclaracaoVariavel(new Id("x"), new ValorInteiro(0)),
                new DeclaracaoProcedimento(new Id("p"), defP)
        );

        ChamadaProcedimento chamadaP3 = new ChamadaProcedimento(
                new Id("p"),
                new ListaExpressao(new ValorInteiro(3))
        );

        SequenciaComando comandosInternos = new SequenciaComando(
                chamadaP3,
                new Write(new Id("x"))
        );

        ComandoDeclaracao blocoInterno = new ComandoDeclaracao(
                new DeclaracaoVariavel(new Id("x"), new ValorInteiro(1)),
                comandosInternos
        );

        ChamadaProcedimento chamadaP4 = new ChamadaProcedimento(
                new Id("p"),
                new ListaExpressao(new ValorInteiro(4))
        );

        SequenciaComando comandosExternos = new SequenciaComando(
                blocoInterno,
                new SequenciaComando(chamadaP4, new Write(new Id("x")))
        );

        return new ComandoDeclaracao(decExterna, comandosExternos);
    }

    public boolean checaTipo() throws Exception {
        Programa programa = new Programa(comando);
        return programa.checaTipo(new ContextoCompilacaoImperativa(new ListaValor()));
    }

    public ListaValor executar() throws Exception {
        Programa programa = new Programa(comando);
        return programa.executar(new ContextoExecucaoImperativa2(new ListaValor()));
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
                System.out.println("Erro de tipo na verificacao");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
