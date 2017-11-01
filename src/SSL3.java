import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.security.cert.X509Certificate;

public class SSL3{
public static void main(String argv[]) throws Exception {

    FileInputStream in = new FileInputStream("C:\\Program Files\\Java\\jdk-9.0.1\\bin\\publickey.store");
    KeyStore ks = KeyStore.getInstance("JKS");
    String pws = "Baumhaus24";
    char[] pw = pws.toCharArray();
    ks.load(in, pw);

    X509Certificate cert = (X509Certificate) ks.getCertificate("publicssl");

    KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
    kmf.init(ks, pw);


    TrustManagerFactory tmf  = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
    tmf.init(ks);
    SSLContext sslContext = SSLContext.getInstance("TLS");
    sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

    SSLSocketFactory factory=(SSLSocketFactory) SSLSocketFactory.getDefault();

    SSLSocket sslsocket =(SSLSocket) factory.createSocket("smtp.gmail.com",587);
    sslsocket.setUseClientMode(true);
    sslsocket.setEnabledProtocols(sslsocket.getSupportedProtocols());
    sslsocket.setEnabledCipherSuites(sslsocket.getSupportedCipherSuites());



    sslsocket.startHandshake();

    DataOutputStream outToServer = new DataOutputStream(sslsocket.getOutputStream());
    BufferedReader inFromServer = new BufferedReader(new InputStreamReader(sslsocket.getInputStream()));



    System.out.println(inFromServer.readLine());


    outToServer.writeBytes("EHLO marorox" + '\n');
    System.out.println(inFromServer.readLine());
    outToServer.writeBytes("AUTH LOGIN" );
    System.out.println(inFromServer.readLine());
    System.out.println(inFromServer.readLine());
    System.out.println(inFromServer.readLine());
    System.out.println(inFromServer.readLine());
    System.out.println(inFromServer.readLine());
    System.out.println(inFromServer.readLine());
    System.out.println(inFromServer.readLine());
    System.out.println(inFromServer.readLine());
    outToServer.writeBytes("AUTH LOGIN" + '\n');
    System.out.println(inFromServer.readLine());
    System.out.println(inFromServer.readLine());
    sslsocket.close();
}}