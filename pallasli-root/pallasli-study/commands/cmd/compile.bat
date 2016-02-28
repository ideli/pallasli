
rem javac命令用于编译Java源文件，语法如下：
rem -sourcepath <路径>　 ：指定Java源文件的路径。
rem -d <路径>　　　　　　：指定编译生成的类文件存放目录，该目录必须存在。如果没有指定目录，生成类文件和源文件放在同一目录下。
rem -classpath <路径>　　：设定要搜索类的路径。可以是目录，jar文件，zip文件（里面都是class文件），会覆盖掉所有在CLASSPATH里面的设定。
rem -deprecation         ：输出源程序中使用了不鼓励使用（Deprecated）的API的具体位置。
rem -verbose             ：输出编译器运行中的详细工作信息。
rem  

set tmp_cur=%~dp0
if not "%JAVA_HOME%" == "" goto gotJava_home
set JAVA_HOME=D:\jdk7
:gotJava_home 
%JAVA_HOME%\bin\javac -sourcepath ./src -d ./classes -classpath ./lib   -deprecation -verbose ./src/*.java