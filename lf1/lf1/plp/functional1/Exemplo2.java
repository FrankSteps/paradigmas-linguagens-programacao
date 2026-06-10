package lf1.plp.functional1;

import java.util.ArrayList;
import java.util.List;

import lf1.plp.expressions2.expression.Expressao;
import lf1.plp.expressions2.expression.Id;
import lf1.plp.expressions2.expression.Valor;
import lf1.plp.expressions2.expression.ValorInteiro;
import lf1.plp.expressions2.expression.ExpSoma;
import lf1.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf1.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf1.plp.functional1.declaration.DecFuncao;
import lf1.plp.functional1.declaration.DecVariavel;
import lf1.plp.functional1.declaration.DeclaracaoFuncional;
import lf1.plp.functional1.expression.Aplicacao;
import lf1.plp.functional1.expression.ExpDeclaracao;
import lf1.plp.functional1.util.ValorFuncao;

/**
 * Exemplo 2: Avalia a expressão:
 * let var x = 3 in
 *   let fun f y = y + x in
 *     let var x = 5 in
 *       f 1
 */
public class Exemplo2 {

	private Expressao exp;

	public Exemplo2() {
		this.exp = construirExpressao();
	}

	private Expressao construirExpressao() {
		Id idX = new Id("x");
		Expressao valor3 = new ValorInteiro(3);
		DecVariavel decVarX = new DecVariavel(idX, valor3);

		Id idY = new Id("y");
		Id idFuncaoF = new Id("f");
		Expressao corpoFuncaoF = new ExpSoma(idY, idX);

		List<Id> parametrosF = new ArrayList<Id>();
		parametrosF.add(idY);
		ValorFuncao funcaoF = new ValorFuncao(parametrosF, corpoFuncaoF);
		DecFuncao decFuncaoF = new DecFuncao(idFuncaoF, funcaoF);

		Expressao valor5 = new ValorInteiro(5);
		DecVariavel decVarX2 = new DecVariavel(idX, valor5);

		List<Expressao> argumentos = new ArrayList<Expressao>();
		argumentos.add(new ValorInteiro(1));
		Aplicacao aplicacao = new Aplicacao(idFuncaoF, argumentos);

		List<DeclaracaoFuncional> decl3 = new ArrayList<DeclaracaoFuncional>();
		decl3.add(decVarX2);
		ExpDeclaracao exp3 = new ExpDeclaracao(decl3, aplicacao);

		List<DeclaracaoFuncional> decl2 = new ArrayList<DeclaracaoFuncional>();
		decl2.add(decFuncaoF);
		ExpDeclaracao exp2 = new ExpDeclaracao(decl2, exp3);

		List<DeclaracaoFuncional> decl1 = new ArrayList<DeclaracaoFuncional>();
		decl1.add(decVarX);
		return new ExpDeclaracao(decl1, exp2);
	}

	public boolean checaTipo()
		throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException {
		Programa programa = new Programa(exp);
		return programa.checaTipo();
	}

	public Valor executar()
		throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException {
		Programa programa = new Programa(exp);
		return programa.executar();
	}

	public static void main(String[] args) {
		try {
			Exemplo2 exemplo = new Exemplo2();
			if (exemplo.checaTipo()) {
				Valor resultado = exemplo.executar();
				System.out.println("Resultado: " + resultado);
			} else {
				System.out.println("Erro de tipo na verificação");
			}
		} catch (VariavelJaDeclaradaException | VariavelNaoDeclaradaException e) {
			e.printStackTrace();
		}
	}
}
