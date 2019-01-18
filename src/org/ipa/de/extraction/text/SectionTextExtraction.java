package org.ipa.de.extraction.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class SectionTextExtraction {

	static TextExtraction textExtraction = new TextExtraction();	
	
	
	protected String sectionIdentifier(String text) {
		
		
		String allContentsInBetween = StringUtils.substringBetween(text, PropertiesHandler.getSectionPropertyValue("START_SECTION"),PropertiesHandler.getSectionPropertyValue("END_SECTION"));
		 
		return allContentsInBetween;
	}
	
	protected static String preProcessText(String text) {
		String textContents = text;
		//To remove special characters from the text		
		textContents = textContents.replaceAll("[^.,()a-zA-Z0-9 ]","");
		return textContents;
	}
	
/*
 * Identify the subsections and extract their content using regex
 */
	protected String subSectionIdentifier(String text) {
		String allContentsInBetween = "";
		
//		This might not work as text in b/w sections will look like e.x. ISO 10218 1 and the ... the text from "1 and the" would be skipped
		Matcher m = Pattern.compile(
//				"[^0-9 ][^A-Za-z]+(.*)[^0-9 ][^A-Za-z]+"
				"[^0-9 A-Za-z]+(.*)[^0-9 A-Za-z]+"
       ).matcher(text);
		while(m.find()){
			allContentsInBetween += m.group(1);
		}
		
		return allContentsInBetween;
	}
}
