package com.java;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;
import com.java.model.LetterModel;

public class PDFBuilder3 extends AbstractPdfView {
 
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
            PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	response.setHeader("Content-Disposition", "inline; filename=\"Certification of Clearance from Criminal Offences.pdf\"");
        // get data model which is passed by the Spring container
        LetterModel r = (LetterModel)model.get("clearanceLetter");
        String stateOrRegionName = r.getStateOrRegionName();
        String townshipName = r.getTownshipName();
        String wardOrVillageName = r.getWardOrVillageName();
        String gender;
        String gender1;
       
        int age = r.calculateAge(r.getDob().toLocalDate());
        if(r.getGender().equals("Female")){
        	gender=" daughter of U ";
            gender1=" Ms.";		
        }
        else {
        	gender=" son of U ";
        	gender1 = " Mr.";
        }
        String fatherName = r.getFatherName();
        String residentName = r.getResidentName();
        String nrc = r.getNrcNo();
        String policeOfficerName = r.getPoliceOfficerName();
        
        Paragraph p1 = new Paragraph("Certification of Clearance from Criminal Offences", new
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
        p3.add(new Chunk("According to the "+townshipName+" police station's criminal records, "+stateOrRegionName+", "+townshipName+", "+wardOrVillageName+" resident,"+gender+fatherName+", the age of "+age+" years old, National Identification Card No. "+nrc+" holder,"+gender1+residentName+" is clear from criminal records."));
        doc.add(p3);
      
        Paragraph p5 = new Paragraph(policeOfficerName);
        p5.setSpacingBefore(72f);
        p5.setAlignment(Element.ALIGN_RIGHT);
        doc.add(p5);
        Paragraph p4= new Paragraph("Township Officer");
        p4.setAlignment(Element.ALIGN_RIGHT);
        doc.add(p4);
        
        Paragraph p6= new Paragraph("Myanmar Police Force");
        p6.setAlignment(Element.ALIGN_RIGHT);
        doc.add(p6);
        Paragraph p7= new Paragraph(townshipName+" Police Station");
        p7.setAlignment(Element.ALIGN_RIGHT);
        doc.add(p7);
        doc.close();
         
    }

}
