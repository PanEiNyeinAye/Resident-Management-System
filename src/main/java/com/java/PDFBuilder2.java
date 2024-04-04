package com.java;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.PdfWriter;
import com.java.model.LetterModel;

public class PDFBuilder2 extends AbstractPdfView {
 
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
            PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	response.setHeader("Content-Disposition", "inline; filename=\"Certification of Residence and Good Character.pdf\"");
        // get data model which is passed by the Spring container
        LetterModel r = (LetterModel)model.get("goodCharacterLetter");
        String stateOrRegionName = r.getStateOrRegionName();
        String townshipName = r.getTownshipName();
        String wardOrVillageName = r.getWardOrVillageName();
        String gender;
        String gender1;
        String gender2;
        if(r.getGender().equals("Female")){
        	gender=" daughter of U ";
            gender1=" Ms.";		
            gender2 = "she";
        }
        else {
        	gender=" son of U ";
        	gender1 = " Mr.";
        	gender2="he";
        }
        String fatherName = r.getFatherName();
        String residentName = r.getResidentName();
        String nrc = r.getNrcNo();
        String wardASName = r.getWardASName();
        
        Paragraph p1 = new Paragraph("Certification of Residence and Good Character", new
        		 Font(FontFamily.HELVETICA, 18, Font.BOLD|Font.UNDERLINE, new BaseColor(0, 0, 0)) );
        p1.setAlignment(Element.ALIGN_CENTER);
        p1.setSpacingAfter(72f);
        doc.add(p1);
        long millis=System.currentTimeMillis(); 
		java.sql.Date date = new java.sql.Date(millis); 
        Paragraph p2 = new Paragraph("Date: "+date+"");
        p2.setAlignment(Element.ALIGN_RIGHT);
        p2.setSpacingAfter(72f);
        doc.add(p2);
        Paragraph p3 = new Paragraph();
        p3.setTabSettings(new TabSettings(56f));
        p3.add(Chunk.TABBING);
        p3.add(new Chunk(stateOrRegionName+", "+townshipName+", "+wardOrVillageName+" resident,"+gender+fatherName+","
          		 +gender1+residentName+", National Identification Card No. "+nrc+" holder is known well. It is confirmed that "+gender2+" has good character and has no criminal record."));
        doc.add(p3);
      
        Paragraph p5 = new Paragraph(wardASName);
        p5.setSpacingBefore(72f);
        p5.setAlignment(Element.ALIGN_RIGHT);
        doc.add(p5);
        Paragraph p6= new Paragraph(wardOrVillageName+" Adminitrator");
        p6.setAlignment(Element.ALIGN_RIGHT);
        doc.add(p6);
        Paragraph p7= new Paragraph(townshipName);
        p7.setAlignment(Element.ALIGN_RIGHT);
        doc.add(p7);
        doc.close();
         
    }

}
