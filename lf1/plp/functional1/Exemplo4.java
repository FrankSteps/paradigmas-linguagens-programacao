package lf1.plp.functional1;

import java.util.ArrayList;
import java.util.List;

import lf1.plp.expressions2.expression.Expressao;
import lf1.plp.expressions2.expression.Id;
import lf1.plp.expressions2.expression.Valor;
import lf1.plp.expressions2.expression.ValorInteiro;
import lf1.plp.expressions2.expression.ExpSoma;
import lf1.plp.expressions2.expression.ExpSub;
import lf1.plp.expressions2.expression.ExpEquals;
import lf1.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf1.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf1.plp.functional1.declaration.DecFuncao;
import lf1.plp.functional1.declaration.DeclaracaoFuncional;
import lf1.plp.functional1.expression.Aplicacao;
import lf1.plp.functional1.expression.ExpDeclaracao;
import lf1.plp.functional1.expression.IfThenElse;
import lf1.plp.functional1.util.ValorFuncao;

/**
 * Exemplo 4: Avalia a expressão:
 * let fun mult x y = if (x == 0) then (0) else (y + mult (x - 1, y)) in mult (3,4)
 *
 * Esta função define uma multiplicação recursiva de dois inteiros usando soma.
 * mult(3, 4) = 4 + mult(2, 4) = 4 + 4 + mult(1, 4) = 4 + 4 + 4 + mult(0, 4)
 *            = 4 + 4 + 4 + 0 = 12
 */
public class Exemplo4 {

	private Expressao exp;

	public Exemplo4() {
		this.exp = construirExpressao();
	}

	private Expressao construirExpressao() {
		Id idX = new Id("x");
		Id idY = new Id("y");
		Id idMult = new Id("mult");

		Expressao condicao = new ExpEquals(idX, new ValorInteiro(0));

		Expressao thenExpressao = new ValorInteiro(0);

		Expressao subtracao = new ExpSub(idX, new ValorInteiro(1));

		List<Expressao> argumentosRecursivos = new ArrayList<Expressao>();
		argumentosRecursivos.add(subtracao);
		argumentosRecursivos.add(idY);
		Aplicacao aplicacaoRecursiva = new Aplicacao(idMult, argumentosRecursivos);

		Expressao elseExpressao = new ExpSoma(idY, aplicacaoRecursiva);

		Expressao corpoFuncao = new IfThenElse(condicao, thenExpressao, elseExpressao);

		List<Id> parametrosMult = new ArrayList<Id>();
		parametrosMult.add(idX);
		parametrosMult.add(idY);
		ValorFuncao funcaoMult = new ValorFuncao(parametrosMult, corpoFuncao);

		DecFuncao decFuncaoMult = new DecFuncao(idMult, funcaoMult);

		List<DeclaracaoFuncional> declaracoes = new ArrayList<DeclaracaoFuncional>();
		declaracoes.add(decFuncaoMult);

		List<Expressao> argumentos = new ArrayList<Expressao>();
		argumentos.add(new ValorInteiro(3));
		argumentos.add(new ValorInteiro(4));
		Aplicacao aplicacao = new Aplicacao(idMult, argumentos);

		return new ExpDeclaracao(declaracoes, aplicacao);
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
			Exemplo4 exemplo = new Exemplo4();
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
