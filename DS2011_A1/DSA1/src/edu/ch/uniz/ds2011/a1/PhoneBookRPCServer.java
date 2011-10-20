package edu.ch.uniz.ds2011.a1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.util.ArrayList;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

import edu.ch.uniz.ds2011.a1.PhoneBookServer.Session;

public class PhoneBookRPCServer extends IPhoneBookServer {
	ArrayList<PhoneBookRecord> phonebook_array;
	
	public PhoneBookRPCServer (){
		AcmeLocator acme = new AcmeLocator();
		InputStream phonebook_stream = acme.getPhoneBook();
		
		phonebook_array = loadData(phonebook_stream); //load the raw phone records into the PhoneBook	
	}
	
	@Override
	protected ArrayList<PhoneBookRecord> loadData(InputStream dbis) {
		String line = ""; // line buffer
		ArrayList<PhoneBookRecord> phone_array = new ArrayList<PhoneBookRecord>(); //phone record array that will be returned
		
		if (dbis != null) {
            try {
            	BufferedReader reader = null;
				try {
					reader = new BufferedReader(new InputStreamReader(dbis, "UTF-8")); // Create a reader for the input stream
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}              
                
				// Read on a line by line basis and add the phone record to the array until all entries have been added
				while(line != null){
					line = reader.readLine();
					if(line != null){
						PhoneBookRecord phone_record = new PhoneBookRecord(line);
						phone_array.add(phone_record);
					}
                }
				
                dbis.close();
            }catch (IOException e) {
				e.printStackTrace();
            }
		}
		return phone_array;
	}

	@Override
	public void start() {	
		// TODO Auto-generated method stub
		WebServer webServer = new WebServer(PORT);
        
        XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();
      
        PropertyHandlerMapping phm = new PropertyHandlerMapping();
        
        try {
			phm.addHandler("PhoneBookRPCServer", edu.ch.uniz.ds2011.a1.PhoneBookRPCServer.class);
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		xmlRpcServer.setHandlerMapping(phm);
        
      
        XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
        serverConfig.setEnabledForExtensions(true);
        serverConfig.setContentLengthOptional(false);

        try {
			webServer.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Object[] getUserByDetails(String userName, String address,
				Long zipCode, String cityName) {
		ArrayList<PhoneBookRecord> result_tmp = new ArrayList<PhoneBookRecord>();
		
		int obj_array_length = 0;
		for(int i=0; i < phonebook_array.size(); i++){
        	PhoneBookRecord next_record = phonebook_array.get(i);
        	if(next_record.getName().equals(userName) && next_record.getAddress().equals(address) &&
        			next_record.getZipCode().equals(zipCode) && next_record.getCity().equals(cityName)){
        		result_tmp.add(next_record);
        		obj_array_length++;
        	}
        }
		
		Object[] obj = new Object[obj_array_length];
		for(int i=0; i < result_tmp.size(); i++){
        	obj[i] = result_tmp.get(i);
        }
		
		return obj;
	}
		
	public Object[] getUserDetails(String userName) {
			ArrayList<PhoneBookRecord> result_tmp = new ArrayList<PhoneBookRecord>();
			
			int obj_array_length = 0;
			for(int i=0; i < phonebook_array.size(); i++){
	        	PhoneBookRecord next_record = phonebook_array.get(i);
	        	if(next_record.getName().equals(userName)){
	        		result_tmp.add(next_record);
	        		obj_array_length++;
	        	}
	        }
			Object[] obj = new Object[obj_array_length];
			for(int i=0; i < result_tmp.size(); i++){
	        	obj[i] = result_tmp.get(i);
	        }
			
			return obj;
	}
	
	public Object[] getUsersByCity(String cityName) {
		ArrayList<PhoneBookRecord> result_tmp = new ArrayList<PhoneBookRecord>();
		
		int obj_array_length = 0;
		for(int i=0; i < phonebook_array.size(); i++){
        	PhoneBookRecord next_record = phonebook_array.get(i);
        	if(next_record.getCity().equals(cityName)){
        		result_tmp.add(next_record);
        		obj_array_length++;
        	}
        }
		Object[] obj = new Object[obj_array_length];
		for(int i=0; i < result_tmp.size(); i++){
        	obj[i] = result_tmp.get(i);
        }
		
		return obj;
	}
	
	public Object[] getUserByPhone(String phoneNumber) {
		ArrayList<PhoneBookRecord> result_tmp = new ArrayList<PhoneBookRecord>();
		
		int obj_array_length = 0;
		for(int i=0; i < phonebook_array.size(); i++){
        	PhoneBookRecord next_record = phonebook_array.get(i);
        	if(next_record.getPhoneNumber().equals(phoneNumber)){
        		result_tmp.add(next_record);
        		obj_array_length++;
        	}
        }
		Object[] obj = new Object[obj_array_length];
		for(int i=0; i < result_tmp.size(); i++){
        	obj[i] = result_tmp.get(i);
        }
		
		return obj;
	}
	
}
