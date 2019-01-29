package org.ipa.de.extraction.text;

import org.apache.commons.lang3.StringUtils;

public class IndexTextExtraction {

	public String extractIndexContents(String text) {
		
		String allIndexContents = "";
		
		allIndexContents = StringUtils.substringBetween(text, IsoTSConstants.indexHeader, IsoTSConstants.tsFooterIndex);
		
		return allIndexContents;
	}

}
