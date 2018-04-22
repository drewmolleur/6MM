package gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuGUI extends JFrame {
	private static final long serialVersionUID = 8300757529304995200L;
	private JTextField menuText;
	private JPanel controls;
	private JButton hvAButton;
	private JButton avAButton;
	
	
	public MenuGUI() {
		super("Six Men's Morris");
		Container c = getContentPane();
		controls = new JPanel();
		controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
		hvAButton = new JButton("Human vs AI");
		hvAButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame game = new GUI();
				game.setSize(600, 700);
				game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				game.setVisible(true);
			}
		});
		hvAButton.setAlignmentX(CENTER_ALIGNMENT);
		hvAButton.setAlignmentY(CENTER_ALIGNMENT);
		avAButton = new JButton("AI vs AI");
		avAButton.setAlignmentX(CENTER_ALIGNMENT);
		
		controls.add(Box.createVerticalGlue());
		controls.add(hvAButton);
		controls.add(avAButton);
		controls.add(Box.createVerticalGlue());
		
		c.add(controls, "Center");
	}
}
