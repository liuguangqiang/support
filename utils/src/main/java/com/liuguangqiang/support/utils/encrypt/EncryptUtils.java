package com.liuguangqiang.support.utils.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A utils for encrypt.
 * <p>
 * <li>MD5</li>
 * <li>SHA1</li>
 * <li>SHA256</li>
 * </p>
 */
public class EncryptUtils {

    public enum EncryptType {
        MD5, SHA1, SHA256
    }

    private static final String ALG_MD5 = "MD5";

    private static final String ALG_SHA1 = "SHA-1";

    private static final String ALG_SHA256 = "SHA-256";

    public static String encode(EncryptType type, String content) {
        MessageDigest instance = null;
        byte[] encryptMsg;
        try {
            switch (type) {
                case MD5:
                    instance = MessageDigest.getInstance(ALG_MD5);
                    break;
                case SHA1:
                    instance = MessageDigest.getInstance(ALG_SHA1);
                    break;
                case SHA256:
                    instance = MessageDigest.getInstance(ALG_SHA256);
                    break;
            }
            encryptMsg = instance.digest(content.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(
                    "Unbelievabl! How can u bypass the method ? No such algorithm !");
        }

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < encryptMsg.length; i++) {
            switch (Integer.toHexString(encryptMsg[i]).length()) {
                case 1:
                    buffer.append("0");
                    buffer.append(Integer.toHexString(encryptMsg[i]));
                    break;
                case 2:
                    buffer.append(Integer.toHexString(encryptMsg[i]));
                    break;
                case 8:
                    buffer.append((Integer.toHexString(encryptMsg[i])).substring(6,
                            8));
                    break;
            }
        }
        return buffer.toString();
    }
}
