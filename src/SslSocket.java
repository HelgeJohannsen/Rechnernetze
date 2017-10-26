import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Base64;
import javax.net.ssl.*;
public class SslSocket {

    public static void main(String argv[]) throws Exception {
        String modifiedSentence;
        String benutzer = "marorox@googlemail.com";
        String passwort = "BzR4S9Zx";
        String originalInput = benutzer;
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());

        SSLSocketFactory factory=(SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket sslsocket=(SSLSocket) factory.createSocket("haw-mailer.haw-hamburg.de",587
        );
        sslsocket.startHandshake();
        DataOutputStream outToServer = new DataOutputStream(sslsocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(sslsocket.getInputStream()));

        System.out.println(inFromServer.readLine());
        sslsocket.close();

    }

}
