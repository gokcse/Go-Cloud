 /*
 	DW/BS
	20020204
	GenericKeyInfo.java
	Listing 17
	Authors the <KeyInfo> element.
 */

import org.w3c.dom.*;

public class GenericKeyInfo {

	// Element to hold complete XML Structure.
	protected Element keyInfo = null;
	protected Document doc = null;

	// Element will be appended in Document only once.
	protected boolean elementAppendedToDoc = false;

	
	// Default constructor.
	public GenericKeyInfo (Document document, String nsQlif, int typeNo) {
		doc = document;
		
		keyInfo = doc.createElement(nsQlif + ":KeyInfo");
		
		// Add the namespace attribute.
		keyInfo.setAttribute("xmlns:" + nsQlif, AlgoNames.getAlgoNSValue(typeNo));
	}// End GenericKeyInfo
	
	// Add <KeyName> child in <KeyInfo>
	public void setKeyName(String keyName) {
		Element tempElem = doc.createElement("KeyName");
		tempElem.appendChild(doc.createTextNode(keyName));
		keyInfo.appendChild(tempElem);
	}// End setKeyName()
	
	// Add <KeyValue> child in <KeyInfo>
	public void setKeyValue (String keyValue){
		Element tempElem = doc.createElement("KeyValue");
		tempElem.appendChild(doc.createTextNode(keyValue));
		keyInfo.appendChild(tempElem);
	}// End setKeyValue()
	
	// Set retrieval Method URI and and Its type.
	public void setRetrievalMethod (String uriValue, int typeNo) {
		Element tempElem = doc.createElement("RetrievalMethod");
		tempElem.setAttribute("URI", uriValue);
		tempElem.setAttribute("Type", AlgoNames.getAlgoNSValue(typeNo));
		keyInfo.appendChild(tempElem);
	}// End setRetrievalMethod() 
	
	// Return the complete XML structure as a Document Object.
	public Document getKeyInfo() {
		if (elementAppendedToDoc == false ) {
			doc.appendChild(keyInfo);
			elementAppendedToDoc = true;
		}
	 	return doc;
	}// End getKeyInfo()
} // end class KeyInfo