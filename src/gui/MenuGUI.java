package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.MyServerSocket;
import model.MyClientSocket;

public class MenuGUI extends JFrame {
	private static final long serialVersionUID = 8300757529304995200L;
	private JTextField menuText;
	private JPanel controls;
        private JPanel textField;
        private JTextField address;
        private JButton join;
        private String IPAddress;
	private JButton hvAButton;
	private JButton avAButton;
	
	private MyServerSocket server;
	private MyClientSocket client;
	
	
	public MenuGUI() {
		super("Six Men's Morris");
		Container c = getContentPane();
		controls = new JPanel();
                textField = new JPanel();
		controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
                address = new JTextField();
                textField.add(address);
                address.setText("");
                address.setEditable(false);
                address.setVisible(true);
                address.setPreferredSize(new Dimension(200,24));
                join = new JButton("join");
                
                join.addActionListener(new ActionListener() {
                	@Override
                	public void actionPerformed(ActionEvent e) {
                		address.setEditable(false);
                		IPAddress = address.getText();
                		System.out.println(IPAddress);
                		
                		try {
							server = new MyServerSocket(IPAddress);
						} catch (Exception e1) {
							System.out.println("Cannot Connect.");
						}
                		client = new MyClientSocket(IPAddress);
                	}
                });
                textField.add(join);
		hvAButton = new JButton("Human vs AI");
		hvAButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame game = new GUI();
				game.setSize(600, 700);
				game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				game.setVisible(true);
				
				hvAButton.setEnabled(false);
                avAButton.setEnabled(false);
			}
		});
		hvAButton.setAlignmentX(CENTER_ALIGNMENT);
		hvAButton.setAlignmentY(CENTER_ALIGNMENT);
		avAButton = new JButton("AI vs AI");
                avAButton.addActionListener(new ActionListener() {
                    @Override 
                    public void actionPerformed(ActionEvent e) {
                    
                        address.setEditable(true);
                        
                        hvAButton.setEnabled(false);
                        avAButton.setEnabled(false);
                    }
                });
		avAButton.setAlignmentX(CENTER_ALIGNMENT);
                
		
		controls.add(Box.createVerticalGlue());
		controls.add(hvAButton);
		controls.add(avAButton);
                //controls.add(address);
                
		controls.add(Box.createVerticalGlue());
		
		c.add(controls, "Center");
                textField.add(Box.createHorizontalGlue());
                c.add(textField, "North");
                
                
	}
}
