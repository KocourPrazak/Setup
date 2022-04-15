import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    static final String INSTALLPATH = "D:\\JGame";
    static final String SRC = INSTALLPATH + "\\src";
    static final String MAIN = SRC + "\\main";
    static final String FMAIN = MAIN + "\\Main.java";
    static final String FUTILS = MAIN + "\\Utils.java";
    static final String TEST = SRC + "\\test";
    static final String RES = INSTALLPATH + "\\res";
    static final String DRAWABLES = RES + "\\drawables";
    static final String VECTORS = RES + "\\vectors";
    static final String ICONS = RES + "\\icons";
    static final String SAVEGAMES = INSTALLPATH + "\\savegames";
    static final String TEMP = INSTALLPATH + "\\temp";
    static final String LOGFILE = TEMP + "\\temp.txt";
    static final String MKDIR = "Create directory";
    static final String MKFILE = "Create file";


    public static void main(String[] args) {
        List<String> dirList = new ArrayList<>(Arrays.asList(SRC, RES, SAVEGAMES, TEMP, MAIN, TEST,
                DRAWABLES, VECTORS, ICONS));
        List<String> fileList = new ArrayList<>(Arrays.asList(FMAIN, FUTILS));
        List<String> result = new ArrayList<>();
        boolean errorPresent = false;

        System.out.println("Installation started");

//Create directories
        for (String directory :
                dirList) {
            String res;

            File dir = new File(directory);

            if (dir.exists()) {
                res = "Already exist, skipped";
            } else {
                if (dir.mkdirs()) {
                    res = "Success";
                } else {
                    res = "Filed";
                    errorPresent = true;
                }
            }
            result.add(MKDIR + " " + directory + " " + res);
        }

//Create files
        for (String file :
                fileList) {
            String res;

            File newFile = new File(file);
            if (newFile.exists()) {
                res = "Already exist, skipped";
            } else {

                try {
                    if (newFile.createNewFile()) {
                        res = "Success";
                    } else {
                        res = "Filed";
                        errorPresent = true;
                    }
                } catch (IOException ex) {
                    res = ex.getLocalizedMessage();
                    errorPresent = true;
                }
            }

            result.add(MKFILE + " " + file + " " + res);
        }

        //Create a log file

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOGFILE))) {
            for (String res :
                    result) {
                bw.write(res + System.lineSeparator());
            }
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }


        System.out.println("Installation finished" + (errorPresent ? " with errors" : " without errors"));
    }


}
