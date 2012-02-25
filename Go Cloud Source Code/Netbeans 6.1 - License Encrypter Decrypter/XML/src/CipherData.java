 /*
 	DW/BS
	20020204
	CipherData.java
	Listing 18
	Authors the <CipherData> element.
 */

import org.w3c.dom.*;

public class CipherData {
	
	// We will create child elements within this document.
	private Document doc = null;
	
	// The main structure.
	private Element cipherData = null;

	// Element will be appended in Document only once.
	private boolean elementAppendedToDoc = false;
	
	// Constructor
	public CipherData(Document document){
		doc = document;
		cipherData = doc.createElement("CipherData");

		elementAppendedToDoc = false;
	}// End CipherData()

	// Sets the encrypted Data inside <CipherValue> Child tag.
	public void setValue(String value){
		Element tempElem = doc.createElement("CipherValue");
		tempElem.appendChild(doc.createTextNode(value));
		cipherData.appendChild(tempElem);
		
	}// End setValue()

	// Adds <CipherReference> element inside CipherData element.
	// Its Attribute URI is passed as parameter.
	// <Transform> element is optional.
	// If want to set only URI then pass NULL in place of transforms Element
	public void setCipherReference (String uriValue, Element transforms) {
		Element tempElem = doc.createElement("CipherReference");
		tempElem.setAttribute("URI", uriValue);
		
		if ( transforms != null )
			tempElem.appendChild(transforms);
		
		cipherData.appendChild(tempElem);
	}// End setCipherReference()
	
	// Retuns the completed <CipherData> structure.
	public Document getCipherData() {
		if (elementAppendedToDoc == false ) {
			doc.appendChild(cipherData);
			elementAppendedToDoc = true;
		}
		return doc;
	}// End getCipherData()
}// end Class CipherData