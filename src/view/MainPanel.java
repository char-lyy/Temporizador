package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {

	private CenterPanel centerPanel = new CenterPanel();
	private SouthPanel southPanel = new SouthPanel();

	public MainPanel() {

		setLayout(new BorderLayout());

		add(centerPanel, BorderLayout.CENTER);

		add(southPanel, BorderLayout.SOUTH);

	}

	public CenterPanel getCenterPanel() {
		return centerPanel;
	}

	public void setCenterPanel(CenterPanel centerPanel) {
		this.centerPanel = centerPanel;
	}

	public SouthPanel getSouthPanel() {
		return southPanel;
	}

	public void setSouthPanel(SouthPanel southPanel) {
		this.southPanel = southPanel;
	}

}
