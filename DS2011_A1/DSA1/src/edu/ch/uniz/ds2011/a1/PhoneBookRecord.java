package edu.ch.uniz.ds2011.a1;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: basca
 * Date: 10/2/11
 * Time: 7:01 PM
 * To change this template use File | Settings | File Templates.
 */

public class PhoneBookRecord implements Serializable {
    public PhoneBookRecord(String textLine){
        String[] data = textLine.split(",");
        // <Name>,<Phone number>,<Address>, <ZIP code>,<City>
        this.name = data[0].trim();
        this.phoneNumber = data[1].trim();
        this.address = data[2].trim();
        this.zipCode = Long.valueOf(data[3].trim());
        this.city = data[4].trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getZipCode() {
        return zipCode;
    }

    public void setZipCode(Long zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String toString(){
        return this.name + "," + this.phoneNumber + "," + this.address + "," + this.zipCode + "," + this.city;
    }

    private String name;
    private String phoneNumber;
    private String address;
    private Long zipCode;
    private String city;


}
