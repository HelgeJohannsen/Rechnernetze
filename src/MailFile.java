import java.io.*;
import java.util.Properties;

public class MailFile {
    String mailAdresse, benutzername, passwort, hostname, portnummer, mailrec, path;
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
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            this.passwort =  in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(mailAdresse);
        System.out.println(benutzername);
        System.out.println(hostname);
        System.out.println(portnummer);
        System.out.println(passwort);

    }

    public static void main(String argv[]){
        MailFile s = new MailFile("mail.properties", "mail.asdasdasd");

    }

}
