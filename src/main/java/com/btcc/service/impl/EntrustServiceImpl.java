package com.btcc.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.btcc.model.AccountInfoResponse;
import com.btcc.model.BuyResponse;
import com.btcc.model.CancelResponse;
import com.btcc.model.Order;
import com.btcc.model.OrderResponse;
import com.btcc.model.SellResponse;
import com.btcc.service.EntrustService;
import com.btcc.utils.SignatureUtil;
import com.google.gson.Gson;

@Service
public class EntrustServiceImpl implements EntrustService{
	
	private  static String ACCESS_KEY = "5b320f9c-1912-4941-bcd2-40312733a9b6";
	private static String SECRET_KEY = "3faaffe8-9c58-4cdd-86c7-d67eb003a2a2";
	private SignatureUtil signatureUtil;
	
	private StringBuffer request(String param,String postdata) throws Exception{
		String tonce = ""+(System.currentTimeMillis() * 1000);
		String params = param;
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
        
        return response;
}
	
	@Override
	public BuyResponse buyOrder(String price, String amount) throws Exception {
        String tonce = ""+(System.currentTimeMillis() * 1000);
        String params = "tonce="+tonce.toString()+"&accesskey="+ACCESS_KEY+"&requestmethod=post&id=1&method=buyOrder2&params="+price+","+amount;
        String postdata = "{\"method\": \"buyOrder2\", \"params\": [\""+price+"\",\""+amount+"\"], \"id\": 1}";
        StringBuffer response = request(params,postdata);
        Gson gson = new Gson();
        BuyResponse buyResponse = new BuyResponse();
        buyResponse = gson.fromJson(response.toString(), BuyResponse.class);
        System.out.println(response.toString());
        return buyResponse;
	}

	@Override
	public SellResponse sellOrder(String price, String amount) throws Exception {
		 	String tonce = ""+(System.currentTimeMillis() * 1000);
	        String params = "tonce="+tonce.toString()+"&accesskey="+ACCESS_KEY+"&requestmethod=post&id=1&method=sellOrder2&params="+price+","+amount;
	        String postdata = "{\"method\": \"sellOrder2\", \"params\": [\""+price+"\",\""+amount+"\"], \"id\": 1}";
	        StringBuffer response = request(params,postdata);
	        //print result
	        Gson gson = new Gson();
	        SellResponse sellResponse = new SellResponse();
	        sellResponse = gson.fromJson(response.toString(), SellResponse.class);
	        System.out.println(sellResponse.getResult());
	        return sellResponse;
	    	
	}
	
	@Override
	public AccountInfoResponse getAccountInfo() throws Exception {
        String tonce = ""+(System.currentTimeMillis() * 1000);
        String params = "tonce="+tonce.toString()+"&accesskey="+ACCESS_KEY+"&requestmethod=post&id=1&method=getAccountInfo&params=all";
        String postdata = "{\"method\": \"getAccountInfo\", \"params\": [\"all\"], \"id\": 1}";
        StringBuffer response = request(params,postdata);
        //print result
        Gson gson = new Gson();
        AccountInfoResponse accountInfoResponse = new AccountInfoResponse();
        accountInfoResponse = gson.fromJson(response.toString(), AccountInfoResponse.class);
        System.out.println(response.toString());
        return accountInfoResponse;
	}

	@Override
	public boolean canceOrder(String id) throws Exception {
		String tonce = ""+(System.currentTimeMillis() * 1000);
        String params = "tonce="+tonce.toString()+"&accesskey="+ACCESS_KEY+"&requestmethod=post&id=1&method=cancelOrder&params="+id;
        String postdata = "{\"method\": \"cancelOrder\", \"params\": ["+id+"], \"id\": 1}";
        StringBuffer response = request(params,postdata);
        //print result
        Gson gson = new Gson();
        CancelResponse cancelResponse = new CancelResponse();
        cancelResponse = gson.fromJson(response.toString(), CancelResponse.class);
        System.out.println(response.toString());
        return cancelResponse.isResult();
	}

	@Override
	public ArrayList<Order> getOrders() throws Exception {
		String tonce = ""+(System.currentTimeMillis() * 1000);
		String params = "tonce="+tonce.toString()+"&accesskey="+ACCESS_KEY+"&requestmethod=post&id=1&method=getOrders&params=false,BTCCNY,1";
		String postdata = "{\"method\": \"getOrders\", \"params\": [false,\"BTCCNY\",1], \"id\": 1}";
        StringBuffer response = request(params,postdata);
        Gson gson = new Gson();
        OrderResponse orderResponse = new OrderResponse();
        orderResponse = gson.fromJson(response.toString(), OrderResponse.class);
        System.out.println(orderResponse.toString());
        return orderResponse.getResult().getOrder();
	}

}
