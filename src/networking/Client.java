package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

//IP CASA = "192.168.0.27"
// IP TEC = "172.18.243.179"
// IP APARTA = "192.168.100.22"

/**
 * Clase encargada de la conexión con el servidor
 */
public class Client extends Thread {

    /**
     * IP del servidor
     */
    private String IP = "192.168.100.4";
    /**
     * Puerto del servidor
     */
	private Integer PORT = 16085;
    /**
     * Numero del jugador
     */
	private String number;
    /**
     * Lista de colores disponibles
     */
    private String[] coloresDisp;
	
    /**
         * Mensaje lista recibido del movimiento de un jugador
         */
        private String[] movimientoJugadores;
	
        /**
         * Mensaje lista de cuales son los jugadores activos
         */
        private String[] jugadoresActivos = new String[4]; 
	
    /**
     * Socket
     */
    private Socket socket;
    /**
     * InputStreamReader
     */
    private InputStreamReader inputStreamReader;
    /**
     * BufferedReader
     */
    private BufferedReader bufferedReader;
    /**
     * PrintStream
     */
	private PrintStream printStream;

    /**
     * Mensaje del servidor
     */
    private String message;
    /**
     * Matriz principal
     */
    private String[][] matrix;
    /**
     * Si esta listo 
     */
    private boolean ready = false;

    /**
     * Inicia las variables necesarias
     * @param port Puerto del Servidor
     * @param Ip IP del servidor
     */
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

    /**
     * Envia un mensaje al servidor
     * @param sText Mensaje a enviar
     */
    public void sendStringToServer(String sText) {
		this.printStream.print(sText);		
	}

    /**
     * Devuelve la matriz de juego
     * @return Matriz de juego
     */
	public synchronized String[][] msg() {
    	if (ready)
    		return matrix;
    	else
    		return null;
    }

    /**
     * Procesa el input de servidor
     * @param input Mensaje del servidor
     * @return Mensaje procesado
     */
    private String[] process_msg(String input) {
    	String[] result = input.split(",");
    	
    	if (result[0].equalsIgnoreCase("hueco")) {
    		result[0] = "5";
    	} else if (result[0].equalsIgnoreCase("turbo")) {
    		result[0] = "7";
    	}    	

    	return result;
    }

    /**
     * Devuelde l número de jugador
     * @return Número de jugador
     */
    public String get_number() {
    	return number;
    }

	    /**
     * 
     * @return Lista String del mensaje del moviento de algun jugador
     */
    public String[] obtenerMovimientoJugadores() {
        if (ready)
            return movimientoJugadores;
        else 
            return null;

    }
    
    /**
     * 
     * @return Lista String del mensaje de cuantos jugadores hay activos
     */
    public String[] obtenerJugadoresActivos() {
        return jugadoresActivos;
    }

	
	
    /**
     * Devuelve los colores disṕonibles
     * @return Colores disponibles
     */
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

    /**
     * Thread que recibe y procesa los mensajes del servidor
     */
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
                    	Map.matriz[Integer.parseInt(splitM[1])][Integer.parseInt(splitM[2])] = Integer.parseInt(splitM[0]);
                    	
                    } else if(message.startsWith("d")) {
                    	ready = true;
                    	if (split[2].equals(Main.cliente.get_number())){
                            Game.player.setHeart(false);
			    System.out.println("Me mataron :(");
                            Game.player.getHeart();
			}
                    } else if (message.startsWith("m")) {
                    	ready = true;    
                    	movimientoJugadores = split;
                    	
                    } else if (message.startsWith("g")) {
                    	System.out.println("Jugador " + split[1] + " ganó");
                    	
                    } else if (message.startsWith("p")) {
                    	System.out.println("Gané " + split[1] + " puntos"); 
                    	
                    } else if (message.startsWith("c")) {
                    	String[] colorSplit = message.split("");
                        this.coloresDisp = colorSplit;
                     
                    } 
                    else if (message.startsWith("k")) {
                    	System.out.println("Jugador " + split[1] + " ganó " + split[2] + " puntos");                   	
                    }  else if (message.startsWith("i")) {
                        ready = true;
                        this.listos = true;
                    }
                    else if(message.startsWith("e")){
                        String[] arrayt = message.split("");
                        jugadoresActivos[0] = arrayt[1];
                        jugadoresActivos[1] = arrayt[2];
                        jugadoresActivos[2] = arrayt[3];
                        jugadoresActivos[3] = arrayt[4];
			    }
			    else {
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
	    public boolean getReady()
	    {
		return listos;
	    }
	}
