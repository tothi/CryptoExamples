import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class HashExample
{
    public static void main(String args[])
    {
	try {
	    MessageDigest md = MessageDigest.getInstance(args[0]);

	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    byte[] buffer = new byte[32 * 1024 * 1024]; // temp buffer
	    int bytesRead;
	    try {
		while((bytesRead = System.in.read(buffer)) > 0)
		    baos.write(buffer, 0, bytesRead);
	    } catch (IOException e) {
		System.out.println("IOException");
	    }
	    // System.out.println("Message is: " + message);
	    byte[] message = baos.toByteArray();
	    md.update(message);
	    String hexdigest = new BigInteger(1, md.digest()).toString(16);
	    System.out.println("Calculated " + args[0] + " hash is: " + hexdigest);
	} catch (NoSuchAlgorithmException e) {
	    System.out.println("No such algorithm: " + args[0]);
	}
    }
}
