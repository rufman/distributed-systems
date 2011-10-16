package edu.ch.uniz.ds2011.a1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: basca
 * Date: 10/2/11
 * Time: 8:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class CLIClientWrapper {
    public CLIClientWrapper(IPhoneBookClient client){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        client.connect("localhost");

        String line = null;
        try {
            while((line = in.readLine()) != null) {
                if (line.equals(IPhoneBookServer.CMD_EXIT)) {
                    System.out.println("EXITING!");
                    client.disconnect();
                    break;
                } else if (line.equals("getUserDetails")) {
                    String arg = in.readLine();
                    if (!arg.trim().equals("")) {
                        ArrayList<PhoneBookRecord> recs = client.getUserDetails(arg);
                        String srecs = "";
                        for(PhoneBookRecord r: recs){
                            srecs += r.toString() + " , ";
                        }
                        System.out.println("GOT = "+srecs);
                    }
                } else if (line.equals("getUsersByCity")) {
                    String arg = in.readLine();
                    if (!arg.trim().equals("")) {
                        ArrayList<PhoneBookRecord> recs = client.getUsersByCity(arg);
                        String srecs = "";
                        for(PhoneBookRecord r: recs){
                            srecs += r.toString() + " , ";
                        }
                        System.out.println("GOT = "+srecs);
                    }
                } else if (line.equals("getUserByPhone")) {
                    String arg = in.readLine();
                    if (!arg.trim().equals("")) {
                        PhoneBookRecord rec = client.getUserByPhone(arg);
                        System.out.println("GOT = "+rec);
                    }
                } else if (line.equals("getUserByDetails")) {
                    String arg1 = in.readLine();
                    String arg2 = in.readLine();
                    String arg3 = in.readLine();
                    String arg4 = in.readLine();
                    if (!arg1.trim().equals("") && !arg2.trim().equals("") && !arg3.trim().equals("") && !arg4.trim().equals("")) {
                        PhoneBookRecord rec = client.getUserByDetails(arg1, arg2, Long.valueOf(arg3.trim()), arg4);
                        System.out.println("GOT = "+rec);
                    }
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]) {
        try {
            IPhoneBookClient client = (IPhoneBookClient)Class.forName(args[0]).newInstance();
            new CLIClientWrapper(client);
            System.out.println("HAVE INSTANCE = "+client);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
