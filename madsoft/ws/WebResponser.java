package madsoft.ws;

import java.io.*;
import java.util.Date;
import madsoft.util.*;

public abstract class WebResponser extends HTTP{
   protected static final String HTTPOK             = "200 OK";
   protected static final String HTTPFILENOTFOUND   = "404 File not found";
   protected static final String HTTPBADRECUEST     = "400 Bad recuest";
   protected static final String HTTPERROR          = "500 HTTP Error";
   protected static final String HTTPCGIERROR       = "500 CGI Error";
   protected static final String HTTPNOTIMPLEMENTED = "501 Not implemented";
   protected static final String HTTPOUTOFROOT      = "404 Out of root";
//--------------------------------------
   public static RestrictManager RESTRICTMANAGER = null;
//--------------------------------------
   protected boolean header;
   protected String HTTPmsg;

   protected boolean body;
//===================================
   public WebResponser(HTTP http, boolean header, String HTTPmsg, boolean body){
      construct(http,header,HTTPmsg,body);
   }
//===================================
   //Empty constructor
   public WebResponser(){}
//===================================
   //This is Object's REAL Constructor, Because you must use dynamix objects
   protected void construct(HTTP http,       //HTTP object (streams, socket, HTTP tags)
                            boolean header,  //Header output y/n
                            String HTTPmsg,  //HTTP message (200 OK)
                            boolean body){   //Body output y/n
      super.construct(http);

      this.header   = header;
      this.HTTPmsg  = HTTPmsg;

      this.body     = body;
   }
//===================================
   protected void done(){
      try{
         if (is != null)
            is.close();
      }catch (IOException e){
         Log.write(this,Log.DEBUG,"Cannot close input stream");
      }

      try{
         if (ps != null)
            ps.close();

         if (os != null){
            os.flush();
            os.close();
         }
      }catch (IOException e){
         Log.write(this,Log.DEBUG,"Cannot close output stream");
      }

      Log.write(this,Log.DEBUG,"Client thread stoped");
   }
//===================================
   public void finalize() throws Throwable {
      try{
         done();
      }catch (Exception e){}

      super.finalize();
   }
//==========================================
   public boolean restrict(){
      if (RESTRICTMANAGER == null)
         return false;
      else
         return RESTRICTMANAGER.restrict(client);
   }
//===================================
   public String getHTTPParam(String a){
      return ( (HTTPtags == null) ? null : (String) HTTPtags.get(a) );
   }
//===================================
   public void placeHTTPHeader(String ext, long len, long date){
      if (!restrict())
         if (header){
            ps.println("HTTP/" + HTTPversion + " " + HTTPmsg);

            Date d = new Date();
            ps.println("Date: " + d);

            if (date > 0){
               d = new Date(date);
               ps.println("Last-modified: " + d);
            }

            ps.println("Server: " + Cfg.LONGSERVERNAME + " " + Cfg.VERSION);
            ps.println("Accept-ranges: bytes");

            if (ext != null)
               ps.println("Content-type: " + MIMEContent.getMime(ext));
            if (len >= 0)
               ps.println("Content-lenght: " + len);

            HTTPHeader();

            ps.println("Connection: close");
            ps.println();
         }
   }
//===================================
   public void placeHTTPHeader(String ext, long len){
      placeHTTPHeader(ext,len, -1);
   }
//===================================
   //User extendable HTTPHeader
   public void HTTPHeader(){}
//===================================
   public void placeHTTPBody(){
      if (!restrict())
         if (body)
            this.HTTPBody();
   }
//===================================
   //User extendable HTTPBody
   public void HTTPBody(){}
//===================================
   public abstract void process();
   /* simplest realisation:
      ---------------------
      placeHTTPHeader();
      placeHTTPBody();
   */
//===================================
}
