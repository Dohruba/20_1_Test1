package remoteMethodCalling;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class TCPLocal implements RequestSender {
    @Override
    public void sendRequest(MethodRequest data, OutputStream os) throws IOException {

        DataOutputStream daos = new DataOutputStream(os);
        daos.writeUTF(data.getRequest());
        os.close();
    }
}
