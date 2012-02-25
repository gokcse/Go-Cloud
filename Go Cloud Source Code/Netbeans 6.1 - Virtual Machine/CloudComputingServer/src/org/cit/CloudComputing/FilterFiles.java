/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cit.CloudComputing;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author JAGANATHAN.N
 */
public class FilterFiles implements FilenameFilter{
 public boolean accept(File directory, String filename) {
   if (filename.endsWith(".txt")) return true;
   return false;

 }
}
