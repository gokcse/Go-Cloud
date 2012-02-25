 /*
 	DW/BS
	20020204
	Transforms.java
	Listing 19
	Authors the <Transforms> element.
 */

import org.w3c.dom.*;

public class Transforms{

	// Element to hold complete XML Structure.
	private Element trans = null;
	private Document doc = null;

	// Element will be appended in Document only once.
	private boolean elementAppendedToDoc = false;

	public Transforms(Document document) {
		doc = document;

		// Get Document from Builder and create the Element.
		trans = doc.createElement("Transforms");
	}// End Transforms()
	
	// Simply add algorithm Transform like Base64 encoding etc.
	public void addAlgorithm(String nsQlif, int algoNo){
		Element tempElem = doc.createElement(nsQlif + ":Transform");
		tempElem.setAttribute("Algorithm", AlgoNames.getAlgoNSValue(algoNo));
		trans.appendChild(tempElem);
	}// End addAlgorithm()
	
	// Add algorithm and XPath Transform Expressions. 
	public void addAlgorithm(String nsQlif, int algoNo , String xPathNs, 
								String xPathNsValue, String xPathValue ){
		// Create Transform Element.
		Element tElem = doc.createElement(nsQlif + ":Transform");
		
		// Set the algorithm attibute of this Element.
		tElem.setAttribute("Algorithm", AlgoNames.getAlgoNSValue(algoNo));

		// Create XPath Child Element.
		Element xpElem = doc.createElement(nsQlif +":XPath");

		// Set Namespace attribute of this Element.
		xpElem.setAttribute("xmlns:" + xPathNs, xPathNsValue);

		// Create Text Node to set XPath Query.
		Text xPathText = doc.createTextNode(xPathValue);

		// Add the Query inside the XPath Element
		xpElem.appendChild(xPathText);
		
		// Add XPath Element inside Transform Element
		tElem.appendChild(xpElem);
		
		// Add complete Transform (with XPath) Element in Transforms Element
		trans.appendChild(tElem);
	}// End addAlgorithm()
	
	// Return the complete XML structure as a Document Object.	
	public Document getTransforms() {
		if (elementAppendedToDoc == false) {
			doc.appendChild(trans);
			elementAppendedToDoc = true;
		}
		return doc;
	}// End getTransforms()
}// End Class Transforms