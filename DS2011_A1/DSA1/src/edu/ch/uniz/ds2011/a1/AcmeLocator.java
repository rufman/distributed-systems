package edu.ch.uniz.ds2011.a1;

import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: basca
 * Date: 10/2/11
 * Time: 7:12 PM
 * To change this template use File | Settings | File Templates.
 */
public final class AcmeLocator {

    public static InputStream getPhoneBook(){
        return AcmeLocator.class.getResourceAsStream("acme.txt");
    }
}
