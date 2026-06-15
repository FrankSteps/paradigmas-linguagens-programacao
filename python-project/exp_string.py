"""
exp_string.py - Operações com strings com type checking rigoroso
"""

from exp_binaria import ExpBinaria
from expressao import Expressao
from tipo import Tipo, inferir_tipo
from abc import abstractmethod


class Concat(ExpBinaria):
    """Concatenação: esq ++ dir (ambas strings)."""
    
    def avalia(self):
        esq = self.esq.avalia()
        dir = self.dir.avalia()
        
        tipo_esq = inferir_tipo(esq)
        tipo_dir = inferir_tipo(dir)
        
        if tipo_esq != Tipo.STRING or tipo_dir != Tipo.STRING:
            raise TypeError(
                f"Concatenação: esperado string ++ string, "
                f"recebido {tipo_esq} ++ {tipo_dir}"
            )
        
        return esq + dir
    
    def tipo(self):
        return Tipo.STRING
    
    def __str__(self):
        return f"({self.esq} ++ {self.dir})"


class Length(Expressao):
    """Comprimento de uma string: length(expr)."""
    
    def __init__(self, expr):
        """
        Inicializa Length.
        
        Args:
            expr: Expressão que deve avaliar para string
        """
        if not isinstance(expr, Expressao):
            raise TypeError(f"Argumento deve ser Expressão, recebido {type(expr).__name__}")
        self.expr = expr
    
    def avalia(self):
        valor = self.expr.avalia()
        
        tipo_valor = inferir_tipo(valor)
        if tipo_valor != Tipo.STRING:
            raise TypeError(
                f"Length: esperado string, recebido {tipo_valor}"
            )
        
        return len(valor)
    
    def tipo(self):
        return Tipo.INT
    
    def __str__(self):
        return f"length({self.expr})"
    
    def __repr__(self):
        return f"Length({self.expr})"
