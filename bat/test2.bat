set projectLocation=C:\Lily\TestPro\workspace\MyMavenWebTest
set tests=%*
cd %projectLocation%
mvn clean -Dtest=%tests% test
pause