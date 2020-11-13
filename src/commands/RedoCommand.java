package commands;

import controller.interfaces.ICommand;

public class RedoCommand implements ICommand{

	@Override
	public void run() {	CommandHistory.redo(); }

}
