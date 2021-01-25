package fileexchange;

import java.io.*;

public class FileExchanger implements Local, Remote {



    @Override
    public void sendFile(String fileName, OutputStream os) throws IOException {

        //open file
        FileInputStream fis = new FileInputStream(fileName);
        this.streamData(fis, os);
        os.close();
    }

    @Override
    public void copyFileFromRemote() throws IOException {

    }

    @Override
    public void deleteFileOnRemote() throws IOException {

    }

    @Override
    public void reciveFile(String filename, InputStream is) throws IOException {

        checkIfFileExists(filename);

        FileOutputStream fos = new FileOutputStream(filename);
        this.streamData(is, fos);

    }

    @Override
    public void deleteFile(String filename) throws FileNotFoundException {

    }

    private void streamData(InputStream is, OutputStream os) throws IOException {
        int read = 0;
        do{
            read = is.read();
            if (read != -1) {
                os.write(read);
            }
        }while (read != -1);
    }

    private String checkIfFileExists(String filename){
        String toReturn = filename;
        int i = 1;
        while (true){
            File file = new File(toReturn);
            if (new File(toReturn).exists()) {
                toReturn = toReturn +"("+i+")";
                i++;
            }else
                break;

        }
        return toReturn;
    }
}
