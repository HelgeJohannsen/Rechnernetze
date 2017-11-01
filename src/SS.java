import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.util.Base64;

public class SS {
    public static void main(String argv[]) throws Exception {
        String modifiedSentence;
        String benutzer = "marorox@googlemail.com";
        String passwort = "BzR4S9Zx";
        String originalInput = benutzer + passwort;
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        SSLSocketFactory factory=(SSLSocketFactory) SSLSocketFactory.getDefault();


        System.setProperty("javax.net.ssl.trustStore","C:\\Program Files\\Java\\jdk-9.0.1\\bin\\keystore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword","password1");

        SSLSocket sslsocket =(SSLSocket) factory.createSocket("smtp-relay.gmail.com",465);
        DataOutputStream outToServer = new DataOutputStream(sslsocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(sslsocket.getInputStream()));
        System.out.println(inFromServer.readLine());
        System.out.println(encodedString);
        outToServer.writeBytes("EHLO test.com" + '\n');

        while (inFromServer.readLine() != null) {
            System.out.println(inFromServer.readLine());
        }
        outToServer.writeBytes("STARTTLS"  + '\n');
      //  outToServer.writeBytes("AUTH PLAIN " + encodedString + '\n');
      //  outToServer.writeBytes("MAIL FROM:<marorox@googlemail.com>");
        //    System.out.println(encodedString);
       //        outToServer.writeBytes("rcpt to: <marorox@googlemail.com>"  +  '\n');
        //       outToServer.writeBytes("DATA"  +  '\n');
        while (inFromServer.readLine() != null) {
            System.out.println(inFromServer.readLine());
        }
        sslsocket.close();
    }
}