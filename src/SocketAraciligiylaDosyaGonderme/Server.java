/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketAraciligiylaDosyaGonderme;

/**
 *
 * @author bendo
 */
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  public final static int SOCKET_PORTU = 300;  
  public final static String GONDERILECEK_DOSYA = "C:\\Users\\bendo\\Desktop\\Lalala\\Accommodation-agreement.pdf";  

  public static void main (String [] args ) throws IOException {
    FileInputStream fs = null;
    BufferedInputStream bs = null;
    OutputStream os = null;
    ServerSocket svsck = null;
    Socket sock = null;
    try {
      svsck = new ServerSocket(SOCKET_PORTU);
      while (true) {
        System.out.println("Beklemede...");
        try {
          sock = svsck.accept();
          System.out.println("Bağlantı kabul edildi : " + sock);
          File dosyam = new File (GONDERILECEK_DOSYA);
          byte [] mybytearray  = new byte [(int)dosyam.length()];
          fs = new FileInputStream(dosyam);
          bs = new BufferedInputStream(fs);
          bs.read(mybytearray,0,mybytearray.length);
          os = sock.getOutputStream();
          System.out.println("Gönderiyor " + GONDERILECEK_DOSYA + "(" + mybytearray.length + " bit)");
          os.write(mybytearray,0,mybytearray.length);
          os.flush();
          System.out.println("Done.");
        }
        finally {
          if (bs != null) bs.close();
          if (os != null) os.close();
          if (sock!=null) sock.close();
        }
      }
    }
    finally {
      if (svsck != null) svsck.close();
    }
  }
}