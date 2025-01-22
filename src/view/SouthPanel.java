package view;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SouthPanel extends JPanel {

	private JButton startButton = new JButton("Setear");
	private JButton pauseButton = new JButton("Pausar");
	private JButton restartButton = new JButton("Reiniciar");

	public SouthPanel() {

		super();
		// TODO Auto-generated constructor stub

		add(startButton, BorderLayout.SOUTH);

		add(pauseButton, BorderLayout.SOUTH);

		add(restartButton, BorderLayout.SOUTH);

	}

	public JButton getStartButton() {
		return startButton;
	}

	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}

	public JButton getPauseButton() {
		return pauseButton;
	}

	public void setPauseButton(JButton pauseButton) {
		this.pauseButton = pauseButton;
	}

	public JButton getRestartButton() {
		return restartButton;
	}

	public void setRestartButton(JButton restartButton) {
		this.restartButton = restartButton;
	}

}