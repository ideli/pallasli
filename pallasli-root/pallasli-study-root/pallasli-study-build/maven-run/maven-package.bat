set curpath=%~dp0

 
rem mvn clean package
 
rem 想让其他项目使用则需将包copy到maven仓库，调用install命令即可
mvn clean install
 
