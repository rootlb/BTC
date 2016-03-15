package com.btcc.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.btcc.model.Order;
import com.btcc.service.EntrustService;
import com.btcc.utils.SignatureUtil;
import com.google.gson.Gson;

@Service
public class EntrustServiceImpl implements EntrustService{
	
	private  static String ACCESS_KEY = "5b320f9c-1912-4941-bcd2-40312733a9b6";
	private static String SECRET_KEY = "3faaffe8-9c58-4cdd-86c7-d67eb003a2a2";
	
	
	private SignatureUtil signatureUtil;
	
	@Override
	public Order buyOrder(String price, String amount) throws Exception {
		
        String tonce = ""+(System.currentTimeMillis() * 1000);
        String params = "tonce="+tonce.toString()+"&accesskey="+ACCESS_KEY+"&requestmethod=post&id=1&method=buyOrder2&params="+price+","+amount;
        String hash = signatureUtil.getSignature(params, SECRET_KEY);
        String url = "https://api.btcchina.com/api_trade_v1.php";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        String userpass = ACCESS_KEY + ":" + hash;
        String basicAuth = "Basic " + DatatypeConverter.printBase64Binary(userpass.getBytes());
        //add reuqest header
        con.setRequestMethod("POST") ;
        con.setRequestProperty("Json-Rpc-Tonce", tonce.toString());
        con.setRequestProperty ("Authorization", basicAuth);
       String postdata = "{\"method\": \"buyOrder2\", \"params\": [\""+price+"\",\""+amount+"\"], \"id\": 1}";
        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postdata);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();

        System.out.println("Post parameters : " + postdata);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
 
        //print result
        
        Gson gson = new Gson();
        Order order = new Order();
        order = gson.fromJson(response.toString(), Order.class);
        System.out.println(response.toString());
        return order;
	}

	@Override
	public Order sellOrder(String price, String amount) throws Exception {
		 String tonce = ""+(System.currentTimeMillis() * 1000);
	        String params = "tonce="+tonce.toString()+"&accesskey="+ACCESS_KEY+"&requestmethod=post&id=1&method=sellOrder2&params="+price+","+amount;
	        String hash = signatureUtil.getSignature(params, SECRET_KEY);
	        String url = "https://api.btcchina.com/api_trade_v1.php";
	        URL obj = new URL(url);
	        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
	        String userpass = ACCESS_KEY + ":" + hash;
	        String basicAuth = "Basic " + DatatypeConverter.printBase64Binary(userpass.getBytes());
	        //add reuqest header
	        con.setRequestMethod("POST") ;
	        con.setRequestProperty("Json-Rpc-Tonce", tonce.toString());
	        con.setRequestProperty ("Authorization", basicAuth);
	       String postdata = "{\"method\": \"sellOrder2\", \"params\": [\""+price+"\",\""+amount+"\"], \"id\": 1}";
	        // Send post request
	        con.setDoOutput(true);
	        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	        wr.writeBytes(postdata);
	        wr.flush();
	        wr.close();
	        int responseCode = con.getResponseCode();

	        System.out.println("Post parameters : " + postdata);
	        System.out.println("Response Code : " + responseCode);
	        BufferedReader in = new BufferedReader(
	                new InputStreamReader(con.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        in.close();
	 
	        //print result
	        Gson gson = new Gson();
	        Order order = new Order();
	        order = gson.fromJson(response.toString(), Order.class);
	        System.out.println(order.getResult());
	        return order;
	    	
	}

}
