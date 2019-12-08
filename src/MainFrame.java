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
    JPanel SettingJPanel;
    private JCheckBox checkClone;
    private JLabel titleLogCheck;
    private JCheckBox checkCorrectPath;


    MainFrame() {
        JFileChooser openFile = new JFileChooser();
        JFileChooser cloneFile = new JFileChooser();

        OpenButton.addActionListener(e -> {
            openFile.setDialogTitle("Выборерите файл или директорию для копирования");
            openFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            openFile.showOpenDialog(rootPanel);
            titlePathOriginal.setText(openFile.getSelectedFile().getPath());
        });

        OpenPathClone.addActionListener(e -> {
            cloneFile.setDialogTitle("Выборерите куда необходимо произвести копирование");
            cloneFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            cloneFile.showOpenDialog(rootPanel);
            titlePathClone.setText(cloneFile.getSelectedFile().getPath());
        });

        startWork.addActionListener(e -> {
            try {
                logString.setText("");
                titleLogCheck.setText("");
                if (!checkCorrectPath.isSelected() && !CopyCore.copyIsCorrect(openFile.getSelectedFile(), cloneFile.getSelectedFile().getPath())) {
                    throw new Exception("Конечная папка, в которую следует скопировать файлы," +
                            "\nявляется дочерней для папки, в которой они находятся." +
                            "\nДля реализации данного копирования, " +
                            "\nукажите флажок в пунке: Разрешить копирование в дочерную папку");
                }
                CopyCore copyCore = new CopyCore(openFile.getSelectedFile());
                copyCore.copy(cloneFile.getSelectedFile());
                if (checkClone.isSelected()) {
                    if (SizeCore.checkFiles(openFile.getSelectedFile(),
                            new File(cloneFile.getSelectedFile().getPath()
                                    + "\\" + openFile.getSelectedFile().getName()))) {
                        titleLogCheck.setText("Проверка выполнена успешно");
                    } else {
                        if (!checkCorrectPath.isSelected()) {
                            throw new Exception("Проверка файлов прошла не корректно!" +
                                    "\nРазмер исходника отличается от созданной копии!");
                        }
                    }
                }
                logString.setText("Копирование завершено");
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
