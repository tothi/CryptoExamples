import java.util.Random;
import java.security.SecureRandom;

// fixed seed generates the same sequence

class RandomExample
{
    public static void main(String args[])
    {
	Random random = new Random(500);
	System.out.print("java.util.Random(500): ");
	for(int i = 0; i < 10; ++i)
	    System.out.print(String.valueOf(random.nextInt(100) + " "));
	System.out.println();

	SecureRandom srandom = new SecureRandom();
	System.out.print("java.security.SecureRandom: ");
	for(int i = 0; i < 10; ++i)
	    System.out.print(String.valueOf(srandom.nextInt(100)) + " ");
	System.out.println();

    }
}
