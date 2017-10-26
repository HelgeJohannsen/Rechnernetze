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

    Socket clientSocket = new Socket("smtp.gmail.com", 25);
    DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
    BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

    modifiedSentence = inFromServer.readLine();
    System.out.println("FROM SERVER: " + modifiedSentence);

    String sentence = "HELO google.com";
    outToServer.writeBytes(sentence + '\n');

    modifiedSentence = inFromServer.readLine();
    System.out.println("FROM SERVER: " + modifiedSentence);

    outToServer.writeBytes("mail from: marorox@googlemail.com" + '\n');

    modifiedSentence = inFromServer.readLine();
    System.out.println("FROM SERVER: " + modifiedSentence);


    clientSocket.close();

        }
}

