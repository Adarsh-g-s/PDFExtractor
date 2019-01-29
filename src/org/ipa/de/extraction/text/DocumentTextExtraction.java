package org.ipa.de.extraction.text;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class DocumentTextExtraction {

	protected String extractContentFromDocuments(){

		File file = new File(PropertiesHandler.getPropertyValue("ISOFILE_LOC"));
		String textContentToBeCleaned = null;

		try {
			PDDocument document = PDDocument.load(file);
			PDFTextStripper pdfStripper = new PDFTextStripper();

			pdfStripper.setStartPage(Integer.parseInt(PropertiesHandler.getPropertyValue("START_PAGE")));
			pdfStripper.setEndPage(Integer.parseInt(PropertiesHandler.getPropertyValue("END_PAGE")));

			textContentToBeCleaned = pdfStripper.getText(document);

			document.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return textContentToBeCleaned;
	}

	protected String extractContentFromTextFile() {

		String textContent = "";
		try {
			String path = PropertiesHandler.getSectionPropertyValue("ISOTXTFILE_LOC");
			Path pathToFile = FileSystems.getDefault().getPath(path);
			textContent = new String(Files.readAllBytes(pathToFile));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return textContent;

	}
}
