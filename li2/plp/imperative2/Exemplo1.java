package li2.plp.imperative2;

import li2.plp.expressions2.expression.ExpSoma;
import li2.plp.expressions2.expression.Id;
import li2.plp.expressions2.expression.ValorInteiro;
import li2.plp.imperative1.command.Atribuicao;
import li2.plp.imperative1.command.Comando;
import li2.plp.imperative1.command.SequenciaComando;
import li2.plp.imperative1.command.Write;
import li2.plp.imperative1.declaration.DeclaracaoComposta;
import li2.plp.imperative1.declaration.DeclaracaoVariavel;
import li2.plp.imperative1.memory.ContextoCompilacaoImperativa;
import li2.plp.imperative1.memory.ListaValor;
import li2.plp.imperative2.command.ChamadaProcedimento;
import li2.plp.imperative2.command.ListaExpressao;
import li2.plp.imperative2.declaration.DeclaracaoProcedimento;
import li2.plp.imperative2.declaration.DefProcedimento;
import li2.plp.imperative2.declaration.ListaDeclaracaoParametro;
import li2.plp.imperative2.memory.ContextoExecucaoImperativa2;

/**
 * Exemplo 1: Executa o programa:
 * { var a = 0, proc incA () {a := a + 1};
 *   call incA(); call incA(); write(a)
 * }
 *
 * Saida esperada: 2
 */
public class Exemplo1 {

    private Comando comando;

    public Exemplo1() {
        this.comando = construirComando();
    }

    private Comando construirComando() {
        Atribuicao corpoIncA = new Atribuicao(
                new Id("a"),
                new ExpSoma(new Id("a"), new ValorInteiro(1))
        );

        DefProcedimento defIncA = new DefProcedimento(
                new ListaDeclaracaoParametro(),
                corpoIncA
        );

        DeclaracaoComposta declaracoes = new DeclaracaoComposta(
                new DeclaracaoVariavel(new Id("a"), new ValorInteiro(0)),
                new DeclaracaoProcedimento(new Id("incA"), defIncA)
        );

        ChamadaProcedimento chamada1 = new ChamadaProcedimento(
                new Id("incA"), new ListaExpressao()
        );

        ChamadaProcedimento chamada2 = new ChamadaProcedimento(
                new Id("incA"), new ListaExpressao()
        );

        SequenciaComando comandos = new SequenciaComando(
                chamada1,
                new SequenciaComando(chamada2, new Write(new Id("a")))
        );

        return new li2.plp.imperative1.command.ComandoDeclaracao(declaracoes, comandos);
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
            Exemplo1 exemplo = new Exemplo1();
            if (exemplo.checaTipo()) {
                ListaValor saida = exemplo.executar();
                System.out.println("Resultado: " + saida.getHead());
            } else {
                System.out.println("Erro de tipo na verificacao");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
