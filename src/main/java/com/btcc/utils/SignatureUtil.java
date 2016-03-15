package com.btcc.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Data
public class SignatureUtil {
private String data;
private String key;

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
}
