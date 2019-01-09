package org.ipa.de.extraction.text;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class DocumentTextExtraction {
	
	protected String extractContentFromDocuments(){
		
		File file = new File(PropertiesHandler.getPropertyValue("ISOFILE_LOC"));
		String textContentToBeCleaned = null;
		
		try {
			PDDocument document = PDDocument.load(file);
			PDFTextStripper pdfStripper = new PDFTextStripper();
			
			/*
			 * Start of the PDF(ISO TS 15066) is from page#7, pass consecutive pages at once in the odd-even form e.x. 7-8, 9-10 etc
			 */
			
			pdfStripper.setStartPage(Integer.parseInt(PropertiesHandler.getPropertyValue("START_PAGE")));
			pdfStripper.setEndPage(Integer.parseInt(PropertiesHandler.getPropertyValue("END_PAGE")));
			
			textContentToBeCleaned = pdfStripper.getText(document);
			
			document.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		return textContentToBeCleaned;
	}
}
