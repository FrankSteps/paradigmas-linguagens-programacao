# Verificando tipos com python como primeira atividade da disciplina

def Sum(a: int | float, b: int | float) -> int | float:
    return a + b
    
def Dump(m: str | int | float) -> None:
    print(m)
    

Dump(Sum(3, 7))
Dump("hello, " + "7")
