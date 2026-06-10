import sys

# Importação fictícia, já que todas outras classes do framework ainda não existem em Python
from expression import ExpSub, ExpSoma, ValorInteiro, Programa

def main():
    # Atividade 1: 4 + 12 - 3
    exp = ExpSub(
        ExpSoma(
            ValorInteiro(4),
            ValorInteiro(12)
        ),
        ValorInteiro(3)
    )

    p = Programa(exp)
    
    if p.checaTipo():
        p.executar()
    else:
        
        print("Erro de Tipo!", file=sys.stderr)


if __name__ == "__main__":
    main() # Executando a partir da main para manter em OO
    