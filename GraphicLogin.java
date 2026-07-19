import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.awt.event.KeyEvent;  // Caps Lock ke liye

public class GraphicLogin {
    private static void logActivity(String logText) {
        try (FileWriter fw = new FileWriter("SecretLog.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(logText);
        } catch (IOException e) {
            System.out.println("Log error");
        }
    }

    public static void main(String[] args) {
        HashMap<String, String> agentDatabase = new HashMap<>();
        agentDatabase.put("neo", "matrix2027");
        agentDatabase.put("ghost", "phantomX");

        JFrame frame = new JFrame("MAINFRAME TERMINAL v7.0");
        frame.setSize(440, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(null);

        Font hackerFont = new Font("Courier New", Font.BOLD, 14);
        Color neonGreen = new Color(57, 255, 20);

        JLabel statusTicker = new JLabel("[SYSTEM] Mainframe Online. Awaiting Connection...");
        statusTicker.setFont(new Font("Courier New", Font.ITALIC, 11));
        statusTicker.setForeground(Color.YELLOW);
        statusTicker.setBounds(20, 10, 400, 20);
        frame.add(statusTicker);

        JLabel userLabel = new JLabel("ENTER CODENAME:");
        userLabel.setFont(hackerFont);
        userLabel.setForeground(neonGreen);
        userLabel.setBounds(30, 60, 150, 25);
        frame.add(userLabel);

        JTextField userText = new JTextField();
        userText.setBackground(Color.DARK_GRAY);
        userText.setForeground(Color.WHITE);
        userText.setCaretColor(neonGreen);
        userText.setBounds(190, 60, 160, 25);
        frame.add(userText);

        JLabel passLabel = new JLabel("DECRYPTION KEY:");
        passLabel.setFont(hackerFont);
        passLabel.setForeground(neonGreen);
        passLabel.setBounds(30, 110, 150, 25);
        frame.add(passLabel);

        JPasswordField passText = new JPasswordField();
        passText.setBackground(Color.DARK_GRAY);
        passText.setForeground(Color.WHITE);
        passText.setCaretColor(neonGreen);
        frame.add(passText);
        passText.setBounds(190, 110, 160, 25);

        JCheckBox showPassword = new JCheckBox("Show Key");
        showPassword.setFont(new Font("Courier New", Font.BOLD, 10));
        showPassword.setBackground(Color.BLACK);
        showPassword.setForeground(neonGreen);
        showPassword.setBounds(190, 140, 100, 20);
        frame.add(showPassword);

        showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                passText.setEchoChar((char) 0);
            } else {
                passText.setEchoChar('*');
            }
        });

        JButton loginButton = new JButton("BYPASS FIREWALL");
        loginButton.setFont(new Font("Courier New", Font.BOLD, 11));
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(neonGreen);
        loginButton.setBorder(BorderFactory.createLineBorder(neonGreen, 2));
        loginButton.setBounds(30, 200, 150, 35);
        frame.add(loginButton);

        JButton regButton = new JButton("INJECT PROFILE");
        regButton.setFont(new Font("Courier New", Font.BOLD, 11));
        regButton.setBackground(Color.BLACK);
        regButton.setForeground(Color.CYAN);
        regButton.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
        regButton.setBounds(210, 200, 140, 35);
        frame.add(regButton);

        JButton clearButton = new JButton("RESET GRID");
        clearButton.setFont(new Font("Courier New", Font.BOLD, 11));
        clearButton.setBackground(Color.BLACK);
        clearButton.setForeground(Color.LIGHT_GRAY);
        clearButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        clearButton.setBounds(30, 260, 320, 30);
        frame.add(clearButton);

        logActivity("[SYSTEM STARTED] Mainframe initialized successfully.");

