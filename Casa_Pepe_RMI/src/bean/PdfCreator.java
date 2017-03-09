package bean;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class PdfCreator {

    public static final String MOVIE
        = "\u0644\u0648\u0631\u0627\u0646\u0633 \u0627\u0644\u0639\u0631\u0628";
    /** Correct movie title. */
    public static final String MOVIE_WITH_SPACES
        = "\u0644 \u0648 \u0631 \u0627 \u0646 \u0633   \u0627 \u0644 \u0639 \u0631 \u0628";
 
    /**
     * Creates a PDF document.
     * @param filename the path to the new PDF document
     * @throws DocumentException 
     * @throws IOException 
     * @throws    DocumentException 
     * @throws    IOException
     */
    public void createPdf(String filename,ArrayList<Plat> listPlats) {
    	 Document document = new Document();
    	    try 
    	    {
    	      PdfWriter.getInstance(document, new FileOutputStream(filename));
    	      document.open();
    	      Paragraph title = new Paragraph("Casa Pepe",FontFactory.getFont(FontFactory.COURIER,28f,Font.BOLD));
    	      title.setAlignment(Element.ALIGN_CENTER);
    	      document.add(title);
	    	  document.add(new Paragraph(" "));
	    	  document.add(new Paragraph(" "));
    	      Iterator<Plat> iter = listPlats.iterator();
    	      while(iter.hasNext()){
    	    	  Plat p = iter.next();
    	    	  Paragraph par1 = new Paragraph(p.getNom()+" ("+p.getGroupe().getNom()+") "+p.getPrix()+" € :");
    	    	  Paragraph par2 = new Paragraph(p.getDescription());
    	    	  par2.setIndentationLeft(40f);
    	    	  document.add(par1);
    	    	  document.add(par2);
    	    	  document.add(new Paragraph(" "));
    	      }
    	    } catch (DocumentException de) {
    	      de.printStackTrace();
    	    } catch (IOException ioe) {
    	      ioe.printStackTrace();
    	    }
    	   
    	    document.close();
    }
}
