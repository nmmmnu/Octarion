package madsoft.ws;

import java.io.*;
import madsoft.util.MixProperties;

class CGIResponser extends WebResponser{
   protected String CGIclassname;
   protected String querystr;
//=============================================
   public CGIResponser(HTTP http, boolean header, boolean body, String CGIclassname, String querystr){
      super(http,header,HTTPOK,body);

      if (CGIclassname.indexOf(".") == -1)
         this.CGIclassname = CGIclassname + ".class";
      else
         this.CGIclassname = CGIclassname;

      this.querystr = querystr;
   }
//=============================================
   protected 
   static 
   synchronized CGIApplet getCGIApplet(String name) throws ClassNotFoundException,
                                                            InstantiationException,
                                                            IllegalAccessException
   {
      FileClassLoader cl = new FileClassLoader();

      CGIApplet cgi = (CGIApplet) cl.loadClass(name).newInstance();
      cgi.init();
      return cgi;
   }
//=============================================
   public void process(){
      CGIApplet cgi = null;

      try{
         //cgi = (CGIApplet) cl.loadClass(CGIclassname).newInstance();
         //cgi.init();
         cgi = getCGIApplet(CGIclassname);
      }catch (Exception e){
         SysCGIErrorResponser ws = new SysCGIErrorResponser(this,header,body);
         ws.process();
         return;
      }

      cgi.construct(this,header,body,querystr);
      cgi.start();
      cgi.process();
   }
//=============================================
}

//27428
