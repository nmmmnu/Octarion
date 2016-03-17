package madsoft.ws;

import java.io.*;
import java.net.*;
import java.util.*;

public class HTTPMethodHEAD extends HTTPMethod {
//==================================================
   public String name(){
      return "HEAD";
   }
//==================================================
   public WebResponser getResponserProcess(){
      if (!header)   //HTTP/0.9 Cant have this method!!!!!!
         return (WebResponser) new SysBadrecuestResponser(this,header,true);

      else if (filename.startsWith(Cfg.getProp("DOCROOT") + Cfg.getProp("CGIROOT")))
         return (WebResponser) new CGIResponser(this,header,false,filename,querystr);

      else if (filename.startsWith(Cfg.getProp("DOCROOT") + Cfg.getProp("PROCROOT")))
         return (WebResponser) new ProcResponser(this,header,false,filename,querystr);

      else if (filename.startsWith(Cfg.getProp("DOCROOT") + Cfg.getProp("PRIVATEROOT")))
         return (WebResponser) new SysFilenotfoundResponser(this,header,false);

      else
         return (WebResponser) new FileResponser(this,header,false,filename);
   }
}

