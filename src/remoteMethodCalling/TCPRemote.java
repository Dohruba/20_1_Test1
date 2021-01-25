package remoteMethodCalling;

import fileexchange.FileExchanger;
import tcp.Connection;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TCPRemote implements RequestReciver {
    @Override
    public void receiveRequest(InputStream is, Connection connection) throws IOException {
        FileExchanger remote = new FileExchanger();
        DataInputStream dais = new DataInputStream(is);

        String[] request = dais.readUTF().split(" ");

        System.out.println(request);

        if (request.length == 2) {
            String action = request[0];
            String filename = request[1];

            switch (action) {
                case "Delete":
                    remote.deleteFile(filename);
                    break;
                case "Copy":
                    remote.sendFile(filename, connection.getOutputStream());
                    break;
                default:
                    System.out.println("Error. No request found: " + request[0]);
            }
        }else if (request.length == 3){
            //Create file remote
        }

    }
}
