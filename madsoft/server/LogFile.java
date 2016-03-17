package madsoft.server;

import  java.io.*;

/**
* Log file
*
* @see Log
*/
public class LogFile{
   /**
   * Log file ext
   */
   public static final String LOGFILEEXT = "log";

   /**
   * Log level
   */
   int         loglevel;

   /**
   * Log can write on console (stdout)
   */
   boolean     writeonconsole = false;

   /**
   * Log print stream, usually connected to a file.
   *
   * If this is equal to null, log cant write to file
   */
   PrintStream ps;
   //============================================

   /**
   * Construct new LogFile.
   *
   * This LogFile will not write on console
   * @param filename name of the file without an ext
   * @param loglevel loglevel
   */
   public LogFile(String filename, int loglevel){
      this.loglevel = loglevel;

      if (filename != null){
         try{
            ps = new PrintStream(new FileOutputStream(filename + "." + LOGFILEEXT, true));
         }catch (Exception e){
            System.out.println("CAN'T CREATE LOG FILE " + filename);
            ps = null;
         }
      }else
         ps = null;
   }
   //============================================

   /**
   * Construct new LogFile
   *
   * @param filename name of the file without an ext
   * @param loglevel loglevel
   * @param writeonconsole Log can write on console
   */
   public LogFile(String filename, int loglevel, boolean writeonconsole){
      this(filename, loglevel);

      this.writeonconsole = writeonconsole;
   }
   //============================================

   /**
   * Write to log
   *
   * @param s message
   */
   public void write(String s){
      if (ps != null) 
         ps.println(s);
   }
   //============================================

   /**
   * Write to log
   *
   * @param s message
   * @param loglevel loglevel, if it is less that this.loglevel, LogFile will not write any
   */
   public void write(String s, int loglevel){
      if (loglevel >= this.loglevel){
         write(s);

         if (writeonconsole)
            System.out.println(s);
      }
   }
   //============================================
}

