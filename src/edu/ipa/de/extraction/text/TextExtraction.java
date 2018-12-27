package edu.ipa.de.extraction.text;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;

public class TextExtraction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("/home/rfa-ag/Downloads/TextMining/Dataset/ISO-TS-15066.pdf"); 
		SectionTextExtraction secTextExtraction = new SectionTextExtraction();
		try {
			PDDocument document = PDDocument.load(file);
			PDFTextStripper pdfStripper = new PDFTextStripper();
			
			pdfStripper.setStartPage(7);
			pdfStripper.setEndPage(8);
			pdfStripper.getParagraphStart();
			
//			pdfStripper.setParagraphStart("/t");
//		    pdfStripper.setSortByPosition(true);
//
//
//		    for (String line: pdfStripper.getText(document).split(pdfStripper.getParagraphStart()))
//		            {
//		                System.out.println(line);
//		                System.out.println("********************************************************************");
//		            }
			
			String text = pdfStripper.getText(document);
//			System.out.println(text);
			
			String cleanedText = secTextExtraction.cleanFootAndSideNotes(text);
//			System.out.println(cleanedText);
//		    sectionIdentifier(text);
		    
			document.close();
			
			


		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

/*	private static String cleanFootAndSideNotes(String text) {
		 TODO Code to clear Foot and Side notes from extracted text
		Do pattern matching to find this string "TECHNICAL SPECIFICATION ISO/TS 15066:2016(E)"
		and remove all contents found after it.
		
		String allContentsInBetween = null;
		String allContentsInBetween1 = null;
		String allContentsInBetween2 = null;
		final String header = "TECHNICAL SPECIFICATION ISO/TS 15066:2016(E)";
		final String subHeader = "ISO/TS 15066:2016(E)";
		final String subHeader2 = "© ISO 2016 – All rights reserved 1ISO/TS 15066:2016(E)";
		final String footerPattern1 = "© ISO 2016 – All rights reserved 1";
		final String footerPattern2 = "2 © ISO 2016 – All rights reserved";
		
		//Check if the TEXT contains the header substring
		
		if(text.contains(header)) {
			System.out.println("Match found! ");
			System.out.println(isContain(text, subHeader));
			System.out.println(isContain(text, footerPattern2));
//			allContentsInBetween = StringUtils.substringBetween(text, "1 Scope",header);
			allContentsInBetween1 = StringUtils.substringBetween(text, subHeader, footerPattern2);
//			System.out.println(allContentsInBetween);
//			System.out.println(allContentsInBetween1);
			allContentsInBetween2 = StringUtils.substringBetween(allContentsInBetween1, footerPattern1, subHeader);
//			System.out.println(allContentsInBetween2);
			allContentsInBetween1=allContentsInBetween1.replace(allContentsInBetween2, "");
			allContentsInBetween1=allContentsInBetween1.replace(subHeader2,"");
			System.out.println(allContentsInBetween1);
		}
		
		Remember for subsequent pages the complete header is not present, only "ISO/TS 15066:2016(E)"
		Check for this pattern, remove it and keep the other contents.
		
		return allContentsInBetween;
		
	}
*/
	/*private static void sectionIdentifier(String text) {
		// TODO Auto-generated method stub
//		text = "Robots and robotic devices — Collaborative robots\n" + 
//				"1 Scope\n" + 
//				"This Technical Specification specifies safety requirements for collaborative industrial robot systems \n" + 
//				"and the work environment, and supplements the requirements and guidance on collaborative industrial \n" + 
//				"robot operation given in ISO 10218-1 and ISO 10218-2.\n" + 
//				"This Technical Specification applies to industrial robot systems as described in ISO 10218-1 and \n" + 
//				"ISO 10218-2. It does not apply to non-industrial robots, although the safety principles presented can be \n" + 
//				"useful to other areas of robotics.\n" + 
//				"NOTE This Technical Specification does not apply to collaborative applications designed prior to its publication\n"
//				+ 
//				"2 Normative references";
		
		 System.out.println(isContain(text,"1 Scope"));
		 System.out.println(isContain(text,"2 Normative references"));
		 String allContentsInBetween = StringUtils.substringBetween(text, "1 Scope","2 Normative references");
		 System.out.println(allContentsInBetween);
		 writeTheContentsToATextFile(allContentsInBetween);
	}*/

	protected static boolean isContain(String source, String subItem){
	         String pattern = "\\b"+subItem+"\\b";
	         Pattern p=Pattern.compile(pattern);
	         Matcher m=p.matcher(source);
	         return m.find();
	    }
	
	protected static void writeTheContentsToATextFile(String allContentsInBetween) {
		  try (PrintWriter out = new PrintWriter("TermsAndDefinitions.txt")) {
			    out.println(allContentsInBetween);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

}
