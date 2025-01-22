package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private MainPanel mainPanel = new MainPanel();
	
	public MainFrame() {
		
		add(mainPanel);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		setTitle("Temporizador");

		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setAlwaysOnTop(true);

		setResizable(false);

		Toolkit display001 = Toolkit.getDefaultToolkit();

		Dimension displaySize = display001.getScreenSize();

		int x = (int) (displaySize.width / 1.3);

		int y = (int) (displaySize.height / 1.3);

		int width = (int) (displaySize.width / 4.4);

		int height = (int) (displaySize.height / 5.5);

		setBounds(x, y, width, height);

	}

	public MainPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}
	
	

}
