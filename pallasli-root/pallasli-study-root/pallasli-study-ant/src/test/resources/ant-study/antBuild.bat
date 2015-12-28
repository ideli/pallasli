
set path=D:\apache-ant-1.9.3\bin;%path%
 
set curpath=%~dp0
%~d0
cd %curpath%

call ant

pause