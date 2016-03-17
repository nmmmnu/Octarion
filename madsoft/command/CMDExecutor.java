package madsoft.command;

import java.io.*;
import java.util.*;

/**
* Class for organising commands into a one package.
* In memory of the Protos Commander from StarCraft :))))
* You must use CMD interface.
*
* @see madsoft.command.CMD
*/

public class CMDExecutor{
   /**
   * Hashtable with implemented commands
   * @see madsoft.command.CMD
   */
   public Hashtable com = new Hashtable();

   /**
   * stdin
   */
   DataInputStream is = null;

   /**
   * stdout
   */
   PrintStream ps = null;
//===============================================

   /**
   * Construct a CMDExecutor
   *
   * @param is stdin
   * @param ps stdout
   */
   public CMDExecutor(DataInputStream is, PrintStream ps){
      super();

      this.is = is;
      this.ps = ps;
   }
//=======================================

   /**
   * Adding a command
   *
   * @param x the command
   * @see madsoft.command.CMD
   */
   public void addCMD(CMD x){
      com.put(x.name(), x);
   }
//=======================================

   /**
   * Execute a command
   *
   * @param s the command name
   * @exception IOException throw by command
   * @see madsoft.command.CMD
   */
   public void execCMD(String s) throws IOException{
      CMD cm = (CMD) com.get(s);
      cm.cmd(is,ps);
   }
//===============================================
}
