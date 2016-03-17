package madsoft.ws;

import java.io.*;
import java.util.*;
import madsoft.command.CMD; //YES I KNOW!!!

class ProcResponser extends WebResponser{
   protected String filename;
   protected String querystr;

   WSCMDExecutor cmd;
//========================================================
   public ProcResponser(HTTP http, boolean header, boolean body, String filename, String querystr){
      super(http,header,HTTPOK,body);

      filename = filename.substring(filename.lastIndexOf(File.separator) + 1);
      this.filename = filename;

      cmd = new WSCMDExecutor(null,ps,AdminServer.WWW);

      if (querystr == null)
         querystr = "";

      this.querystr = querystr;
   }
//========================================================
   public void HTTPBody(){
      if (filename.equals(Cfg.getProp("DEFAULTDOC")))
         HTTPBodyList();
      else
         HTTPBodyCMD();
   }
//========================================================
   public void HTTPBodyList(){
      ps.println("<HTML><HEAD><TITLE>Web server virtual proc directory</TITLE></HEAD>");
      ps.println("<BODY><H1>Web server virtual proc directory</H1>");
      ps.println("<Hr>");
      ps.println("<Table>");

      for (Enumeration e = cmd.com.elements(); e.hasMoreElements();){
         CMD x = (CMD) e.nextElement();
         ps.print("<TR><TD>");
         ps.print("<A HRef=\""+ x.name() + "?" + Cfg.getProp("ADMINKEY") + "\">");
         ps.print(x.name() + "</A></TD><TD>" + x.description() + "</TD></TR>");
      }

      ps.println("</Table>");
      ps.println("<BODY><HTML>");
   }
//========================================================
   public void HTTPBodyCMD(){
      ps.println("<HTML><HEAD><TITLE>Web server virtual proc directory</TITLE></HEAD>");
      ps.println("<BODY><H1><A Href=\"" + Cfg.getProp("DEFAULTDOC") + "?" + Cfg.getProp("ADMINKEY") + "\">Web server virtual proc directory result</A></H1>");
      ps.println("<Hr>");
      ps.println("<H2>Your recuest: " + filename + "</H2>");
      ps.println("<H2>Server response:</H2>");
      ps.println("<Hr>");
      ps.println("<Pre>");

      try{
         cmd.execCMD(filename);
      }catch (Exception e){
         ps.println();
         ps.println();         
         ps.println("--------------");
         ps.println("ERROR!!!");
         ps.println("Posibly reasons:");
         ps.println("  1. If you try to execute input command it will throw error...");
         ps.println("  2. Command fail");
      }

      ps.println("</Pre>");
      ps.println("<Hr>");
      ps.println("<H2>End server response</H2>");
      ps.println("<BODY><HTML>");
   }
//========================================================
   public void process(){
      if ( !querystr.equals(Cfg.getProp("ADMINKEY")) ){
         SysFilenotfoundResponser ws = new SysFilenotfoundResponser(this,header,body);
         ws.process();
      }else{
         placeHTTPHeader("htm", -1);
         placeHTTPBody();
      }
   }
//=================================================
}
