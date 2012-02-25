/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cit.CloudComputing;

/**
 *
 * @author JAGANATHAN-N
 */
public class Software {
    String name;
    String description;
    String command;
    String server_ip;
    String server_port;
    String type;
    public Software(String name, String description, String command, String server_ip, String server_port,String type){
        this.name=name;
        this.description=description;
        this.command=command;
        this.server_ip=server_ip;
        this.server_port=server_port;
        this.type=type;
    }

    @Override
    public String toString() {
        System.out.println(name  + " " + description + " " + command + " " + server_ip + " " + server_port);
        return super.toString();
    }
}

