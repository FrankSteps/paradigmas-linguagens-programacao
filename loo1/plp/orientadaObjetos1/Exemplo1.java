package loo1.plp.orientadaObjetos1;
import loo1.plp.orientadaObjetos1.comando.Atribuicao;
import loo1.plp.orientadaObjetos1.comando.ChamadaMetodo;
import loo1.plp.orientadaObjetos1.comando.ComDeclaracao;
import loo1.plp.orientadaObjetos1.comando.Comando;
import loo1.plp.orientadaObjetos1.comando.Sequencial;
import loo1.plp.orientadaObjetos1.comando.Write;
import loo1.plp.orientadaObjetos1.declaracao.classe.DecClasse;
import loo1.plp.orientadaObjetos1.declaracao.classe.DecClasseSimples;
import loo1.plp.orientadaObjetos1.declaracao.procedimento.DecProcedimento;
import loo1.plp.orientadaObjetos1.declaracao.procedimento.DecProcedimentoComposta;
import loo1.plp.orientadaObjetos1.declaracao.procedimento.DecProcedimentoSimples;
import loo1.plp.orientadaObjetos1.declaracao.procedimento.ListaDeclaracaoParametro;
import loo1.plp.orientadaObjetos1.declaracao.variavel.DecVariavel;
import loo1.plp.orientadaObjetos1.declaracao.variavel.DecVariavelObjeto;
import loo1.plp.orientadaObjetos1.declaracao.variavel.SimplesDecVariavel;
import loo1.plp.orientadaObjetos1.expressao.ListaExpressao;
import loo1.plp.orientadaObjetos1.expressao.This;
import loo1.plp.orientadaObjetos1.expressao.binaria.ExpSoma;
import loo1.plp.orientadaObjetos1.expressao.leftExpression.AcessoAtributoThis;
import loo1.plp.orientadaObjetos1.expressao.leftExpression.Id;
import loo1.plp.orientadaObjetos1.expressao.leftExpression.LeftExpression;
import loo1.plp.orientadaObjetos1.expressao.valor.ValorInteiro;
import loo1.plp.orientadaObjetos1.memoria.ContextoExecucaoOO1;
import loo1.plp.orientadaObjetos1.util.TipoClasse;
import loo1.plp.orientadaObjetos1.util.TipoPrimitivo;

/**
 * Atividade 1.
 *
 * Programa loo1 equivalente a:
 *
 * { classe Contador {
 *     int valor = 1;
 *     proc print() { write(this.valor) },
 *     proc inc() { this.valor := this.valor+1 }
 *   };
 *   { Contador c := new Contador; c.inc(); c.print(); }
 * }
 */
public class Exemplo1 {

    public static void main(String[] args) {

        try {

        // ---------- declaracao da classe Contador ----------

        // atributo: int valor = 1;
        DecVariavel atributos = new SimplesDecVariavel(
                TipoPrimitivo.TIPO_INTEIRO, new Id("valor"), new ValorInteiro(1));

        // proc print() { write(this.valor) }
        DecProcedimento decPrint = new DecProcedimentoSimples(
                new Id("print"),
                new ListaDeclaracaoParametro(),
                new Write(new AcessoAtributoThis(new This(), new Id("valor"))));

        // proc inc() { this.valor := this.valor+1 }
        Comando corpoInc = new Atribuicao(
                new AcessoAtributoThis(new This(), new Id("valor")),
                new ExpSoma(new AcessoAtributoThis(new This(), new Id("valor")), new ValorInteiro(1)));
        DecProcedimento decInc = new DecProcedimentoSimples(
                new Id("inc"), new ListaDeclaracaoParametro(), corpoInc);

        DecProcedimento metodos = new DecProcedimentoComposta(decPrint, decInc);

        DecClasse decClasse = new DecClasseSimples(new Id("Contador"), atributos, metodos);

        // ---------- comando principal ----------

        // Contador c := new Contador;
        DecVariavel decC = new DecVariavelObjeto(
                new TipoClasse(new Id("Contador")), new Id("c"), new Id("Contador"));

        // c.inc();
        LeftExpression c = new Id("c");
        Comando chamaInc = new ChamadaMetodo(c, new Id("inc"), new ListaExpressao());

        // c.print();
        Comando chamaPrint = new ChamadaMetodo(c, new Id("print"), new ListaExpressao());

        Comando comandoInterno = new Sequencial(chamaInc, chamaPrint);

        Comando comandoPrincipal = new ComDeclaracao(decC, comandoInterno);

        // ---------- monta e executa o programa ----------

        Programa programa = new Programa(decClasse, comandoPrincipal);
        programa.executar(new ContextoExecucaoOO1());

        } catch (Exception e) {
            System.out.println("Erro na execucao do programa: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
