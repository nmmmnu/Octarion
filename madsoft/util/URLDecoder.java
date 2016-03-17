package madsoft.util;


/**
* This appears in Core Web Programming from
* Prentice Hall Publishers, and may be freely used
* or adapted.
* <p>1997 Marty Hall, hall@apl.jhu.edu.
*/
public class URLDecoder{
   /**
   * Decode an URL
   */
   public static String decode(String encoded){
      StringBuffer decoded = new StringBuffer();
      int i = 0;
      char currentChar;
      char decodedChar;
      while(i < encoded.length()){
         currentChar = encoded.charAt(i);

         if (currentChar == '+'){
            decoded.append(" ");
            i = i + 1;

         }else if (currentChar == '%'){
            String charCode = encoded.substring(i+1, i+3);
            decodedChar = (char)Integer.parseInt(charCode, 16);
            decoded.append(decodedChar);
            i = i + 3;

         }else{
            decoded.append(currentChar);
            i = i + 1;
         }
      }

      return(decoded.toString());
   }
//===============================================
}
