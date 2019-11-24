import javax.swing.*;
import java.io.File;

public class MainFrame {
    private JPanel rootPanel;
    JLabel title;
    JPanel OpenFileJPanel;
    JLabel titleOpenFile;
    JPanel LogJPanel;
    private JLabel logString;
    private JTextField titlePathOriginal;
    private JButton OpenButton;
    JLabel titlePleaseOpenFile;
    JPanel CloneJPanel;
    JLabel titleClone;
    private JTextField titlePathClone;
    JLabel titlePleaseClone;
    private JButton OpenPathClone;
    private JButton startWork;


    MainFrame() {
        JFileChooser openFile = new JFileChooser();
        JFileChooser cloneFile = new JFileChooser();

        OpenButton.addActionListener(e -> {
            openFile.setDialogTitle("Выбор файл или директорию");
            openFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            openFile.showOpenDialog(rootPanel);
            titlePathOriginal.setText(openFile.getSelectedFile().getPath());
        });

        OpenPathClone.addActionListener(e -> {
            cloneFile.setDialogTitle("Выбор директории");
            cloneFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            cloneFile.showOpenDialog(rootPanel);
            titlePathClone.setText(cloneFile.getSelectedFile().getPath());
        });

        startWork.addActionListener(e -> {
            try {
                CopyCore.copy(openFile.getSelectedFile(),cloneFile.getSelectedFile().getPath());
                if (SizeCore.checkFiles(openFile.getSelectedFile(),
                        new File(cloneFile.getSelectedFile().getPath() + "\\"+ openFile.getSelectedFile().getName()))) {
                    logString.setText("Копирование прошло успешно");
                }
                else
                { JOptionPane.showMessageDialog(rootPanel,
                            "Ошибка при проверке файлов");
                 logString.setText("Внимание! Не совпадет размер исходного материала с копированным");
                }


            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPanel,
                        "Произошла ошибка: " + ex.getLocalizedMessage());
            }
        });
    }

    JPanel getRootPanel() {
        return rootPanel;
    }
}
