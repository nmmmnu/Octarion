package madsoft.ws;

import java.io.*;
import java.net.*;
import java.util.*;

public class HTTPMethodGET extends HTTPMethod{
//==================================================
   public String name(){
      return "GET";
   }
//==================================================
   public WebResponser getResponserProcess(){
      if (filename.startsWith(Cfg.getProp("DOCROOT") + Cfg.getProp("CGIROOT")))
         return (WebResponser) new CGIResponser(this,header,true,filename,querystr);

      else if (filename.startsWith(Cfg.getProp("DOCROOT") + Cfg.getProp("PROCROOT")))
         return (WebResponser) new ProcResponser(this,header,true,filename,querystr);

      else if (filename.startsWith(Cfg.getProp("DOCROOT") + Cfg.getProp("PRIVATEROOT")))
         return (WebResponser) new SysFilenotfoundResponser(this,header,true);

      else
         return (WebResponser) new FileResponser(this,header,true,filename);
   }
//==================================================
}