package org.ipa.de.extraction.text;

import org.apache.commons.lang3.StringUtils;

public class SectionTextExtraction {

	static TextExtraction textExtraction = new TextExtraction();	
	
	
	protected static void sectionIdentifier(String text) {
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
		
		 System.out.println(textExtraction.isContain(text,"1 Scope"));
		 System.out.println(textExtraction.isContain(text,"2 Normative references"));
		 String allContentsInBetween = StringUtils.substringBetween(text, "1 Scope","2 Normative references");
		 System.out.println(allContentsInBetween);
		 textExtraction.writeTheContentsToATextFile(allContentsInBetween);
	}
}
