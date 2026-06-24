import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private Player currentPlayer;
    private PlayerService playerService;
    private GameLogic gameLogic;
    private JButton[] buttons;
    private JLabel lblStatus;
    private boolean gameOver;

    public GameFrame(Player player) {
        this.currentPlayer = player;
        this.playerService = new PlayerService();
        this.gameLogic = new GameLogic();
        this.gameOver = false;

        setTitle("Tic-Tac-Toe - Game");
        setSize(500, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 30));

        JLabel lblTitle = new JLabel("TIC-TAC-TOE", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(new Color(0, 200, 150));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));

        lblStatus = new JLabel("Your turn! (X)", SwingConstants.CENTER);
        lblStatus.setFont(new Font("Arial", Font.PLAIN, 14));
        lblStatus.setForeground(Color.LIGHT_GRAY);

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.setBackground(new Color(30, 30, 30));
        topPanel.add(lblTitle);
        topPanel.add(lblStatus);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 8, 8));
        boardPanel.setBackground(new Color(80, 80, 80));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        buttons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 36));
            buttons[i].setBackground(new Color(40, 40, 40));
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setFocusPainted(false);
            buttons[i].setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2));
            final int index = i;
            buttons[i].addActionListener(e -> handlePlayerMove(index));
            boardPanel.add(buttons[i]);
        }
        mainPanel.add(boardPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        bottomPanel.setBackground(new Color(30, 30, 30));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));

        JButton btnRestart = new JButton("Restart");
        btnRestart.setBackground(new Color(70, 130, 180));
        btnRestart.setForeground(Color.WHITE);
        btnRestart.setFont(new Font("Arial", Font.BOLD, 13));
        btnRestart.setFocusPainted(false);
        btnRestart.setBorderPainted(false);
        btnRestart.addActionListener(e -> restartGame());

        JButton btnMenu = new JButton("Menu");
        btnMenu.setBackground(new Color(100, 100, 100));
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setFont(new Font("Arial", Font.BOLD, 13));
        btnMenu.setFocusPainted(false);
        btnMenu.setBorderPainted(false);
        btnMenu.addActionListener(e -> {
            MainMenuFrame menuFrame = new MainMenuFrame(currentPlayer);
            menuFrame.setBounds(getBounds());
            menuFrame.setExtendedState(getExtendedState());
            menuFrame.setVisible(true);
            dispose();
        });

        bottomPanel.add(btnRestart);
        bottomPanel.add(btnMenu);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void handlePlayerMove(int index) {
        if (gameOver) return;

        boolean moved = gameLogic.makeMove(index, 'X');
        if (!moved) return;

        buttons[index].setText("X");
        buttons[index].setForeground(new Color(0, 200, 150));

        if (gameLogic.checkWinner('X')) {
            finishGame("WIN");
            return;
        }

        if (gameLogic.isDraw()) {
            finishGame("DRAW");
            return;
        }

        lblStatus.setText("Computer is thinking...");

        Timer timer = new Timer(500, e -> {
            int compIndex = gameLogic.computerMove();
            buttons[compIndex].setText("O");
            buttons[compIndex].setForeground(new Color(255, 100, 100));

            if (gameLogic.checkWinner('O')) {
                finishGame("LOSE");
                return;
            }

            if (gameLogic.isDraw()) {
                finishGame("DRAW");
                return;
            }

            lblStatus.setText("Your turn! (X)");
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void finishGame(String result) {
        gameOver = true;
        playerService.updateStatistics(currentPlayer, result);

        String message;
        Color color;
        if (result.equals("WIN")) {
            message = "You WIN! +10 points";
            color = new Color(0, 200, 150);
        } else if (result.equals("LOSE")) {
            message = "You LOSE! +0 points";
            color = new Color(255, 100, 100);
        } else {
            message = "DRAW! +3 points";
            color = new Color(218, 165, 32);
        }

        lblStatus.setText(message);
        lblStatus.setForeground(color);

        JOptionPane.showMessageDialog(GameFrame.this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    private void restartGame() {
        gameLogic.resetBoard();
        gameOver = false;
        lblStatus.setText("Your turn! (X)");
        lblStatus.setForeground(Color.LIGHT_GRAY);
        for (JButton btn : buttons) {
            btn.setText("");
        }
    }
}