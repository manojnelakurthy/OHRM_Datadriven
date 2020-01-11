set projectlocation=G:\Practice\OHRM
cd %projectlocation%
set classpath=%projectlocation%\bin;%projectlocation%\lib\*
java org.testng.TestNG %projectlocation%\testng.xml