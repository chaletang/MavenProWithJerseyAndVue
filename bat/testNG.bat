set projectLocation=C:\Lily\TestPro\workspace\MyMavenWebTest
cd %projectLocation%
mvn clean test -DsuiteXmlFile=src/test/resources/TestNG-bat.xml
pause