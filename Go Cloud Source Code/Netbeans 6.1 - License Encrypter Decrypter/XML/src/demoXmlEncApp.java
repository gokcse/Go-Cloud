 /*
 	DW/BS
	20020521
	demoXmlEncApp.java
	Listing 1
	A demo application class that demonstrates XML encryption.
	It uses the class XmlEncryption.java. 
 */


import java.io.*;
import java.util.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.apache.crimson.tree.XmlDocument;
import java.security.*;
import javax.crypto.*;


class demoXmlEncApp {
	
	// This main method is devised to demonstrate XML Encryption functionality.
	public static void main (String args[]) {
		if (args.length != 1) {
			System.out.println("USAGE: java demoXmlEncApp <n>");
			System.out.println("\t\t\tn = 1 : Complete XML-File Encryption");
			System.out.println("\t\t\tn = 2 : Element Encryption");
			System.out.println("\t\t\tn = 3 : Element-Content Encryption\n");
		}
		else {
			// Testing the provider for TripleDES encryption support.
			try {
				Cipher testCipher = Cipher.getInstance("DESede");
           		}//try 
			catch(Exception e) {
				// JCE provider not installed on this system.
				// Installing the JCE provider.
				System.err.println("INSTALLING PROVIDER: SunJCE");
                		Provider sunjceprov = new com.sun.crypto.provider.SunJCE();
				Security.addProvider(sunjceprov);
				System.err.println("PROVIDER INSTALLED... CONTINUING");
            	}//catch

			// Create an instance of this class.
                  demoXmlEncApp demoApp = new demoXmlEncApp();

			// Demonstrate what happens 
			// at the book seller's (sender) end.
                  demoApp.simulateBookSellersEnd(args);

			// Demonstrate what happens 
			// at the publisher's (recepient) end. 
                  demoApp.simulatePublishersEnd();
		}// End else
	}// End main()


	void simulateBookSellersEnd(String args[]) {
		try{
			//******BEGINNING OF PROCESSING AT THE BOOKS_SELLER'S END******
				
			// Reading the source file from disk.
			FileInputStream fis = new FileInputStream("Order.xml");
			byte [] aFile = new byte [fis.available()];
			fis.read(aFile);
			// The source file is converted into a String.
			String aFileString = new String(aFile);
          

			// The XML Encrypted file string
			String aEncString = null;
			// The encryption key	
			Key theKey = null;
			// Generating the key for encryption
			KeyGenerator KG = KeyGenerator.getInstance("DESede"); 
			theKey = KG.generateKey();


			// Creating a new XmlEncryption Object for encryption:
			XmlEncryption aXmlEnc = new XmlEncryption();
			// Setting the clear Document Object
			aXmlEnc.setClearDoc(aFileString);
			// Setting the encryption key
			aXmlEnc.setEncKey(theKey);
			// Setting the required algorithm
			aXmlEnc.setAlgoName("DESede");
			// Setting the name of the encryption key
			aXmlEnc.setKeyName("theKey");
			// Setting the EncryptedData tag ID 
			aXmlEnc.setEncId("Test");
			// Selecting the XML Encryption method
			if(args[0].equals("1"))
				// XML Encryption of the complete XML file
				aEncString = aXmlEnc.encryptCompleteXmlFile();
			if(args[0].equals("2"))
				// XML Encryption of an Element in the XML file
				aEncString = aXmlEnc.encryptElementOfXmlFile("CardNo");
			if(args[0].equals("3"))
				// XML Encryption of the content(textual) 
				// of an Element in the XML file
				aEncString = aXmlEnc.encryptElementContentOfXmlFile("CardNo");
			if(args[0].equals("4"))
				// XML Encryption of the content(Elements) 
				// of an Element in the XML file
				aEncString = aXmlEnc.encryptElementContentOfXmlFile("Payment");
				
			// Writing the XML Encrypted file to disk
			try {
				byte[] aToFile = aEncString.getBytes();
				FileOutputStream aFOS = new FileOutputStream("aEnc.xml");
				aFOS.write(aToFile);
				aFOS.close();
			}
			catch(Exception e) {
				System.out.println("Unable to create 'aEnc.xml'.");
			}

			//Writing the encryption key to a file on disk
			try {
				FileOutputStream keyFOS = new FileOutputStream("theKey");
				keyFOS.write(theKey.getEncoded());
				keyFOS.close();
			}
			catch(Exception e) {
				System.out.println("Unable to save the encryption key.");
			}
								
				//******END OF PROCESSING AT THE BOOKS_SELLER'S END******

		}//try
		catch(Exception e) {
			System.out.println("Unable to read the input XML file.");
		}//catch
      }//simulateBookSellersEnd


	void simulatePublishersEnd() {
		//******BEGINNING OF PROCESSING AT THE PUBLISHER'S END******
				
		// The XML Encrypted file String
		String bEncString = null;
		// The decrypted file String
		String bFileString = null;
		// Reading the XML Encrypted file from disk.
		try {
			FileInputStream bFIS = new FileInputStream("aEnc.xml");
			byte[] bFromFile = new byte[bFIS.available()];
			bFIS.read(bFromFile);
			// Converting the XML Encrypted file to String
			bEncString = new String(bFromFile);
			bFIS.close();
		}//try
		catch(Exception e) {
			System.out.println("Unable to read the encrypted file.");
		}//catch
		
		// Printing the received XML Encrypted file to the console:
		System.out.println("Order.xml AS VIEWED BY THE SALES DEPARTMENT:\n\n"
			+bEncString);
		// Creating a new XmlEncryption Object for decryption
		XmlEncryption bXmlEnc = new XmlEncryption();
		// Decrypting the XML Encrypted file String
		bFileString = bXmlEnc.getDecryptedData(bEncString);

		// Printing the decrypted file to the console:
		System.out.println(
			"Order.xml AS VIEWED BY THE ACCOUNTS DEPARTMENT:\n\n"+bFileString);
				
		//******END OF PROCESSING AT THE PUBLISHER'S END******

      }//simulatePublishersEnd

}// End class demoXmlEncApp