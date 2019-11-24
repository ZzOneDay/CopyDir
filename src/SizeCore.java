import java.io.File;

class SizeCore {
    static boolean checkFiles(File original, File clone) {
        System.out.println("original " + original.getName() + " - > " + original.getPath());
        System.out.println("clone " + clone.getName() + " - >" + clone.getPath());
        return sizeFile(original) == sizeFile(clone);
    }

    private static long sizeFile(File file) {
        if (file.isDirectory()) {
            File[] director = file.listFiles();
            int size = 0;
            assert director != null; //Чтобы IDA не ругалась :)
            for (File oneFile : director) {

                if (oneFile.isDirectory()) {
                    size += sizeFile(oneFile);
                } else {
                    size += oneFile.length();
                }
            }
            return size;
        } else {
            return file.length();
        }
    }
}
