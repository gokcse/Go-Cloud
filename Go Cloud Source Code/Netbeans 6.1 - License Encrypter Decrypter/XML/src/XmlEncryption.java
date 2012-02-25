 /*
 	DW/BS
	20020521
	XmlEncryption.java
	Listing 2
	A wrapper class that can generate complete XML encrypted file.
	It uses all the other classes. 
	Users of the XML Encryption Engine will only need to interact with 
	this class.
 */

import java.awt.RenderingHints;
import java.io.*;
import java.util.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.apache.crimson.tree.XmlDocument;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;


public class XmlEncryption {


	// Name of the algorithm which will be used to encrypt the data.
	private String algoName = null;
	
	// Name of the secret key which was previously agreed upon 
	// and saved with the given name.
	private String keyName = null; 
	
	// ID attribute of the main encryption type structure 
	private String encId = null;
	
	// It will be used to get a new Document objects.
	private DocumentBuilder docBuilder = null;
	
	// The Document object holding the XML file
	private Document clearDoc = null;
	
	//The encryption key
	private Key encKey = null;
	
	//The decryption key
	private SecretKey decKey = null;

	
	// The default constructor
	public XmlEncryption() {
		// Create DocumentBuilder object from DocumentBuilderFactory.
		try {
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} 
		catch (ParserConfigurationException e) { 
			docBuilder = null; 
		}
	}
	
		
	public void setAlgoName(String name) {
		this.algoName = name;
	}// End setAlgoName() 
	
	
	public String getAlgoName() {
		return this.algoName;
	}// End getAlgoName() 
	
	
	public void setKeyName (String key) {
		this.keyName = key; 
	}// End setKeyName()
	
	
	public String getKeyName() {
		return this.keyName; 
	}// End getKeyName()
	
	
	public void setEncId (String id) {
		this.encId = id;
	}// End setEncId()
	
	
	public String getEncId() {
		return this.encId;
	}// End setEncId()
	
	
	public void setClearDoc(String fileString) {
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream
				(fileString.getBytes());
			this.clearDoc = docBuilder.parse(bais);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}// End setClearDoc()
	
	
	public Document getClearDoc() {
		return this.clearDoc;
	}// End getClearDoc()
	
	
	public void setEncKey(Key encKey) {
		this.encKey = encKey;
	}// End setEncKey()
	
	
	public Key getEncKey() {
		return this.encKey;
	}// End getEncKey()
	
	
	public void setDecKey(SecretKey decKey) {
		this.decKey = decKey;
	}// End setDecKey()
	
	
	public Key getDecKey() {
		return this.decKey;
	}// End getDecKey()
	
	
	// Generate a Complete XML Encrypted File.
	public String encryptCompleteXmlFile() {
		// Take an Object of EncryptedData Class. 
		// It represents an <EncryptedData> Element.
		EncryptedData encDataObj = this.getEncryptedDataDoc(this.encId, "DOCUMENT");
		// Get XML Structure for <EncryptionMehtod> element.
		Document encMethodDoc = this.getEncryptionMethodDoc(this.algoName);	
		// Get XML Structure for <KeyInfo> element.		
		Document encKeyInfoDoc = this.getKeyInfoDoc(this.keyName);
		// Read the given file data which will be encrypted.
		String plainData = this.getString((XmlDocument)this.clearDoc);
		// Use of JCA/JCE to get encrypted data.
		String cipherData = this.getEncryptedData(plainData);
		// Get XML Structure for <CipherData> element.				
		Document cipherDataDoc = this.getCipherDataDoc(cipherData);
		// Join these XML Structures.
		encDataObj.addChild(encMethodDoc);
		encDataObj.addChild(encKeyInfoDoc);
		encDataObj.addChild(cipherDataDoc);
		//return the resultant in a file
		return getString((XmlDocument)encDataObj.getEncData());
	}// End encryptCompleteXmlFile()

	
	// Generate element encrypted XML file.
	public String encryptElementOfXmlFile(String elementName) {
	
		// Take an Object of EncryptedData Class. 
		// It represents <EncryptedData> Element.
		EncryptedData encDataObj = this.getEncryptedDataDoc(this.encId, "ELEMENT");
		// Get XML Structure for <EncryptionMehtod> element.
		Document encMethodDoc = this.getEncryptionMethodDoc(this.algoName);	
		// Get XML Structure for <KeyInfo> element.		
		Document encKeyInfoDoc = this.getKeyInfoDoc(this.keyName);
		//The node object is transformend into a string.
		String plainData = this.getClearNode(elementName).toString().trim();
		// Method call to get encrypted and base 64 encoded data.
		String base64cipherData = this.getEncryptedData(plainData);
		// Get XML Structure for <CipherData> element.				
		Document cipherDataDoc = this.getCipherDataDoc(base64cipherData);
		// Join these XML Structures.
		encDataObj.addChild(encMethodDoc);
		encDataObj.addChild(encKeyInfoDoc);
		encDataObj.addChild(cipherDataDoc);
		// Return the encrypted content as a Document type object.
		Document encElementDoc = encDataObj.getEncData();	
		//Replace the specified element with its encrypted form
		boolean repstatus = false;
		repstatus = replaceElement(encElementDoc,elementName);
		return getString((XmlDocument)clearDoc);
	}// End encryptElementOfXmlFile()


