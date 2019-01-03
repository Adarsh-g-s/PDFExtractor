package org.ipa.de.extraction.text;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class DocumentTextExtraction {
	
	protected String extractContentFromDocuments(){
		File file = new File(Constants.FILE_LOC);
		String textContentToBeCleaned = null;
		
		try {
			PDDocument document = PDDocument.load(file);
			PDFTextStripper pdfStripper = new PDFTextStripper();
			
			pdfStripper.setStartPage(9);
			pdfStripper.setEndPage(10);
			
			textContentToBeCleaned = pdfStripper.getText(document);
			
			document.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		return textContentToBeCleaned;
	}
	
}
