package edu.ch.uniz.ds2011.a1;

import java.io.BufferedReader;

/**
 * Created by IntelliJ IDEA.
 * User: basca
 * Date: 10/2/11
 * Time: 9:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class CLIServerWrapper {

    public CLIServerWrapper(IPhoneBookServer server){
        server.start();
    }

    public static void main(String args[]) {
        try {
            IPhoneBookServer server = (IPhoneBookServer)Class.forName(args[0]).newInstance();
            new CLIServerWrapper(server);
            System.out.println("HAVE INSTANCE = "+server);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
