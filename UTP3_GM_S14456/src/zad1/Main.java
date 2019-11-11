/**
 *
 *  @author Grzechnik Mariusz S14456
 *
 */

package zad1;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;


public class Main {

	  public static void main(String[] args) {
		  
		  new ListFrame();
	  }
	}
class ListFrame extends JFrame implements ActionListener{
	Future<Integer> zadanie;
	public JTextArea textArea = new JTextArea(20,30);
	public JPanel listPanel = new JPanel();
	public JPanel panel = new JPanel();
	public JButton startButton = new JButton("Start");
	public JButton stopButton = new JButton("Stop");
	public JButton wynikButton = new JButton("Wynik");
	public JButton zabijButton = new JButton("Zabij");
	public JButton terminateButton = new JButton("Terminate");
	public ListModel<Future<Integer>> listtModel = new DefaultListModel<>();
	public JList<Future<Integer>> listaZadan = new JList<Future<Integer>>(listtModel);
	
	
	int numerWatku = 0;
	public ListFrame(){
		this.add(new JScrollPane(textArea));
		
		panel.add(startButton);
		startButton.addActionListener(this);
		
		panel.add(stopButton);
		stopButton.addActionListener(this);
		
		panel.add(wynikButton);
		wynikButton.addActionListener(this);
		
		panel.add(zabijButton);
		zabijButton.addActionListener(this);
		
		panel.add(terminateButton);
		terminateButton.addActionListener(this);
		
		this.add(listPanel, "North");
		listPanel.add(listaZadan);
		
		this.add(panel, "South");
		listaZadan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String cmd = arg0.getActionCommand();

		try {
			Method metodaWatku = this.getClass().getDeclaredMethod("watek"+ cmd);
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
	
	
	ExecutorService executorService = Executors.newFixedThreadPool(3);
	public void watekStart() {
		try {
		zadanie = executorService.submit(new Zadanie(++numerWatku, 15));
		((DefaultListModel<Future<Integer>>) listtModel).addElement(zadanie);
		textArea.append("Zadanie " + numerWatku + " zainicjowane\n");
		} catch(RejectedExecutionException e) {
			JOptionPane.showMessageDialog(null, "ExecutorService wylaczony, program nie bedzie przyjmowal nowych zadan");
			return;
		}
	}
	public void watekStop() {
		if(zadanie.isCancelled()) {
			JOptionPane.showMessageDialog(null, "Zadanie przerwane");
		} else if(zadanie.isDone()) {
			JOptionPane.showMessageDialog(null, "Zadanie ukonczone");
		}
		else {
			listaZadan.getSelectedValue().cancel(true);
			textArea.append("Zadanie "+ listaZadan.getSelectedValue() + " zatrzymane\n");
			((DefaultListModel<Future<Integer>>) listtModel).removeElement(listaZadan.getSelectedValue());
		}
	}
	public void watekWynik() {
		String msg = "";
		if(listaZadan.getSelectedValue().isDone()){
			try {
				msg = "Ukonczono. Wynik: " + listaZadan.getSelectedValue().get();
			} catch (InterruptedException | ExecutionException e) {
				msg = e.getMessage();
				e.printStackTrace();
			}
		} else {
			msg = "Zadanie w toku";
		}
		JOptionPane.showMessageDialog(null, msg);
	}
	public void watekZabij() {
		executorService.shutdown();
		JOptionPane.showMessageDialog(null,"ExecutorService wylaczony, program nie bedzie przyjmowal nowych zadan");
	}
	
	public void watekTerminate() {
		((DefaultListModel<Future<Integer>>) listtModel).clear();
		List<Runnable> awaiting = executorService.shutdownNow();
		 for (Runnable r : awaiting) {
		      textArea.append(r.getClass().getName()+'\n');
		    }
		 textArea.setText("");
	}
	public class Zadanie implements Callable<Integer>{

		int numerZadania, limit, suma;
		
		public Zadanie(int numerZadania, int limit) {
			this.numerZadania = numerZadania;
			this.limit=limit;
		}
		
		@Override
		public Integer call() throws Exception {
			
			for(int i = 0; i<limit; i++) {
				if(Thread.currentThread().isInterrupted()) {
					System.out.println("Watek przerwany");
					return null;
				}
				suma+=i;
				textArea.append("Zadanie "+ numerZadania + ": " + suma + '\n');
				Thread.sleep(1000);
			}
			return suma;
		}
	}
	

}
