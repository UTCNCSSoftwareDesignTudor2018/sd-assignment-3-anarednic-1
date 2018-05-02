Prerequisites for installing the application:
	- Eclipse
	- PostgreSQL

Steps of installation:
	- Create in PostgreSQL a new database
	- Open Eclipse and import the project named "Assignment3"
	- Change in the file persistence.xml the credentials of the PostgreSQL 
	user and the name of the database to connect to
	- Run Server.java
	- Run FirstFrame.java, which is the client

Steps of access:
	After running FirstFrame.java, a new frame shows up. If the user selects
Reader, a reader view will show up. If the user selects Writer, he can connect
to the application as a Writer or register a new Writer. For connecting a writer, 
it is enough to input the username and the password and click Connect. For registering 
a new writer, the user should fill in the username, the password, and the name of 
the new writer and click Register. 

Scenarios: 
	- A Reader can view a list of all published articles, select one and read it.
	- A Writer can view a list of his own articles, post, update, or delete
	an article.