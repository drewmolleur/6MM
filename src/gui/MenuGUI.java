package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Server;
import static model.Server.startSender;
import static model.Server.startServer;

public class MenuGUI extends JFrame {
    
    private static final long serialVersionUID = 8300757529304995200L;
    private JTextField menuText;
    private final JPanel controls;
    private final JPanel textField;
    private JTextField address;
    private final JButton join;
    private String enemyIP;
    private JButton hvAButton;
    private JButton avAButton;

    public static Server socket;

    public MenuGUI() {

        super( "Six Men's Morris" );
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
                
                startServer();
                startSender();
                /*address.setEditable( false );
                enemyIP = address.getText();
                System.out.println( enemyIP );         // GOOD  
                System.out.println("Socket Created");  // GOOD
                try {
                    socket = new Server();
                } catch (Exception ex) {
                    Logger.getLogger(MenuGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                */
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