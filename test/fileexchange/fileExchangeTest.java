package fileexchange;

import org.junit.Assert;
import org.junit.Test;
import remoteMethodCalling.MethodRequest;
import remoteMethodCalling.MethodRequestImpl;
import remoteMethodCalling.RequestSender;
import remoteMethodCalling.TCPLocal;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class fileExchangeTest {

    final String FILENAME = "TestFile.txt";
    final File file = new File(FILENAME);
    final String textForFile = "This is a test sentence";

    @Test
    public void WriteReadTest() throws IOException, InterruptedException {
        FileOutputStream fos = new FileOutputStream(file);
        byte[] byteArray = textForFile.getBytes(StandardCharsets.UTF_8);
        fos.write(byteArray);

        FileOutputStream os = new FileOutputStream("TestFile2.txt");//Recipient
        Local local = new FileExchanger();
        local.sendFile(FILENAME, os);

        FileInputStream fis = new FileInputStream(file);//File to send
        Remote remote = new FileExchanger();
        remote.reciveFile(FILENAME, fis);
        File secondFile =new File("TestFile2.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String chicken = br.readLine();


        System.out.println(chicken);
        Assert.assertTrue(secondFile.exists());
        secondFile.delete();

    }
    @Test(expected = FileNotFoundException.class)
    public void badWriteTest() throws IOException, InterruptedException {
        OutputStream os = new FileOutputStream("TestFile2.txt");//Recipient
        Local local = new FileExchanger();
        local.sendFile("WrongFileName.txt", os);
    }

    @Test
    public void deleteTest1() throws IOException {
        File deletable = new File("DeletableRemote.txt");
        Remote remote = new FileExchanger();
        Assert.assertTrue(deletable.exists());
        remote.deleteFile(deletable.getName());
        Assert.assertFalse(deletable.exists());
        deletable = new File("Deletable.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(deletable);
        fileOutputStream.write("Something".getBytes(StandardCharsets.UTF_8));
    }

    @Test(expected = FileNotFoundException.class)
    public void badDeleteTest1() throws IOException{
        File deletable = new File("Deletable.txt");
        Remote remote = new FileExchanger();
        Assert.assertTrue(deletable.exists());
        remote.deleteFile("WrongName");
    }

    @Test
    public void deleteOnRemoteTest() throws IOException {
        File deletable = new File("DeletableRemote.txt");
        MethodRequest methodRequest = new MethodRequestImpl("Delete DeletableRemote.txt");

        deletable = new File("DeletableRemote.txt");
        Assert.assertFalse(deletable.exists());
        FileOutputStream fileOutputStream = new FileOutputStream(deletable);
        fileOutputStream.write("Something".getBytes(StandardCharsets.UTF_8));
    }


    @Test
    public void badDeleteOnRemoteTest() throws IOException {
        File deletable = new File("DeletableRemote.txt");
        MethodRequest methodRequest = new MethodRequestImpl("Delete DeletableRemote.txt");
        RequestSender requester = new TCPLocal();
        FileOutputStream fos = new FileOutputStream(deletable);

        requester.sendRequest(methodRequest, fos);

        deletable = new File("DeletableRemote.txt");
        Assert.assertFalse(deletable.exists());
        FileOutputStream fileOutputStream = new FileOutputStream(deletable);
        fileOutputStream.write("Something".getBytes(StandardCharsets.UTF_8));
    }

    @Test
    public void copyFileGoodTest() throws IOException {
        MethodRequest methodRequest = new MethodRequestImpl("Copy DeletableRemote.txt");
        File toCopy = new File("CopyTest.txt");
        RequestSender requester = new TCPLocal();
        FileOutputStream fos = new FileOutputStream(toCopy);

        requester.sendRequest(methodRequest, fos);

        File copy = new File("CopyTest(1).txt");
        Assert.assertTrue(copy.exists());
        copy.delete();
    }

   // @Test
   // public void


}
