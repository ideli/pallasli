@echo off

setlocal

call setupCmdLine.bat

call ant_mapper.bat generate
