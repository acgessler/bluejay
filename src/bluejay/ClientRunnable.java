package bluejay;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Accepts string from client. Stores in a variable local to client and returns OK or error message. 
 * When client asks for the numerical value of variable, returns value.
 *  
 */

public class ClientRunnable implements Runnable {
	
	private Socket clientSocket;
	private Map<String, Integer> map;

	public ClientRunnable(Socket clientSock) {
		clientSocket = clientSock;
		map = new HashMap<String, Integer>();
		
	}

	public void run() {
		//reads data in from client socket
		BufferedReader input;
		try {
			input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			System.out.println("Failed to obtain input stream.");
			e.printStackTrace();
			
			try {
				clientSocket.close();
			} catch (IOException e1) {
				
			}
			return;
		}
		// parses data
		try {
			String line = input.readLine();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//respond to client request for value of stored variables
	}

	//creates variable and assigns parsed value to it

}
