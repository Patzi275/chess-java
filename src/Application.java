import com.graphics.PrincipalUI;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {
    public Application() {
        initUI();
    }

    private void initUI() {
        PrincipalUI b = new PrincipalUI();
        //add(b);
        setContentPane(b);

        setResizable(!true);

        pack();
        setTitle("PChess");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
        });
    }
}
