set curpath=%~dp0
%~d0
cd %curpath%

rem main ²âÊÔ¼¯
java -cp lib/junit-4.10.jar;./bin/ junit.samples.AllTests

rem junit ×¢½â²âÊÔ
java -cp lib/junit-4.10.jar;./bin/ org.junit.runner.JUnitCore  com.lyt.service.SuiteTest

pause