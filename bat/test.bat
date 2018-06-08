set projectLocation=C:\Lily\TestPro\workspace\MyMavenWebTest
set suites=%*
cd %projectLocation%
mvn clean test -DsuiteXmlFile=%suites%
pause