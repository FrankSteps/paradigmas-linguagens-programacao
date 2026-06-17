package lf1.plp.functional1;

import java.util.ArrayList;
import java.util.List;
import lf1.plp.expressions2.expression.ExpSoma;
import lf1.plp.expressions2.expression.Expressao;
import lf1.plp.expressions2.expression.Id;
import lf1.plp.expressions2.expression.ValorInteiro;
import lf1.plp.functional1.declaration.DecFuncao;
import lf1.plp.functional1.declaration.DeclaracaoFuncional;
import lf1.plp.functional1.expression.Aplicacao;
import lf1.plp.functional1.expression.ExpDeclaracao;
import lf1.plp.functional1.util.ValorFuncao;

/**
 * Exemplo 1: Avalia a expressão: let fun f x = x + 1 in f 2
 */
public class Exemplo1 {

	


	public static void main(String[] args) {


		Id x = new Id("x");
		Expressao corpoFuncao = new ExpSoma(x, new ValorInteiro(1));

		List<Id> parametros = new ArrayList<Id>();
		parametros.add(x);

		ValorFuncao funcaoF = new ValorFuncao(parametros, corpoFuncao);

		Id idFuncaoF = new Id("f");
		DecFuncao decFuncao = new DecFuncao(idFuncaoF, funcaoF);

		List<DeclaracaoFuncional> declaracoes = new ArrayList<DeclaracaoFuncional>();
		declaracoes.add(decFuncao);

		List<Expressao> argumentos = new ArrayList<Expressao>();
		argumentos.add(new ValorInteiro(2));
		Aplicacao aplicacao = new Aplicacao(idFuncaoF, argumentos);

		ExpDeclaracao exp = new ExpDeclaracao(declaracoes, aplicacao);

		try {
			Programa programa = new Programa(exp);	
			if (programa.checaTipo()) {
				System.out.println("Resultado: " + programa.executar());
			} else {
				System.out.println("Erro de tipo na verificação");
			}
		} catch (Exception e) {
			System.out.println("Erro de:"+ e.getMessage());
			e.printStackTrace();
		}
	}
}
