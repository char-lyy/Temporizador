package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.Timer;

/**
 * Manage time and interaction with timer buttons and sounds.
 * 
 * @author char-lyy
 *
 */
public class TimerManager extends Thread {

	private JTextPane timerPanel;
	private JButton startButton;
	private JButton stopButton;
	private JButton restartButton;
	private Timer timer;
	private String intialTimerText;
	private Clip clip;

	public TimerManager(JTextPane timerPanel, JButton startButton, JButton stopButton, JButton restartButton) {
		super();
		this.timerPanel = timerPanel;
		this.startButton = startButton;
		this.stopButton = stopButton;
		this.restartButton = restartButton;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		timerPanel.setText(defaultIntialTime());
		startButton = setupStartButton(startButton);
		stopButton = setupPauseButton(stopButton);
		restartButton = setupRestartButton(restartButton);
	}

	/**
	 * Change the states of the home button
	 * 
	 * @param startButton
	 * @return the button configured with your action listener
	 */
	public JButton setupStartButton(JButton startButton) {
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (startButton.getText() == "Setear") {

					if (!verifyTimerPanel(timerPanel.getText())) {
						timerPanel.setText("Tiempo no valido. Ingrese nuevamente");
						timerPanel.setText(nullValueTimer());
					} else {
						timer = setupTimer();
						startButton.setText("Iniciar");

					}

				} else {
					if (startButton.getText() == "Iniciar") {
						timer.setInitialDelay(0);
						timer.start();
						startButton.setText("Detener");

					} else {
						timer.stop();
						timerPanel.setText(nullValueTimer());
						startButton.setText("Setear");
					}
				}
			}

		});
		return startButton;
	}

	/**
	 * Configures a button to reset a timer
	 * 
	 * @param restartButton
	 * @return the button configured with your action listener
	 */
	public JButton setupRestartButton(JButton restartButton) {
		restartButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				timerPanel.setText(intialTimerText);
//				timer.setInitialDelay(timer.getInitialDelay());
				timer.stop();
				timer = setupTimer();
				startButton.setText("Iniciar");
			}
		});

		return restartButton;
	}

	/**
	 * Set a button to pause a timer.
	 * 
	 * @param pauseButton
	 * @return the button configured with your action listener
	 */
	public JButton setupPauseButton(JButton pauseButton) {
		pauseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (pauseButton.getText() == "Pausar") {
					timer.stop();
					pauseButton.setText("Reanudar");
				} else if (pauseButton.getText() == "Reanudar") {
					timer.restart();
					pauseButton.setText("Pausar");
				}

			}
		});

		return pauseButton;
	}

	/**
	 * Verify that MM : SS has the corresponding time format.
	 * 
	 * @param s
	 * @return true if has the corresponding time format.
	 */
	public boolean verifyTimerPanel(String s) {

		String min = "";
		String sec = "";

		int i = 0;

		while (s.charAt(i) != ':' && s.charAt(i) != ' ') {
			min += s.charAt(i);
			i++;
		}

		while (i < s.length()) {
			if (s.charAt(i) != ':' && s.charAt(i) != ' ') {
				sec += s.charAt(i);
			}
			i++;
		}

		for (i = 0; i < min.length(); i++) {
			if (!Character.isDigit(min.charAt(i))) {
				return false;
			}
		}

		for (i = 0; i < sec.length(); i++) {
			if (!Character.isDigit(sec.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Verifies that all characters in a string are numbers
	 * 
	 * @param text
	 * @return true if all characters in the string are numbers. False otherwise.
	 */
	public static boolean isNumbers(String text) {
		// Verificar si la cadena está vacía
		if (text == null || text.length() == 0) {
			return false;
		}

		return true;
	}

	/**
	 * Creates a new instance of Timer and configures it using the JTextPane text
	 * from the timer GUI.
	 * 
	 * @return The configured timer
	 */
	public Timer setupTimer() {
		intialTimerText = timerPanel.getText();
		return new Timer(1000, new ActionListener() {
			int timeArray[] = parseIntTimeArray(timerPanel.getText());
			int min = timeArray[0];
			int sec = timeArray[1];

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				timerPanel.setText(formatTimeOutput(min) + " : " + formatTimeOutput(sec));
				if (sec == 0) {
					if (min == 0) {
						// Timer has reached "00:00"
						timer.stop();
						startButton.setText("Detener");
						makeSound();
					} else {
						sec = 60;
						min--;
					}
				}
				sec--;
			}

		});
	}

	/**
	 * Makes the sound repeatedly until the user presses the startButton when it is
	 * set to "Detener".
	 */
	public void makeSound() {
		try {

			URL soundUrl = getClass().getResource("/files/alarm.wav");

			if (soundUrl != null) {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl);

				clip = AudioSystem.getClip();
				clip.open(audioInputStream);

				clip.addLineListener(new LineListener() {
					@Override
					public void update(LineEvent event) {
						if (event.getType() == LineEvent.Type.STOP) {
							if (startButton.getText().equals("Detener")) {
								clip.setFramePosition(0);
								clip.start();
							}
						}
					}
				});

				clip.start();
			} else {
				System.out.println("No se pudo cargar el recurso de audio.");
			}
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
			ex.printStackTrace();
			System.out.println("Error al reproducir el sonido.");
		}
	}

	public void stopSound() {
		if (clip != null && clip.isRunning()) {
			clip.stop();
		}
	}

	public static String nullValueTimer() {
		return "-- : --";
	}

	public String formatTimeOutput(int number) {

		if (number > 9) {
			return number + "";
		} else {
			return "0" + number;
		}

	}

	public static String defaultIntialTime() {
		return "30 : 00";
	}

	/**
	 * Converts a string representing minutes and seconds "MM : SS" to an array of
	 * integers [MM, SS].
	 * 
	 * @param s time in format "MM : SS"
	 * @return integer array
	 */
	public static int[] parseIntTimeArray(String s) {

		int[] time = new int[2];

		String min = "";
		String sec = "";

		int i = 0;

		while (s.charAt(i) != ':' && s.charAt(i) != ' ') {

			min += s.charAt(i);

			i++;
		}

		while (i < s.length()) {

			if (s.charAt(i) != ':' && s.charAt(i) != ' ') {
				sec += s.charAt(i);
			}
			i++;
		}

		time[0] = Integer.parseInt(min);
		time[1] = Integer.parseInt(sec);

		return time;
	}

}
