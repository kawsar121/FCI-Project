import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DigitalWatch extends JFrame {
    private JLabel timeLabel;
    private SimpleDateFormat timeFormat;
    private String time;

    public DigitalWatch() {
        setTitle("Digital Watch");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        timeFormat = new SimpleDateFormat("HH:mm:ss");
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 50));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        add(timeLabel);
        
        Timer timer = new Timer(1000, e -> {
            time = timeFormat.format(Calendar.getInstance().getTime());
            timeLabel.setText(time);
        });
        
        timer.start();
        
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DigitalWatch::new);
    }
}

