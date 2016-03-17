package madsoft.ws;

import madsoft.util.*;
import java.util.*;

// This appears in Core Web Programming from
// Prentice Hall Publishers, and may be freely used
// or adapted. 1997 Marty Hall, hall@apl.jhu.edu.

class QueryStringParser extends CgiParser{
//===============================================
   public QueryStringParser(String queryString){
      super(queryString, "&", "=");
   }
//===============================================
}
//***********************************************
class CgiParser{
   private String data;
   private String delims1;
   private String delims2;
//===============================================
   public  MixProperties table = new MixProperties();
//===============================================
   public CgiParser(String data, String delims1, String delims2){
      this.data = data;
      this.delims1 = delims1;
      this.delims2 = delims2;
   }
//===============================================
   public MixProperties parse(){
      if (data == null)
         return table;

      StringTokenizer st = new StringTokenizer(data, delims1);

      String namevalue;
      String name;
      String value;

      while(st.hasMoreTokens()){
         namevalue = st.nextToken();

         StringTokenizer st1 = new StringTokenizer(namevalue, delims2);

         name = URLDecoder.decode(st1.nextToken());

         if (st1.hasMoreTokens())
            value = URLDecoder.decode(st1.nextToken());
         else
            value = "";

         table.put(Trans.localizeString(name,  Cfg.getIntProp("UNICODELANGUAGE")),
                   Trans.localizeString(value, Cfg.getIntProp("UNICODELANGUAGE")));
      }

      return table;
   }
//===============================================   
}
