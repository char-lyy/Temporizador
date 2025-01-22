package main;

import controller.TimerManager;
import view.*;

/**
 * Simple timer project
 * @author char-lyy
 *
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MainFrame mainFrame = new MainFrame();
		
		MainPanel mainPanel = mainFrame.getMainPanel();

		TimerManager timerManager = new TimerManager(mainPanel.getCenterPanel().getTimerPanel(),
				mainPanel.getSouthPanel().getStartButton(), mainPanel.getSouthPanel().getPauseButton(),
				mainPanel.getSouthPanel().getRestartButton());
		
		timerManager.run();
	}

}