	// Generate element content encrypted XML file.
	public String encryptElementContentOfXmlFile(String elementName) {
		// Take an Object of EncryptedData Class. 
		// It represents <EncryptedData> Element.
		EncryptedData encDataObj = this.getEncryptedDataDoc(this.encId, "CONTENT");
		// Get XML Structure for <EncryptionMehtod> element.
		Document encMethodDoc = this.getEncryptionMethodDoc(this.algoName);	
		// Get XML Structure for <KeyInfo> element.		
		Document encKeyInfoDoc = this.getKeyInfoDoc(this.keyName);
		//The node object is transformed into a string.
		String plainData = this.getElementContent(elementName).trim();
		// Method call to get encrypted and base 64 encoded data.
		String base64cipherData = this.getEncryptedData(plainData);
		// Get XML Structure for <CipherData> element.				
		Document cipherDataDoc = this.getCipherDataDoc(base64cipherData);
		// Join these XML Structures.
		encDataObj.addChild(encMethodDoc);
		encDataObj.addChild(encKeyInfoDoc);
		encDataObj.addChild(cipherDataDoc);//
		// Return the encrypted content as a Document type object.
		Document encElementDoc = encDataObj.getEncData();
		//Replace the specified element with its encrypted form
		boolean repstatus = false;
		repstatus = replaceContent(encElementDoc, elementName);
		return getString((XmlDocument)clearDoc);
	}// End encryptElementOfXmlFile()

	
	// This is where the actual JCA/JCE data encryption takes place.
	public String getEncryptedData(String data) {
		String clearData = new String(data);
		String cipherDataBase64	= null;
		try {
			//padding the last byte with " "
			int pad = clearData.length()%8;
			for(int i=0;i<(8-pad);i++) 
				clearData += " ";
			// Creating a Cipher Object.
			Cipher cipherObj = Cipher.getInstance(this.algoName+"/CBC/NoPadding");
			// Initializing the Cipher Object in encryption mode.
			cipherObj.init(Cipher.ENCRYPT_MODE,this.encKey);
			// Retreiving the IV
			byte [] iv = cipherObj.getIV();
			//Ciphering the clear text.
			byte[] cipherTemp = cipherObj.doFinal(clearData.getBytes());
			//Prefixing the IV to the cipher string to produce cipherData
			int ivlen = iv.length;
			int cTlen = cipherTemp.length;
			int cDlen = ivlen+cTlen	;
			byte [] cipherData = new byte[cDlen];
			// First 8 bytes are IV bytes
			for (int i=0;i<ivlen;i++) 
				cipherData[i] = (byte)iv[i];
			// Rest of the bytes are cipher bytes	
			for (int i=ivlen;i<cDlen;i++)
				cipherData[i] = (byte)cipherTemp[i-8];	
			//Base 64 encoding of cipherData
			cipherDataBase64 = getBase64Encoded(cipherData);
		}
		catch (Exception e) {
			System.out.println("Problem in getEncryptedData()");
			e.printStackTrace();
		}
		return cipherDataBase64;
	}// End getEncryptedData()
	
