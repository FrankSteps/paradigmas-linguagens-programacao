"""
exp_logica.py - Operações lógicas com type checking rigoroso
"""

from exp_binaria import ExpBinaria
from expressao import Expressao
from tipo import Tipo, inferir_tipo


class And(ExpBinaria):
    """AND lógico: esq && dir (ambos bool)."""
    
    def avalia(self):
        esq = self.esq.avalia()
        dir = self.dir.avalia()
        
        tipo_esq = inferir_tipo(esq)
        tipo_dir = inferir_tipo(dir)
        
        if tipo_esq != Tipo.BOOL or tipo_dir != Tipo.BOOL:
            raise TypeError(
                f"AND: esperado bool && bool, "
                f"recebido {tipo_esq} && {tipo_dir}"
            )
        
        return esq and dir
    
    def tipo(self):
        return Tipo.BOOL
    
    def __str__(self):
        return f"({self.esq} && {self.dir})"


class Or(ExpBinaria):
    """OR lógico: esq || dir (ambos bool)."""
    
    def avalia(self):
        esq = self.esq.avalia()
        dir = self.dir.avalia()
        
        tipo_esq = inferir_tipo(esq)
        tipo_dir = inferir_tipo(dir)
        
        if tipo_esq != Tipo.BOOL or tipo_dir != Tipo.BOOL:
            raise TypeError(
                f"OR: esperado bool || bool, "
                f"recebido {tipo_esq} || {tipo_dir}"
            )
        
        return esq or dir
    
    def tipo(self):
        return Tipo.BOOL
    
    def __str__(self):
        return f"({self.esq} || {self.dir})"


class Not(Expressao):
    """Negação lógica: !expr (apenas bool)."""
    
    def __init__(self, expr):
        """
        Inicializa Not.
        
        Args:
            expr: Expressão que deve avaliar para bool
        """
        if not isinstance(expr, Expressao):
            raise TypeError(f"Argumento deve ser Expressão, recebido {type(expr).__name__}")
        self.expr = expr
    
    def avalia(self):
        valor = self.expr.avalia()
        
        tipo_valor = inferir_tipo(valor)
        if tipo_valor != Tipo.BOOL:
            raise TypeError(
                f"NOT: esperado bool, recebido {tipo_valor}"
            )
        
        return not valor
    
    def tipo(self):
        return Tipo.BOOL
    
    def __str__(self):
        return f"!({self.expr})"
    
    def __repr__(self):
        return f"Not({self.expr})"


class Equals(ExpBinaria):
    """Igualdade: esq == dir (mesmo tipo)."""
    
    def avalia(self):
        esq = self.esq.avalia()
        dir = self.dir.avalia()
        
        tipo_esq = inferir_tipo(esq)
        tipo_dir = inferir_tipo(dir)
        
        if tipo_esq != tipo_dir:
            raise TypeError(
                f"Equals: operandos devem ser do mesmo tipo, "
                f"recebido {tipo_esq} == {tipo_dir}"
            )
        
        return esq == dir
    
    def tipo(self):
        return Tipo.BOOL
    
    def __str__(self):
        return f"({self.esq} == {self.dir})"
