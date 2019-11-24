import java.io.*;

class CopyCore {

    static void copy(File original, String targetPath) throws Exception {
        System.out.println("Копирование файла: " + original.getName() + " path: " + original.getPath());
        if (!original.isDirectory()) {
            FileInputStream fileInputStream = new FileInputStream(original.getPath());
            FileOutputStream fileOutputStream = new FileOutputStream(targetPath + "\\" + original.getName());
            fileOutputStream.write(fileInputStream.readAllBytes());
            fileOutputStream.close();
        } else {
            File dir = new File(targetPath + "//" + original.getName());
            dir.mkdir();
            File[] filesList = original.listFiles();
            for (File file : filesList) {
                copy(file, dir.getPath());
            }
        }
    }
}
