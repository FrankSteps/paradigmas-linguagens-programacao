"""
exp_binaria.py - Classe abstrata para operações binárias com type checking
"""

from abc import abstractmethod
from expressao import Expressao
from tipo import Tipo, inferir_tipo


class ExpBinaria(Expressao):
    """Classe base para operações binárias."""
    
    def __init__(self, esq, dir):
        """
        Inicializa operação binária.
        
        Args:
            esq: Expressão do operando esquerdo
            dir: Expressão do operando direito
        """
        if not isinstance(esq, Expressao):
            raise TypeError(f"Operando esquerdo deve ser Expressão, recebido {type(esq).__name__}")
        if not isinstance(dir, Expressao):
            raise TypeError(f"Operando direito deve ser Expressão, recebido {type(dir).__name__}")
        
        self.esq = esq
        self.dir = dir
    
    @abstractmethod
    def avalia(self):
        """Avalia a expressão binária."""
        pass
    
    @abstractmethod
    def tipo(self):
        """Retorna o tipo resultante."""
        pass
    
    def __repr__(self):
        return f"{self.__class__.__name__}({self.esq}, {self.dir})"
