import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

class CopyCore {
    File original;
    CopyCore (File original) {
        this.original = original;
    }

    void copy(File copy) {
        ArrayList<String> paths = new ArrayList<>();
        try {
            Files.walk(Paths.get(original.getPath())).skip(0).forEach(file -> paths.add(file.toString()));
            for (String path : paths) {
                System.out.println(path + "- > " + getCorrectPath(Paths.get(path).toFile(), copy));
                copyFile(Path.of(path).toFile(),copy);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getCorrectPath (File file, File copy) {
        return copy.getPath() + "\\" +
                file.getPath().substring(this.original.getPath().length() - this.original.getName().length());
    }


    private void copyFile(File file, File copy)  {
        System.out.println("Копирование файла: " + file.getName() + " path: " + file.getPath());
        if (!file.isDirectory()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file.getPath());
                FileOutputStream fileOutputStream = new FileOutputStream(getCorrectPath(file, copy));
                fileOutputStream.write(fileInputStream.readAllBytes());
                fileOutputStream.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            File dir = new File(getCorrectPath(file,copy));
            dir.mkdir();
        }
    }

    static boolean copyIsCorrect (File original, String targetPath) {
        String originalString = original.getPath();
        if (originalString.length() > targetPath.length()) {return true;}
        String targetString = targetPath.substring(0,originalString.length());
        return !originalString.equals(targetString); //Если строчки равны, то неправильное копирование
    }
}
