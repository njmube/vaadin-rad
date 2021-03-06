import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.assertTrue;

public class SaltTest {

    @Test
    public void getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        byte[] b = new byte[] {1,2};

        StringBuilder builder = new StringBuilder("byte[] salt = new byte[] {");
        for(int i=0; i<16; i++) {
            builder.append(salt[i]);
            if(i!=15) {
                builder.append(",");
            }
        }
        builder.append("};");

        System.err.println(builder);
    }

    @Test
    public void getPasswordHash() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String password = "password";
        String passwordHash;

        /*
        byte[] salt = new byte[] {-42,111,-102,-67,37,4,-36,-75,48,99,126,126,-120,98,105,117};
        String passwordHash;

        final char[] chars = password.toCharArray();
        PBEKeySpec spec = new PBEKeySpec(chars, salt, 1000, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        passwordHash = Base64.encodeBase64String(hash);
        */
        String oldHash = "$2a$10$emvvR53LIzBlEnl.F7GAqeC4/IcppozwcFkNkPVPvbji/e1aToEvK";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        passwordHash=encoder.encode(password);
        System.err.println(passwordHash);

        assertTrue(encoder.matches(password, passwordHash));
        assertTrue(encoder.matches(password, oldHash));
    }

}

