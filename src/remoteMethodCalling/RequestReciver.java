package remoteMethodCalling;

import tcp.Connection;

import java.io.IOException;
import java.io.InputStream;

public interface RequestReciver {
    /**
     * Recive data from stream and create file
     * @param
     */
    void receiveRequest(InputStream is, Connection connection) throws IOException;
}
