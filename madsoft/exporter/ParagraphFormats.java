package madsoft.exporter;

public class ParagraphFormats{
   public static final byte ALIGNDEFAULT = 0;
   public static final byte ALIGNLEFT    = 1;
   public static final byte ALIGNCENTER  = 2;
   public static final byte ALIGNRIGHT   = 3;
   public static final byte ALIGNJUSTIFY = 4; // why not ?!?!?!
//=========================================================
   public FontStyle fontstyle = new FontStyle();

   public byte      fontsize = 0; // 0 default
                                  // 1 to 6 - HTML headings

   public byte      align    = ALIGNDEFAULT;
//=========================================================
   public ParagraphFormats(){
      super();
   }
//=========================================================
   public ParagraphFormats(byte fontsize){
      this();
      this.fontsize = fontsize;
   }
//=========================================================
   public ParagraphFormats(byte fontsize, byte align){
      this(fontsize);
      this.align = align;
   }
//=========================================================
   public ParagraphFormats(byte fontsize, byte align, FontStyle fontstyle){
      this(fontsize,align);
      this.fontstyle = fontstyle;
   }
//=========================================================
   String atag(){
      String s = "";

      switch (align){
         case ALIGNLEFT   : s = " Align = Left";    break;
         case ALIGNRIGHT  : s = " Align = Right";   break;
         case ALIGNCENTER : s = " Align = Center";  break;
         case ALIGNJUSTIFY: s = " Align = Justify"; break;
      }

      return s;
   }
//=========================================================
   String tag(boolean b){
      String s = "";

      if (b){
         switch (fontsize){
            case 1:s = s + "<H1"; break;
            case 2:s = s + "<H2"; break;
            case 3:s = s + "<H3"; break;
            case 4:s = s + "<H4"; break;
            case 5:s = s + "<H5"; break;
            case 6:s = s + "<H6"; break;

            default:s = s + "<P";
         }

         s = s + atag() + ">";

         s = s + fontstyle.tag(b);
      }else{
         s = s + fontstyle.tag(b);

         switch (fontsize){
            case 1:s = s + "</H1>"; break;
            case 2:s = s + "</H2>"; break;
            case 3:s = s + "</H3>"; break;
            case 4:s = s + "</H4>"; break;
            case 5:s = s + "</H5>"; break;
            case 6:s = s + "</H6>"; break;

            default:s = s + "</P>";
         }
      }

      return s;
   }
//=========================================================
}
