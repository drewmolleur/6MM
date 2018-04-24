package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
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
	private final JPanel controls;
        private final JPanel textField;
        private JTextField address;
        private final JButton join;
        private String clientIP = null;
	private JButton hvAButton;
	private JButton avAButton;
	public MyServerSocket server;
	public MyClientSocket client;
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
                	public void actionPerformed( ActionEvent e ) {
                		address.setEditable( false );
                		clientIP = address.getText();
                		System.out.println( clientIP );                    
                                try {
                                    server = new MyServerSocket( clientIP );
                                    } catch (Exception e1) {
                                            System.out.println( "Cannot Connect to server." );
                                    }
                            try {
                                server.listen( clientIP );
                            } catch (Exception ex) {
                                System.out.println("Can't listen");
                            }
                                    try {
                                        client = new MyClientSocket(
                                                InetAddress.getByName(clientIP),
                                                52018);
                                        } catch ( Exception ex ) {
                                            System.out.println( "Cannot Connect to client." );
                                        }
                            try {
                                client.start();
                            } catch (IOException ex) {
                                System.out.println("Can't get it up");
                            }
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
		controls.add(Box.createVerticalGlue());
                textField.add(Box.createHorizontalGlue());
                c.add(controls, "Center");
                c.add(textField, "North"); 
	}
}