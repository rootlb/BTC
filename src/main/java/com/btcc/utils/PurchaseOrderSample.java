package com.btcc.utils;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Mac;
import javax.net.ssl.HttpsURLConnection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.DatatypeConverter;

import org.json.JSONObject;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

public class PurchaseOrderSample {
	private  static String ACCESS_KEY = "5b320f9c-1912-4941-bcd2-40312733a9b6";
	private static String SECRET_KEY = "3faaffe8-9c58-4cdd-86c7-d67eb003a2a2";
	    private static String HMAC_SHA1_ALGORITHM = "HmacSHA1";
	    private String postdata="";
	    private String tonce = ""+(System.currentTimeMillis() * 1000);
	    public static void main(String[] args) throws Exception {
	    try {
	    IO.Options opt = new IO.Options();
	    opt.reconnection = true;
	    Logger.getLogger(PurchaseOrderSample.class.getName()).setLevel(Level.FINE);
	    final Socket socket = IO.socket("https://websocket.btcc.com", opt); 
	    socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
	    PurchaseOrderSample sm= new PurchaseOrderSample();
	    @Override
	    public void call(Object... args) {
	    System.out.println("connected");
	    socket.emit("subscribe", "marketdata_cnybtc"); // subscribe
	    socket.emit("subscribe", "marketdata_cnyltc"); // subscribe another market
	    socket.emit("subscribe", "marketdata_btcltc"); // subscribe another market
	    socket.emit("subscribe", "grouporder_cnybtc"); // subscribe grouporder 
	    socket.emit("subscribe", "grouporder_cnyltc"); // subscribe another market
	    socket.emit("subscribe", "grouporder_btcltc"); // subscribe another market
	    //Use 'private' method to subscribe the order and account_info feed
	    try {
	    List arg = new ArrayList();
	    arg.add(sm.get_payload());
	    arg.add(sm.get_sign());
	    socket.emit("private",arg);
	    } catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    }
	    }
	    }).on("trade", new Emitter.Listener() {
	    @Override
	    public void call(Object... args) {
	    JSONObject json = (JSONObject) args[0]; //receive the trade message
	    System.out.println(json); 
	    }
	    }).on("ticker", new Emitter.Listener() {
	    @Override
	    public void call(Object... args) {
	    JSONObject json = (JSONObject) args[0];//receive the ticker message
	    System.out.println(json);
	    }
	    }).on("grouporder", new Emitter.Listener() {
	    @Override
	    public void call(Object... args) {
	    JSONObject json = (JSONObject) args[0];//receive the grouporder message
	    System.out.println(json);
	    }
	    }).on("order", new Emitter.Listener() {
	    @Override
	    public void call(Object... args) {
	    JSONObject json = (JSONObject) args[0];//receive your order feed
	    System.out.println(json);
	    }
	    }).on("account_info", new Emitter.Listener() {
	    @Override
	    public void call(Object... args) {
	    JSONObject json = (JSONObject) args[0];//receive your account_info feed
	    System.out.println(json);
	    }
	    }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
	    @Override
	    public void call(Object... args) {
	    System.out.println("disconnected");
	    }
	    });
	    socket.connect();
	    } catch (URISyntaxException ex) {
	    Logger.getLogger(PurchaseOrderSample.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    } 
	    public String get_payload() throws Exception{
	    postdata = "{\"tonce\":\""+tonce.toString()+"\",\"accesskey\":\""+ACCESS_KEY+"\",\"requestmethod\": \"post\",\"id\":\""+tonce.toString()+"\",         \"method\": \"subscribe\", \"params\": [\"order_cnyltc\",\"account_info\"]}";//subscribe order feed for cnyltc market and balance feed
	    System.out.println("postdata is: " + postdata);
	    return postdata;
	    }
	    public String get_sign() throws Exception{
	    String params = "tonce="+tonce.toString()+"&accesskey="+ACCESS_KEY+"&requestmethod=post&id="+tonce.toString()+"&method=subscribe&params=order_cnyltc,account_info"; //subscribe the order of cnyltc market and the account_info
	    String hash = getSignature(params, SECRET_KEY);
	    String userpass = ACCESS_KEY + ":" + hash;
	    String basicAuth = DatatypeConverter.printBase64Binary(userpass.getBytes());
	    return basicAuth;
	    }
	    public String getSignature(String data,String key) throws Exception {
	    // get an hmac_sha1 key from the raw key bytes
	    SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
	    // get an hmac_sha1 Mac instance and initialize with the signing key
	    Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
	    mac.init(signingKey);
	    // compute the hmac on input data bytes
	    byte[] rawHmac = mac.doFinal(data.getBytes());
	    return bytArrayToHex(rawHmac);
	    }
	    private String bytArrayToHex(byte[] a) {
	    StringBuilder sb = new StringBuilder();
	    for(byte b: a)
	    sb.append(String.format("%02x", b&0xff));
	    return sb.toString();
	    }
}