package madsoft.ws;

import java.io.*;
import java.net.*;
import java.util.*;

//YES, I KNOW this this method not work absolutely correctly
//HTTP/1.0 Can't have this method too but I dont worry about this :)

class OPTIONSResponser extends WebResponser{
   String filename;
//==================================================
   public OPTIONSResponser(HTTP http){
      super(http,true,HTTPOK,false);
      HTTPversion = "1.1";
   }
//==================================================
   public void HTTPHeader(){
      ps.print("Allow :");

      for (Enumeration e = WWWClient.methods.keys(); e.hasMoreElements();){
         ps.print(" " + e.nextElement());
         if (e.hasMoreElements())
            ps.print(",");
      }

      ps.println();
   }
//==================================================
   public void process(){
      placeHTTPHeader(null,0);
      placeHTTPBody();
   }
//==================================================
}

public class HTTPMethodOPTIONS extends HTTPMethod{
//==================================================
   public String name(){
      return "OPTIONS";
   }
//==================================================
   public WebResponser getResponserProcess(){
      if (!header)  //HTTP/0.9 Cant have this method!!!!!!
         return (WebResponser) new SysBadrecuestResponser(this, header, true);

      return (WebResponser) new OPTIONSResponser(this);
   }
//==================================================
}