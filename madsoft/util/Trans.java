package madsoft.util;
/**
* Type transfere library
*/
public class Trans{
   /**
   * BG UNICODE Constant
   * @see #localizeChar
   * @see #localizeString
   */
   public final static int UNICODEBULGARIA = (int) 'À' - 192; //848 za Bulgaria
//=================================================
   public static String int2str(int x){
      Integer i = new Integer(x);
      return i.toString();
   }
//=================================================
   public static int str2int(String x){
      Integer i = new Integer(x);
      return i.intValue();
   }
//=================================================

   /**
   * string to int with default
   */
   public static int str2int(String x, int d){
      Integer i;
      try{
         i = new Integer(x);
      }catch (Exception e){
         i = new Integer(d);
      }
      return i.intValue();
   }
//=================================================

   /**
   * IP Addr to string with a IP mask constant.
   *
   * Unalike java.net.InetAddress.toString this function not return the DNS entry
   *
   * When IP mask constant < 3 function return for example:
   * <pre>
   * 3    1.1.1.1
   * 2    1.1.1.*
   * 1    1.1.*.*
   * 0    1.*.*.*
   * </pre>
   * @param x IP Addr
   * @param a IP mask constant
   * @return IP to String
   */
   public static String ip2str(java.net.InetAddress x, int a){
      String s = "";
      byte[] ip = x.getAddress();

      for (int i = 0;i < 4;i++){
         int d = (ip[i] > 0 ) ? ip[i] : ip[i] + 256;

         if (i > a)
            s = s + "*";
         else
            s = s + d;

         if (i < (4 - 1))
            s = s + ".";
      }

      return s;
   }
//=================================================

   /**
   * IP Addr to string without a DNS entry.
   */
   public static String ip2str(java.net.InetAddress x){
      return ip2str(x,3);
   }

//=================================================

   /**
   * Char Localization.
   *
   * Localize a standard char (00 - FF) into UNICODE char (0000 - FFFF),
   * with "hard" adding the integer value ( result = c + x ).
   * <p>Hey i;m not sure that this will work with all languages!!!!!!!!
   * @param c standard char (old style)
   * @param x UNICODE constant
   * @return UNICODE char
   * @see #localizeString
   * @see #UNICODEBULGARIA
   */
   public static char localizeChar(char c, int x){
      int i = (int) c;
      if (i < 128)
         return c;
      i = i + x;
      return (char) i;
   }
//=================================================

   /**
   * String Localization.
   *
   * Make localization with localizeChar function.
   * @param c standard string (old style)
   * @param x UNICODE constant
   * @return UNICODE string
   * @see #localizeChar
   * @see #UNICODEBULGARIA
   */
   public static String localizeString(String s, int x){
      StringBuffer sb = new StringBuffer();
      for (int i = 0;i < s.length();i++)
         sb.append(localizeChar(s.charAt(i), x));

      return sb.toString();
   }
//=================================================

   /**
   * Right formating.
   *
   * for example
   * <pre>'xxx' -> '   xxx'</pre>
   * @param s Format var
   * @param i positions
   * @return Formated String
   */
   public static String rightFormat(String s, int i){
      while (s.length() < i)
         s = " " + s;

      return s;
   }
//=================================================

   /**
   * Right formating.
   *
   * for example
   * <pre>'xxx' -> '   xxx'</pre>
   * @param s Format var
   * @param i positions
   * @return Formated String
   */
   public static String rightFormat(long s, int i){
      Long l = new Long(s);
      return rightFormat(l.toString(),i);
   }
}
