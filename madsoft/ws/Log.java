package madsoft.ws;

import madsoft.server.LogFile;

public class Log extends madsoft.server.Log{
   public final static LogFile WWW = new LogFile("www", 1);
   //------------------------------------
   public static final void init(){
      welcomemsg =

     "********************************\n"+
     "*                              *\n"+
     "*        O C T A R I O N       *\n"+
     "*      web server ver. " + Cfg.VERSION + "   *\n"+
     "*                              *\n"+
     "* by Nikolay Mijaylov Mijaylov *\n"+
     "*                              *\n"+
     "* copyright (c) 04.1998, Sofia *\n"+
     "*                              *\n"+
     "********************************\n"+
     "\n";

     welcome();
   }
   //------------------------------------
}
