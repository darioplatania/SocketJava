# SocketJava

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
  SEND TO CLIENT
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
```
