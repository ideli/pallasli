set tmp_cur=%~dp0
if not "%JAVA_HOME%" == "" goto gotJava_home
set JAVA_HOME=D:\jdk7
:gotJava_home 
%JAVA_HOME%\bin\javafxpackager -createjar -appclass Test -srcdir classes -outdir archive -outfile Test.jar

%JAVA_HOME%\bin\javafxpackager\javafxpackager -deploy -appclass Test -native image -srcdir archive -outdir deploy -outfile Test