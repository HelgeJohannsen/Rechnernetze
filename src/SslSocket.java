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
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream in = new FileInputStream("C:\\Program Files\\Java\\jdk-9.0.1\\bin\\keystore.jks");
        String pws = "password";
        char[] pw = pws.toCharArray();
        ks.load(in, pw);

        X509Certificate cert = (X509Certificate) ks.getCertificate("mail1");

        TrustManagerFactory tmf  = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ks);

        System.out.println(tmf.getAlgorithm());
        String modifiedSentence;
        String benutzer = "marorox@googlemail.com";
        String passwort = "BzR4S9Zx";
        String originalInput = benutzer;
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());

        SSLSocketFactory factory=(SSLSocketFactory) SSLSocketFactory.getDefault();

        SSLSocket sslsocket =(SSLSocket) factory.createSocket("smtp-relay.gmail.com",465);


       sslsocket.startHandshake();
        DataOutputStream outToServer = new DataOutputStream(sslsocket.getOutputStream());
       BufferedReader inFromServer = new BufferedReader(new InputStreamReader(sslsocket.getInputStream()));


        outToServer.writeBytes("HELO marorox" );
        outToServer.writeBytes("AUTH PLAIN " + encodedString);

     //   outToServer.writeBytes("AUTH PLAIN " + encodedString );
        while (inFromServer.readLine() != null) {
            System.out.println(inFromServer.readLine());
        }
        System.out.println(inFromServer.readLine());
        sslsocket.close();

    }

}
