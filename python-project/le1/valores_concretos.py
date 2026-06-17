"""
valores_concretos.py - Não precisa mais!

Como Python é dinamicamente tipado, uma única classe Valor()
em tipo.py é suficiente. Arquivo mantido para compatibilidade.
"""

# Import reenviado
from valor import Valor as ValorInteiro
from valor import Valor as ValorString
from valor import Valor as ValorBooleano

__all__ = ['ValorInteiro', 'ValorString', 'ValorBooleano']
