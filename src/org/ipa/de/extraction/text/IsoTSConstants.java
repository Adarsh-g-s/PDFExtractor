package org.ipa.de.extraction.text;

import java.util.HashMap;
import java.util.Map;

public class IsoTSConstants {
	
	public static final String header = "TECHNICAL SPECIFICATION ISO/TS 15066:2016(E)";
	public static final String subHeader = "\n"+"ISO/TS 15066:2016(E)"+"\n";
	public static final String indexHeader = "ISO/TS 15066:2016(E)";
	public static final String tsFooterIndex = "© ISO 2016 – All rights reserved iii";
	
	
	  /*Constants for ISO TS 15066
	  Hash map to store odd and even foot note pages*/
	 
	public static final Map<String, String> tsFooterPatternMap = new HashMap<String, String>();
	static{
		
		for(int i=1;i<=Integer.parseInt(PropertiesHandler.getPropertyValue("END_PAGE"));i++) {
		
			tsFooterPatternMap.put("footerPattern"+i, "© ISO 2016 – All rights reserved "+i);
			++i;
			tsFooterPatternMap.put("footerPattern"+i, i+" © ISO 2016 – All rights reserved");
		
		}
	}
	
	public static final String sideNote1 = "B55EB1B3C7662F79D1B59483A53B9F2F82C98BEEB79395946AC566B6CDF0148328DA7E29C2DB4A9C98A56EB84751C09DF42982F151E73615912024BE33C078D0313FF49141000AFE4F2B010705BB25778CEF0775AAABBB96E5706ADD5D94523CEC09BC81B38ED5B3A256A131A197AE3E87";
	public static final String sideNote2 = "N\n" + 
			"o\n" + 
			"rm\n" + 
			"en\n" + 
			"-D\n" + 
			"o\n" + 
			"w\n" + 
			"n\n" + 
			"lo\n" + 
			"ad\n" + 
			"-B\n" + 
			"eu\n" + 
			"th\n" + 
			"-F\n" + 
			"ra\n" + 
			"u\n" + 
			"n\n" + 
			"h\n" + 
			"o\n" + 
			"fe\n" + 
			"r \n" + 
			"IP\n" + 
			"A\n" + 
			" F\n" + 
			"ac\n" + 
			"h\n" + 
			"in\n" + 
			"fo\n" + 
			"rm\n" + 
			"at\n" + 
			"io\n" + 
			"n\n" + 
			" u\n" + 
			". B\n" + 
			"ib\n" + 
			"lio\n" + 
			"th\n" + 
			"ek\n" + 
			"-K\n" + 
			"d\n" + 
			"N\n" + 
			"r.\n" + 
			"54\n" + 
			"19\n" + 
			"86\n" + 
			"7-\n" + 
			"L\n" + 
			"fN\n" + 
			"r.\n" + 
			"74\n" + 
			"02\n" + 
			"55\n" + 
			"40\n" + 
			"01\n" + 
			"-2\n" + 
			"01\n" + 
			"6-\n" + 
			"02\n" + 
			"-1\n" + 
			"9 \n" + 
			"11\n" + 
			":0\n" + 
			"6";

}
