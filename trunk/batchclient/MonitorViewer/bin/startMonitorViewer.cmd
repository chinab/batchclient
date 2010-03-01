@echo off

rem ----- Save Environment Variables That May Change --------------------------
set _CLASSPATH=%CLASSPATH%
set _CP=%CP%

rem ----- Verify and Set Required Environment Variables -----------------------
if not "%JAVA_HOME%" == "" goto gotJava
echo You must set JAVA_HOME to point at your Java Development Kit installation
goto cleanup
:gotJava

rem ------ Set PROJECT ROOT --------

rem ----- Prepare Appropriate Java Execution Commands -------------------------
set MEM_ARGS=

if not "%OS%" == "Windows_NT" goto noTitle
set _STARTJAVA=start "tools" %JAVA_HOME%/bin/java %OPTION% %MEM_ARGS%
set _RUNJAVA=%JAVA_HOME%/bin/java %OPTION% %MEM_ARGS%
goto gotTitle

:noTitle
set _STARTJAVA=start "%JAVA_HOME%/bin/java"
set _RUNJAVA="%JAVA_HOME%/bin/java"
:gotTitle

rem ----- Set Up The Runtime Classpath ----------------------------------------
set CP=%JAVA_HOME%/lib/dt.jar;%JAVA_HOME%/lib/tools.jar;%JAVA_HOME%/jre/lib/rt.jar

set LIB_HOME=../lib

set CP=%CP%;%LIB_HOME%/idw-gpl.jar
set CP=%CP%;%LIB_HOME%/javacsv.jar
set CP=%CP%;%LIB_HOME%/jcommon-1.0.15.jar
set CP=%CP%;%LIB_HOME%/jfreechart-1.0.12.jar
set CP=%CP%;../classes
set CLASSPATH=%CP%

rem ----- Run application -----------------------------------------------------
%_RUNJAVA% com.vicutu.MonitorViewerApp %1 %2 %3 %4 %5 %6 %7 %8 %9

rem ----- Restore Environment Variables ---------------------------------------
:cleanup
set CLASSPATH=%_CLASSPATH%
set _CLASSPATH=
set CP=%_CP%
set _RUNJAVA=
set _STARTJAVA=
:finish