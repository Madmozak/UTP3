/**
 *
 *  @author Grzechnik Mariusz S14456
 *
 */

package zad4;


public class Writer implements Runnable {
    Author textAutor;
 
    public Writer(Author autor){
        textAutor = autor;
    }
 
    @Override
    public void run() {
        while(textAutor.getTextController() != false){
            try {
                System.out.println(textAutor.kolejka.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }    
    }
}
