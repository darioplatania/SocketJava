import java.net.*;
import java.io.*;

public class Server implements Runnable {

	/**
	 * @param args
	 */
	private Socket          socket   = null;
	private ServerSocket    server   = null;
	private DataInputStream streamIn = null;
	private Thread 		    thread   = null;
	
	
	public Server(int port){
		try
		{
			System.out.println("Binding to port:" + port + InetAddress.getLocalHost() + " please wait..");
			server = new ServerSocket(port); /*Create a Socket*/
			
			InetAddress IP=InetAddress.getLocalHost();
			System.out.println("Server Starter" );
			System.out.println("Listening on:" + IP.getHostAddress()+":"+port );
			start();		
			
		}catch(IOException ioe)
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
				
				open();/*call open function*/
				
				boolean done = false;
				while(!done)
				{
					try
					{
						String line = streamIn.readUTF();
						System.out.println(line);
			            done = line.equals(".bye");
					}
				catch(IOException ioe)
					{
					done = true;
					}				
				}		
		       close();/*call close function*/
		  }
		   catch(IOException ie)
        	{ 
				System.out.println("Acceptance Error: " + ie);  
			}
	    }
	}
	
	/*start & stop function*/
	public void start()
	   {  if (thread == null)
	      {  thread = new Thread(this); 
	         thread.start();
	      }
	   }
	   public void stop()
	   {  if (thread != null)
	      {  thread.interrupt(); 
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
	     System.out.println("Connection close");
	   }	
		
	/*MAIN*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server server = null;
		if(args.length != 1)
			System.out.println( "wrong number of parameters e.g. java server 'port'");
		else
			server = new Server(Integer.parseInt(args[0]));		
	}
}
