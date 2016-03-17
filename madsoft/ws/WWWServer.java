package madsoft.ws;

import java.io.*;
import java.net.*;
import java.util.*;

import madsoft.server.TCPClient;
import madsoft.server.TCPServer;

import madsoft.util.MemoryManager;

public class WWWServer extends TCPServer{
   Date      startdate = new Date();
//=======================================
   public WWWServer(){
      super(Cfg.getIntProp("SERVERPORT"), 
            Cfg.getIntProp("MAXBACKLOG"), 
            Cfg.getIntProp("TIMEOUT"), 
            Cfg.getIntProp("MAXTHREADS"));

      setName("WWW server thread");
      
      Log.write(this,Log.NORMAL,"Document root: " + Cfg.getProp("DOCROOT"));

    /*Log.write(this,Log.NORMAL,"System document root: " + Cfg.getProp("SYSDOCROOT"));
      Log.write(this,Log.NORMAL,"CGI root: " + Cfg.getProp("CGIROOT"));
      Log.write(this,Log.NORMAL,"Proc root: " + Cfg.getProp("PROCROOT"));
      Log.write(this,Log.NORMAL,"Private root: " + Cfg.getProp("PRIVATEROOT"));
    */
   }
//=======================================
   protected TCPClient getAClient(Socket s){
      WWWClient wc = new WWWClient(s);
      wc.setPriority(wc.NORM_PRIORITY);

      return wc;
   }
//=======================================
   protected boolean canAcceptConnection(){
      boolean b = ((MemoryManager.getFreeMem() / 1024) > Cfg.getIntProp("MINMEMORY"));

      if (!b){
         Log.write(this,Log.EXTENDED,"WARNING!!! Server running on low memory!!!");
         MemoryManager.fullGC();
      }

      return b &&
             super.canAcceptConnection();
   }
//==========================================
}

