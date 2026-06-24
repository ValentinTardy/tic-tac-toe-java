import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {
    private Player currentPlayer;
    private JButton btnStartGame;
    private JButton btnStatistics;
    private JButton btnTopScorers;
    private JButton btnExit;

    public MainMenuFrame(Player player) {
        this.currentPlayer = player;

        setTitle("Tic-Tac-Toe - Main Menu");
        setMinimumSize(new java.awt.Dimension(450, 550));
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 30));

        JLabel lblTitle = new JLabel("MAIN MENU", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(new Color(0, 200, 150));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 5, 0));

        JLabel lblWelcome = new JLabel("Welcome, " + currentPlayer.getUsername() + "!", SwingConstants.CENTER);
        lblWelcome.setForeground(Color.LIGHT_GRAY);
        lblWelcome.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(new Color(30, 30, 30));
        titlePanel.add(lblTitle);
        titlePanel.add(lblWelcome);

        mainPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel btnPanel = new JPanel(new GridLayout(4, 1, 0, 15));
        btnPanel.setBackground(new Color(30, 30, 30));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 60));

        btnStartGame = createButton("Start Game", new Color(0, 200, 150));
        btnStatistics = createButton("My Statistics", new Color(70, 130, 180));
        btnTopScorers = createButton("Top 5 Scorers", new Color(218, 165, 32));
        btnExit = createButton("Exit", new Color(180, 60, 60));

        btnPanel.add(btnStartGame);
        btnPanel.add(btnStatistics);
        btnPanel.add(btnTopScorers);
        btnPanel.add(btnExit);

        mainPanel.add(btnPanel, BorderLayout.CENTER);
        add(mainPanel);

        btnStartGame.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame(currentPlayer);
            gameFrame.setBounds(getBounds());
            gameFrame.setExtendedState(getExtendedState());
            gameFrame.setVisible(true);
            dispose();
        });

        btnStatistics.addActionListener(e -> {
            StatisticsFrame statsFrame = new StatisticsFrame(currentPlayer);
            statsFrame.setVisible(true);
        });

        btnTopScorers.addActionListener(e -> {
            TopScorersFrame topFrame = new TopScorersFrame();
            topFrame.setVisible(true);
        });

        btnExit.addActionListener(e -> System.exit(0));
    }

    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        return btn;
    }
}