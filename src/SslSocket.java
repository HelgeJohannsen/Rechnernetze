import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.security.cert.X509Certificate;
import java.util.Base64;
import javax.net.ssl.*;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;

public class SslSocket {

    public static void main(String argv[]) throws Exception {
        System.setProperty("javax.net.ssl.trustStore", "clienttrust");

        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket sslsocket =(SSLSocket) factory.createSocket("smtp-relay.gmail.com",465);

        SSLSession session = sslsocket.getSession();
        Certificate[] cchain = session.getPeerCertificates();
        String modifiedSentence;
        String benutzer = "marorox@googlemail.com";
        String passwort = "BzR4S9Zx";
        String originalInput = benutzer;
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());

       sslsocket.startHandshake();
        DataOutputStream outToServer = new DataOutputStream(sslsocket.getOutputStream());
       BufferedReader inFromServer = new BufferedReader(new InputStreamReader(sslsocket.getInputStream()));
        System.out.println(inFromServer.readLine());
        System.out.println(inFromServer.readLine());
        outToServer.writeBytes("EHLO test.com" );
        System.out.println(inFromServer.readLine());
        System.out.println(inFromServer.readLine());
        outToServer.writeBytes("AUTH PLAIN " + encodedString);

     //   outToServer.writeBytes("AUTH PLAIN " + encodedString );
        while (inFromServer.readLine() != null) {
            System.out.println(inFromServer.readLine());
        }
        System.out.println(inFromServer.readLine());
        sslsocket.close();

    }

}
