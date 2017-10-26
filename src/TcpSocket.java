import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.net.*;
import java.io.*;
import java.util.Base64;

public class TcpSocket{
public static void main(String argv[]) throws Exception {
    String modifiedSentence;
    String benutzer = "marorox@googlemail.com";
    String passwort = "BzR4S9Zx";
    String originalInput = benutzer;
    String originalInput2 = "AUTH LOGIN marorox";
    String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
    String encodedString2 = Base64.getEncoder().encodeToString(originalInput2.getBytes());
    Socket clientSocket = new Socket("smtp.gmail.com", 587);
    DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
    BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

    outToServer.writeBytes("EHLO smtp.gmail.com" + '\n');
    outToServer.writeBytes("mail from: <marorox@googlemail.com>"  +  '\n');

    while(inFromServer.readLine()!= null){
        System.out.println(inFromServer.readLine());
    }
    System.out.println(inFromServer.readLine());
    clientSocket.close();

        }




}