        // --- LOGIN ACTION WITH TIMER & CAPS LOCK DETECTION ---
        loginButton.addActionListener(new ActionListener() {
            int attempts = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isCapsOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                if(isCapsOn) {
                    statusTicker.setText("[WARNING] CAPS LOCK IS ON");
                    statusTicker.setForeground(Color.ORANGE);
                }
                
                if (attempts >= 3) {
                    frame.getContentPane().setBackground(new Color(150, 0, 0));
                    statusTicker.setText("[CRITICAL] MAIN FRAME TERMINATED.");
                    statusTicker.setForeground(Color.WHITE);
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(frame, "TERMINAL LOCKED!", "SECURITY EXCEPTION", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                statusTicker.setText("[PROCESSING] Decrypting firewall layers...");
                statusTicker.setForeground(Color.YELLOW);
                loginButton.setEnabled(false);
                
                Timer timer = new Timer(1500, evt -> {
                    String inputUser = userText.getText().trim();
                    String inputPass = new String(passText.getPassword()).trim();

                    if (agentDatabase.containsKey(inputUser) && agentDatabase.get(inputUser).equals(inputPass)) {
                        frame.getContentPane().setBackground(new Color(0, 100, 0));
                        statusTicker.setText("[SUCCESS] Access Granted for: " + inputUser);
                        statusTicker.setForeground(neonGreen);
                        JOptionPane.showMessageDialog(frame, "Access Granted, Agent " + inputUser, "DECRYPTION SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                        logActivity("[LOGIN SUCCESS] Agent '" + inputUser + "' successfully bypassed the firewall.");
                        attempts = 0;
                    } else {
                        attempts++;
                        int remaining = 3 - attempts;
                        frame.getContentPane().setBackground(new Color(80, 0, 0));
                        Toolkit.getDefaultToolkit().beep();
                        logActivity("[LOGIN FAILED] Invalid attempt on codename: " + inputUser + " (Attempt " + attempts + ")");
                        
                        if (remaining > 0) {
                            statusTicker.setText("[WARNING] Access Denied. Remainder: " + remaining);
                            statusTicker.setForeground(Color.RED);
                        } else {
                            frame.getContentPane().setBackground(new Color(150, 0, 0));
                            statusTicker.setText("[CRITICAL] System Lockout initiated.");
                            statusTicker.setForeground(Color.WHITE);
                            logActivity("[SECURITY LOCKOUT] Terminal blocked due to 3 failed breaches.");
                        }
                    }
                    loginButton.setEnabled(true);
                });
                timer.setRepeats(false);
                timer.start();
            }
        });

        // --- REGISTER ACTION ---
        regButton.addActionListener(e -> {
            String newUser = userText.getText().trim();
            String newPass = new String(passText.getPassword()).trim();

            if (newUser.isEmpty() || newPass.isEmpty()) {
                statusTicker.setText("[ALERT] Fields empty!");
                statusTicker.setForeground(Color.ORANGE);
                Toolkit.getDefaultToolkit().beep();
                return;
            }

            if(newPass.length() < 6) {
                statusTicker.setText("[ALERT] Key too weak! Min 6 chars required.");
                statusTicker.setForeground(Color.ORANGE);
                Toolkit.getDefaultToolkit().beep();
                return;
            }

            if (agentDatabase.containsKey(newUser)) {
                statusTicker.setText("[ERROR] Profile collision.");
                statusTicker.setForeground(Color.RED);
                Toolkit.getDefaultToolkit().beep();
                logActivity("[REGISTRATION FAILED] Attempted reuse of codename: " + newUser);
            } else {
                agentDatabase.put(newUser, newPass);
                statusTicker.setText("[UPDATE] Agent '" + newUser + "' successfully added.");
                statusTicker.setForeground(Color.CYAN);
                logActivity("[REGISTRATION SUCCESS] New agent registered: " + newUser);
            }
        });

        // --- CLEAR ACTION ---
        clearButton.addActionListener(e -> {
            userText.setText("");
            passText.setText("");
            showPassword.setSelected(false);
            passText.setEchoChar('*');
            statusTicker.setText("[RESET] Terminal inputs flushed.");
            statusTicker.setForeground(Color.YELLOW);
            frame.getContentPane().setBackground(Color.BLACK);
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}