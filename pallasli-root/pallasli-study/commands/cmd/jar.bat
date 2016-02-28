rem 语法jar ｛ctxu｝[vfm0M] [jar-文件] [manifest-文件] [-C 目录] 文件名 ...

set tmp_cur=%~dp0
if not "%JAVA_HOME%" == "" goto gotJava_home
set JAVA_HOME=D:\jdk7
:gotJava_home 
%JAVA_HOME%\bin\jvisualvm.exe