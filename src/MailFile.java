import java.io.*;
import java.net.UnknownHostException;
import java.util.Base64;
import java.util.Properties;
import java.util.Base64;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class MailFile {
    String mailAdresse;
    static String benutzername;
    static String passwort;
    String hostname;
    String portnummer;
    String mailrec;
    String path;
    Properties properties = new Properties();
    public MailFile(String mailrec, String path) {
        this.mailrec = mailrec;
        this.path = path;
        Properties properties = new Properties();
        try {
            BufferedInputStream stream = new BufferedInputStream(new FileInputStream("mail.properties"));
            properties.load(stream);
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.mailAdresse = properties.getProperty("mailadresse");
        this.benutzername = properties.getProperty("benutzername");
        this.hostname = properties.getProperty("hostname");
        this.portnummer = properties.getProperty("portnummer");
        this.passwort = "Klient2017";
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        try {
//            this.passwort =  in.readLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println(mailAdresse);
        System.out.println(benutzername);
        System.out.println(hostname);
        System.out.println(portnummer);
        System.out.println(passwort);

    }

    public static void main(String argv[]) throws UnknownHostException, IOException{
        MailFile s = new MailFile("mail.properties", "mail.asdasdasd");
        String encodedUser = Base64.getEncoder().encodeToString(benutzername.getBytes());
        String encodedPW = Base64.getEncoder().encodeToString(passwort.getBytes());
        String encoded = encodedUser + encodedPW;
        SSLSocketFactory factory =(SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket sslsocket =(SSLSocket) factory.createSocket("mailgate.informatik.haw-hamburg.de",465);
        DataOutputStream outToServer = new DataOutputStream(sslsocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(sslsocket.getInputStream()));


        System.out.println(inFromServer.readLine());
        outToServer.writeBytes("EHLO abv313" + '\n');
        System.out.println(inFromServer.readLine());
        System.out.println(inFromServer.readLine());
        System.out.println(inFromServer.readLine());
        System.out.println(inFromServer.readLine());
        System.out.println(inFromServer.readLine());
        System.out.println(inFromServer.readLine());
        System.out.println(inFromServer.readLine());
        System.out.println(inFromServer.readLine());
        System.out.println(inFromServer.readLine());
        outToServer.writeBytes("AUTH LOGIN " + '\n');
        System.out.println(inFromServer.readLine());
        outToServer.writeBytes(encodedUser + '\n');
        System.out.println(inFromServer.readLine());
        outToServer.writeBytes(encodedPW + '\n');
        System.out.println(inFromServer.readLine());
        outToServer.writeBytes("MAIL FROM: <Helge.johannsen@haw-hamburg.de>" + '\n');
        System.out.println(inFromServer.readLine());
        outToServer.writeBytes("RCPT TO: <helgemmo@googlemail.com>" + '\n');
        System.out.println(inFromServer.readLine());
        outToServer.writeBytes("DATA" + '\n');
        System.out.println(inFromServer.readLine());
        outToServer.writeBytes("test1" + '\n');
        outToServer.writeBytes("test2" + '\n');
        outToServer.writeBytes("." + '\n');
        System.out.println(inFromServer.readLine());
        System.out.println(inFromServer.readLine());
    }
    public  void send(String msg){


    }



}