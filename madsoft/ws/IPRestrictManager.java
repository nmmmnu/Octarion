package madsoft.ws;

import java.io.*;
import java.net.*;
import madsoft.util.*;

public class IPRestrictManager extends MixProperties implements RestrictManager{
   private final static String CFGFILENAME = Cfg.SERVERNAME + "-restrictions" + ".ini";

//---------------------------
   synchronized public void load() {
      Log.write(null,Log.NORMAL,"Loading Restriction configuration.");
      try{
         load(new FileInputStream(CFGFILENAME));
      }catch (IOException e){
         Log.write(null,Log.ERROR,"Unable to read Restriction configuration.");
      }
  }

//---------------------------
   public boolean restrict(Socket client){
      boolean b = (Cfg.getIntProp("IPRESTRICT") == 0);

      for (int i=0;i < 4;i++){
         String addr = Trans.ip2str(client.getInetAddress(),i);

         if (containsKey(addr))
            b = !getBooleanProperty(addr);
      }

      if (b)
         Log.write(null,Log.EXTENDED,"Access Restricted, IP: " + Trans.ip2str(client.getInetAddress()));

      return b;
   }
//---------------------------
}