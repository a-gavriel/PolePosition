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
	
	private String IP = "192.168.100.4";
	private Integer PORT = 16085;
	private String number;
        private String[] coloresDisp;
	
	private Socket socket;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
	private PrintStream printStream;

    private String message;
    private String[][] matrix;
    private boolean ready = false;
	private static Scanner scanner;
    
    public Client(Integer port, String Ip) {
    	try {
                this.PORT = port;
                this.IP = Ip;
    		matrix = new String[168][168];
    		socket = new Socket(IP, PORT);
    		printStream = new PrintStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }  
    
        
    public void sendStringToServer(String sText) {
		this.printStream.print(sText);		
	}
    
    public synchronized String[][] msg() {
    	if (ready)
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
    	
    	//System.out.println(result.toString());
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
                    String[] split = message.split(",");                    
                    
                    if (message == null) {
                    	break;						
					}   
                                        
                    if (split[0].startsWith("You")) {
                    	number = split[1];
                    	System.out.println(number);
                    	
                    } else if (message.startsWith("h") || message.startsWith("t")){
                    	ready = true;
                    	String[] splitM = process_msg(message);                    	
                    	matrix[Integer.parseInt(splitM[1])][Integer.parseInt(splitM[2])] = splitM[0];
                    	
                    } else if(message.startsWith("d")) {
                    	ready = true;
                    	System.out.println("Jugador " + split[1] + ", disparó al jugador " + split[2]); 
                    	
                    } else if (message.startsWith("m")) {
                    	ready = true;    
                    	System.out.println("Jugador " + split[1] + ", esta en la posicion " + split[2] + ", " + split[3]);
                    	
                    } else if (message.startsWith("g")) {
                    	System.out.println("Jugador " + split[1] + " ganó");
                    	
                    } else if (message.startsWith("p")) {
                    	System.out.println("Gané " + split[1] + " puntos"); 
                    	
                    } else if (message.startsWith("c")) {
                    	String[] colorSplit = message.split("");
                        this.coloresDisp = colorSplit;
                    	for (int k = 1; k < colorSplit.length; ++k)	{
                    		if (k != 0) {
                    			switch (colorSplit[k])
                    			{
                    			case "1":
                    				System.out.println("Queda el color azul");
                    				break;
                    			case "2":
                    				System.out.println("Queda el color verde");
                    				break;
                    			case "3":
                    				System.out.println("Queda el color rojo");
                    				break;
                    			case "4":
                    				System.out.println("Queda el color amarillo");
                    				break;
                    			default:
                    				System.out.println("No quedan colores");
                    			}
                    		}
                    	}
                    } 
                    else if (message.startsWith("k")) {
                    	System.out.println("Jugador " + split[1] + " ganó " + split[2] + " puntos");                   	
                    } else {
                    	matrix[i] = message.split("");
                    	i++;
                    } 
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
    public String[] getColors()
    {
        if(coloresDisp!= null){
            return coloresDisp;
        }
        else{
            String[] n = {"no"};
            return n;
        }
    }
}
