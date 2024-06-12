import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

public class PasswordGenerator extends JFrame {

    private JTextField passwordField;
    private JSpinner lengthSpinner;
    private JCheckBox upperCaseCheckBox;
    private JCheckBox lowerCaseCheckBox;
    private JCheckBox digitsCheckBox;
    private JCheckBox specialCharsCheckBox;

    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIALCHARS = "!@#$%^&*()-_=+<>?";

    public PasswordGenerator() {
        setTitle("Password Generator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        add(new JLabel("Password Length:"), gbc);
        gbc.gridx++;
        lengthSpinner = new JSpinner(new SpinnerNumberModel(12, 4, 32, 1));
        add(lengthSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        upperCaseCheckBox = new JCheckBox("Include Uppercase Letters");
        upperCaseCheckBox.setSelected(true);
        add(upperCaseCheckBox, gbc);

        gbc.gridy++;
        lowerCaseCheckBox = new JCheckBox("Include Lowercase Letters");
        lowerCaseCheckBox.setSelected(true);
        add(lowerCaseCheckBox, gbc);

        gbc.gridy++;
        digitsCheckBox = new JCheckBox("Include Digits");
        digitsCheckBox.setSelected(true);
        add(digitsCheckBox, gbc);

        gbc.gridy++;
        specialCharsCheckBox = new JCheckBox("Include Special Characters");
        specialCharsCheckBox.setSelected(true);
        add(specialCharsCheckBox, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton generateButton = new JButton("Generate Password");
        add(generateButton, gbc);

        gbc.gridy++;
        passwordField = new JTextField(20);
        passwordField.setEditable(false);
        add(passwordField, gbc);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generatePassword();
            }
        });

        setVisible(true);
    }

    private void generatePassword() {
        int length = (int) lengthSpinner.getValue();
        StringBuilder charPool = new StringBuilder();

        if (upperCaseCheckBox.isSelected()) charPool.append(UPPERCASE);
        if (lowerCaseCheckBox.isSelected()) charPool.append(LOWERCASE);
        if (digitsCheckBox.isSelected()) charPool.append(DIGITS);
        if (specialCharsCheckBox.isSelected()) charPool.append(SPECIALCHARS);

        if (charPool.length() == 0) {
            passwordField.setText("Select at least one character set");
            return;
        }

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charPool.length());
            password.append(charPool.charAt(index));
        }

        passwordField.setText(password.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PasswordGenerator::new);
    }
}
