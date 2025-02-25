@echo off
title Task CLI Application
setlocal

:: Set PATH based on the location of this script file
set SCRIPT_DIR=%~dp0
set JAVA_CLASSPATH=%SCRIPT_DIR%out\production\task-cli

:: Run Java
java -classpath "%JAVA_CLASSPATH%" TaskCliApplication %*

endlocal
