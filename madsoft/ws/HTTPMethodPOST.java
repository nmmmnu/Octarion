package madsoft.ws;

import java.io.*;
import java.net.*;
import java.util.*;

public class HTTPMethodPOST extends HTTPMethod{
//==================================================
   public String name(){
      return "POST";
   }
//==================================================
   public WebResponser getResponserProcess(){
      if ((filename.startsWith(Cfg.getProp("DOCROOT") + Cfg.getProp("CGIROOT"))) &&
         (header)) { //HTTP/0.9 Cant have this method!!!!!!
         int l = HTTPtags.getIntProperty("Content-Length",0);

         if (l > 0){
            byte buffer[] = new byte[l + 1];

            try{
               is.read(buffer,0,l);
               querystr = new String(buffer);
            }catch (Exception e){
               querystr = "";
            }
         }else
            querystr = "";
         return (WebResponser) new CGIResponser(this,header,true,filename,querystr);
      }

      return (WebResponser) new SysBadrecuestResponser(this,header,true);
   }
//==================================================
}