import java.math.BigInteger;
import java.util.Random;

public class Participant {
    private String name;
    private BigInteger n, g, key;
    private BigInteger privateInt; // случайное большое целое число
    public BigInteger publicInt; // число, пересылаемое другому пользователю

    public BigInteger getPrivateInt() {
        return privateInt;
    }

    public BigInteger getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public BigInteger getPublicInt() {
        return publicInt;
    }

    public Participant(String name, BigInteger n, BigInteger g) {
        try {
            this.name = name;
            this.n = n;
            this.g = g;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        this.generatePrivateInt();
        this.generatePublicInt();
    }

    private void generatePrivateInt() {
        Random rand = new Random();
        this.privateInt = new BigInteger(32, rand); // от 0 до 2^32 - 1
    }

    private void generatePublicInt() {
        this.publicInt = this.g.modPow(this.privateInt, n);
    }

    public void calculateKey(BigInteger otherParticipantNum) {
        this.key = otherParticipantNum.modPow(this.privateInt, this.n);
    }
}