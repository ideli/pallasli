rem java命令用于运行Java程序，它会启动Java虚拟机，Java虚拟机加载相关的类，然后调用主程序main()方法。
rem -classpath ：设定要搜索的类的路径，可以是目录，jar文件，zip文件（里面都是class文件），会覆盖掉所有的CLASSPATH的设定。多个用‘;’分隔。
rem -verbose   ：输出运行时详细工作信息。
rem -jar       ：指定可执行的jar包。

 

在/test目录下运行编译后的Test程序：


set tmp_cur=%~dp0
if not "%JAVA_HOME%" == "" goto gotJava_home
set JAVA_HOME=D:\jdk7
:gotJava_home 
%JAVA_HOME%\bin\java -classpath ./classes Test