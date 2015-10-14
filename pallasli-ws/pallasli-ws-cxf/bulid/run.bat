@echo off
set MainPath=com.pallas.ws.startservice.DataServer

:start
if not "%JAVA_HOME%" == "" goto gotJava_home
set JAVA_HOME=D:\jdk1.6.0_16
:gotJava_home

set MEM_ARGS=-Xms128m -Xmx512m
set JAVA_OPTIONS=-Duser.timezone=Asia/Shanghai
set CLASSPATH=.\bin\;%JAVA_HOME%\jre\lib\rt.jar

setlocal enabledelayedexpansion
for %%j in (.\lib\*.jar) do (
	set CLASSPATH=!CLASSPATH!;%%j
	rem echo current jar is %%j.	
)
rem echo CLASSPATH=%CLASSPATH%

@echo on
set JAVA_HOME=D:\jdk1.6.0_16
"%JAVA_HOME%\bin\java" %MEM_ARGS% %JAVA_OPTIONS% %MainPath%

endlocal

:end
pause