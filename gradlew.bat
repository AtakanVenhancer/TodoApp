@ECHO OFF
set DIR=%~dp0
set APP_HOME=%DIR:~0,-1%
set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar

IF NOT "" == "%JAVA_HOME%" (
  set JAVA_EXE=%JAVA_HOME%\bin\java.exe
) ELSE (
  set JAVA_EXE=java
)

"%JAVA_EXE%" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
