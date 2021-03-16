package avaj.launcher.parser;

import avaj.launcher.AvajLauncher;
import avaj.launcher.exceptions.DecryptionException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class MD5Decryptor {

    private static MD5Decryptor mD5Decryptor = null;

    MessageDigest messageDigest;

    private final Map<String, Integer> decryptedIntegers = new HashMap<>();

    private Integer lastDecryptedInteger = 0;

    private MD5Decryptor(MessageDigest messageDigest) {
        this.messageDigest = messageDigest;
    }

    public static MD5Decryptor getInstance() throws NoSuchAlgorithmException {
        if (mD5Decryptor == null) {
            mD5Decryptor = new MD5Decryptor(MessageDigest.getInstance("MD5"));
        }
        return mD5Decryptor;
    }

    public int decryptInteger(String encryptedInteger) {
        Integer alreadyDecrypted = decryptedIntegers.get(encryptedInteger);
        if (alreadyDecrypted != null) return alreadyDecrypted;

        while (lastDecryptedInteger <= AvajLauncher.MAX_INT_FOR_DECRYPT) {
            String integerString = lastDecryptedInteger.toString();
            messageDigest.update(integerString.getBytes());
            byte[] digest = messageDigest.digest();
            String hash = String.format("%032x", new BigInteger(1, digest));
            decryptedIntegers.put(hash, lastDecryptedInteger);
            if (hash.equals(encryptedInteger)) {
                return lastDecryptedInteger++;
            }
            lastDecryptedInteger++;
        }
        throw new DecryptionException();
    }

    public String decryptType(String encryptedType) {
        switch (encryptedType) {
            case "994736b4f0aec72f6e5ae580051d012f":
                return "Baloon";
            case "554cd647d6b135f7e36ab1214c5e816a":
                return "JetPlane";
            case "2ab8b43468e8b92b0fc5c81e70e35a2d":
                return "Helicopter";
        }
        throw new DecryptionException();
    }
}
