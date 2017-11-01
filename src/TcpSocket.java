import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.net.*;
import java.io.*;
import java.util.Base64;

public class TcpSocket{
    public static void main(String argv[]) throws Exception {
        String modifiedSentence;
        String benutzer = "marorox";
        String passwort = "BzR4S9Zx";
        String originalInput = benutzer + passwort;
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        Socket clientSocket = new Socket("smtp-relay.gmail.com", 25);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        outToServer.writeBytes("HELO marorox" + '\n');
        outToServer.writeBytes("AUTH PLAIN " + encodedString );

    //    System.out.println(encodedString);
        //       outToServer.writeBytes("rcpt to: <marorox@googlemail.com>"  +  '\n');
        //       outToServer.writeBytes("DATA"  +  '\n');
        while(inFromServer.readLine()!= null){
            System.out.println(inFromServer.readLine());
        }
        System.out.println(inFromServer.readLine());
        clientSocket.close();

    }




}