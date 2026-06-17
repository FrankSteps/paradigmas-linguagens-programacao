"""
expressao.py - Interface abstrata base para expressões
"""

from abc import ABC, abstractmethod


class Expressao(ABC):
    """Interface para expressões."""
    
    @abstractmethod
    def avalia(self):
        """Avalia a expressão e retorna o valor."""
        pass
    
    @abstractmethod
    def tipo(self):
        """Retorna o tipo da expressão após avaliação."""
        pass
