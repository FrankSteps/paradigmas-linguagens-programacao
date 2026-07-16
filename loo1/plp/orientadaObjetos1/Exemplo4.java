package loo1.plp.orientadaObjetos1;

import loo1.plp.orientadaObjetos1.comando.Atribuicao;
import loo1.plp.orientadaObjetos1.comando.ChamadaMetodo;
import loo1.plp.orientadaObjetos1.comando.ComDeclaracao;
import loo1.plp.orientadaObjetos1.comando.Comando;
import loo1.plp.orientadaObjetos1.comando.IfThenElse;
import loo1.plp.orientadaObjetos1.comando.New;
import loo1.plp.orientadaObjetos1.comando.Sequencial;
import loo1.plp.orientadaObjetos1.comando.Skip;
import loo1.plp.orientadaObjetos1.comando.While;
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
import loo1.plp.orientadaObjetos1.expressao.binaria.ExpOr;
import loo1.plp.orientadaObjetos1.expressao.leftExpression.AcessoAtributoId;
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
 * Atividade 4.
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
 *     proc remove(int v) {
 *       {
 *         LValor aux = this;
 *         while(not((aux.prox == null) or (((aux).prox).valor == v))) do {
 *           aux := aux.prox
 *         };
 *         if ( not( aux.prox == null) ) then {
 *           aux.prox := ((aux).prox).prox
 *         }
 *         else { skip}
 *       }
 *     },
 *     proc print() {
 *       write(this.valor);
 *       if (not(this.prox == null)) then {(this).prox.print()}
 *       else {skip}
 *     }
 *   };
 *   { LValor lv := new LValor;
 *     lv.insere(2); lv.insere(3); lv.insere(4); lv.print();
 *     lv.remove(3); lv.print()
 *   }
 * }
 */
public class Exemplo4 {

    public static void main(String[] args) {

        try {

        // ---------- declaracao da classe LValor ----------

        // int valor = -100, LValor prox = null;
        DecVariavel decValor = new SimplesDecVariavel(
                TipoPrimitivo.TIPO_INTEIRO, new Id("valor"), new ValorInteiro(-100));
        DecVariavel decProx = new SimplesDecVariavel(
                new TipoClasse(new Id("LValor")), new Id("prox"), new ValorNull());
        DecVariavel atributos = new CompostaDecVariavel(decValor, decProx);

        // proc insere(int v) { ... }  (igual a Atividade 3)
        DecParametro paramVInsere = new DecParametro(new Id("v"), TipoPrimitivo.TIPO_INTEIRO);
        ListaDeclaracaoParametro parametrosInsere = new ListaDeclaracaoParametro(paramVInsere);

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

        // proc remove(int v) { { LValor aux = this; while(...) do {...}; if(...) then {...} else {skip} } }
        DecParametro paramVRemove = new DecParametro(new Id("v"), TipoPrimitivo.TIPO_INTEIRO);
        ListaDeclaracaoParametro parametrosRemove = new ListaDeclaracaoParametro(paramVRemove);

        // LValor aux = this;
        DecVariavel decAux = new SimplesDecVariavel(
                new TipoClasse(new Id("LValor")), new Id("aux"), new This());

        LeftExpression aux = new Id("aux");

        // not((aux.prox == null) or (((aux).prox).valor == v))
        Expressao auxProxIgualNull = new ExpEquals(
                new AcessoAtributoId(aux, new Id("prox")), new ValorNull());
        Expressao auxProxValorIgualV = new ExpEquals(
                new AcessoAtributoId(new AcessoAtributoId(aux, new Id("prox")), new Id("valor")),
                new Id("v"));
        Expressao condWhile = new ExpNot(new ExpOr(auxProxIgualNull, auxProxValorIgualV));

        // aux := aux.prox
        Comando corpoWhile = new Atribuicao(aux, new AcessoAtributoId(aux, new Id("prox")));

        Comando comandoWhile = new While(condWhile, corpoWhile);

        // if ( not( aux.prox == null) ) then { aux.prox := ((aux).prox).prox } else { skip}
        Expressao condIf = new ExpNot(new ExpEquals(
                new AcessoAtributoId(aux, new Id("prox")), new ValorNull()));

        Comando thenRemove = new Atribuicao(
                new AcessoAtributoId(aux, new Id("prox")),
                new AcessoAtributoId(new AcessoAtributoId(aux, new Id("prox")), new Id("prox")));

        Comando comandoIf = new IfThenElse(condIf, thenRemove, new Skip());

        Comando corpoRemove = new ComDeclaracao(decAux, new Sequencial(comandoWhile, comandoIf));

        DecProcedimento decRemove = new DecProcedimentoSimples(
                new Id("remove"), parametrosRemove, corpoRemove);

        // proc print() { ... }  (igual a Atividade 3)
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

        DecProcedimento metodos = new DecProcedimentoComposta(
                decInsere, new DecProcedimentoComposta(decRemove, decPrint));

        DecClasse decClasse = new DecClasseSimples(new Id("LValor"), atributos, metodos);

        // ---------- comando principal ----------

        // LValor lv := new LValor;
        DecVariavel decLv = new DecVariavelObjeto(
                new TipoClasse(new Id("LValor")), new Id("lv"), new Id("LValor"));

        LeftExpression lv = new Id("lv");

        // lv.insere(2); lv.insere(3); lv.insere(4); lv.print(); lv.remove(3); lv.print()
        Comando insere2 = new ChamadaMetodo(lv, new Id("insere"),
                new ListaExpressao(new ValorInteiro(2)));
        Comando insere3 = new ChamadaMetodo(lv, new Id("insere"),
                new ListaExpressao(new ValorInteiro(3)));
        Comando insere4 = new ChamadaMetodo(lv, new Id("insere"),
                new ListaExpressao(new ValorInteiro(4)));
        Comando print1 = new ChamadaMetodo(lv, new Id("print"), new ListaExpressao());
        Comando remove3 = new ChamadaMetodo(lv, new Id("remove"),
                new ListaExpressao(new ValorInteiro(3)));
        Comando print2 = new ChamadaMetodo(lv, new Id("print"), new ListaExpressao());

        Comando comandoInterno = new Sequencial(insere2,
                new Sequencial(insere3,
                        new Sequencial(insere4,
                                new Sequencial(print1,
                                        new Sequencial(remove3, print2)))));

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
