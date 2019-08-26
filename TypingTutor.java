package Project;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class TypingTutor extends JFrame implements ActionListener {

	JLabel lblTime;
	JLabel lblScore;
	JLabel lblWord;
	JTextField txtWord;
	JButton btnStart;
	JButton btnStop;

	Timer timer;
	int timeRemaining;
	int score;
	boolean isRunning;
	String[] words;

	public TypingTutor(String[] words) {

		this.words = words;

		GridLayout layout = new GridLayout(3, 2);

		Font font = new Font("Comic Sans MS", 1, 120);

		super.setLayout(layout);

		super.setTitle("Typing Tutor");
		// super.setSize(100, 200);

		// lblTime = new JLabel("Time", JLabel.CENTER);
		lblTime = new JLabel("Time");
		lblTime.setFont(font);
		super.add(lblTime);

		lblScore = new JLabel("Score");
		lblScore.setFont(font);
		super.add(lblScore);

		lblWord = new JLabel();
		lblWord.setFont(font);
		super.add(lblWord);

		txtWord = new JTextField();
		txtWord.setFont(font);
		super.add(txtWord);

		btnStart = new JButton("Start");
		btnStart.setFont(font);
		// test t = new test() ;
		// btnStart.addActionListener(t);
		btnStart.addActionListener(this);
		super.add(btnStart);

		btnStop = new JButton("Stop");
		btnStop.setFont(font);
		btnStop.addActionListener(this);
		super.add(btnStop);

		super.setExtendedState(MAXIMIZED_BOTH);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);

		super.setVisible(true);

		setUpGame();
	}

	private void setUpGame() {

		timer = new Timer(1000, this);
		timeRemaining = 5;
		score = 0;
		isRunning = false;

		lblTime.setText("Time: ");
		lblScore.setText("Score: ");
		lblWord.setText("");
		txtWord.setText("");
		txtWord.setEnabled(false);
		btnStart.setText("Start");
		btnStop.setText("Stop");
		btnStop.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnStart) {
			handleStart();
		} else if (e.getSource() == btnStop) {
			handleStop();
		} else {
			handleTimer();
		}
	}

	private void handleStart() {

		if (isRunning == false) {

			timer.start();

			txtWord.setEnabled(true);
			btnStart.setText("Pause");
			btnStop.setEnabled(true);

			isRunning = true;

		} else {

			timer.stop();

			txtWord.setEnabled(false);
			btnStart.setText("Start");

			isRunning = false;
		}
	}

	private void handleStop() {

		timer.stop();
		int choice = JOptionPane.showConfirmDialog(this, "Replay ?");
		if (choice == JOptionPane.YES_OPTION) {
			setUpGame();

		} else if (choice == JOptionPane.NO_OPTION) {
			super.dispose();

		} else {
			if (timeRemaining < 0) {
				setUpGame();
			} else {
				timer.start();
			}
		}
	}

	private void handleTimer() {

		timeRemaining--;

		if (lblWord.getText().equals(txtWord.getText()) && lblWord.getText().length() > 0) {
			score++;
		}

		lblScore.setText("Score: " + score);

		if (timeRemaining < 0) {
			handleStop();
			return;
		}

		lblTime.setText("Time: " + timeRemaining);

		txtWord.setText("");
		int idx = (int) (Math.random() * words.length);
		lblWord.setText(words[idx]);
	}

}
