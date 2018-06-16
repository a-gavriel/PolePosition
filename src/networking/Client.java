package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

//IP CASA = "192.168.0.27"
// IP TEC = "172.18.243.179"
// IP APARTA = "192.168.100.22"

public class Client extends Thread {	
	
	private static final String IP = "192.168.100.4";
	private static final Integer PORT = 16060;
	private String number;
	
	private Socket socket;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
	private PrintStream printStream;

    private String message;
    private String[][] matrix;
    private boolean ready = false;
	private static Scanner scanner;
    
    public Client() {
    	try {
    		matrix = new String[168][168];
    		socket = new Socket(IP, PORT);
    		printStream = new PrintStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }  
    
    public static void main(String[] args) {
    	Client client = new Client();
    	client.start(); 
    	
    	scanner = new Scanner(System.in);
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
		this.printStream.print(sText);		
	}
    
    public synchronized String[][] msg() {
    	if (matrix != null)
    		return matrix;
    	else
    		return null;
    }
    
    private String[] process_msg(String input) {
    	String[] result = input.split(",");
    	
    	if (result[0].equalsIgnoreCase("hueco")) {
    		result[0] = "5";
    	} else if (result[0].equalsIgnoreCase("turbo")) {
    		result[0] = "7";
    	}    	
    	
    	return result;
    }
    
    
    public String get_number() {
    	return number;
    }       

    @Override
    public void run() {
        try {   
        	int i = 0;
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            while (true) {     
                while(true) {
                    message = bufferedReader.readLine();
                    if (message == null) {
                    	break;						
					}                       
                    if (message.startsWith("0")) {
                    	matrix[i] = message.split("");
                    	i++;                     
                    } else if (message.startsWith("h") || message.startsWith("t")){
                    	String[] split = process_msg(message);
                    	System.out.println(split[0]);
                    	matrix[Integer.parseInt(split[1])][Integer.parseInt(split[2])] = split[0];    
                    	ready = true;
                    }                    
                    
                    if (ready) {
                    	System.out.println(matrix[1][1]);
                    }
                    //System.out.println(matrix.length);
                }             
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally  {
        	try {
				inputStreamReader.close();
				bufferedReader.close();
				socket.close();	
				Thread.currentThread().interrupt();
			} catch (IOException e) {
				e.printStackTrace();
			}	
        }        
    }
}
