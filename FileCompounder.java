import java.io.*;
import java.util.HashMap;

public class FileCompounder {
    private static HashMap<String, BufferedReader> files = new HashMap<>();
    private static BufferedWriter fileToSend;

    /*static {
        try {
            fileToSend = new BufferedWriter(new FileWriter("fileToSend.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    private static BufferedWriter getFileToSend(){
        if (fileToSend==null){
            try {
                fileToSend = new BufferedWriter(new FileWriter("fileToSend.txt"));
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
        return fileToSend;
    }

    public static String compoundFile(String requestedFilename) {
        try {
            BufferedReader currentReader = getReader(requestedFilename);
            String line;
            BufferedWriter fileToSend = getFileToSend();
            try {
                while ((line = currentReader.readLine()) != null) {
                    if (line.contains("execute_script")) {
                        String[] strings = line.split(" ");
                        if (strings.length > 1) {
                            //System.out.println("strings[1]: " + strings[1]);
                            compoundFile(strings[1]);
                        }
                        continue;
                    }
                    //System.out.println("In " + requestedFilename + ": " + line);
                    fileToSend.write(line + "\n");
                    fileToSend.flush();
                }
            } catch (SecurityException e) {
                //e.printStackTrace();
            }
            currentReader.close();
        } catch (IOException e) {
            try {
                fileToSend.write("Файл не найден" + "\n");
            } catch (IOException ignoreIt) {
                //ignoreIt.printStackTrace();
            }
            //System.out.println(requestedFilename + " ERROR");
            //e.printStackTrace();
            return "fileToSend.txt";
        }
        //System.out.println("REMOVE " + requestedFilename);
        files.remove(requestedFilename);
        if (files.size()==0){
            try {
                fileToSend.close();
                //fileToSend=null;
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
        return "fileToSend.txt";
    }

    private static BufferedReader getReader(String requestedFilename) throws FileNotFoundException {
        BufferedReader currentReader;
        if (files.containsKey(requestedFilename)) {
            //System.out.println(requestedFilename);
            currentReader = files.get(requestedFilename);
        } else {
            currentReader = new BufferedReader(new FileReader(requestedFilename));
            files.put(requestedFilename, currentReader);
        }
        return currentReader;
    }

    public static byte[] getBytes(String argument) throws IOException {
        InputStream in = new FileInputStream(argument);
        //System.out.println("input is open");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int i;
        while ((i=in.read())!=-1){
            out.write(i);
            //System.out.println("Байт: "+i);
        }
        File file = new File(argument);
        file.delete();
        return out.toByteArray();
    }
}
