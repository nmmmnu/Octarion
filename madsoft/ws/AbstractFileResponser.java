package madsoft.ws;

import java.io.*;
import madsoft.util.MixProperties;

abstract class AbstractFileResponser extends WebResponser{
   protected static final int BUFFERSIZE = 0x10000; //10KB
   protected String filename;

   public    boolean err = false;

   private   FileInputStream fis;
   private   long filesize;
   private   long filedate;

//========================================================
   public AbstractFileResponser(HTTP http, boolean header, String HTTPmsg, boolean body, String filename){
      super(http,header,HTTPmsg,body);
      this.filename = filename;
   }
//========================================================
   abstract void ResponceError(int code);
//========================================================
   public void HTTPBody(){
      byte[] buffer = new byte[BUFFERSIZE];
      int br = 0;

      try{
         while (br != -1){
            br = fis.read(buffer,0,BUFFERSIZE);
            if (br != -1){
               os.write(buffer,0,br);
               os.flush();
            }
         }

         fis.close();
      }catch (IOException e){}
   }
//========================================================
   protected String getFileExt(){
      int i = filename.lastIndexOf('.');
      return ((i == -1) || (i == filename.length())) ? "" : filename.substring(i + 1);
   }
//========================================================
   public void process(){
      String path;
      try{
         File f = new File(filename);
         path = f.getCanonicalPath();

         if (path.startsWith(Cfg.getProp("DOCROOT"),0) == false){
            ResponceError(1);
            return;
         }

         filesize = f.length();
         filedate = f.lastModified();
         fis = new FileInputStream(f);
      }catch (IOException e){
         ResponceError(2);
         return;
      }

      //----------------------------------------

      placeHTTPHeader(getFileExt(), filesize, filedate);
      placeHTTPBody();
   }
//=================================================
}
