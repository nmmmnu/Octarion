package madsoft.ws;

import java.io.*;
import madsoft.util.*;

public class MIMEContent{
   private final static String CFGFILENAME = Cfg.SERVERNAME + "-mime" + ".ini";

   static MixProperties pr = null;

//---------------------------
   static void init(){
      MixProperties prd = new MixProperties();

      //--------------------------------
      prd.put("text", "text/plain");
      prd.put("txt",  "text/plain");
      prd.put("java", "text/plain");
      //--------------------------------
      prd.put("html", "text/html");
      prd.put("htm",  "text/html");
      //--------------------------------
      prd.put("gif",  "image/gif");
      prd.put("xbm",  "image/x-bitmap");
      //-------------------------------- 
      prd.put("png",  "image/png");
      prd.put("ping", "image/png");
      //--------------------------------
      prd.put("jpg",  "image/jpeg");
      prd.put("jpeg", "image/jpeg");
      prd.put("jif",  "image/jpeg");
      //--------------------------------
      prd.put("class","application/octet-stream");
      //--------------------------------

      pr = new MixProperties(prd);
   }
//---------------------------
   synchronized public static void load() {
      Log.write(null,Log.NORMAL,"Loading MIME configuration.");

      init();

      try{
         pr.load(new FileInputStream(CFGFILENAME));
      }catch (IOException e){
         Log.write(null,Log.ERROR,"Unable to read MIME configuration.");
      }
  }

//---------------------------
   public static String getMime(String ext){
      return pr.getProperty(ext.toLowerCase(), "text/plain");
   }
//---------------------------
}
