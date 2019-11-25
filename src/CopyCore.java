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
            File[] filesList = original.listFiles();
            File dir = new File(targetPath + "//" + original.getName());
            dir.mkdir();
            for (File file : filesList) {
                copy(file, dir.getPath());
            }
        }
    }

    static boolean copyIsCorrect (File original, String targetPath) {
        String originalString = original.getPath();
        if (originalString.length() > targetPath.length()) {return true;}
        String targetString = targetPath.substring(0,originalString.length());
        return !originalString.equals(targetString); //Если строчки равны, то неправильное копирование
    }
}
