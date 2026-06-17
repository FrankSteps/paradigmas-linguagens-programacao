package li2.plp.functional2.expression;

import li2.plp.expressions2.expression.Expressao;
import li2.plp.expressions2.expression.Valor;

/**
 * Marca um valor que nao pode ser reduzido.
 */
public abstract class ValorIrredutivel implements Valor {

    @Override
    public abstract Expressao clone();
}
