package remoteMethodCalling;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class requestTest {

    @Test
    public void goodCreateRequestTest() throws IOException {
        MethodRequest methodRequest = null;
        try {
            methodRequest = new MethodRequestImpl("Delete method.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertEquals(methodRequest.getRequest(), "Delete method.txt");
    }

    @Test(expected = Exception.class)
    public void badCreateRequestTest(){
        try {
            MethodRequest methodRequest = new MethodRequestImpl("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendRequestTest() throws IOException {
        RequestSender requestSender = new Local();
        try {
            requestSender.sendRequest(new MethodRequestImpl("Create something.txt Sentence for the file."), new FileOutputStream("SendRequest.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void badSendRequestTest() throws IOException {
        RequestSender requestSender = new Local();
        try {
            requestSender.sendRequest(new MethodRequestImpl(""), new FileOutputStream("SendRequest.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void goodReciveAndCreateRequest() throws IOException {
        FileOutputStream fos = new FileOutputStream("SendRequest.txt"); //Bei TCP, Outputstream der Connection
        RequestSender requestSender = new Local();
        try {
            requestSender.sendRequest(new MethodRequestImpl("Create something.txt Sentence for the file."),fos );
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestReciver requestReciver = new Remote();
        requestReciver.receiveRequest(new FileInputStream("SendRequest.txt"),fos );
        Assert.assertTrue(new File("something.txt").exists());
    }
    @Test(expected = Exception.class)
    public void badReciveRequest() throws IOException {
        FileOutputStream fos = new FileOutputStream("SendRequest.txt"); //Bei TCP, Outputstream der Connection
        RequestSender requestSender = new Local();
        try {
            requestSender.sendRequest(new MethodRequestImpl("Cop"),fos );
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestReciver requestReciver = new Remote();
        requestReciver.receiveRequest(new FileInputStream("SendRequest.txt"),fos );
    }

}
