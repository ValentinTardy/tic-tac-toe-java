import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private PlayerService playerService;

    public LoginFrame() {
        playerService = new PlayerService();

        setTitle("Tic-Tac-Toe - Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 30));

        JLabel lblTitle = new JLabel("TIC-TAC-TOE", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setForeground(new Color(0, 200, 150));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBackground(new Color(30, 30, 30));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setForeground(Color.WHITE);
        txtUsername = new JTextField();

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setForeground(Color.WHITE);
        txtPassword = new JPasswordField();

        btnLogin = new JButton("LOGIN");
        btnLogin.setBackground(new Color(0, 200, 150));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setFocusPainted(false);

        formPanel.add(lblUsername);
        formPanel.add(txtUsername);
        formPanel.add(lblPassword);
        formPanel.add(txtPassword);
        formPanel.add(new JLabel());
        formPanel.add(btnLogin);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText().trim();
                String password = new String(txtPassword.getPassword()).trim();

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter username and password!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Player player = playerService.login(username, password);

                if (player != null) {
                    JOptionPane.showMessageDialog(null, "Welcome, " + player.getUsername() + "!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                    MainMenuFrame menuFrame = new MainMenuFrame(player);
                    menuFrame.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        txtPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnLogin.doClick();
            }
        });
    }
}