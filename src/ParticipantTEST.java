import org.junit.Assert;
import org.junit.Test;
import java.math.BigInteger;

public class ParticipantTEST {
    @Test
    public void unitTest(){
        Participant alice = new Participant("Алиса", new BigInteger("23"), new BigInteger("5"));
        Participant bob = new Participant("Алиса", new BigInteger("23"), new BigInteger("5"));
        System.out.println(alice.getName() + ": x = " + alice.getPrivateInt() + ", X = " + alice.publicInt);
        System.out.println(bob.getName() + ": y = " + bob.getPrivateInt() + ", X = " + bob.publicInt);
        alice.calculateKey(bob.publicInt);
        bob.calculateKey(alice.publicInt);
        System.out.println("Key = " + alice.getKey());
        Assert.assertEquals(alice.getKey(), bob.getKey());
    }
}