	//Decrypting the XML Encrypted file
	public String getDecryptedData(String encString) {
		String decString = "UNDER DEVELOPMENT";
		try	{
			//get the encrypted XML file string parsed into a Document object
			ByteArrayInputStream bais = new ByteArrayInputStream(encString.getBytes());
			Document encDoc = docBuilder.parse(bais);
			//Get a list of all the EncryptedData tags 
			NodeList nl = encDoc.getElementsByTagName("EncryptedData");
			//Load, decrypt and replace each EncryptedData tag in the Document object
			for(int i=0;i<nl.getLength();i++) {
				//Loading an element
				Node edata = nl.item(i);
				//Extracting the values of  Algorithm, KeyName, 
				//Type(of encryption) and CipherValue
				String algo = null;
				String keyname = null;
				String encType = null;
				String ciphervalue = null;
				//Setting the values 
				edata.normalize();
				//Setting the value of encType
				encType = edata.getAttributes().getNamedItem("Type").getNodeValue();
				//Setting the values of the remaining parameters
				NodeList algoNL = edata.getChildNodes();
				for(int j=0;j<algoNL.getLength();j++) {
					//Setting the value of algo
					if(algoNL.item(j).getNodeName().equals("EncryptionMethod"))
						algo = algoNL.item(j).getAttributes().
							getNamedItem("Algorithm").getNodeValue();
					//Setting the value of keyname
					if(algoNL.item(j).getNodeName().equals("ds:KeyInfo")) {
						NodeList knNL = algoNL.item(j).getChildNodes();
						for(int k=0;k<knNL.getLength();k++)	{
							if(knNL.item(k).getNodeName().equals("KeyName"))
								keyname = (knNL.item(k).getFirstChild().
									getNodeValue());
						}
					}
					//Setting the value of ciphervalue
					if(algoNL.item(j).getNodeName().equals("CipherData")) {
						NodeList cvNL = algoNL.item(j).getChildNodes();
						for(int v=0;v<cvNL.getLength();v++)	{
							if(cvNL.item(v).getNodeName().equals
								("CipherValue"))
								ciphervalue = (cvNL.item(v).getFirstChild().
									getNodeValue());
						}
					}
				}
				if (algo.equals("http://www.w3.org/2001/04/xmlenc#tripledes-cbc"))
					algo = "DESede";
				//Reading the key file and generating/setting decKey
				this.generateDecKey(keyname, algo);
				//Decrypt the cipher 
				String decbit = Decrypt(ciphervalue,this.decKey,algo).trim();
				//Replacement Logic
				//For replacing an entire XML file
				if(encType.equals(
				"http://www.isi.edu/in-notes/iana/assignments/media-types/text/xml"))
					decString = decbit;
				//Decrypted element replacement	
				else 
				if(encType.equals("http://www.w3.org/2001/04/xmlenc#Element")) {
					try	{
						byte [] a = decbit.getBytes();
						ByteArrayInputStream bais2 = 
							new ByteArrayInputStream(a);
						Document decdoc = docBuilder.parse(bais2); 
						Node decNode = 
							encDoc.importNode(decdoc.getFirstChild(), true);
						edata.getParentNode().replaceChild(decNode,edata);
					}
					catch(org.xml.sax.SAXParseException spe) {
						Text decText = encDoc.createTextNode(decbit);
						edata.getParentNode().replaceChild(decText,edata);
					}
					decString = (getString((XmlDocument)encDoc));
				}
				//Decrypted content replacement
				else if(encType.equals("http://www.w3.org/2001/04/xmlenc#Content")) {
					try {
						// Works if the content is a single child element.
						byte [] a = decbit.getBytes();
						ByteArrayInputStream bais2 = new ByteArrayInputStream(a);
						Document decdoc = docBuilder.parse(bais2); 
						Node decNode = 
							encDoc.importNode(decdoc.getFirstChild(), true);
						edata.getParentNode().replaceChild(decNode,edata);
					}
					catch(org.xml.sax.SAXParseException spe) {
						//In case the content is plain text 
						//or a group of child elements
						Text decText = encDoc.createTextNode(decbit);
						edata.getParentNode().replaceChild(decText,edata);
					}
					decString = (getString((XmlDocument)encDoc));
				}
			}		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return decString;			
	}// End getDecryptedData()
	
		
	// This is where the actual JCA/JCE data decryption takes place.
	private String Decrypt(String encString, Key decKey, String algo) {
		// Decoding the Base 64 Encoded IV+cipher String into a byte array
		byte[] g = getBase64Decoded(encString);
		int glen = g.length;
		// Separating the IV from the byte array
		byte [] iv = new byte[8]; 
		for(int t=0;t<8;t++)
			iv[t] = g[t];
		// Separating the cipher from the byte array
		byte [] Enc = new byte[glen-8];
		for(int p=8;p<glen;p++)
			Enc[p-8] = g[p];
		// This will hold the decrypted String
		String decString = null;
		// Decrypting the cipher and setting decString:
		try {
			IvParameterSpec ivps = new IvParameterSpec(iv);
			AlgorithmParameters aparam = AlgorithmParameters.getInstance(algo);
			aparam.init(ivps);
		    Cipher cipherObj = Cipher.getInstance(algo+"/CBC/NoPadding");
			cipherObj.init(Cipher.DECRYPT_MODE, decKey, aparam);
			decString = new String(cipherObj.update(Enc));
		}
		catch(Exception e) {
			System.out.println("Problem in Decrypt()");
			e.printStackTrace();
		}
		return decString;
	}//End Decrypt()
	
	
	//Base 64 Encoding a byte array and returning it as a String
	private String getBase64Encoded(byte[] plainText) {
		String encoded = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Base64OutputStream out= new Base64OutputStream(baos,true);
			out.write(plainText);
			out.close();
			String encText = new String(baos.toByteArray());
			baos.close();
			encoded = encText;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return encoded;
	}//End getBase64Encoded()
	
	//Base 64 Decoding a String and returning it as a byte array
	private byte[] getBase64Decoded(String codedText) {
		String ct = codedText;
		int lenEnc = ct.length();
		int lenDec = 0;
		// To calculate the max size of the decoded byte array	
		if (ct.charAt(lenEnc-1) == '=')
				if (ct.charAt(lenEnc-2) == '=') {
					lenDec = ((lenEnc-4)* 3/4);
					lenDec = ((lenDec - (lenDec/77))+1);
				}
				else {
					lenDec = ((lenEnc-4)* 3/4);
					lenDec = ((lenDec - (lenDec/77))+2);
				}
			else {
				lenDec = (lenEnc * 3/4);
				lenDec = (lenDec - (lenDec/77));
			}
		byte [] decoded = new byte[lenDec];
		try {
			
			ByteArrayInputStream bais = new ByteArrayInputStream(ct.getBytes());
			Base64InputStream in = new Base64InputStream(bais);
			in.read(decoded);
			in.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return decoded;
	}// End getBase64Decoded()
	
	// To generate the named Key Object and to set the decryption key
	private void generateDecKey(String keyname, String algo) {
		try {
			FileInputStream dkFIS = new FileInputStream(keyname);
			byte [] dk = new byte[dkFIS.available()];
			dkFIS.read(dk);
			dkFIS.close();
			// Generating/setting the decryption key.
			try {
				SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algo);
				DESedeKeySpec dkSpec = new DESedeKeySpec(dk);
				SecretKey sKey = keyFactory.generateSecret(dkSpec);
				this.setDecKey(sKey); 
			}
			catch(Exception e) {
				System.out.println("Unable to generate/set the decryption key.");
				e.printStackTrace();
			}
		}
		catch(Exception e) {
			System.out.println("Unable to set the decryption key.");
			e.printStackTrace();
		}
	}// End generateDecKey()

		
	//Replace the specified element with the encrypted data element
	private boolean replaceElement(Document edDoc, String elementName) {
		boolean isReplaced = false;
		try {
			Node clearNode = this.getClearNode(elementName);
			Node parentNode = clearNode.getParentNode();
			Node edNode = clearDoc.importNode(edDoc.getFirstChild(), true);
			parentNode.replaceChild(edNode,clearNode);
			isReplaced = true;
		}
		catch(Exception e) {
			isReplaced = false;
			System.out.println("\nReplacement Failed.");
			e.printStackTrace();
		}
		return isReplaced;
	}//End replaceElement()
	
	
	//Replace the specified element's content with the encrypted data element
	private boolean replaceContent(Document edDoc, String elementName) {
		boolean isReplaced = false;
		try {
			if (removeAllChildNodes(elementName)) {
				Node parentNode = this.getClearNode(elementName);
				//Importing the encryptedData Node
				Node edNode = clearDoc.importNode(edDoc.getFirstChild(), true);
				//Appending to an already empty parentNode
				parentNode.appendChild(edNode);
				isReplaced = true;		
			}
			else 
				System.out.println(
					"Child nodes not removed; replacement did not initiate.");
		}
		catch(Exception e) {
			isReplaced = false;
			System.out.println("\nContent replacement failed.");
			e.printStackTrace();
		}
		return isReplaced;
	}//End replaceContent()
	
	
	//Search the document tree and return the specified element as a Node Object.
	private Node getElement(Document doc, String elementName) {
 		NodeList nList = doc.getElementsByTagName(elementName);
		Node theNode = nList.item(0);
		return theNode;	
	}// End getElement();
	
	
	//Get a clear node by its element name
	private Node getClearNode(String elementName) {
		return getElement(this.clearDoc, elementName);
	}// End getClearNode()
		
	
	//Get a clear node's content by its element name
	public String getElementContent(String elementName) {
		String elementContent = new String();
		NodeList nl = this.getClearNode(elementName).getChildNodes();
		for(int i=0;i<nl.getLength();i++) 
			elementContent += (nl.item(i)).toString().trim();
		return elementContent;	
	}// End setElementContent()
	
	
	//Remove all the child nodes of clearNode
	private boolean removeAllChildNodes(String elementName) {
		boolean removed = false;
		Node clearNode = this.getClearNode(elementName);
		try	{
			while(clearNode.hasChildNodes()) {
				NodeList t = clearNode.getChildNodes();	
				// Removing a child Node 
				clearNode.removeChild(t.item(0));
			}//All children removed
			removed = true;
		}
		catch(Exception e) {
			removed = false;
			e.printStackTrace();
		}
		return removed;
	}//End removeAllChildNodes()
	
	
	// Get the new Document object for Document Builder
	private Document getNewDocument() {
		if (docBuilder != null)
			return docBuilder.newDocument();
		else
			return null;	
	}


	// Returns the EncryptedData object.
	public EncryptedData getEncryptedDataDoc(String Id, String encType) {
		EncryptedData ed = new EncryptedData(this.getNewDocument());
		ed.setId(Id);
		if (encType.equals("DOCUMENT"))
			ed.setType(AlgoNames.DOCUMENT);
		if (encType.equals("ELEMENT"))
			ed.setType(AlgoNames.ELEMENT);	
		if (encType.equals("CONTENT"))
			ed.setType(AlgoNames.CONTENT);	
		return ed;
	}// End getEncryptedDataDoc()
	
	
	// Returns the <EncryptionMehtod> structure.
	public Document getEncryptionMethodDoc (String algoName) {
		EncryptionMethod em = new EncryptionMethod(this.getNewDocument());
		if (algoName.equals("DESede"))
			em.setAlgorithm(AlgoNames.TRIPLE_DES);
		return em.getEncMethod();
	}// End getEncryptionMethodDoc()


	// Returns the <KeyInfo> structure.
	public Document getKeyInfoDoc (String keyName) {
		GenericKeyInfo ki = 
			new GenericKeyInfo(this.getNewDocument(),"ds", AlgoNames.XML_DSIG);
		ki.setKeyName(keyName);
		return ki.getKeyInfo();	
	}// End getKeyInfoDoc()


	// Returns the <CipherData> structure.
	public Document getCipherDataDoc (String data) {
		CipherData cd = new CipherData(this.getNewDocument());
		cd.setValue(data);
		return cd.getCipherData();
	}// getCipherDataDoc()
	
		
	//Convert an XmlDocument type object into a String
	public String getString(XmlDocument xmldoc) {	
		String DocumentAsString = null;
		try	{
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			xmldoc.write(bos);
			DocumentAsString = bos.toString();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return DocumentAsString;
	}//End getString()
	
	
	//Convert a Document type object into a String 
	//The output string does not contain initial encoding tags
	public String getString(Document doc) {	
		String DocumentAsString = null;
		try	{
			if (doc.getFirstChild().getNodeName().equals("#comment")) {
				DocumentAsString = "<!--"+doc.getFirstChild().getNodeValue()+
					"-->\n\n";
				DocumentAsString += doc.getDocumentElement().toString();
			}
			else
				DocumentAsString = doc.getDocumentElement().toString();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return DocumentAsString;
	}//End getString() 

    void setEncKey(RenderingHints.Key theKey) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
	

}// End class demoXmlEncApp