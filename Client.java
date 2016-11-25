import java.net.*;
import java.io.*;
import java.util.*;

public class Client
{
	/**
    * @param args
    */
   private Socket                   socket    = null;
   private Scanner                  scanner   = null;
   private DataOutputStream         streamOut = null;

   public Client(String serverName, int serverPort)
   {
	  System.out.println("Establishing connection. Please wait ...");
      	try
	      {
					 socket = new Socket(serverName, serverPort);
	         System.out.println("Connected: " + socket);
	         start();

	         String line = "";
	         while (!line.equals(".bye"))
	         {
	       	 try
	            {
	       		   System.out.println("Insert file name or type in '.bye' for close the connection");
	       	       line = scanner.nextLine();
	               streamOut.writeUTF(line);
	               streamOut.flush();
	            }
	            catch(IOException ioe)
	            {
								System.out.println("Sending error: " + ioe.getMessage());
	            }
	         }
	      }
	    catch(UnknownHostException uhe)
	      {
	    	  System.out.println("Host unknown: " + uhe.getMessage());
	      }
	    catch(IOException ioe)
	      {
	    	  System.out.println("Unexpected exception: " + ioe.getMessage());
	      }
   }

   /*start function*/
   public void start() throws IOException
   {
	  scanner   = new Scanner(System.in);
    streamOut = new DataOutputStream(socket.getOutputStream());
   }

   /*stop function*/
   public void stop()
   {
		 try
      {
	     if (scanner   != null)  scanner.close();
       if (streamOut != null)  streamOut.close();
       if (socket    != null)  socket.close();
      }
      catch(IOException ioe)
      {
				System.out.println("Error closing ...");
      }
   }

	public static void main(String[] args)
	{
	// TODO Auto-generated method stub
	   {  Client client = null;
	      if (args.length != 2)
	         System.out.println("Usage: java Client host port");
		    else
		       client = new Client(args[0], Integer.parseInt(args[1]));
		}
	}

}
