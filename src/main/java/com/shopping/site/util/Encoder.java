package com.shopping.site.util;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Slf4j
public class Encoder {
	private static final String HMAC_SHA256 = "HmacSHA256";
	
	public String signHmacSHA256(String data, String secretKey) {        
        try {
        	SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), HMAC_SHA256);
            Mac mac = Mac.getInstance(HMAC_SHA256);
			mac.init(secretKeySpec);
			byte[] rawHmac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
	        return toHexString(rawHmac);
		} catch (InvalidKeyException e) {
            log.error("Problem with key", e);
            return null;
		} catch (NoSuchAlgorithmException e) {
            log.error("Problem with algorithm", e);
            return null;
		}
    }
	
    private String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        Formatter formatter = new Formatter(sb);
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return sb.toString();
    }
}
