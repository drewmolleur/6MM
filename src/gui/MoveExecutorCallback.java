package gui;

import model.Move;

public interface MoveExecutorCallback {
	public void makeMove(Move move);
	public void terminate();
}
