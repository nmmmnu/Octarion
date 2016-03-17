package madsoft.ws;

import java.io.*;
import java.util.*;

import madsoft.util.*;

import madsoft.command.*;
import madsoft.command.CMD; //YES I KNOW!!!

class WSCMDExecutor extends CMDExecutor{
   public WSCMDExecutor(DataInputStream is, PrintStream ps,WWWServer WWW){
      super(is,ps);

      addCMD(new CMDdown());
      addCMD(new CMDinfo());
      addCMD(new CMDkill());
      addCMD(new CMDmime());
      addCMD(new CMDps());
      addCMD(new CMDstart(WWW));
      addCMD(new CMDstop(WWW));
      addCMD(new CMDstat(WWW));
      addCMD(new CMDgc());
      addCMD(new CMDmethods());

      //addCMD(new CMDmem());
   }
}

//*************************************

class CMDdown implements CMD{
   public String name(){
      return "down";
   }

   public String description(){
      return "shut down the server";
   }

   public void cmd(DataInputStream is, PrintStream ps) throws IOException{
      ps.println("You can start server only from system console!!!");
      ps.println("Do you really want to shutdown the server ?");
      ps.print  ("Write 'yes' to do this :");

      String s = is.readLine();

      ps.println();

      if (s.startsWith("ye",0)){ //Yeah!
         ps.println("Server shutdown");
         Log.write(this,Log.NORMAL,"Server shutdown");

         System.exit(0);
      }else
         ps.println("Excelent...");
   }
}

//*************************************

class CMDinfo implements CMD{
   public String name(){
      return "info";
   }

   public String description(){
      return "display enviroment information";
   }

   public void cmd(DataInputStream is, PrintStream ps){
      ps.println("Web server configuration");
      ps.println("========================");
      Cfg.mpr.list(ps);
   }
}

//*************************************

class CMDmime implements CMD{
   public String name(){
      return "mime";
   }

   public String description(){
      return "display registred mime types";
   }

   public void cmd(DataInputStream is, PrintStream ps){
      ps.println("Web server mime types");
      ps.println("=====================");
      MIMEContent.pr.list(ps);
   }
}
//*************************************

class CMDps implements CMD{
   public String name(){
      return "ps";
   }

   public String description(){
      return "display implemented server proceses";
   }

   public void cmd(DataInputStream is, PrintStream ps){
      ps.println("Web server proceses");
      ps.println("===================");

      for (Enumeration e = FileClassLoader.classes.keys(); e.hasMoreElements();)
         ps.println(e.nextElement());
   }
}

//*************************************

class CMDstart implements CMD{
   WWWServer WWW = null;

   public CMDstart(WWWServer WWW){
      super();
      this.WWW = WWW;
   }

   public String name(){
      return "start";
   }

   public String description(){
      return "start the server";
   }

   public void cmd(DataInputStream is, PrintStream ps){
      WWW.resume_();
   }
}

//*************************************

class CMDstop implements CMD{
   WWWServer WWW = null;

   public CMDstop(WWWServer WWW){
      super();
      this.WWW = WWW;
   }

   public String name(){
      return "stop";
   }

   public String description(){
      return "stop the server";
   }

   public void cmd(DataInputStream is, PrintStream ps){
      WWW.suspend_();
   }
}

//*************************************

class CMDstat extends madsoft.util.MemoryManager implements CMD{
   WWWServer WWW = null;

   public CMDstat(WWWServer WWW){
      super();
      this.WWW = WWW;
   }

   public String name(){
      return "stat";
   }

   public String description(){
      return "display server status";
   }

   public void cmd(DataInputStream is, PrintStream ps){
      ps.println("Server is working since " + WWW.startdate);

      ps.println();

      if (WWW.isWorking)
         ps.println("Web server currently is working");
      else
         ps.println("Web server currently is stoped");

      ps.println();

      ps.println("WWW threads");
      ps.println("===========");
      ps.println("Current hits: " + Trans.rightFormat(WWW.getThreadsCount(),12));
      ps.println("Max hits:     " + Trans.rightFormat(WWW.getMaxHitsCount(),12));
      ps.println("Total hits:   " + Trans.rightFormat(WWW.getTotalHitsCount(),12));

      ps.println();

      ps.println("Web server memory");
      ps.println("=================");
      ps.println("Total:  " + Trans.rightFormat(getTotalMem(),12) + " bytes");
      ps.println("Free:   " +  Trans.rightFormat(getFreeMem(),12) + " bytes");
      ps.println("Min:    " +   Trans.rightFormat((Cfg.getIntProp("MINMEMORY") * 1024),12) + " bytes");
      ps.println("---------");
      ps.println("Free[%]:" +  Trans.rightFormat(getFreeMemPr(),12) + " %");
   }
}

//*************************************

class CMDkill implements CMD{
   public String name(){
      return "kill";
   }

   public String description(){
      return "KILL ALL (!) implemented proceses";
   }

   public void cmd(DataInputStream is, PrintStream ps) throws IOException{
      ps.println("When you kill implemented proceses you may lose system resourses!!!");
      ps.println("Do you really want to kill all proceses ?");
      ps.print  ("Write 'yes' to do this :");

      String s = is.readLine();

      ps.println();

      if (s.startsWith("ye",0)){ //Yeah!
         ps.println("Killing all proceses");
         Log.write(this,Log.NORMAL,"Killing all proceses");
         FileClassLoader.classes.clear();
      }else
         ps.println("Excelent...");
   }
}

//*************************************

class CMDgc extends madsoft.util.MemoryManager implements CMD{
   public String name(){
      return "gc";
   }

   public String description(){
      return "Start Java's gc()";
   }

   public void cmd(DataInputStream is, PrintStream ps){
      fullGC();
   }
}

//*************************************

class CMDmethods implements CMD{
   public String name(){
      return "methods";
   }

   public String description(){
      return "display implemented server methods";
   }

   public void cmd(DataInputStream is, PrintStream ps){
      ps.println("Web server HTTP methods:");

      for (Enumeration e = WWWClient.methods.keys(); e.hasMoreElements();)
         ps.println(e.nextElement());

      ps.println();
      ps.println();
      ps.println("Notes:");
      ps.println("  1. This is same like HTTP method \"OPTIONS\" (if it is implemented)");
      ps.println("  2. Be sure that you have implemented HTTP method \"GET\"");
   }
}

//*************************************

/*
class CMDnew implements CMD{
   public String name(){
      return "???";
   }

   public String description(){
      return "??? ???";
   }

   public void cmd(DataInputStream is, PrintStream ps){
   }
}
*/
