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
set MEM_ARGS=-Xmx64m -Xms32m

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

set LIB_HOME=../content/WEB-INF/lib

set CP=%CP%;%LIB_HOME%/commons-beanutils.jar
set CP=%CP%;%LIB_HOME%/commons-cli.jar
set CP=%CP%;%LIB_HOME%/commons-codec-1.3.jar
set CP=%CP%;%LIB_HOME%/commons-collections.jar
set CP=%CP%;%LIB_HOME%/commons-digester.jar
set CP=%CP%;%LIB_HOME%/commons-io.jar
set CP=%CP%;%LIB_HOME%/commons-lang-2.3.jar
set CP=%CP%;%LIB_HOME%/commons-logging-1.1.jar
set CP=%CP%;%LIB_HOME%/dom4j-1.6.1.jar
set CP=%CP%;%LIB_HOME%/htmlparser.jar
set CP=%CP%;%LIB_HOME%/httpclient-4.0.1.jar
set CP=%CP%;%LIB_HOME%/httpcore-4.0.1.jar
set CP=%CP%;%LIB_HOME%/httpcore-nio-4.0.1.jar
set CP=%CP%;%LIB_HOME%/httpmime-4.0.1.jar
set CP=%CP%;%LIB_HOME%/junit-4.4.jar
set CP=%CP%;%LIB_HOME%/log4j-1.2.11.jar
set CP=%CP%;%LIB_HOME%/spring.jar
set CP=%CP%;%LIB_HOME%/vicutu-lib.jar

set CP=%CP%;../content/WEB-INF/classes
set CLASSPATH=%CP%

rem ----- Run application -----------------------------------------------------
%_RUNJAVA% com.vicutu.download.BatchClientApp %1 %2 %3 %4 %5 %6 %7 %8 %9

rem ----- Restore Environment Variables ---------------------------------------
:cleanup
set CLASSPATH=%_CLASSPATH%
set _CLASSPATH=
set CP=%_CP%
set _RUNJAVA=
set _STARTJAVA=
:finish