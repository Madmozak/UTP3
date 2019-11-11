package zad2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;

/*
public class Main {
	public static void main(String[] args) {

		int port = 8080;
		String hostname = "localhost";
		
		Frame frame =new Frame();
		Server server = new Server(port, hostname);
		//new Client(port, hostname);

	}

}

class Frame extends JFrame implements ActionListener{
	
	int port = 8080;
	String hostname = "localhost";
	
	public JTextArea textArea = new JTextArea(20,30);
	public JPanel listPanel = new JPanel();
	public JPanel panel = new JPanel();
	public JButton startServerButton = new JButton("startServer");
	public JButton startClientButton = new JButton("startClient");
	public JButton stopClientButton = new JButton("stopClient");
	public ListModel<Future<Integer>> listtModel = new DefaultListModel<>();
	public JList<Future<Integer>> listaZadan = new JList<Future<Integer>>(listtModel);
	public Frame() {
		
		this.add(new JScrollPane(textArea));
		
		//panel.add(startServerButton);
		startServerButton.addActionListener(this);
		
		panel.add(startClientButton);
		startClientButton.addActionListener(this);
		
		panel.add(stopClientButton);
		stopClientButton.addActionListener(this);
		
		this.add(panel, "South");
		//listaZadan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	ExecutorService executorService = Executors.newFixedThreadPool(3);
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String cmd = arg0.getActionCommand();
		
		try {
			Method metodaWatku = this.getClass().getDeclaredMethod(cmd);
			metodaWatku.invoke(this);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void startClient() {
		new Client(port, hostname);
		
		System.out.println("Client dziala");
	}
	
	public void stopClient() {
		
	}
	

}
class Server {

    public Server(int port, String localhost){
    try (ServerSocket serverSocket = new ServerSocket(port)) {

        System.out.println("Server is listening on port " + port);

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("New client connected");

            new ServerThread(socket).start();
        }

    } catch (IOException ex) {
        System.out.println("Server exception: " + ex.getMessage());
        ex.printStackTrace();
    }

}
class ServerThread extends Thread {
	private Socket socket;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
	        InputStream input = socket.getInputStream();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	
	        OutputStream output = socket.getOutputStream();
	        PrintWriter writer = new PrintWriter(output, true);
	
	        String text;
	
	        do {
	            text = reader.readLine();
	            String reverseText = new StringBuilder(text).reverse().toString();
	            writer.println("Server: " + reverseText);
	
	        } while (!text.equals("bye"));
	
	        socket.close();
	    } catch (IOException ex) {
	        System.out.println("Server exception: " + ex.getMessage());
	        ex.printStackTrace();
	    }
		}
	}
}
*/

public class Main{
	  
	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.runServer();
		}
	}
class Server extends JFrame implements ActionListener {
	
	public static Server server; 
	public ServerSocket serverSocket;
	public Future<Integer> zadanie;
    int serverPort = 8080;
    public ExecutorService executorService = Executors.newFixedThreadPool(3);        
    
    public JTextArea textArea = new JTextArea(20,30);
	public JPanel listPanel = new JPanel();
	public JPanel panel = new JPanel();
	public JButton startServerButton = new JButton("startServer");
	public JButton startClientButton = new JButton("startClient");
	public JButton stopClientButton = new JButton("stopClient");
	public JButton stopServerButton = new JButton("stopServer");
	public ListModel<Future<Integer>> listtModel = new DefaultListModel<>();
	public JList<Future<Integer>> listaZadan = new JList<Future<Integer>>(listtModel);
	
	public Server() {
		
		this.add(new JScrollPane(textArea));
		
		panel.add(stopServerButton);
		stopServerButton.addActionListener(this);
		
		panel.add(startClientButton);
		startClientButton.addActionListener(this);
		
		panel.add(stopClientButton);
		stopClientButton.addActionListener(this);
		
		this.add(panel, "South");
		//listaZadan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		String cmd = e.getActionCommand();
		Method metodaWatku;
		try {
			metodaWatku = this.getClass().getDeclaredMethod(cmd);
			metodaWatku.invoke(this);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public void startClient() throws InterruptedException {
		zadanie = executorService.submit(new Client(serverPort, "localhost"));

		System.out.println("Client dziala");
	}
	
	public void stopClient() {
		
	}
	
    void runServer() {        

        try {
            System.out.println("Starting Server");
            serverSocket = new ServerSocket(serverPort); 

            while(!serverSocket.isClosed()) {
                System.out.println("Waiting for request");
                try {
                    Socket s = serverSocket.accept();
                    System.out.println("Processing request");
                    executorService.submit(new ServerThread(s));
                } catch(IOException ioe) {
                    System.out.println("Error accepting connection");
                    ioe.printStackTrace();
                }
            }
        }catch(IOException e) {
            System.out.println("Error starting Server on "+serverPort);
            e.printStackTrace();
        }
    }

    //Call the method when you want to stop your server
    public void stopServer() {
        //Stop the executor service.
        executorService.shutdownNow();
        
        try {
            //Stop accepting requests.
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Error in server shutdown");
            e.printStackTrace();
        }
        System.out.println("Server Stop");
        System.exit(0);
    }

    class ServerThread implements Runnable {

        private Socket socket;

        public ServerThread(Socket connection) {
            this.socket = connection;
        }

        public void run() {
        	try {
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				textArea.append(in.readLine() + "\n");
				//textArea.append(zadanie.get().toString() + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            try {
                socket.close();
            }catch(IOException ioe) {
                System.out.println("Error closing client connection");
            }

        }
    }        
}




class Client implements Callable<Integer>{

    String hostname = "localhost";
    int port = 8080;
    int limit = 15;
    int suma = 0;
    PrintWriter writer;
    public Client(int port, String hostname) throws InterruptedException{

    try (Socket socket = new Socket(hostname, port)) {  
    	writer = new PrintWriter(socket.getOutputStream(), true);
    
    	for(int i = 0; i<limit; i++) {
			if(Thread.currentThread().isInterrupted()) {
				System.out.println("Watek przerwany");
			}
			suma+=i;
			writer.println("Zadanie: " + suma + '\n');
			//Thread.sleep(1000);
		}
    	
    	//Zadanie zad = new Zadanie();
    	//zad.call();
    	
    	//writer.println();
        //socket.close();

    } catch (UnknownHostException ex1) {

        System.out.println("Server not found: " + ex1.getMessage());

    } catch (IOException ex1) {

        System.out.println("I/O error: " + ex1.getMessage());
    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
}
  public class Zadanie implements Callable<Integer>{
	@Override
	public Integer call() throws Exception {
		for(int i = 0; i<limit; i++) {
			if(Thread.currentThread().isInterrupted()) {
				System.out.println("Watek przerwany");
			}
			suma+=i;
			writer.println("Zadanie: " + suma + '\n');
			//Thread.sleep(1000);
		}
		return suma;
	}
    
}
@Override
public Integer call() throws Exception {
	// TODO Auto-generated method stub
	return null;
	
	}
}


