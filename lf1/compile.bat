@echo off
cd /d c:\Users\joaop\Downloads\lf1\lf1\plp
if not exist bin md bin
javac -d bin -sourcepath . ^
  expressions1\util\Tipo.java ^
  expressions2\declaration\DecVariavel.java ^
  expressions2\expression\*.java ^
  expressions2\memory\*.java ^
  functional1\*.java ^
  functional1\util\*.java ^
  functional1\memory\*.java ^
  functional1\declaration\*.java ^
  functional1\expression\*.java
echo Compilacao concluida!
pause
