/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cit.CloudComputing;



/**
 *
 * @author Jaganathan,Ashok,Gokul,Zosangzeli
 */
public interface CloudComputable{
	String getInfo();
	String[] listOfServices();
        String[] listOfAvailableComponents();
        String openFileService(String filename);
        void saveFileService(String data, String filename);

}
