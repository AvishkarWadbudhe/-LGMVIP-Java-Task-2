package tic;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeGame extends JFrame {
    private JButton[] buttons;
    private boolean isPlayerXTurn;
    
    public TicTacToeGame() {
        initialize();
    }
    
    private void initialize() {
        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));
        
        buttons = new JButton[9];
        isPlayerXTurn = true;
        
        for (int i = 0; i < 9; i++) {
            JButton button = new JButton();
            button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
            button.addActionListener(new ButtonClickListener());
            
            buttons[i] = button;
            add(button);
        }
        
        setVisible(true);
    }
    
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JButton clickedButton = (JButton) event.getSource();
            
            if (isPlayerXTurn) {
                clickedButton.setText("X");
            } else {
                clickedButton.setText("O");
            }
            
            clickedButton.setEnabled(false);
            isPlayerXTurn = !isPlayerXTurn;
            
            checkWinner();
        }
    }
    
    private void checkWinner() {
        if (checkRows() || checkColumns() || checkDiagonals()) {
            String winner = isPlayerXTurn ? "O" : "X";
            JOptionPane.showMessageDialog(this, "Player " + winner + " wins!");
            resetGame();
        } else if (checkDraw()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetGame();
        }
    }
    
    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            int row = i * 3;
            
            if (!buttons[row].getText().isEmpty() &&
                buttons[row].getText().equals(buttons[row + 1].getText()) &&
                buttons[row + 1].getText().equals(buttons[row + 2].getText())) {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (!buttons[i].getText().isEmpty() &&
                buttons[i].getText().equals(buttons[i + 3].getText()) &&
                buttons[i + 3].getText().equals(buttons[i + 6].getText())) {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean checkDiagonals() {
        if (!buttons[0].getText().isEmpty() &&
            buttons[0].getText().equals(buttons[4].getText()) &&
            buttons[4].getText().equals(buttons[8].getText())) {
            return true;
        }
        
        if (!buttons[2].getText().isEmpty() &&
            buttons[2].getText().equals(buttons[4].getText()) &&
            buttons[4].getText().equals(buttons[6].getText())) {
            return true;
        }
        
        return false;
    }
    
    private boolean checkDraw() {
        for (JButton button : buttons) {
            if (button.getText().isEmpty()) {
                return false;
            }
        }
        
        return true;
    }
    
    private void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
            button.setEnabled(true);
        }
        
        isPlayerXTurn = true;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TicTacToeGame();
            }
        });
    }
}
