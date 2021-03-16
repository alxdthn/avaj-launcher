package avaj.launcher.parser;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class MD5Decryptor {

    private static MD5Decryptor mD5Decryptor = null;

    MessageDigest messageDigest;

    private final Map<String, Integer> decryptedIntegers = new HashMap<>();

    private MD5Decryptor(MessageDigest messageDigest) {
        this.messageDigest = messageDigest;
    }

    public static MD5Decryptor getInstance() throws NoSuchAlgorithmException {
        if (mD5Decryptor == null) {
            mD5Decryptor = new MD5Decryptor(MessageDigest.getInstance("MD5"));
        }
        return mD5Decryptor;
    }

    public int decryptInteger(String value) {
        messageDigest.update(value.getBytes());
        byte[] digest = messageDigest.digest();
        String hash = DatatypeConverter.printHexBinary(digest);

        Integer alreadyDecrypted = decryptedIntegers.get(hash);
        if (alreadyDecrypted == null) {

        } else {
            return alreadyDecrypted;
        }
    }
}
