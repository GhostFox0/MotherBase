package inc.sam.cipherlogin;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

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

    public static CipherClass getInstance(Context a) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeySpecException {
        if (instance==null){
            instance =new CipherClass(a);
        }
        return instance;
    }

    public CipherClass(Context context) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeySpecException {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        if (!preferences.getBoolean("firstTime",false)){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("firstTime",true);
            generator = KeyGenerator.getInstance("AES");
            generator.init(128);

            symmetricKey = generator.generateKey();
            editor.putString("symmetricKey",Base64.encodeToString(symmetricKey.getEncoded(),Base64.NO_WRAP));
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);

            keyPair = keyPairGenerator.genKeyPair();

            publicKey=keyPair.getPublic();
            editor.putString("publicKey",Base64.encodeToString(publicKey.getEncoded(),Base64.NO_WRAP));
            privateKey = keyPair.getPrivate();
            editor.putString("privateKey",Base64.encodeToString(privateKey.getEncoded(),Base64.NO_WRAP));
            editor.commit();
        }
        else{
            symmetricKey = new SecretKeySpec(Base64.decode(preferences.getString("symmetricKey",null),Base64.NO_WRAP),"AES");
            preferences.getString("privateKey",null);
            publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(preferences.getString("publicKey",null).getBytes("UTF-8"),Base64.NO_WRAP)));
            privateKey =KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(preferences.getString("privateKey",null).getBytes("UTF-8"),Base64.NO_WRAP)));
        }
    }

    public KeyGenerator getGenerator() {
        return generator;
    }

    public KeyPairGenerator getKeyPairGenerator() {
        return keyPairGenerator;
    }

    public KeyPair getKeyPair() {
        return keyPair;
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
