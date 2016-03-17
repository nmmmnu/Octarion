package madsoft.ws;

import java.io.*;
import java.net.*;
import java.util.*;      

import madsoft.server.*;
import madsoft.command.*;
import madsoft.command.CMD; //YES I KNOW!!!

import madsoft.util.Trans;

public class AdminServer extends TCPServer{
   static WWWServer WWW;
//=======================================
   public AdminServer(WWWServer WWW){
      super(Cfg.getIntProp("ADMINPORT"),5);
      this.WWW = WWW;

      setName("Admin server thread");
   }
//=======================================
   protected TCPClient getAClient(Socket s){      
      AdminClient ac = new AdminClient(this, s, WWW);
      ac.setPriority(ac.MAX_PRIORITY);
      return ac;
   }
//=======================================
}

//***************************************

class AdminClient extends TelnetClient{
   static final String HELLOMSG = "WS remote admin server\n\r" +
                                  "======================";
//=======================================
   Hashtable com = new Hashtable();
//=======================================
   public AdminClient(TCPServer server, Socket client, WWWServer WWW){
      super(client, HELLOMSG,
                    Cfg.getProp("ADMINKEY"),
                    null);
      //zashto???
      cmd = new WSCMDExecutor(is,ps,WWW);

      setName("Admin telnet thread");
   }
//=======================================
}

