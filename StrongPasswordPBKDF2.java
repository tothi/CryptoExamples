import java.util.Base64;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;

class StrongPasswordPBKDF2
{
    public static void main(String args[]) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
	long start = System.currentTimeMillis();
	System.out.println(generateStrongPasswordHash(args[0], Integer.parseInt(args[1])));
	long finish = System.currentTimeMillis();
	System.out.println("PBKDF2 hash calculation with random salt took " + (finish-start) + " ms");
    }

    private static String generateStrongPasswordHash(String password, int iterations) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();
         
        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return "sha256:" + iterations + ":" + Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException
    {
	SecureRandom sr = new SecureRandom();
	byte[] salt = new byte[16];
	sr.nextBytes(salt);
	return salt;
    }

}
