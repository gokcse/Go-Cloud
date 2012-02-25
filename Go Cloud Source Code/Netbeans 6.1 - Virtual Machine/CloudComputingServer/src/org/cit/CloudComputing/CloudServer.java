/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cit.CloudComputing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.io.File;
import java.net.Socket;
import java.net.ServerSocket;
import java.rmi.registry.Registry;
import java.util.StringTokenizer;

class Serve implements Runnable {

    static Registry newregistry;
    public BufferedReader br;
    public DataOutputStream dos;
    public Socket client_socket;
    public CloudComputable software;

    Serve(Socket client_socket) {
        try {
            this.client_socket = client_socket;
            br = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
            dos = new DataOutputStream(client_socket.getOutputStream());
            new Thread(this).start();
        } catch (Exception e) {
        }
    }

    public void run() {
        try {


        String root_directory=br.readLine();
        software = new Frame_class("");
            dos.writeBytes(software.getInfo() + "\n");
            String[] services = software.listOfServices();
            dos.writeBytes(new Integer(services.length).toString() + "\n");
            for (int i = 0; i < services.length; i++) {
                dos.writeBytes(" " + new Integer(i + 1).toString() + "." + services[i] + "\n");
            }

            /* Get list of components */
            String[] components = software.listOfAvailableComponents();
            dos.writeBytes(new Integer(components.length).toString() + "\n");
            for (int i = 0; i < components.length; i++) {
                dos.writeBytes(components[i] + "\n");
            }
            /* Provide Service */
            while (true) {
                String service = br.readLine();
                if (service.equalsIgnoreCase("openService()")) {

                    File f = new File(root_directory);

                    String[] files = f.list(new FilterFiles());
                    dos.writeBytes(new Integer(files.length + 1).toString() + "\n");
                    dos.writeBytes("Select a file \n");
                    for (String string : files) {
                        dos.writeBytes(string + "\n");
                    }
                    service = "";
                }
                if (service.equalsIgnoreCase("Select a file ")) {
                    String reply = software.openFileService(br.readLine());
                    StringTokenizer st = new StringTokenizer(reply, "\n");
                    dos.writeBytes(new Integer(st.countTokens()).toString() + "" +
                            "\n");
                    while (st.hasMoreTokens()) {
                        dos.writeBytes(st.nextToken() + "\n");
                    }

                }
                if(service.equalsIgnoreCase("saveFileService")){
                    String filename=br.readLine();
                    Integer count=new Integer(br.readLine());
                    String data="";
                    for(int i=0;i<count;i++){
                        data=data+br.readLine()+"\n";
                    }
                    software.saveFileService(data,filename);
                }




            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class CloudServer {

    static int server_port = 1234;
    static ServerSocket ser = null;
    static Registry newregistry;

    public static void main(String[] args) throws Exception {

        ser = new ServerSocket(server_port);
        while (true) {
            new Serve(ser.accept());
        }
    }
}
