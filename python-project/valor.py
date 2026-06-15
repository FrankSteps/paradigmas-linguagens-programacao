"""
valor.py - Classe para valores concretos (com type checking)
"""

from expressao import Expressao
from tipo import Tipo, inferir_tipo


class Valor(Expressao):
    """Um valor armazena uma constante e pode ser avaliada."""
    
    def __init__(self, valor):
        # Valida o tipo na construção
        self._tipo = inferir_tipo(valor)
        self.valor = valor
    
    def avalia(self):
        """Retorna o valor armazenado."""
        return self.valor
    
    def tipo(self):
        """Retorna o tipo do valor."""
        return self._tipo
    
    def __repr__(self):
        return f"Valor({self.valor!r})"
    
    def __str__(self):
        return str(self.valor)
