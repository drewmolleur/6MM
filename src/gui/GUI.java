package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import algorithms.AlphaBetaPruning;
import model.BoardState;
import model.Move;
import model.Server;

public class GUI extends JFrame {
	private static final long serialVersionUID = -514606427157467570L;
	private BoardState currentGame;
	private final Board boardPanel;
	private final JPanel controls;
	private final JButton newGameButton;
	private final JTextField maxTimeTextField;
	private final JTextField maxDepthTextField;
	private final JLabel statusLabel;
	private AlphaBetaPruning solver;
	private volatile MoveExecutorCallback moveExecutor;

	private class MoveExecutor implements MoveExecutorCallback {
		private boolean terminate = false;
		
                @Override
		public synchronized void terminate() {
			this.terminate = true;
			solver.terminateSearch();
		}
		
		@Override
		public synchronized void makeMove(Move move) {
			if (terminate) {
				return;
			}

			currentGame.makeMove(move);
			boardPanel.repaint();
			
			if (currentGame.hasCurrentPlayerLost()) {
				if (currentGame.getCurrentPlayer() == 1) {
					statusLabel.setText("You won!");
				} else {
					statusLabel.setText("You lost!");
				}
			} else if (currentGame.getCurrentPlayer() == 1) {
				statusLabel.setText("Making move...");
				
				int maxDepth = Integer.parseInt(maxDepthTextField.getText());
				int maxTime = Integer.parseInt(maxTimeTextField.getText()) * 1000;
				
				solver.setMaxDepth(maxDepth);
				solver.setMaxTime(maxTime);
				
				new Thread(() -> {
                                    Move move1 = solver.searchForBestMove();
                                    MoveExecutor.this.makeMove(move1);
                                    Server.sendMove(move1);
                                    System.out.println(move1);
                                }).start();
			} else {
				statusLabel.setText("Your move");
				boardPanel.makeMove();
			}
		}
	}
	
	private void startNewGame() {
		if (moveExecutor != null) {
			moveExecutor.terminate();
		}
		currentGame = new BoardState();
		moveExecutor = new MoveExecutor();
		boardPanel.setBoard(currentGame, moveExecutor);
		statusLabel.setText("Your move");
		int maxDepth = Integer.parseInt(maxDepthTextField.getText());
		int maxTime = Integer.parseInt(maxTimeTextField.getText()) * 1000;
		solver = new AlphaBetaPruning(currentGame, maxDepth, maxTime);
		boardPanel.makeMove();
                //currentGame.tooglePlayer();
                //Move move = solver.searchForBestMove();
                //moveExecutor.makeMove(move);
	}
	private void startNewGameToo() {
		if (moveExecutor != null) {
			moveExecutor.terminate();
		}
		currentGame = new BoardState();
		moveExecutor = new MoveExecutor();
		boardPanel.setBoard(currentGame, moveExecutor);
		statusLabel.setText("Your move");
		int maxDepth = Integer.parseInt(maxDepthTextField.getText());
		int maxTime = Integer.parseInt(maxTimeTextField.getText()) * 1000;
		solver = new AlphaBetaPruning(currentGame, maxDepth, maxTime);
		//boardPanel.makeMove();
                currentGame.tooglePlayer();
                Move move = solver.searchForBestMove();
                moveExecutor.makeMove(move);
	}
	public GUI() {
		super("Six Men's Morris");
		boardPanel = new Board();
		add(boardPanel, BorderLayout.CENTER);
		controls = new JPanel();
		controls.setLayout(new FlowLayout());
		newGameButton = new JButton("New game");
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startNewGame();
			}
		});
		controls.add(newGameButton);
		//controls.add(new JLabel("Max move time:"));
		maxTimeTextField = new JTextField(3);
		maxTimeTextField.setText("10");
		//controls.add(maxTimeTextField);
		//controls.add(new JLabel("Max searching depth:"));
		maxDepthTextField = new JTextField(3);
		maxDepthTextField.setText("5");
		//controls.add(maxDepthTextField);
		controls.add(new JLabel("Status:"));
		statusLabel = new JLabel("Your move");
		controls.add(statusLabel);
		add(controls, BorderLayout.SOUTH);
		maxDepthTextField.setVisible(false);
		maxTimeTextField.setVisible(false);
		startNewGame();
	}
	public GUI(int one) {
		super("Six Men's Morris");
		boardPanel = new Board();
		add(boardPanel, BorderLayout.CENTER);
		controls = new JPanel();
		controls.setLayout(new FlowLayout());
		newGameButton = new JButton("New game");
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startNewGame();
			}
		});
		controls.add(newGameButton);
		//controls.add(new JLabel("Max move time:"));
		maxTimeTextField = new JTextField(3);
		maxTimeTextField.setText("10");
		//controls.add(maxTimeTextField);
		//controls.add(new JLabel("Max searching depth:"));
		maxDepthTextField = new JTextField(3);
		maxDepthTextField.setText("5");
		//controls.add(maxDepthTextField);
		controls.add(new JLabel("Status:"));
		statusLabel = new JLabel("Your move");
		controls.add(statusLabel);
		add(controls, BorderLayout.SOUTH);
		maxDepthTextField.setVisible(false);
		maxTimeTextField.setVisible(false);
		startNewGameToo();
	}
	public static void main(String[] args) {
		JFrame menu = new MenuGUI();
		menu.setSize(300, 300);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setVisible(true);
	}
}
