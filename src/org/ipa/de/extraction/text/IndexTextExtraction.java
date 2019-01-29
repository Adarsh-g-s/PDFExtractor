package org.ipa.de.extraction.text;

import org.apache.commons.lang3.StringUtils;

public class IndexTextExtraction {

	public String extractIndexContents(String text) {
		String allIndexContents = "";
		
		if(text.contains(IsoTSConstants.indexHeader)) {
			
		
			allIndexContents = StringUtils.substringBetween(text, IsoTSConstants.indexHeader, IsoTSConstants.tsFooterIndex);
		
			
		}else if(text.contains(IsoConstants.subHeader)) {
			
			allIndexContents = StringUtils.substringBetween(text, IsoConstants.isoFooterIndex, IsoConstants.sideNote1);
			
		}else if(text.contains(IsoConstants.subHeader2)) {
			
			allIndexContents = StringUtils.substringBetween(text, IsoConstants.isoFooterIndex, IsoConstants.sideNote3);
		}
		return allIndexContents;
	}

}
