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
//		writeTheContentsToATextFile(textAfterCleaning);
	}


	protected static boolean isContain(String source, String subItem){
	         String pattern = "\\b"+subItem+"\\b";
	         Pattern p=Pattern.compile(pattern);
	         Matcher m=p.matcher(source);
	         return m.find();
	    }
	
	protected static void writeTheContentsToATextFile(String allContentsInBetween) {
		  try (PrintWriter out = new PrintWriter("TS15066.txt")) {
			    out.println(allContentsInBetween);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	
	protected static String cleanFootAndSideNotes(String text) {
		/* TODO Code to clear Foot and Side notes from extracted text
		Do pattern matching to find sub strings and remove/extract all contents found after/before it.
		
		2 pages at a time.
		
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
			
			/*
			 * Text extraction from the first 2 pages of ISO doc i.e. page#1-2.
			 */

			allContentsInBetween = StringUtils.substringBetween(text, "\n"  , Constants.header);
			allContentsInBetween1 = StringUtils.substringBetween(text, Constants.subHeader, Constants.footerPattern2);

			allContentsInBetween2 = StringUtils.substringBetween(allContentsInBetween1, Constants.footerPattern1, Constants.subHeader);
			
			allContentsInBetween1=allContentsInBetween1.replace(allContentsInBetween2, "");
			allContentsInBetween1=allContentsInBetween1.replace(Constants.subHeader2,"");
			allContentsInBetween += allContentsInBetween1;
			
		}else if(text.contains(Constants.subHeader)) {
			System.out.println("Match found! ");
			/* To ensure this code works for new pages change/add 3 variables in Constants.java
			 * 1. footerPatternOdd#
			 * 2. footerPatternEven#
			 * 3. subHeader#
			 * similar to already existing ones.
			 * 
			 * Text extraction from the later pages of ISO doc i.e. page#3-4 onwards, always in pairs of odd-even.
			 */
			
			allContentsInBetween = StringUtils.substringBetween(text, Constants.subHeader, Constants.footerPattern5);
			
			//Remove allContentsInBetween from text.
			
			allContentsInBetween1 = StringUtils.substringBetween(text, Constants.subHeader, Constants.footerPattern6);
			allContentsInBetween1 = allContentsInBetween1.replace(allContentsInBetween, "");
			//Extract only content between the footerPattern3 and subHeader and remove this content from allContentsInBetween1
			
			allContentsInBetween2 = StringUtils.substringBetween(allContentsInBetween1, Constants.footerPattern5, Constants.subHeader);
			allContentsInBetween1 = allContentsInBetween1.replace(allContentsInBetween2, "");
			allContentsInBetween1 = allContentsInBetween1.replace(Constants.subHeader4, "");
			allContentsInBetween += allContentsInBetween1;
		}
		
		/*Remember for subsequent pages the complete header is not present, only "ISO/TS 15066:2016(E)"
		Check for this pattern, remove it and keep the other contents.
		*/
		return allContentsInBetween;
		
	}

}
