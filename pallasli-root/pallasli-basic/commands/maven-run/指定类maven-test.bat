
set curpath=%~dp0
%~d0
cd %curpath%

echo clean 清理目录target/
echo resources
echo compile 编译target/classes目录
echo testResources
echo testCompile  
call mvn test -DfailIfNoTests=false
rem call mvn test -DfailIfNoTests=false可以跳过测试
call mvn test -Dtest=TestAccountCaptchaServiceImp,TestRandomGenerator
 pause
