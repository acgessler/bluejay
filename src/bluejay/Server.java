package bluejay;

import java.io.IOException;
import java.net.*;

/**
 * 
 * Main class for our multi-threaded server that allows clients to connect and assigns numeric values to 
 * server-side variables that are private to the client. 
 * 
 * The server then returns the value of these variables upon request.
 * 
 * Sample session: Client sends "a=2". Server responds by sending "OK".
 * Client sends "a". Server responds by sending "2".
 * Client sends "b". Server responds by sending error message.
 * 
 **/
public class Server {

	public static final int PORT = 1066; 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServerSocket serverSock;
		
		// stock multithreaded server logic
		try {
			serverSock = new ServerSocket(PORT);
		} catch (IOException e) { 
			//TODO error handling
			System.out.println("Unable to create server socket. (Port is likely already in use.");
			e.printStackTrace();
			return;
		}
		
		while(true) {
			Socket clientSock; 
			try {
				clientSock = serverSock.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			Thread t = new Thread(new ClientRunnable());
			t.start();
		}
		// TODO: clean way of shutting down server
		
		//serverSock.close();
	}

}
