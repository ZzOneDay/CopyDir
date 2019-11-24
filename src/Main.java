import javax.swing.*;

public class Main {
    public static void main(String[] args)  {
        JFrame frame = new JFrame();
        MainFrame mainFrame = new MainFrame();
        frame.add(mainFrame.getRootPanel());

        frame.setSize(500,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
