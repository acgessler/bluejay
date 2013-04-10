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
			parseLine(line);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//respond to client request for value of stored variables
	}
	

	private void parseLine(String line) {
		//if the first word of the string is a valid variable name (case-sensitive), then 
		//could actually call line.trim to avoid the extra code below but is good 
		//exercise on writing stuff for corner cases
		for (int i=0; i< line.length(); i++) {
			if (line.charAt(i) != ' ' && line.charAt(i) != '\t' ) {
				line = line.substring(i);
				break;
			}
		}
		
		if (line.length() == 0 || line.charAt(0) == ' ') {
			sendError("Empty line. Please try again.");
			return;
		}
		
			//if the first word of string matches an existing variable, return value 
		int index =line.indexOf('=');
		if (index != -1) {
			//extract first word
			String var = line.substring(0, index).trim(); 
			
			// todo: also accept values with - or + in front of it
			
			// extract stuff on the right of the = sign
			String value = line.substring(index + 1).trim();
			for (int i = 0; i<value.length(); i++) {
				char c = value.charAt(i);
				if (Character.isDigit(c) == false) {
						sendError("Non-numeric value for variable was given.");
						return;
				}
			}
			
			//parse integer
			//Integer.p
			
			// check if valid numeric value is there
			
			// put stuff in hashmap
		}
		else {
			//look for value in hashmap to return
		}
			//if first word is followed by an = and a signed integer, then 
			// store first word as entry in hashmap
		
			//else sendError(); server returns an error message.
			//return;
		
		//else. When the first symbol is anything else, the server returns an error message.
	}

	//creates variable and assigns parsed value to it

}
