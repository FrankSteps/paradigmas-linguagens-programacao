"""
exemplo1.py - Testes e exemplos com TYPE CHECKING RIGOROSO
"""

from valor import Valor
from exp_aritmetica import Soma, Sub, Mult, Div
from exp_string import Concat, Length
from exp_logica import And, Or, Not, Equals


def teste_aritmetica():
    """Testes de operações aritméticas."""
    print("\n--- ARITMÉTICA (com type checking) ---")
    
    print(f"5 + 3 = {Soma(Valor(5), Valor(3)).avalia()}")
    print(f"10 - 4 = {Sub(Valor(10), Valor(4)).avalia()}")
    print(f"6 * 7 = {Mult(Valor(6), Valor(7)).avalia()}")
    print(f"10 / 3 = {Div(Valor(10), Valor(3)).avalia()}")
    
    # Expressão complexa
    expr = Mult(Soma(Valor(5), Valor(3)), Valor(2))
    print(f"(5 + 3) * 2 = {expr.avalia()}")
    print(f"Expressão: {expr}")


def teste_strings():
    """Testes de operações com strings."""
    print("\n--- STRINGS (com type checking) ---")
    
    print(f'"Hello" ++ "World" = {Concat(Valor("Hello"), Valor("World")).avalia()}')
    print(f'length("Python") = {Length(Valor("Python")).avalia()}')
    print(f'length("Hi") + 1 = {Soma(Length(Valor("Hi")), Valor(1)).avalia()}')


def teste_logica():
    """Testes de operações lógicas."""
    print("\n--- LÓGICA (com type checking) ---")
    
    print(f"True && False = {And(Valor(True), Valor(False)).avalia()}")
    print(f"True || False = {Or(Valor(True), Valor(False)).avalia()}")
    print(f"!True = {Not(Valor(True)).avalia()}")
    print(f"5 == 5 = {Equals(Valor(5), Valor(5)).avalia()}")
    print(f"5 == 3 = {Equals(Valor(5), Valor(3)).avalia()}")


def teste_expressoes_complexas():
    """Testes com expressões mais complexas."""
    print("\n--- EXPRESSÕES COMPLEXAS ---")
    
    expr1 = Equals(Soma(Valor(5), Valor(3)), Valor(8))
    print(f"(5 + 3) == 8 = {expr1.avalia()}")
    
    expr2 = Or(And(Valor(True), Valor(True)), Valor(False))
    print(f"(True && True) || False = {expr2.avalia()}")


def teste_erros():
    """Testes de TYPE CHECKING - deve recusar operações inválidas."""
    print("\n--- TESTES DE TYPE CHECKING (ERROS ESPERADOS) ---")
    
    # Erro 1: Somar string com inteiro
    print("\n1. Tentando somar string + int:")
    try:
        Soma(Valor("oops"), Valor(5)).avalia()
        print("  ✗ Deveria ter gerado erro!")
    except TypeError as e:
        print(f"  ✓ Erro capturado: {e}")
    
    # Erro 2: AND com inteiros (deveria ser bool)
    print("\n2. Tentando AND com inteiros:")
    try:
        And(Valor(1), Valor(2)).avalia()
        print("  ✗ Deveria ter gerado erro!")
    except TypeError as e:
        print(f"  ✓ Erro capturado: {e}")
    
    # Erro 3: Length de inteiro (deveria ser string)
    print("\n3. Tentando Length de inteiro:")
    try:
        Length(Valor(42)).avalia()
        print("  ✗ Deveria ter gerado erro!")
    except TypeError as e:
        print(f"  ✓ Erro capturado: {e}")
    
    # Erro 4: Concatenação de int com string
    print("\n4. Tentando concatenar int ++ string:")
    try:
        Concat(Valor(123), Valor("texto")).avalia()
        print("  ✗ Deveria ter gerado erro!")
    except TypeError as e:
        print(f"  ✓ Erro capturado: {e}")
    
    # Erro 5: Equals com tipos diferentes
    print("\n5. Tentando comparar int == string:")
    try:
        Equals(Valor(5), Valor("cinco")).avalia()
        print("  ✗ Deveria ter gerado erro!")
    except TypeError as e:
        print(f"  ✓ Erro capturado: {e}")
    
    # Erro 6: NOT com número (deveria ser bool)
    print("\n6. Tentando NOT de inteiro:")
    try:
        Not(Valor(42)).avalia()
        print("  ✗ Deveria ter gerado erro!")
    except TypeError as e:
        print(f"  ✓ Erro capturado: {e}")
    
    # Erro 7: Divisão por zero
    print("\n7. Tentando divisão por zero:")
    try:
        Div(Valor(10), Valor(0)).avalia()
        print("  ✗ Deveria ter gerado erro!")
    except (ValueError, ZeroDivisionError) as e:
        print(f"  ✓ Erro capturado: {e}")


def main():
    """Função principal."""
    print("\n" + "="*60)
    print("TESTE DO SISTEMA DE EXPRESSÕES - COM TYPE CHECKING RIGOROSO")
    print("="*60)
    
    teste_aritmetica()
    teste_strings()
    teste_logica()
    teste_expressoes_complexas()
    teste_erros()
    
    print("\n" + "="*60)
    print("TESTES COMPLETOS!")
    print("="*60 + "\n")


if __name__ == '__main__':
    main()

