 /*
 	DW/BS
	20020204
	AlgoNames.java
	Listing 20
	This class is only for convenience of use. 
	It contains names of Algorithms as static integers
	and their corresponding namespaces as strings.
 */

public class AlgoNames {

	public static final int TRIPLE_DES    =  1; 
	public static final int AES_128	      =  2; 
	public static final int	AES_256	      =  3;  
	public static final int RSA_V1_5      =  4;  
	public static final int RSA_OAEP      =  5;  
	public static final int DH		      =  6; 
	public static final int	KW_TRIPLE_DES =  7;   
	public static final int KW_AES_128    =  8;  
	public static final int	KW_AES_256    =  9;  
	public static final int	SHA1   	      = 10; 
	public static final int	SHA256        = 11; 

	public static final int	XPATH         = 100; 
	public static final int BASE_64		  = 101;
	public static final int ENC_KEY		  = 102;
	public static final int XML_DSIG	  = 103;

	// Fields to represent what type of Data to be encrypted.
	public static final int ELEMENT   	  = 201; 
	public static final int CONTENT   	  = 202; 
	public static final int DOCUMENT  	  = 203; 
	
	
	
	public static String getAlgoNSValue(int algoNo){
		String algoNS = "";
		switch (algoNo){
			case TRIPLE_DES :  algoNS = "http://www.w3.org/2001/04/xmlenc#tripledes-cbc"; break;
			case AES_128	:  algoNS = "http://www.w3.org/2001/04/xmlenc#aes128-cbc";    break;
			case AES_256	:  algoNS = "http://www.w3.org/2001/04/xmlenc#aes256-cbc";    break;
			case RSA_V1_5	:  algoNS = "http://www.w3.org/2001/04/xmlenc#rsa-1_5"; 	  break;
			case RSA_OAEP	:  algoNS = "http://www.w3.org/2001/04/xmlenc#rsa-oaep-mgf1p";break;
			case DH			:  algoNS = "http://www.w3.org/2001/04/xmlenc#dh";			  break;
			case KW_TRIPLE_DES:algoNS = "http://www.w3.org/2001/04/xmlenc#kw-tripledes";  break;
			case KW_AES_128	:  algoNS = "http://www.w3.org/2001/04/xmlenc#kw-aes128";	  break;
			case KW_AES_256	:  algoNS = "http://www.w3.org/2001/04/xmlenc#kw-aes256";	  break;
			case SHA1		:  algoNS = "http://www.w3.org/2000/09/xmldsig#sha1"; 		  break;
			case SHA256		:  algoNS = "http://www.w3.org/2000/09/xmldsig#sha256";		  break;
			case XPATH		:  algoNS = "http://www.w3.org/TR/1999/REC-xpath-19991116";   break;
			case BASE_64	:  algoNS = "http://www.w3.org/2000/09/xmldsig#base64";		  break;
			case ENC_KEY	:  algoNS = "http://www.w3.org/2001/04/xmlenc#EncryptedKey";  break;
			case XML_DSIG	:  algoNS = "http://www.w3.org/2000/09/xmldsig#";		 	  break;	
			case ELEMENT	:  algoNS = "http://www.w3.org/2001/04/xmlenc#Element";		  break;	
			case CONTENT	:  algoNS = "http://www.w3.org/2001/04/xmlenc#Content";		  break;	
			case DOCUMENT	:  algoNS = "http://www.isi.edu/in-notes/iana/assignments/media-types/text/xml";  break;
	
		} // end switch(algoNo)
		return algoNS;
	}// End getAlgoNsValue()
}// End Class