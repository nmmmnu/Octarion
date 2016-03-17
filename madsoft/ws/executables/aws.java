package madsoft.ws;

import java.io.*;
import java.net.*;
import madsoft.server.*;

public class aws extends TCPServer{
//=======================================
   public aws(int port, int max){
      super(port,max);
   }
//=======================================
   protected TCPClient getAClient(Socket s){
      return new awc(s);
   }
//=======================================
   public static void main(String[] args){
      Log.init();
      Log.write(null,Log.NORMAL,"Abstract client manager started...");
      aws s = new aws(81,1);
      s.start();
   }
//=======================================
}

//****************************************

class awc extends TCPClient{
//=======================================
   public awc(Socket client){
      super(client);
   }
//=======================================
   public void process(){
      try{
         String s;

         while ((s = is.readLine()) != null) { // loop through the rest of the input lines

            System.out.println(s);
         }

         ps.println("HTTP/1.0 200 OK");
         ps.println("Content-type:text/plain");
         ps.println();
         ps.println("OK Transfer Completed :o)");

      }catch (IOException e){
         Log.write(this,Log.DEBUG,"Error while responding");
      }catch (NullPointerException e){
         Log.write(this,Log.DEBUG,"Connection reset by peer");
      }

      done();
   }
//=======================================
}
