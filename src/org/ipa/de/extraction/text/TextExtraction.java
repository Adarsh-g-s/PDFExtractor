package org.ipa.de.extraction.text;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class TextExtraction {

	public static void main(String[] args) {

		DocumentTextExtraction documentTE = new DocumentTextExtraction();
		SectionTextExtraction sectionTE = new SectionTextExtraction();
		IndexTextExtraction indexTE = new IndexTextExtraction();
		String textBeforeCleaning = null;
		String textAfterCleaning = "";
		String sectionWiseText = "";
		String indexContents = "";



		/*
		 * Extract contents from PDF to text file.
		 */

		textBeforeCleaning = documentTE.extractContentFromDocuments();
		//		System.out.println(textBeforeCleaning);
		/*
		 * Extract index names from the ISO documents and populate it to a txt file 
		 */
		indexContents = indexTE.extractIndexContents(textBeforeCleaning);
		//		System.out.println(indexContents);
		writeTheContentsToATextFile(indexContents);

		try {
			textAfterCleaning = cleanFootAndSideNotes(textBeforeCleaning);
			//		System.out.println(textAfterCleaning);
			writeTheContentsToATextFile(textAfterCleaning);
		}catch(NullPointerException npe) {
			System.err.println("Please change the (start & end) page numbers to the format of odd-even(consecutive pages)");
		}

		/*	
	  Extract section wise content from text file.
		 */	 

		textAfterCleaning = documentTE.extractContentFromTextFile();
		sectionWiseText = sectionTE.sectionIdentifier(textAfterCleaning);
		//		textAfterCleaning = sectionTE.sectionIdentifier(text);
		//		System.out.println(textAfterCleaning);
		if(sectionWiseText == null) {
			System.out.println("Please check that there is no addtional space in START or END section in section.properties file");
		}
		System.out.println(sectionWiseText);
		writeTheSectionContentsToATextFile(sectionWiseText);
	}

	protected static boolean isContain(String source, String subItem){
		String pattern = "\\b"+subItem+"\\b";
		Pattern p=Pattern.compile(pattern);
		Matcher m=p.matcher(source);
		return m.find();
	}

	protected static void writeTheContentsToATextFile(String allContentsInBetween) {
		try (PrintWriter out = new PrintWriter(new FileOutputStream(new File(PropertiesHandler.getPropertyValue("OutFile")), true))) {
			out.println(allContentsInBetween);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected static void writeTheSectionContentsToATextFile(String allContentsInBetween) {
		try (PrintWriter out = new PrintWriter(new FileOutputStream(new File(PropertiesHandler.getSectionPropertyValue("OutFile")), true))) {
			out.println(allContentsInBetween);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/* Code to clear Foot and Side notes from extracted text
	Do pattern matching to find sub strings and remove/extract all contents found after/between it.
	@return entire document content of the corresponding ISO type after data cleaning
	 */
	protected static String cleanFootAndSideNotes(String text) throws NullPointerException {

		String allContentsInBetween = "";
		String allContentsInBetween1 = "";

		//Check if the TEXT contains the header substring of ISO TS

		//Code to extract contents from ISO TS

		if(text.contains(IsoTSConstants.header)) {
			System.out.println("Match found! ");
			//Subtracting the last page given by 6 in order to clean the first 6 pages of ISO docs
			for(int i=1;i<=(Integer.parseInt(PropertiesHandler.getPropertyValue("END_PAGE"))-6);i++) {
				//Only for the I page, use this methodology

				if(i==1) {
					allContentsInBetween = StringUtils.substringBetween(text, "\n"  , IsoTSConstants.header);
					++i;
					//One time replacement of garbage Strings
					text = text.replace(IsoTSConstants.subHeader, "");
					text = text.replace(IsoTSConstants.sideNote1, "");
				}
				//From II page onwards, to extract contents
				allContentsInBetween1 = StringUtils.substringBetween(text, IsoTSConstants.sideNote2, IsoTSConstants.tsFooterPatternMap.get("footerPattern"+i));

				allContentsInBetween += allContentsInBetween1;
				//replace every time single occurrence of this subString
				text = text.replaceFirst(IsoTSConstants.sideNote2, "");
			}
			System.out.println(allContentsInBetween);

			//Code to extract contents from ISO 10218-1
		}else if(text.contains(IsoConstants.header)) {
			System.out.println("Match found! ");
			//Subtracting the last page given by 6 in order to clean the first 6 pages of ISO docs
			for(int i=1;i<=(Integer.parseInt(PropertiesHandler.getPropertyValue("END_PAGE"))-6);i++) {

				allContentsInBetween += StringUtils.substringBetween(text, IsoConstants.singleFooterPatternMap.get("footerPattern"+i), IsoConstants.sideNote1);
				++i;
				allContentsInBetween1 = StringUtils.substringBetween(text, IsoConstants.singleFooterPatternMap.get("footerPattern"+i), IsoConstants.sideNote1);
				allContentsInBetween += allContentsInBetween1;

			}
			System.out.println(allContentsInBetween);

			//Code to extract contents from ISO 10218-2
		}else if(text.contains(IsoConstants.header2)) {
			System.out.println("Match found! ");
			//Subtracting the last page given by 6 in order to clean the first 6 pages of ISO docs
			for(int i=1;i<=(Integer.parseInt(PropertiesHandler.getPropertyValue("END_PAGE"))-6);i++) {

				allContentsInBetween += StringUtils.substringBetween(text, IsoConstants.singleFooterPatternMap.get("footerPattern"+i), IsoConstants.sideNote3);
				++i;
				//Extra condition to ensure null doesnt get printed when the last page# is odd. 
				if(i<=(Integer.parseInt(PropertiesHandler.getPropertyValue("END_PAGE"))-6)) {
					allContentsInBetween1 = StringUtils.substringBetween(text, IsoConstants.singleFooterPatternMap.get("footerPattern"+i), IsoConstants.sideNote3);
				}
				allContentsInBetween += allContentsInBetween1;
				allContentsInBetween1 = "";
			}	
			System.out.println(allContentsInBetween);
		}

		return allContentsInBetween;

	}

}
