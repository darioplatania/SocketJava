# SocketJava

### This is an example of sending files .txt via socket

#SERVER STEP
```
/*-------------------------------------------------------------------------------
	CREATE SOCKET
  BIND
  LISTEN
  ACCEPT
  RECEIVE TO CLIENT
	CLOSE SOCKET
-------------------------------------------------------------------------------*/
```

#CLIENT STEP
```
/*-------------------------------------------------------------------------------
	CREATE SOCKET
  SEND TO SERVER
	CHIUSURA SOCKET
-------------------------------------------------------------------------------*/
```

# Ready

## To clone project:

```
mkdir /path/to/your/project

git clone git@bitbucket.org:darioplatania/SocketJava.git
```
### open two terminal and type

###Server executable => open terminal go to the project folder and type
```
javac Server.java
java Server 'port_number' e.g. java Server 8080
```

### Client executable => open terminal go to the project folder socket_tcp/ClientFolder and type
```
javac Client.java
java Client 'address' 'port_number' e.g. java Client 127.0.0.1 8080

When prompted to enter the filename 'sendfile.txt' and go to the tmp folder to see the file copy
```
