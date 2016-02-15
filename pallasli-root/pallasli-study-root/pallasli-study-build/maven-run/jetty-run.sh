
启动jetty
./mvn jetty:run -Djetty.port=8888


启动cargo
mvn cargo:start

重新部署cargo
mvn cargo:redeploy

cargo源文件部署
<type>existing</type>

cargo远程部署
<configuration>
    <type>runtime</type>
    <properties> 
       <cargo.remote.username>admin</cargo.remote.username>
       <cargo.remote.password>admin</cargo.remote.password>
       <cargo.remote.manager.url>http://localhost:8080/manager</cargo.remote.manager.url>
    </properties>
</configuration>

