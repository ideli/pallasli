
set curpath=%~dp0
%~d0
cd %curpath%

call mvn cobertura:cobertura
 pause
