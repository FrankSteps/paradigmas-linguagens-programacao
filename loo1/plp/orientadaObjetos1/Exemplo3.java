package loo1.plp.orientadaObjetos1;

import loo1.plp.orientadaObjetos1.comando.Atribuicao;
import loo1.plp.orientadaObjetos1.comando.ChamadaMetodo;
import loo1.plp.orientadaObjetos1.comando.ComDeclaracao;
import loo1.plp.orientadaObjetos1.comando.Comando;
import loo1.plp.orientadaObjetos1.comando.IfThenElse;
import loo1.plp.orientadaObjetos1.comando.New;
import loo1.plp.orientadaObjetos1.comando.Sequencial;
import loo1.plp.orientadaObjetos1.comando.Skip;
import loo1.plp.orientadaObjetos1.comando.Write;
import loo1.plp.orientadaObjetos1.declaracao.classe.DecClasse;
import loo1.plp.orientadaObjetos1.declaracao.classe.DecClasseSimples;
import loo1.plp.orientadaObjetos1.declaracao.procedimento.DecParametro;
import loo1.plp.orientadaObjetos1.declaracao.procedimento.DecProcedimento;
import loo1.plp.orientadaObjetos1.declaracao.procedimento.DecProcedimentoComposta;
import loo1.plp.orientadaObjetos1.declaracao.procedimento.DecProcedimentoSimples;
import loo1.plp.orientadaObjetos1.declaracao.procedimento.ListaDeclaracaoParametro;
import loo1.plp.orientadaObjetos1.declaracao.variavel.CompostaDecVariavel;
import loo1.plp.orientadaObjetos1.declaracao.variavel.DecVariavel;
import loo1.plp.orientadaObjetos1.declaracao.variavel.DecVariavelObjeto;
import loo1.plp.orientadaObjetos1.declaracao.variavel.SimplesDecVariavel;
import loo1.plp.orientadaObjetos1.expressao.Expressao;
import loo1.plp.orientadaObjetos1.expressao.ListaExpressao;
import loo1.plp.orientadaObjetos1.expressao.This;
import loo1.plp.orientadaObjetos1.expressao.binaria.ExpEquals;
import loo1.plp.orientadaObjetos1.expressao.leftExpression.AcessoAtributoThis;
import loo1.plp.orientadaObjetos1.expressao.leftExpression.Id;
import loo1.plp.orientadaObjetos1.expressao.leftExpression.LeftExpression;
import loo1.plp.orientadaObjetos1.expressao.unaria.ExpNot;
import loo1.plp.orientadaObjetos1.expressao.valor.ValorInteiro;
import loo1.plp.orientadaObjetos1.expressao.valor.ValorNull;
import loo1.plp.orientadaObjetos1.memoria.ContextoExecucaoOO1;
import loo1.plp.orientadaObjetos1.util.TipoClasse;
import loo1.plp.orientadaObjetos1.util.TipoPrimitivo;

/**
 * Atividade 3.
 *
 * Programa loo1 equivalente a:
 *
 * { classe LValor {
 *     int valor = -100,
 *     LValor prox = null;
 *     proc insere(int v) {
 *       if ((this).valor == -100) then {
 *         this.valor := v;
 *         this.prox := new LValor
 *       } else {(this).prox.insere(v)}
 *     },
 *     proc print() {
 *       write(this.valor);
 *       if (not(this.prox == null)) then {(this).prox.print()}
 *       else {skip}
 *     }
 *   };
 *   { LValor lv := new LValor;
 *     lv.insere(3); lv.insere(4);
 *     lv.print()
 *   }
 * }
 */
public class Exemplo3 {

    public static void main(String[] args) {

        try {

        // ---------- declaracao da classe LValor ----------

        // int valor = -100, LValor prox = null;
        DecVariavel decValor = new SimplesDecVariavel(
                TipoPrimitivo.TIPO_INTEIRO, new Id("valor"), new ValorInteiro(-100));
        DecVariavel decProx = new SimplesDecVariavel(
                new TipoClasse(new Id("LValor")), new Id("prox"), new ValorNull());
        DecVariavel atributos = new CompostaDecVariavel(decValor, decProx);

        // proc insere(int v) { ... }
        DecParametro paramV = new DecParametro(new Id("v"), TipoPrimitivo.TIPO_INTEIRO);
        ListaDeclaracaoParametro parametrosInsere = new ListaDeclaracaoParametro(paramV);

        Expressao condValorPadrao = new ExpEquals(
                new AcessoAtributoThis(new This(), new Id("valor")), new ValorInteiro(-100));

        Comando thenInsere = new Sequencial(
                new Atribuicao(new AcessoAtributoThis(new This(), new Id("valor")), new Id("v")),
                new New(new AcessoAtributoThis(new This(), new Id("prox")), new Id("LValor")));

        Comando elseInsere = new ChamadaMetodo(
                new AcessoAtributoThis(new This(), new Id("prox")),
                new Id("insere"),
                new ListaExpressao(new Id("v")));

        Comando corpoInsere = new IfThenElse(condValorPadrao, thenInsere, elseInsere);

        DecProcedimento decInsere = new DecProcedimentoSimples(
                new Id("insere"), parametrosInsere, corpoInsere);

        // proc print() { ... }
        Comando writeValor = new Write(new AcessoAtributoThis(new This(), new Id("valor")));

        Expressao condProxNulo = new ExpNot(
                new ExpEquals(new AcessoAtributoThis(new This(), new Id("prox")), new ValorNull()));

        Comando thenPrint = new ChamadaMetodo(
                new AcessoAtributoThis(new This(), new Id("prox")),
                new Id("print"), new ListaExpressao());

        Comando corpoPrint = new Sequencial(
                writeValor, new IfThenElse(condProxNulo, thenPrint, new Skip()));

        DecProcedimento decPrint = new DecProcedimentoSimples(
                new Id("print"), new ListaDeclaracaoParametro(), corpoPrint);

        DecProcedimento metodos = new DecProcedimentoComposta(decInsere, decPrint);

        DecClasse decClasse = new DecClasseSimples(new Id("LValor"), atributos, metodos);

        // ---------- comando principal ----------

        // LValor lv := new LValor;
        DecVariavel decLv = new DecVariavelObjeto(
                new TipoClasse(new Id("LValor")), new Id("lv"), new Id("LValor"));

        LeftExpression lv = new Id("lv");

        // lv.insere(3); lv.insere(4); lv.print()
        Comando insere3 = new ChamadaMetodo(lv, new Id("insere"),
                new ListaExpressao(new ValorInteiro(3)));
        Comando insere4 = new ChamadaMetodo(lv, new Id("insere"),
                new ListaExpressao(new ValorInteiro(4)));
        Comando print = new ChamadaMetodo(lv, new Id("print"), new ListaExpressao());

        Comando comandoInterno = new Sequencial(insere3, new Sequencial(insere4, print));

        Comando comandoPrincipal = new ComDeclaracao(decLv, comandoInterno);

        // ---------- monta e executa o programa ----------

        Programa programa = new Programa(decClasse, comandoPrincipal);
        programa.executar(new ContextoExecucaoOO1());

        } catch (Exception e) {
            System.out.println("Erro na execucao do programa: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
