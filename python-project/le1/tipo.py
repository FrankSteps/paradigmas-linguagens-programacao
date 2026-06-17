"""
tipo.py - Enum de tipos com conversão segura
"""

from enum import Enum


class Tipo(Enum):
    """Tipos de dados suportados."""
    INT = "int"
    STRING = "string"
    BOOL = "bool"
    
    def __str__(self):
        return self.value
    
    def __repr__(self):
        return f"Tipo.{self.name}"


def inferir_tipo(valor):
    """Infere o tipo de um valor Python."""
    if isinstance(valor, bool):  # bool antes de int, pois bool é subclasse de int
        return Tipo.BOOL
    elif isinstance(valor, int):
        return Tipo.INT
    elif isinstance(valor, str):
        return Tipo.STRING
    else:
        raise TypeError(f"Tipo não suportado: {type(valor).__name__}")


def validar_tipo(valor, tipo_esperado):
    """Valida se um valor corresponde ao tipo esperado."""
    tipo_real = inferir_tipo(valor)
    if tipo_real != tipo_esperado:
        raise TypeError(
            f"Tipo inválido: esperado {tipo_esperado}, "
            f"recebido {tipo_real} (valor: {valor!r})"
        )
    return valor
