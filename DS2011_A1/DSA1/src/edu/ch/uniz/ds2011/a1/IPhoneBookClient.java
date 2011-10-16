package edu.ch.uniz.ds2011.a1;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: basca
 * Date: 10/2/11
 * Time: 6:28 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IPhoneBookClient {
    public void connect(String address);
    public void disconnect();
    public ArrayList<PhoneBookRecord> getUserDetails(String userName);
    public ArrayList<PhoneBookRecord> getUsersByCity(String cityName);
    public PhoneBookRecord getUserByPhone(String phoneNumber);
    public PhoneBookRecord getUserByDetails(String userName, String address, Long zipCode, String cityName);
}
