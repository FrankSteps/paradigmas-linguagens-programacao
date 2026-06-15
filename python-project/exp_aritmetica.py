"""
exp_aritmetica.py - Operações aritméticas com type checking rigoroso
"""

from exp_binaria import ExpBinaria
from tipo import Tipo, inferir_tipo, validar_tipo


class Soma(ExpBinaria):
    """Soma: esq + dir (apenas números)."""
    
    def avalia(self):
        esq = self.esq.avalia()
        dir = self.dir.avalia()
        
        tipo_esq = inferir_tipo(esq)
        tipo_dir = inferir_tipo(dir)
        
        if tipo_esq != Tipo.INT or tipo_dir != Tipo.INT:
            raise TypeError(
                f"Soma: esperado int + int, "
                f"recebido {tipo_esq} + {tipo_dir}"
            )
        
        return esq + dir
    
    def tipo(self):
        return Tipo.INT
    
    def __str__(self):
        return f"({self.esq} + {self.dir})"


class Sub(ExpBinaria):
    """Subtração: esq - dir (apenas números)."""
    
    def avalia(self):
        esq = self.esq.avalia()
        dir = self.dir.avalia()
        
        tipo_esq = inferir_tipo(esq)
        tipo_dir = inferir_tipo(dir)
        
        if tipo_esq != Tipo.INT or tipo_dir != Tipo.INT:
            raise TypeError(
                f"Subtração: esperado int - int, "
                f"recebido {tipo_esq} - {tipo_dir}"
            )
        
        return esq - dir
    
    def tipo(self):
        return Tipo.INT
    
    def __str__(self):
        return f"({self.esq} - {self.dir})"


class Mult(ExpBinaria):
    """Multiplicação: esq * dir (apenas números)."""
    
    def avalia(self):
        esq = self.esq.avalia()
        dir = self.dir.avalia()
        
        tipo_esq = inferir_tipo(esq)
        tipo_dir = inferir_tipo(dir)
        
        if tipo_esq != Tipo.INT or tipo_dir != Tipo.INT:
            raise TypeError(
                f"Multiplicação: esperado int * int, "
                f"recebido {tipo_esq} * {tipo_dir}"
            )
        
        return esq * dir
    
    def tipo(self):
        return Tipo.INT
    
    def __str__(self):
        return f"({self.esq} * {self.dir})"


class Div(ExpBinaria):
    """Divisão inteira: esq / dir (apenas números)."""
    
    def avalia(self):
        esq = self.esq.avalia()
        dir = self.dir.avalia()
        
        tipo_esq = inferir_tipo(esq)
        tipo_dir = inferir_tipo(dir)
        
        if tipo_esq != Tipo.INT or tipo_dir != Tipo.INT:
            raise TypeError(
                f"Divisão: esperado int / int, "
                f"recebido {tipo_esq} / {tipo_dir}"
            )
        
        if dir == 0:
            raise ValueError("Divisão por zero")
        
        return esq // dir
    
    def tipo(self):
        return Tipo.INT
    
    def __str__(self):
        return f"({self.esq} / {self.dir})"
