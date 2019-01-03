package org.ipa.de.extraction.text;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class TextExtraction {

	public static void main(String[] args) {
		
		DocumentTextExtraction documentTE = new DocumentTextExtraction();
		String textBeforeCleaning = null;
		String textAfterCleaning = null;
		
		textBeforeCleaning = documentTE.extractContentFromDocuments();
//		System.out.println(textBeforeCleaning);
		
		textAfterCleaning = cleanFootAndSideNotes(textBeforeCleaning);
		System.out.println(textAfterCleaning);

	}


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
	
	protected static String cleanFootAndSideNotes(String text) {
		/* TODO Code to clear Foot and Side notes from extracted text
		Do pattern matching to find this string "TECHNICAL SPECIFICATION ISO/TS 15066:2016(E)"
		and remove all contents found after it.
		*/
		String allContentsInBetween = null;
		String allContentsInBetween1 = null;
		String allContentsInBetween2 = null;
		
//		System.out.println(text);
		//Check if the TEXT contains the header substring
		
		if(text.contains(Constants.header)) {
			System.out.println("Match found! ");
//			System.out.println(isContain(text, Constants.subHeader));
//			System.out.println(isContain(text, Constants.footerPattern1));
//			System.out.println(isContain(text, Constants.footerPattern2));

			allContentsInBetween = StringUtils.substringBetween(text, Constants.sideNote2, Constants.header);
			allContentsInBetween1 = StringUtils.substringBetween(text, Constants.subHeader, Constants.footerPattern2);

			allContentsInBetween2 = StringUtils.substringBetween(allContentsInBetween1, Constants.footerPattern1, Constants.subHeader);
			
			allContentsInBetween1=allContentsInBetween1.replace(allContentsInBetween2, "");
			allContentsInBetween1=allContentsInBetween1.replace(Constants.subHeader2,"");
			allContentsInBetween += allContentsInBetween1;
			
		}else if(text.contains(Constants.subHeader)) {
			System.out.println("Match found! ");
			
			
			allContentsInBetween = StringUtils.substringBetween(text, Constants.subHeader, Constants.footerPattern3);
			
			//Remove allContentsInBetween from text.
			
			allContentsInBetween1 = StringUtils.substringBetween(text, Constants.subHeader, Constants.footerPattern4);
			allContentsInBetween1 = allContentsInBetween1.replace(allContentsInBetween, "");
			//Extract only content between the footerPattern3 and subHeader and remove this content from allContentsInBetween1
			
			allContentsInBetween2 = StringUtils.substringBetween(allContentsInBetween1, Constants.footerPattern3, Constants.subHeader);
			allContentsInBetween1 = allContentsInBetween1.replace(allContentsInBetween2, "");
			allContentsInBetween1 = allContentsInBetween1.replace(Constants.subHeader3, "");
			allContentsInBetween += allContentsInBetween1;
		}
		
		/*Remember for subsequent pages the complete header is not present, only "ISO/TS 15066:2016(E)"
		Check for this pattern, remove it and keep the other contents.
		*/
		return allContentsInBetween;
		
	}

}
