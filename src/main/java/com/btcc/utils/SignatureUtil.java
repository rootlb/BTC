package com.btcc.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Data
public class SignatureUtil {
private String data;
private String key;
private String postdata="";
private String tonce = ""+(System.currentTimeMillis() * 1000);

private  static String ACCESS_KEY = "5b320f9c-1912-4941-bcd2-40312733a9b6";
private static String SECRET_KEY = "3faaffe8-9c58-4cdd-86c7-d67eb003a2a2";

private static String HMAC_SHA1_ALGORITHM = "HmacSHA1";

public static String getSignature(String data,String key) throws Exception {
    // get an hmac_sha1 key from the raw key bytes
    SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
    // get an hmac_sha1 Mac instance and initialize with the signing key
    Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
    mac.init(signingKey);
    // compute the hmac on input data bytes
    byte[] rawHmac = mac.doFinal(data.getBytes());
    return bytArrayToHex(rawHmac);  
}
/**
 * 认证头用 Base64 编码
 * @param Authorization（认证头） 
 * @return 用Base64编码后的认证头
 */
private static String bytArrayToHex(byte[] a) {
   StringBuilder sb = new StringBuilder();
   for(byte b: a)
      sb.append(String.format("%02x", b&0xff));
   return sb.toString();
}

public String get_payload() throws Exception{
	postdata = "{\"tonce\":\""+tonce.toString()+"\",\"accesskey\":\""+ACCESS_KEY+"\",\"requestmethod\": \"post\",\"id\":\""+tonce.toString()+"\",\"method\": \"subscribe\", \"params\": [\"order_cnybtc\",\"order_cnyltc\",\"order_btcltc\",\"account_info\"]}";//subscribe order and balance feed
		
	System.out.println("postdata is: " + postdata);
	return postdata;
}

public String get_sign() throws Exception{
	String params = "tonce="+tonce.toString()+"&accesskey="+ACCESS_KEY+"&requestmethod=post&id="+tonce.toString()+"&method=subscribe&params=order_cnybtc,order_cnyltc,order_btcltc,account_info"; 
	String hash = getSignature(params, SECRET_KEY);
	String userpass = ACCESS_KEY + ":" + hash;
	String basicAuth = DatatypeConverter.printBase64Binary(userpass.getBytes());
	return basicAuth;
}

}
