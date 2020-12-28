import java.io.File;

/** 
    ディレクトリ内のファイル一覧を取得して、ファイル名を変換していくツール
 **/
public class FileNameConverterMain {
    public static void main(String[] args) {
        String dir = args[0];
        System.out.println("dir=" + dir);
        File mainDir = new File(dir);
        File fileList[] = mainDir.listFiles();
        for (File f : fileList) {
            System.out.println("f=" + f.toString());
            if(f.isFile()) {
                String fileName = f.getName();
                if (fileName.indexOf(".") >= 0) {
                    String name = fileName.substring(0, fileName.indexOf("."));
                    String exe = fileName.substring(fileName.indexOf(".") + 1, fileName.length());
                    if (name.indexOf("_") >= 0) {
                        String befUnder = name.substring(0, name.indexOf("_"));
                        String nameEndThree = name.substring(name.indexOf("_") + 1, name.length());
                        int number = Integer.valueOf(nameEndThree);
                        if (number % 2 == 1) {
                            String newNumStr = String.format("%03d", number + 1);
                            String newFileName = befUnder + "_" + newNumStr + "a" + "." + exe;
                            System.out.println(newFileName);
                            File newFile = new File(dir + newFileName);
                            if (f.renameTo(newFile)) {
                            } else {
                            }
                        }
                    }
                }
            }
        }
    }
}

