package com.xl.base;

import org.junit.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2018-04-30
 * @Time: 11:13
 * To change this template use File | Settings | File Templates.
 */
public class RSATest {
    @Test
    public void demoTest() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        System.out.println(keyPairGenerator);
        keyPairGenerator.initialize(512);
        KeyPair keyPair = keyPairGenerator.genKeyPair();

    }
}
