package zad2;

import java.net.*;
import java.util.concurrent.Callable;

import javax.swing.JTextArea;

import java.io.*;
/*
public class Client implements Callable<Socket>{
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    JTextArea textArea;
    int id;
 
    String actual;
 
    public Client(Socket soc, JTextArea consol, int id){
        this.socket = soc;
        this.textArea = consol;
        this.id= id;
    }
 
    @Override
    public Socket call() throws IOException {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
 
        while(true){
 
            if(Thread.currentThread().isInterrupted()){
                textArea.append("Client no."+id+" was cancelled\n");
                break;
            }
            textArea.append("Client "+id+" send: "+in.readUTF()+"\n");
        }
 
        return socket;
    }
 
}
*/