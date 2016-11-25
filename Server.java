import java.net.*;
import java.text.*;
import java.util.*;
import java.io.*;

public class Server implements Runnable {


	/**
	 * @param args
	 */
	private Socket          socket    = null;
	private ServerSocket    server    = null;
	private DataInputStream streamIn  = null;
	private Thread 		    thread    = null;
	private String          timestamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());


public Server(int port)
{
	try
	{
	  System.out.println("Binding to port:" + port + InetAddress.getLocalHost() + " please wait..");
	  server = new ServerSocket(port); /*Create a Socket*/

	  InetAddress IP=InetAddress.getLocalHost();
	  System.out.println("Server Starter" );
	  System.out.println("Listening on:" + IP.getHostAddress()+":"+port );
	  start();

	  log("Connection Server at " + timestamp); /*call log function and write server connection*/
	}
	catch(IOException ioe)
	{
	  System.out.println(ioe);
	}

}

public void run()
{
	while(thread != null)
	{
	  try
	    {
		System.out.println("Waiting for Client..");

		socket = server.accept();
		System.out.println("Client accepted: " + socket);

		log("Connection Client at " + timestamp + socket); /*call log function and write client connection*/
		open();/*call open function*/

		boolean done = false;
		while(!done)
		{
		   try
		   {
		     String line = streamIn.readUTF();
		     System.out.println(line);
		     read(line); /*call read function*/
		   }
		  catch(IOException ioe)
		  {
		     done = true;
		  }
		}
              close();/*call close function*/
              log("Client Closed connection at " + timestamp); /*call log function and write clinet connection closed*/
           }
	 catch(IOException ie)
         {
	      System.out.println("Acceptance Error: " + ie);
         }
       }
}

/*start & stop function*/
public void start()
{
    if (thread == null)
    {
     thread = new Thread(this);  
     thread.start();
    }
}
public void stop()
{
    if (thread != null)
    {
     thread.interrupt();
     thread = null;
    }
}

/*open & close function*/
public void open() throws IOException
{
 streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
}
public void close() throws IOException
{
  if (socket != null)    socket.close();
  if (streamIn != null)  streamIn.close();
  System.out.println("Connection closed");
}

/*read file function*/
public void read(String line)
{
 try
 {
    FileReader reader = new FileReader(line); /*se il file non esiste va nel ramo catch*/
    System.out.println("File existing!");

    PrintWriter out = new PrintWriter("tmp/" + line); /*file di prova per output*/
    Scanner file_in = new Scanner (reader); /*metodo per scannerizzare il file*/

    /*read file and send to client*/
    while(file_in.hasNextLine())
    {
	String file_read = file_in.nextLine();
        out.println(file_read);
    }
    out.close();
    file_in.close();
    reader.close();
    System.out.println("File Sended!");

 }
 catch(IOException e)
 {
    if(line.contains(".bye"))
     {
	System.out.println("Client disconnected! ");
     }
    else
        System.out.println("File not existing!");
 }
	
}

/*log function*/
public void log(String write) throws FileNotFoundException, IOException
{
   FileWriter writer = new FileWriter("log.txt",true);
   PrintWriter buff = new PrintWriter(writer);
   List<String> arrayList = new ArrayList<String>();
   arrayList.add(write);

   for(int i=0;i<arrayList.size();i++)
   {
     buff.write("****" + arrayList.get(i) + "****\n");
     buff.close();
   }
}

/*MAIN*/
public static void main(String[] args)
{
  // TODO Auto-generated method stub
  Server server = null;
  if(args.length != 1)
     System.out.println( "wrong number of parameters e.g. java server 'port'");
  else
     server = new Server(Integer.parseInt(args[0]));
}
	
}
