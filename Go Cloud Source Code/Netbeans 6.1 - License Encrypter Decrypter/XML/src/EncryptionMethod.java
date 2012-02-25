 /*
 	DW/BS
	20020204
	EncryptionMethod.java
	Listing 16
	Authors the <EncryptionMethod> element.
 */

import org.w3c.dom.*;
import javax.xml.parsers.*;

public class EncryptionMethod{
	
	// Element to hold complete XML Structure.
	private Element encMethod = null;
	private Document doc = null;

	// Element will be appended in Document only once.
	private boolean elementAppendedToDoc = false;

	// Constructor
	public EncryptionMethod (Document document) {
		doc = document;
		encMethod = doc.createElement("EncryptionMethod");
	}// End EncryptionMethod()
	
	// Set the name of Algorithm.
	public void setAlgorithm(int algoNo){
		encMethod.setAttribute("Algorithm", AlgoNames.getAlgoNSValue(algoNo));
	}// End setAlgorithm()
	
	// Get the complete Document Sturcture.
	public Document getEncMethod() {
		if (elementAppendedToDoc == false) {
			doc.appendChild(encMethod);
			elementAppendedToDoc = true;
		}
		return doc;
	}// End getEncMethod()
}// End Class EncryptionMethod