@echo off

setlocal

call setupCmdLine.bat

call ant_sqlMap.bat generate
