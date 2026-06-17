package lf1.plp.functional1;

import java.util.ArrayList;
import java.util.List;

import lf1.plp.expressions2.expression.Expressao;
import lf1.plp.expressions2.expression.Id;
import lf1.plp.expressions2.expression.Valor;
import lf1.plp.expressions2.expression.ValorInteiro;
import lf1.plp.expressions2.expression.ValorString;
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
 * Exemplo 3: Avalia a expressão:
 * let var y = 3 in
 *   let fun f x = x + y in
 *     let var z = "abc" in
 *       f 3
 */
public class Exemplo3 {

	private Expressao exp;

	public Exemplo3() {
		this.exp = construirExpressao();
	}

	private Expressao construirExpressao() {
		Id idY = new Id("y");
		Expressao valor3 = new ValorInteiro(3);
		DecVariavel decVarY = new DecVariavel(idY, valor3);

		Id idX = new Id("x");
		Id idFuncaoF = new Id("f");
		Expressao corpoFuncaoF = new ExpSoma(idX, idY);

		List<Id> parametrosF = new ArrayList<Id>();
		parametrosF.add(idX);
		ValorFuncao funcaoF = new ValorFuncao(parametrosF, corpoFuncaoF);
		DecFuncao decFuncaoF = new DecFuncao(idFuncaoF, funcaoF);

		Id idZ = new Id("z");
		Expressao valorAbc = new ValorString("abc");
		DecVariavel decVarZ = new DecVariavel(idZ, valorAbc);

		List<Expressao> argumentos = new ArrayList<Expressao>();
		argumentos.add(new ValorInteiro(3));
		Aplicacao aplicacao = new Aplicacao(idFuncaoF, argumentos);

		List<DeclaracaoFuncional> decl3 = new ArrayList<DeclaracaoFuncional>();
		decl3.add(decVarZ);
		ExpDeclaracao exp3 = new ExpDeclaracao(decl3, aplicacao);

		List<DeclaracaoFuncional> decl2 = new ArrayList<DeclaracaoFuncional>();
		decl2.add(decFuncaoF);
		ExpDeclaracao exp2 = new ExpDeclaracao(decl2, exp3);

		List<DeclaracaoFuncional> decl1 = new ArrayList<DeclaracaoFuncional>();
		decl1.add(decVarY);
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
			Exemplo3 exemplo = new Exemplo3();
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
