package view;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextPane;


@SuppressWarnings("serial")
public class CenterPanel extends JPanel {

	private JTextPane timerPanel;

	public JTextPane getTimerPanel() {
		return timerPanel;
	}

	public void setTimerPanel(JTextPane timerPanel) {
		this.timerPanel = timerPanel;
	}

	public CenterPanel() {
		super();
		// TODO Auto-generated constructor stub
		timerPanel = new JTextPane();

//		timerPanel.setText(SouthPanel.defaultIntialTime());

		timerPanel.setBackground(getBackground());

		timerPanel.setFont(new Font("Consolas", Font.BOLD, 35));

		add(timerPanel);
	}

}