 package madsoft.server;

import java.io.*;
import java.util.*;

/**
* Log object manipulate several LogFiles
*
* @see LogFile
*/
public class Log{
   /**
   * Log delimiter
   */
   public final static String DELIMITER = ',' + "";

   /**
   * Normal log.
   *<pre>
   * file:    normal
   * level :  1
   * console: yes
   *</pre>
   */
   public final static LogFile NORMAL   = new LogFile("normal"  , 1,  true);

   /**
   * Error log.
   *<pre>
   * file:    error
   * level :  1
   * console: yes
   *</pre>
   */
   public final static LogFile ERROR    = new LogFile("error"   , 2,  true);

   /**
   * Extended log.
   *<pre>
   * file:    extended
   * level :  1
   * console: yes
   *</pre>
   */
   public final static LogFile EXTENDED = new LogFile("extended", 3,  true);

   /**
   * Debug log.
   *<pre>
   * file:    no!!!
   * level :  1
   * console: yes
   *</pre>
   */
   public final static LogFile DEBUG    = new LogFile(null   , 200, true);

   /**
   * Welcome message.
   * It is good idea to change this :)
   */
   public static String welcomemsg = "(: Abstract TCP Server :)";

   /**
   * Global Log level
   */
   public static int    loglevel   = 250;
//============================================

   /**
   * Write to log
   *
   * @param lgf a LogFile
   * @param s messaage
   */
   public static void write(LogFile lgf, String s){
      Date d = new Date();

      s = d.toString() + DELIMITER + s;

      lgf.write(s);
   }
//============================================

   /**
   * Write to log
   *
   * @param obj an Object who write to log (usually "this")
   * @param lgf a LogFile
   * @param s messaage
   */
   public static void write(Object obj, LogFile lgf, String s){
         if (obj != null)
            s = obj.getClass().getName() + DELIMITER + s;
         else
            s = "unknown" + DELIMITER + s;

      Runtime run = Runtime.getRuntime();
      long mem = run.freeMemory() / 1024;

      Date d = new Date();

      s = mem + DELIMITER + d.toString() + DELIMITER + s;

      lgf.write(s, loglevel);
   }
//============================================

   /**
   * Display welcome message (and make initialisations).
   * You can inherit this to change welcomemsg &
   * loglevel, and to define more LogFiles...
   */
   synchronized public static void welcome(){
      System.out.println(welcomemsg);
   }
//============================================

   /**
   * Write on console
   *
   * @param s message
   */
   public static void writeCon(String s){
      System.out.println(s);
   }
//============================================
}
