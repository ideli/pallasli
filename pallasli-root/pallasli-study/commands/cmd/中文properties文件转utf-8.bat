set tmp_cur=%~dp0
if not "%JAVA_HOME%" == "" goto gotJava_home
set JAVA_HOME=D:\jdk7
:gotJava_home 
%JAVA_HOME%\bin\native2ascii  %tmp_cur%/zh.properties %tmp_cur%/u.properties
