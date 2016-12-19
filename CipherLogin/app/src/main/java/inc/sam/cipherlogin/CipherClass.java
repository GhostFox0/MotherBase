package inc.sam.cipherlogin;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * Created by Samuele.Pretto on 19/12/2016.
 */

public class CipherClass {

    private Cipher cipher, decipher;
    private KeyGenerator generator;
    private SecretKey symmetricKey;
    private KeyPairGenerator keyPairGenerator;
    private KeyPair keyPair;
    private PublicKey publicKey;
    private PrivateKey privateKey;

    private static CipherClass instance;

    public static CipherClass getInstance() throws NoSuchAlgorithmException {
        if (instance==null){
            instance =new CipherClass();
        }
        return instance;
    }

    public CipherClass() throws NoSuchAlgorithmException {

        generator = KeyGenerator.getInstance("AES");
        generator.init(128);

        symmetricKey = generator.generateKey();

        keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);

        keyPair = keyPairGenerator.genKeyPair();

        publicKey=keyPair.getPublic();

        privateKey = keyPair.getPrivate();
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public SecretKey getSymmetricKey() {
        return symmetricKey;
    }

    public String encrypt(String text,String algorithm,String paramKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        cipher = Cipher.getInstance(algorithm);
        switch (paramKey){
            case "public":
                cipher.init(Cipher.ENCRYPT_MODE,publicKey);
                break;
            case "private":
                cipher.init(Cipher.ENCRYPT_MODE,privateKey);
                break;
            case "symmetric":
                cipher.init(Cipher.ENCRYPT_MODE,symmetricKey);
                break;
        }
        return Base64.encodeToString(cipher.doFinal(text.getBytes("UTF-8")),Base64.DEFAULT);

    }

    public String decrypt(String text, String algorithm, String paramKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        decipher = Cipher.getInstance(algorithm);
        switch (paramKey){
            case "public":
                decipher.init(Cipher.DECRYPT_MODE,publicKey);
                break;
            case "private":
                decipher.init(Cipher.DECRYPT_MODE,privateKey);
                break;
            case "symmetric":
                decipher.init(Cipher.DECRYPT_MODE,symmetricKey);
                break;
        }
        return new String(decipher.doFinal(Base64.decode(text.getBytes(),Base64.DEFAULT)));
    }

}
