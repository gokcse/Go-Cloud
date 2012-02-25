package org.cit.CloudComputing;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Jaganathan,Ashok,Gokul,Zosangzeli
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.util.StringTokenizer;
import java.net.Socket;

public class CloudClient {

    static int server_port;
    static Socket cli = null;
    static BufferedReader br;
    static BufferedReader keyinp = null;
    static String server_ip;
    static DataOutputStream dos;

    public static void begin() throws Exception {
        cli = new Socket(server_ip, server_port);
        br = new BufferedReader(new InputStreamReader(cli.getInputStream()));
        keyinp = new BufferedReader(new InputStreamReader(System.in));
        dos = new DataOutputStream(cli.getOutputStream());
    }

    public static String getInfo() throws Exception {
        return br.readLine();
    }

    public static String[] listOfServices() throws Exception {
        Integer no = new Integer(br.readLine());
        String[] services = new String[no];
        for (int i = 0; i < no; i++) {
            services[i] = br.readLine();
        }
        return services;
    }

    public static String[] listOfAvailableComponents() throws Exception {
        Integer no = new Integer(br.readLine());
        String[] components = new String[no];
        for (int i = 0; i < no; i++) {
            components[i] = br.readLine();
        }
        return components;
    }

    public static String openService()throws Exception{
        dos.writeBytes("openService()\n");
        int count=Integer.parseInt(br.readLine());
        StringBuffer ret=new StringBuffer();
        for(int i=0;i<count;i++){
            ret.append("\n" + br.readLine());
        }
        return ret.toString();
    }

       public static String openServiceSelectedFile(String filename)throws Exception{
        dos.writeBytes("Select a file \n");
        dos.writeBytes(filename + "\n");
        int count=Integer.parseInt(br.readLine());
        StringBuffer ret=new StringBuffer();
        for(int i=0;i<count;i++){
            ret.append(br.readLine() + "\n");
        }
        return ret.toString();
    }

    public static void saveSelectedFile(String data,String filename)throws Exception{
        StringTokenizer st=new StringTokenizer(data,"\n");
        dos.writeBytes("saveFileService\n");
        dos.writeBytes(filename+"\n");
        dos.writeBytes(new Integer(st.countTokens()).toString() + "\n");
           while(st.hasMoreTokens()){
               dos.writeBytes(st.nextToken() + "\n");
           }
    }



}
