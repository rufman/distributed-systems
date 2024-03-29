package edu.ch.uniz.ds2011.a1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class PhoneBookClient implements IPhoneBookClient {
	Socket clientSocket;
	BufferedReader reader;
	PrintWriter writer;
	
	@Override
	public void connect(String address) {
		try {
			clientSocket = new Socket(address, IPhoneBookServer.PORT);
			reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void disconnect() {
		try {
			writer.println("disconnect##");
			writer.flush();
			reader.close();
			writer.close();
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public PhoneBookRecord getUserByDetails(String userName, String address,
			Long zipCode, String cityName) {
		String[] split_list;
		String command_message = String.format("getUserByDetails##%s%%%%%s%%%%%s%%%%%s", userName, address, zipCode, cityName);
		PhoneBookRecord phone_record = null;
		
		split_list = rpc(command_message);
		if(!split_list[0].equals("")){
			phone_record = new PhoneBookRecord(split_list[0]);
		}
		
		return phone_record;
	}

	@Override
	public PhoneBookRecord getUserByPhone(String phoneNumber) {
		String[] split_list;
		String command_message = String.format("getUserByPhone##%s", phoneNumber);
		PhoneBookRecord phone_record = null;
		
		split_list = rpc(command_message);
		if(!split_list[0].equals("")){
			phone_record = new PhoneBookRecord(split_list[0]);
		}
		
		return phone_record;
	}

	@Override
	public ArrayList<PhoneBookRecord> getUserDetails(String userName) {
		String[] split_list;
		String command_message = String.format("getUserDetails##%s", userName);
		ArrayList<PhoneBookRecord> phone_array = new ArrayList<PhoneBookRecord>();
		
		split_list = rpc(command_message);
		if(!split_list[0].equals("")){
			for(int i=0; i < split_list.length; i++){
				PhoneBookRecord phone_record = new PhoneBookRecord(split_list[i]);
				phone_array.add(phone_record);
			}
		}
		
		return phone_array;
	}

	@Override
	public ArrayList<PhoneBookRecord> getUsersByCity(String cityName) {
		String[] split_list;
		String command_message = String.format("getUsersByCity##%s", cityName);
		ArrayList<PhoneBookRecord> phone_array = new ArrayList<PhoneBookRecord>();
		
		split_list = rpc(command_message);
		if(!split_list[0].equals("")){
		for(int i=0; i < split_list.length; i++){
				PhoneBookRecord phone_record = new PhoneBookRecord(split_list[i]);
				phone_array.add(phone_record);
			}
		}
		
		return phone_array;
	}
	
	private String[] rpc(String line){
		String record_list = null;
		
		//write to writer and then flush
		writer.println(line);
		writer.flush();
		
		try {
			record_list = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return record_list.split(IPhoneBookServer.SEP_PARTS);
	}

}
