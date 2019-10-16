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
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Client {

  public final static int SOCKET_PORT = 300;      
  public final static String SERVER = "10.0.0.227";  
  public final static String
       Alinacak_Dosya = "C:\\Users\\bendo\\Desktop\\Banana\\validen.pdf"; 

  public final static int Dosya_Alan = 6022386; 

  public static void main (String [] args ) throws IOException {
    int okunanBit;
    int durum = 0;
    FileOutputStream fs = null;
    BufferedOutputStream bs = null;
    Socket sock = null;
    try {
      sock = new Socket(SERVER, SOCKET_PORT);
      System.out.println("Bağlanıyor..");

      // receive file
      byte [] bitArray  = new byte [Dosya_Alan];
      InputStream is = sock.getInputStream();
      fs = new FileOutputStream(Alinacak_Dosya);
      bs = new BufferedOutputStream(fs);
      okunanBit = is.read(bitArray,0,bitArray.length);
      durum = okunanBit;

      do {
         okunanBit =
            is.read(bitArray, durum, (bitArray.length-durum));
         if(okunanBit >= 0) durum += okunanBit;
      } while(okunanBit > -1);

      bs.write(bitArray, 0 , durum);
      bs.flush();
      System.out.println("Dosya " + Alinacak_Dosya
          + " indirildi (" + durum + " okunan bit)");
    }
    finally {
      if (fs != null) fs.close();
      if (bs != null) bs.close();
      if (sock != null) sock.close();
    }
  }

}
