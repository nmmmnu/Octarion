// Web Server Loader

package madsoft.ws;

import java.io.*;
import madsoft.command.*;

public class ws_start{
   public static void main(String[] args){
      madsoft.util.MemoryManager.runFinalizersOnExit(true);

      Log.init();

      Cfg.load();

      Log.loglevel = Cfg.getIntProp("LOGLEVEL");

      MIMEContent.load();

      IPRestrictManager rm = new IPRestrictManager();
      rm.load();
      WebResponser.RESTRICTMANAGER = rm;

      //---------------------------------------
      WWWServer   ws = new WWWServer();

      WWWClient.addMethod(new madsoft.ws.HTTPMethodGET());
      WWWClient.addMethod(new madsoft.ws.HTTPMethodHEAD());
      WWWClient.addMethod(new madsoft.ws.HTTPMethodPOST());
      WWWClient.addMethod(new madsoft.ws.HTTPMethodOPTIONS());

      ws.setPriority(ws.MAX_PRIORITY);
      ws.start();
      //---------------------------------------
      AdminServer as = new AdminServer(ws);
      as.setPriority(as.MAX_PRIORITY);
      as.start();
      //---------------------------------------
      Log.writeCon("It's working now!");
      Log.writeCon("----------------------------");

      //fx x = new fx();
      //x.start();
      
      mixer(ws);
   }

   public static final void mixer(WWWServer ws){
      DataInputStream is  = new DataInputStream(System.in);
      PrintStream     ps  = new PrintStream(System.out);
      WSCMDExecutor   cmd = new WSCMDExecutor(is, ps, ws);

      while (true){
         try{
            CMDShell shell = new CMDShell(is,ps,cmd, "", null);
            shell.process();
         }catch (Exception e){
            ps.println("Exception " + e);
         }

         ps.println("Restarting shell...");
      }
   }
}

class fx extends Thread{
   public void run(){
      while (true){
         System.out.println("I'm alive!");

         try{
            sleep(30000);
         }catch (Exception e){
            System.out.println("Exception " + e);
         }
      }
   }
}

