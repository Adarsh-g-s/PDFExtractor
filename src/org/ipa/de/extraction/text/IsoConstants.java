package org.ipa.de.extraction.text;

import java.util.HashMap;
import java.util.Map;

public class IsoConstants {
	
	/*
	 * Constants pertaining to ISO 10218-1
	 */
	public static final String header = "INTERNATIONAL STANDARD ISO 10218-1:2011(E)";
	public static final String subHeader = "ISO 10218-1:2011(E)";
	public static final String sideNote1 = "B55EB1B3C7662F79D1B59483A53B9F2F82C98BEEB79395946AC566B6CDF0148328DA7E29C2DB4A9C98A56EB84751C09DF42982F151E73615912024BE33C078D0313FF49141000AFE4F2B010705BB25778CEF0775AAABBC9EE6736FDE5D94523CEC09BC81B38ED5B3A35AA131A497AF3187";
	
	/*
	 * Constants pertaining to ISO 10218-2
	 */
	public static final String header2 = "INTERNATIONAL STANDARD ISO 10218-2:2011(E)";
	public static final String subHeader2 = "ISO 10218-2:2011(E)";
	public static final String sideNote3 = "B55EB1B3C7662F79D1B59483A53B9F2F82C98BEEB79395946AC566B6CDF0148328DA7E29C2DB4A9C98A56EB84751C09DF42982F151E73615912024BE33C078D0313FF49141000AFE4F2B010705BB25778CEF0775AAABBC9EE4756ED05D94523CEC09BC81B38ED5B3A35AA131A497AA3B87";
	
	/*
	 * Constants common to ISO 10218-1 and ISO 10218-2
	 * Hash map to store odd and even foot note pages
	 */
	public static final Map<String, String> singleFooterPatternMap = new HashMap<String, String>();
	static{
		
		for(int i=1;i<=Integer.parseInt(PropertiesHandler.getPropertyValue("END_PAGE"));i++) {
		
			singleFooterPatternMap.put("footerPattern"+i, "© ISO 2011 – All rights reserved "+i);
			++i;
			singleFooterPatternMap.put("footerPattern"+i, i+" © ISO 2011 – All rights reserved");
			
		
	}
	}
	
	
	
}
