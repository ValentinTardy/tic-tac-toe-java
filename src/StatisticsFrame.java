import javax.swing.*;
import java.awt.*;

public class StatisticsFrame extends JFrame {
    private Player currentPlayer;
    private PlayerService playerService;

    public StatisticsFrame(Player player) {
        this.currentPlayer = player;
        this.playerService = new PlayerService();

        setTitle("My Statistics");
        setSize(420, 380);
        setLocationRelativeTo(null);
        setResizable(true);

        Player fresh = playerService.getPlayerById(currentPlayer.getId());
        if (fresh != null) currentPlayer = fresh;

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 30));

        JLabel lblTitle = new JLabel("MY STATISTICS", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(new Color(0, 200, 150));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        JPanel statsPanel = new JPanel(new GridLayout(5, 2, 10, 15));
        statsPanel.setBackground(new Color(30, 30, 30));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        statsPanel.add(createLabel("Player:"));
        statsPanel.add(createValue(currentPlayer.getUsername()));
        statsPanel.add(createLabel("Wins:"));
        statsPanel.add(createValue(String.valueOf(currentPlayer.getWins())));
        statsPanel.add(createLabel("Losses:"));
        statsPanel.add(createValue(String.valueOf(currentPlayer.getLosses())));
        statsPanel.add(createLabel("Draws:"));
        statsPanel.add(createValue(String.valueOf(currentPlayer.getDraws())));
        statsPanel.add(createLabel("Score:"));
        statsPanel.add(createValue(String.valueOf(currentPlayer.getScore())));

        mainPanel.add(statsPanel, BorderLayout.CENTER);

        JButton btnClose = new JButton("Close");
        btnClose.setBackground(new Color(100, 100, 100));
        btnClose.setForeground(Color.WHITE);
        btnClose.setFont(new Font("Arial", Font.BOLD, 13));
        btnClose.setFocusPainted(false);
        btnClose.setBorderPainted(false);
        btnClose.addActionListener(e -> dispose());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(30, 30, 30));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        bottomPanel.add(btnClose);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(Color.LIGHT_GRAY);
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));
        return lbl;
    }

    private JLabel createValue(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        return lbl;
    }
}