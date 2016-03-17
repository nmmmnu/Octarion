package madsoft.ws;

import java.io.*;
import madsoft.util.*;

public class CGIApplet extends WebResponser{
   protected String querystr;

   protected MixProperties table = null;

   protected String contentType = "htm";
//===================================
   //constructor is empty :)
   public void CGIApplet(){}
//===================================
   public void construct(HTTP http, boolean header, boolean body, String querystr){
      super.construct(http,header,HTTPOK,body);
      this.querystr = querystr;
      table = null;
   }
//===================================
   public void init(){}
//===================================
   public void start(){}
//===================================
   public String getQueryParam(String a){
      if (table == null){
         QueryStringParser qsp = new QueryStringParser(querystr);
         table = qsp.parse();
      }

      return ( (table == null) ? null : (String) table.get(a) );
   }
//===================================
   public void process(){
      placeHTTPHeader(contentType,-1);
      placeHTTPBody();
   }
//===================================
   // In fact this method must be abstract, but it is Hello World :)
   public void HTTPBody(){
      ps.println("<HTML><HEAD><TITLE>Web server OCTARION Abstract CGI message</TITLE></HEAD>");
      ps.println("<BODY><H1>Web server OCTARION Abstract CGI message</H1>");
      ps.println("<Hr>");
      ps.println("<H2>Hello World</H2><BODY><HTML>");
   }
//===================================
}