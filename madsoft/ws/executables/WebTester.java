import java.net.*;
import java.io.*;
import java.util.*;
import madsoft.util.Trans;
import madsoft.server.TCPClient;

public class WebTester extends TCPClient{
   String file;
//=============================
   public WebTester(Socket c, String file){
      super(c);

      this.file = file;
   }
//=============================
   public void process(){
      try{
         ps.println("GET " + file + " HTTP/1.0");
         ps.println();
         ps.println();

         String s = "";
         while (s != null){
            s = is.readLine();
            yield();
         }
      }catch (Exception e){}
   }
//=============================
   public static void main(String[] args){
      if (args.length < 4){
         System.out.println("Usage: java WebTester host port file count");
         System.exit(0);
      }

      System.out.println("http://" + args[0] + ":" + Trans.str2int(args[1],80) + args[2]);

      while (true){
         System.out.println("Time :" + new Date());

         WebTester wt = null;

         for (int i = 0; i < Trans.str2int(args[3],1); i++){
            try{
               System.out.print("\rThread # " + i);

               Socket c = new Socket(args[0],Trans.str2int(args[1],80));

               wt = new WebTester(c, args[2]);
               wt.start();
            }catch (Exception e){
               System.out.println("\rException :)");
            }
         }

         try{
            wt.join();
         }catch (Exception e){}

         System.out.println();
      }
   }
//=============================
}