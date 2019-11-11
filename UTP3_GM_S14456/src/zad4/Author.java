/**
 *
 *  @author Grzechnik Mariusz S14456
 *
 */

package zad4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Author implements Runnable {
	 
    public BlockingQueue<String> kolejka = new ArrayBlockingQueue<String>(1024);
    private static boolean WRITER_CONTROLER = true;
    String[] stringArguments;
 
    public Author(String[] args){
        stringArguments = args;
    }
 
    public void run() {
        for(int i=0; i<stringArguments.length; i++){
            try {
                Thread.sleep(1000);
                kolejka.put(stringArguments[i]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }   
 
        }
        setTextController(false);
 
    }
 
    public static boolean getTextController() {
        return WRITER_CONTROLER;
    }
 
    public static void setTextController(boolean control) {
        WRITER_CONTROLER = control;
    }
}  