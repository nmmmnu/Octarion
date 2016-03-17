package madsoft.exporter;

import java.io.*;
/**
* This class provide export to ASCII text format
*/
public class TextExporter extends AbstractExporter{
    /**
    * Construct an exporter
    */
    public TextExporter(OutputStream stream){
       super(stream);
    }
//=========================================================

    /**
    * End paragraph
    */
    public void endparagraph(){
       outputLn();
    }
//=========================================================
}

