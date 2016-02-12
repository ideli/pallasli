set curpath=%~dp0
%~d0
cd %curpath%

rem junit ×¢½â²âÊÔ
java -cp lib/junit-4.10.jar;./bin/ org.junit.runner.JUnitCore  com.lyt.service.CalculatorParameterizedTest
java -cp lib/junit-4.10.jar;./bin/ org.junit.runner.JUnitCore  com.lyt.service.CalculatorTest

pause