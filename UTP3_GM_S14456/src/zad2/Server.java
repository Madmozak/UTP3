package zad2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.DefaultListModel;
import javax.swing.JTextArea;
/*
public class Server extends Thread{

    JTextArea consol;
    DefaultListModel<String> list;
    ServerSocket srv;
    ExecutorService exec;
    Map<Integer,Future<Socket>> tasks;
 
    public Server(int port, JTextArea consol,DefaultListModel<String> list){
        try {
            srv = new ServerSocket(port);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        this.consol = consol;
        this.list = list;
        exec = Executors.newCachedThreadPool();
        tasks = new HashMap<>();
        start();
    }
 
    public void run(){
        consol.append("Start Server");
        int counter = 0;
        while(true){
            try {
                tasks.put(counter, exec.submit(new Client(srv.accept(),consol,counter)));
                consol.append("Client no."+(counter)+" was connected\n");
                list.addElement("Client no."+counter);
                System.out.println(tasks);
                counter++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
    public void stopClient(int i){
        System.out.println(i);
        Future<Socket> task = tasks.get(i);
        task.cancel(true);
 
    }
 
}
*/
