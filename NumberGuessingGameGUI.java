package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame implements ActionListener {

    private int numberToGuess;
    private int maxTries = 5;
    private int attempts = 0;

    private JTextField inputField;
    private JLabel messageLabel;
    private JLabel headingLabel;
    private JButton guessButton;
    private JButton restartButton;

    public NumberGuessingGameGUI() {
        // Random number to guess
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;

        // Frame setup
        setTitle("🎲 Number Guessing Game");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 250, 255)); // light background

        // NORTH panel - heading
        headingLabel = new JLabel("🔢 Guess a number between 1 and 100", JLabel.CENTER);
        headingLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        headingLabel.setForeground(new Color(30, 30, 30));
        headingLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(headingLabel, BorderLayout.NORTH);

        // CENTER panel - input and buttons
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        centerPanel.setBackground(new Color(245, 250, 255));

        inputField = new JTextField();
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        centerPanel.add(inputField);

        guessButton = new JButton("🎯 Guess");
        guessButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        guessButton.setBackground(new Color(0, 123, 255));
        guessButton.setForeground(Color.WHITE);
        guessButton.addActionListener(this);
        centerPanel.add(guessButton);

        restartButton = new JButton("🔄 Restart Game");
        restartButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        restartButton.setBackground(new Color(40, 167, 69));
        restartButton.setForeground(Color.WHITE);
        restartButton.setVisible(false);
        restartButton.addActionListener(e -> restartGame());
        centerPanel.add(restartButton);

        add(centerPanel, BorderLayout.CENTER);

        // SOUTH panel - message label
        messageLabel = new JLabel("🎮 You have 5 attempts. Good luck!", JLabel.CENTER);
        messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        messageLabel.setForeground(new Color(50, 50, 50));
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(messageLabel, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // center the window
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = inputField.getText();
        try {
            int guess = Integer.parseInt(input);

            if (guess < 1 || guess > 100) {
                showMessage("⚠️ Enter a number between 1 and 100!", Color.RED);
                return;
            }

            attempts++;

            if (guess < numberToGuess) {
                showMessage("⬇ Too Low! Attempts left: " + (maxTries - attempts), Color.ORANGE);
            } else if (guess > numberToGuess) {
                showMessage("⬆ Too High! Attempts left: " + (maxTries - attempts), Color.ORANGE);
            } else {
                showMessage("✅ Correct! You guessed it in " + attempts + " attempts!", new Color(0, 150, 0));
                guessButton.setEnabled(false);
                restartButton.setVisible(true);
                return;
            }

            if (attempts >= maxTries) {
                showMessage("❌ Game Over! Correct number was: " + numberToGuess, Color.RED);
                guessButton.setEnabled(false);
                restartButton.setVisible(true);
            }

        } catch (NumberFormatException ex) {
            showMessage("⚠️ Please enter a valid number!", Color.RED);
        }

        inputField.setText("");
    }

    private void showMessage(String text, Color color) {
        messageLabel.setText(text);
        messageLabel.setForeground(color);
    }

    private void restartGame() {
        numberToGuess = new Random().nextInt(100) + 1;
        attempts = 0;
        inputField.setText("");
        guessButton.setEnabled(true);
        restartButton.setVisible(false);
        showMessage("🎮 You have 5 attempts. Good luck!", new Color(50, 50, 50));
    }

    public static void main(String[] args) {
        new NumberGuessingGameGUI();
    }
}
