package madsoft.ws;

import java.io.*;
import madsoft.util.MixProperties;

class SysFileResponser extends AbstractFileResponser{
   public boolean err = false;
//========================================================
   public SysFileResponser(HTTP http, boolean header, String HTTPmsg, boolean body, String filename){
      super(http,header,HTTPmsg,body,filename);
   }
//========================================================
   void ResponceError(int code){
      err = true;
   }
}

//*************************************************

class SysResponser extends WebResponser{
   String filename;
//======================================
   public SysResponser(HTTP http, boolean header, String HTTPmsg, boolean body, String filename){
      super(http,header,HTTPmsg,body);
      this.filename = Cfg.getProp("DOCROOT") + Cfg.getProp("SYSDOCROOT") + File.separator + filename;
   }
//======================================
   public void HTTPBody(){
      ps.println("<HTML><HEAD><TITLE>Web server error</TITLE></HEAD>");
      ps.println("<BODY><H1>Web server error</H1>");
      ps.println("<Hr>");
      ps.println("<H2>" + HTTPmsg + "</H2><BODY><HTML>");
   }
//======================================
   public void process(){
      SysFileResponser ws = new SysFileResponser(this,header,HTTPmsg,body,filename);
      ws.process();

      if (ws.err){
         placeHTTPHeader("htm",-1);
         placeHTTPBody();
      }
   }
//======================================
}
