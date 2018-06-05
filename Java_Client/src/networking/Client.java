package networking;

import java.awt.SecondaryLoop;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

//IP CASA = "192.168.0.27"
// IP TEC = "172.18.243.179"
// IP APARTA = "192.168.100.22"

public class Client extends Thread {
	
	private static final String IP = "192.168.0.27";
	private static final int PORT = 10000;
	private String number;
	
	Socket socket;
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;
	private DataOutputStream outputStream;
	PrintStream printStream;

    String message;
    
    public Client() {
    	try {
    		socket = new Socket(IP, PORT);
    		printStream = new PrintStream(socket.getOutputStream());
			outputStream = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void main(String[] args) {
    	Client client = new Client();
    	client.start(); 
    	
    	Scanner scanner = new Scanner(System.in);
		while (true) {
			while (!scanner.hasNextLine()) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			String sInput = scanner.nextLine();
			
			
			if (sInput.equalsIgnoreCase("quit")) {
				break;
			}
			
			client.printStream.println(sInput);
		}
    }
    
    public void sendStringToServer(String sText) {
		try {
			outputStream.writeUTF(sText);
			outputStream.flush();			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

    @Override
    public void run() {

        try {   
            while (true) {
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                message = bufferedReader.readLine();
                
                if (message.startsWith("You"))
                {
                	number = message;
                	System.out.println("logeafo");
                }
                
                System.out.println(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
