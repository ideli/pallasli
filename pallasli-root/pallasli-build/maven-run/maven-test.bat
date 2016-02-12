
set curpath=%~dp0
%~d0
cd %curpath%

echo clean 清理目录target/
echo resources
echo compile 编译target/classes目录
echo testResources
echo testCompile  


call mvn clean test
 
 pause
