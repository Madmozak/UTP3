package zad2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Future;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/*

public class Console extends JFrame implements ActionListener {
	int port = 8080;
	
	public static void main(String args[]) {
		Console con = new Console();
		
	}
	
	public JTextArea textArea = new JTextArea(20,30);
	public JPanel listPanel = new JPanel();
	public JPanel panel = new JPanel();
	public JButton startServerButton = new JButton("startServer");
	public JButton startClientButton = new JButton("startClient");
	public JButton stopClientButton = new JButton("stopClient");
	public DefaultListModel<String> listtModel = new DefaultListModel<>();
	public JList<Future<Integer>> listaZadan = new JList<Future<Integer>>();
	public int id=0;
	
	
	public Console() {
		this.add(new JScrollPane(textArea));
		
		panel.add(startServerButton);
		startServerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Server(port, textArea, listtModel);
			}
		
		});
		
		panel.add(startClientButton);
		startClientButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Server.currentThread().run();
				id++;
			}
		
		});
		
		panel.add(stopClientButton);
		stopClientButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
	
				
			}
		
		});
		panel.add(listaZadan);
		this.add(panel, "South");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
*/
