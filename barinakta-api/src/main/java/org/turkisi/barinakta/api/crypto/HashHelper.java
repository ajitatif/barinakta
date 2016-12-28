package org.turkisi.barinakta.api.crypto;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class HashHelper {

    public static String calculateHash(String clearText) throws NoSuchAlgorithmException {

        MessageDigest md5 = MessageDigest.getInstance("MD5");

        byte[] digest = md5.digest(applySaltAndPepper(clearText).getBytes(Charset.forName("UTF-8")));
        return new String(digest);
    }

    private static String applySaltAndPepper(String clearText) {
        return "10001" + clearText + "10001";
    }
}
