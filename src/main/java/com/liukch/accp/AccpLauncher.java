package com.liukch.accp;

import io.vertx.core.Launcher;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author liukch on 2019-11-14
 */
public class AccpLauncher extends Launcher {
    static {
        // Tell Vert.x to use log4j2 as the logging framework
        // See: https://vertx.io/docs/vertx-core/java/#_logging
//        System.setProperty("vertx.logger-delegate-factory-class-name", "io.vertx.core.logging.Log4j2LogDelegateFactory");
    }

    public static void main(String[] args) {
        // Open AWS ACCP.
        // See https://github.com/corretto/amazon-corretto-crypto-provider
//        AmazonCorrettoCryptoProvider.install();
        System.out.println();
        final String name;
        try {
            name = Cipher.getInstance("AES/GCM/NoPadding").getProvider().getName();
            System.out.println("Crypto provider name: " + name);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        new AccpLauncher().dispatch(args);
    }
}
