package remoteMethodCalling;

import java.io.IOException;
import java.io.OutputStream;

public interface RequestSender {
    /**
     * send sensor data
     * @param data
     * @param os stream to receiver
     */
    void sendRequest(MethodRequest data, OutputStream os) throws IOException;
}
