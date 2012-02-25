 /*
 	DW/BS
	20020204
	EncryptedData.java
	Listing 15
	Authors the <EncryptedData> element which is the parent of
	all XML Encryption structures except <EncryptedKey> element.
 */

import org.w3c.dom.*;

public class EncryptedData{
	
	// Element to hold complete XML Structure.
	Element encData = null;
	Document doc = null;

	// Element will be appended in Document only once.
	private boolean elementAppendedToDoc = false;

	// Default Constructor
	public EncryptedData(Document document) {
		doc = document;
		encData = doc.createElement("EncryptedData");
		
		// Add the namespace attribute
		encData.setAttribute("xmlns","http://www.w3.org/2001/04/xmlenc#");
	}// End EncryptedData()

	// Specify the ID of this EncryptedData as there
	// can be many EncryptedData elements in the same XML file.
	public void setId(String id){
		encData.setAttribute("Id", id);
	}// End setId()
	
	// There can be three types:
	// 201 for ELEMENT
	// 202 for CONTENT
	// 303 for DOCUMENT
	public void setType(int type){
		encData.setAttribute("Type", AlgoNames.getAlgoNSValue(type));
	}// end setType()
	

	// If an Arbitrary type is to be Encrypted, specify its mimeType.
	public void setArbitraryType(String mimeType){
		String typeValue = "http://www.isi.edu/in-notes/iana/assignments/media-types/" + mimeType;
		encData.setAttribute("Type", typeValue);		
	}// End setArbitraryType()
	
	// Add any of these XML Documents KeyInfo | CipherData | Transforms | EncryptionMethod.
	public void addChild(Document document)
	{
		NodeList nList = document.getChildNodes();

		for (int i=0 ; i<nList.getLength(); i++){
			Node tempNode = nList.item(i);
			// If it is a Root Element  
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				Node importedNode = (Node)doc.importNode(tempNode, 
												true /* import all childern*/);
				encData.appendChild((Element)importedNode);
				return;
			}// end if (tempNode.getNodeType() == Node.ELEMENT_NODE) 
		}// end for (int i=0 ; i<nList.getLength(); i++)
	}// End addChild()	
	
	// Return the complete XML structure as a Document Object.
	public Document getEncData(){
		if (elementAppendedToDoc == false) {
			doc.appendChild(encData);
			elementAppendedToDoc = true;
		}
		return doc;
	}// end getEncData()
}// End class EncryptedData