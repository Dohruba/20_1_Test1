package fileexchange;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface Remote {
    /**
     * Received content from input stream and writes it to local file
     * @param filename local file name
     * @param is content provider
     */
    void reciveFile(String filename, InputStream is) throws IOException;

    void deleteFile(String filename) throws FileNotFoundException;
}
//Dies kann eine Sache und nur eine Sache
//Es hat nichts mit tcp zu tun. Es bekommt eine Inputstream und schreibt es in eine Datei im Rechner