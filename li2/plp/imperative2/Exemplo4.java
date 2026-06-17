package li2.plp.imperative2;

import li2.plp.expressions1.util.TipoPrimitivo;
import li2.plp.expressions2.expression.ExpEquals;
import li2.plp.expressions2.expression.ExpNot;
import li2.plp.expressions2.expression.ExpSub;
import li2.plp.expressions2.expression.Id;
import li2.plp.expressions2.expression.ValorInteiro;
import li2.plp.expressions2.expression.ValorString;
import li2.plp.imperative1.command.Atribuicao;
import li2.plp.imperative1.command.Comando;
import li2.plp.imperative1.command.ComandoDeclaracao;
import li2.plp.imperative1.command.IfThenElse;
import li2.plp.imperative1.command.SequenciaComando;
import li2.plp.imperative1.command.Skip;
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
 * Exemplo 4: Executa o programa:
 * { var b = 3,
 *   proc escreveRecursivo (int a) {
 *     if (not (a == 0)) then {
 *       var x = 0; x := a - 1;
 *       write("Ola");
 *       call escreveRecursivo(x)
 *     } else skip
 *   };
 *   call escreveRecursivo(a)   <- nota: chama com "a" que nao esta declarado
 * }
 *
 * Saida esperada: IdentificadorNaoDeclaradoException (variavel "a" nao declarada)
 * O professor usa esse exemplo para demonstrar o erro de identificador nao declarado.
 */
public class Exemplo4 {

    public static void main(String[] args) {
        try {
            Atribuicao atribX = new Atribuicao(
                    new Id("x"),
                    new ExpSub(new Id("a"), new ValorInteiro(1))
            );

            ChamadaProcedimento chamadaRec = new ChamadaProcedimento(
                    new Id("escreveRecursivo"),
                    new ListaExpressao(new Id("x"))
            );

            SequenciaComando comandosBlocoThen = new SequenciaComando(
                    atribX,
                    new SequenciaComando(new Write(new ValorString("Ola")), chamadaRec)
            );

            ComandoDeclaracao blocoThen = new ComandoDeclaracao(
                    new DeclaracaoVariavel(new Id("x"), new ValorInteiro(0)),
                    comandosBlocoThen
            );

            IfThenElse ifCmd = new IfThenElse(
                    new ExpNot(new ExpEquals(new Id("a"), new ValorInteiro(0))),
                    blocoThen,
                    new Skip()
            );

            ListaDeclaracaoParametro params = new ListaDeclaracaoParametro(
                    new DeclaracaoParametro(new Id("a"), TipoPrimitivo.INTEIRO)
            );

            DefProcedimento defEscreve = new DefProcedimento(params, ifCmd);

            DeclaracaoComposta declaracoes = new DeclaracaoComposta(
                    new DeclaracaoVariavel(new Id("b"), new ValorInteiro(3)),
                    new DeclaracaoProcedimento(new Id("escreveRecursivo"), defEscreve)
            );

            ChamadaProcedimento chamadaComErro = new ChamadaProcedimento(
                    new Id("escreveRecursivo"),
                    new ListaExpressao(new Id("a"))
            );

            Comando comando = new ComandoDeclaracao(declaracoes, chamadaComErro);

            Programa programa = new Programa(comando);
            if (programa.checaTipo(new ContextoCompilacaoImperativa(new ListaValor()))) {
                ListaValor saida = programa.executar(new ContextoExecucaoImperativa2(new ListaValor()));
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
            
        }
    }
}
