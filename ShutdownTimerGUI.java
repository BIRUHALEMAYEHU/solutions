import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class ShutdownTimerGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("PC Shutdown Timer");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        JLabel label = new JLabel("Enter time (minutes):");
        label.setBounds(20, 20, 150, 25);
        frame.add(label);
        JTextField textField = new JTextField();
        textField.setBounds(170, 20, 100, 25);
        frame.add(textField);
        JButton button = new JButton("Start Timer");
        button.setBounds(90, 60, 120, 30);
        frame.add(button);
        button.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                try {
                    int minutes = Integer.parseInt(textField.getText());
                    int seconds = minutes * 60;
                    JOptionPane.showMessageDialog(frame, "PC will shut down in " + minutes + " minute(s).");
                    new Thread(() -> {
                        try {
                            TimeUnit.SECONDS.sleep(seconds);
                            Runtime.getRuntime().exec("shutdown -s -t 0");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                        }
                    }).start();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
                }
            }
        });
        frame.setVisible(true);
    }
}
