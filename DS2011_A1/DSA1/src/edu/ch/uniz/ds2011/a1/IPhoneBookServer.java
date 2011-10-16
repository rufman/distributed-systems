package edu.ch.uniz.ds2011.a1;

import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: basca
 * Date: 10/2/11
 * Time: 6:28 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class IPhoneBookServer {
    public static Integer PORT = 44919;
    public static String SEP_COMMAND = "##";
    public static String SEP_ARGUMENTS = "%%";
    public static String SEP_PARTS = "%%";
    public static String CMD_EXIT = "EXIT";
    public static String RPC_HANDLER = "ACME";
    public abstract void start();
    protected abstract ArrayList<PhoneBookRecord> loadData(InputStream dbis);
}
