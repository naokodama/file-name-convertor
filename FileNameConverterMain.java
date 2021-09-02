import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/** 
    ディレクトリ内のファイル一覧を取得して、ファイル名を変換していくツール
 **/
public class FileNameConverterMain {
    static final int CHANGE_NUMBER_STR = 0;
    static final int LAST_STR_SPLITED_BY_UNDERSCORE = 1;
    
    public static void main(String[] args) {
        String dir = args[0];
        int convertMode = Integer.valueOf(args[1]);
        System.out.println("dir=" + dir);
        File mainDir = new File(dir);
        ArrayList<File> fileList = new ArrayList<File> (Arrays.asList(mainDir.listFiles()));
        convertMain(dir, fileList, convertMode);
    }

    private static void convertMain(String directoryPath, ArrayList<File> fileList, int convertMode) {
        for (File f : fileList) {
            System.out.println("f=" + f.toString());
            if(f.isFile()) {
                String fileName = f.getName();
                if (fileName.indexOf(".") >= 0) {
                    String name = fileName.substring(0, fileName.indexOf("."));
                    String exe = fileName.substring(fileName.indexOf(".") + 1, fileName.length());
                    String newFileName = getNewFileName(convertMode, name);
                    newFileName = newFileName + "." + exe;
                    System.out.println("newFileName :" + newFileName);
                    File newFile = new File(directoryPath + "\\" + newFileName);
                    if (f.renameTo(newFile)) {
                    } else {
                    }
                }
            } else {
                File directory = new File(f.toString());
                ArrayList<File> nodeFileList = new ArrayList<File> (Arrays.asList(directory.listFiles()));
                convertMain(directory.toString(), nodeFileList, convertMode);
            }
        }
    }

    private static String getNewFileName(int convertMode, String fileName) {
        String tmpFileName = fileName;
        switch (convertMode) {
        case CHANGE_NUMBER_STR:
            tmpFileName = getNewNameWithChangedNumberStr(tmpFileName);
            break;
        case LAST_STR_SPLITED_BY_UNDERSCORE:
            tmpFileName = getLastStrSplitedByUnderscore(tmpFileName);
            break;
        default:
            break;
        }
        return tmpFileName;
    }

    private static String getLastStrSplitedByUnderscore(String fileName) {
        String[] fileNameSplitedByUnderscore = fileName.split("_");
        String lastStr = fileNameSplitedByUnderscore[fileNameSplitedByUnderscore.length - 1];
        System.out.println("lastStr :" + lastStr);
        return lastStr;
    }

    private static String getNewNameWithChangedNumberStr(String fileName) {
        String newFileName = fileName;
        if (fileName.indexOf("_") >= 0) {
            String befUnder = fileName.substring(0, fileName.indexOf("_"));
            String fileNameEndThree = fileName.substring(fileName.indexOf("_") + 1, fileName.length());
            int number = Integer.valueOf(fileNameEndThree);
            if (number % 2 == 1) {
                String newNumStr = String.format("%03d", number + 1);
                newFileName = befUnder + "_" + newNumStr + "a";
                System.out.println(newFileName);
            }
        }
        return newFileName;
    }
}

