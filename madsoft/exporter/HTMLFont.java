package madsoft.exporter;
                         
public class HTMLFont{
   String face = null;
   String size = null;
   String color = null;
//=========================================================
   public HTMLFont(){
      super();
   }
//=========================================================
   public HTMLFont(String face,String size,String color){
      this();
      this.face = face;
      this.size = size;
      this.color = color;
   }
//=========================================================
   String tag(boolean b){
      String s = "";

      if (b){
         s = s + "<Font";

         if (face != null)
            s = s + " Face = \"" + face + "\"";

         if (size != null)
            s = s + " Size = \"" + size + "\"";

         if (face != null)
            s = s + " Color = \"" + color + "\"";

         s = s + '>';
      }else
         s = s + "</Font>";

      return s;
   }
//=========================================================
}
