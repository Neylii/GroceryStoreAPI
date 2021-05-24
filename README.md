# GroceryStoreAPI

## Download:
**1** - Go to this link: https://github.com/Neylii/GroceryStoreAPI .

**2** - Press on clone.

**3** - Download zip.

**4** - Extract zip where you want it.

**5** - Open the project in an IDE, for example Eclipse.

**6** - It is needed to have both jboss-client.jar and jboss-javaee-all.7.0-1.0.3.Final.jar as referenced libraries.

## Connect Widfly:
**1** - Open a command promt and navigate to wildfly\bin.

**2** - Then run "standalone.bat" and leave it open.

## Connect Derby:
**1** - Open a second command promt and navigate to db-derby\bin.

**2** - Then run "NetworkServerControl.bat -p 50000 start" and leave it open.

**3** - Open yet a third command promt and navigate again to db-derby\bin.

**4** - In this command promt write "ij.bat" and then "connect 'jdbc:derby://localhost:50000/GroceryStore; create=true' ;"



## Using the API:
In your IDE, export the project as a jar and war file. One can choose a export destination for these files. 
It should be the wildfly\standalone\deployments. Or simple copy over the jar and war file into this folder 
once you have them. It is needed to change the configuration file called standalone.xml which is located 
in wildfly\standalone\configuration. Under the <datasources> add:

<datasource jndi-name="java:/GroceryStore" pool-name="GroceryStore" enabled="true" use-java-context="true">
	<connection-url>jdbc:derby://localhost:50000/GroceryStore</connection-url>
	<driver>derbyclient.jar</driver>
	<security>
		<user-name>APP</user-name>
        	<password>APP</password>
	</security>
</datasource>


Now in your IDE run Main.java to add some data into the database. 

In the TestClient.java run the desired methods to try the API. Or one can use for example Insomnia and write
http://localhost:8080/GroceryStore/webservice/products
to show all products. Or add /<number> in the end to show a specific product. Or try any other methods.


Enjoy and thank you for using our application!