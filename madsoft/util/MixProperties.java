package madsoft.util;

import java.util.Properties;

/**
* Class with Properties extentions
*/
public class MixProperties extends Properties{

   /**
   * Construct MixProperties object
   */
   public MixProperties(){
      super();
   }
//==========================================

   /**
   * Construct MixProperties object with defaults
   *
   * @param p Default values
   */
   public MixProperties(Properties p){
      super(p);
   }
//==========================================

   /**
   * Return int property value
   *
   * @param k Property name
   * @return int value
   */
   public int getIntProperty(String k){
      return Trans.str2int(super.getProperty(k));
   }
//==========================================

   /**
   * Return int property value with default value
   *
   * @param k Property name
   * @param d Default value
   * @return int value
   */
   public int getIntProperty(String k, int d){
      String tmp = super.getProperty(k);
      return (  (tmp == null) ? d : Trans.str2int(tmp)  );
   }
//==========================================

   /**
   * Insert int property value
   *
   * @param k Property name
   * @param d value
   */
   public void putInt(String k, int d){
      super.put(k,Trans.int2str(d));
   }
//==========================================

   /**
   * Return boolean property value
   *
   * @param k Property name
   * @return boolean value
   */
   public boolean getBooleanProperty(String k){
      int i = Trans.str2int(super.getProperty(k),0);
      return (i != 0);
   }
//==========================================

   /**
   * Return boolean property value with default value
   *
   * @param k Property name
   * @param d Default property name
   * @return boolean value
   */
   public void putBoolean(String k, boolean d){
      super.put(k,Trans.int2str( (d) ? 1 : 0 ));
   }
//==========================================
}
