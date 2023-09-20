import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Входные параметры отсутсвуют");
            return;
        }
        BigInteger n;
        try {
            n = new BigInteger(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Входные параметры заданы некорректно.\n" + e.getMessage());
            return;
        }
        if (!n.isProbablePrime(100)) {
            throw new IllegalArgumentException("Введенное число не является простым.");
        }
        BigInteger g = generatePrimitiveRoot(n);
        System.out.println("В качестве примитивного корня выбрано g = " + g);
        Participant alice = new Participant("Алиса", n, g);
        Participant bob = new Participant("Боб", n, g);
        System.out.println("Пользователь \"" + alice.getName() + "\" сгенерировал следующее большое целое число: " + alice.getPrivateInt());
        System.out.println("Пользователь \"" + alice.getName() + "\" отослал пользователю \"" + bob.getName() + "\" число X = " + alice.getPublicInt());
        bob.calculateKey(alice.getPublicInt());
        System.out.println();
        System.out.println("Пользователь \"" + bob.getName() + "\" сгенерировал следующее большое целое число: " + bob.getPrivateInt());
        System.out.println("Пользователь \"" + bob.getName() + "\" отослал пользователю " + alice.getName() + " число Y = " + bob.getPublicInt());
        alice.calculateKey(bob.getPublicInt());
        System.out.println();
        System.out.println("Пользователь \"" + alice.getName() + "\" вычислил ключ " + alice.getKey());
        System.out.println("Пользователь \"" + bob.getName() + "\" вычислил ключ " + bob.getKey());
        System.out.println("Пользователи успешно сгенерировали секретный ключ: " + alice.getKey());
    }

    public static BigInteger generatePrimitiveRoot(BigInteger n) {
        Random rand = new Random();
        BigInteger prime = new BigInteger(n.bitLength(), rand);
        ArrayList<BigInteger> orders = getDivisors(n.subtract(BigInteger.ONE));
        for (; ; ) {
            if (prime.compareTo(n) >= 0) {
                prime = BigInteger.ONE;
            }
            if (isPrimitive(prime, n, orders)) {
                return prime;
            }
            prime = prime.add(BigInteger.ONE);
        }
    }

    public static boolean isPrimitive(BigInteger prime, BigInteger n, ArrayList<BigInteger> orders) {
        if (prime.compareTo(BigInteger.ONE) < 0 || prime.compareTo(n) >= 0) {
            return (false);
        }
        for (BigInteger each : orders) {
            if (prime.modPow(each, n).compareTo(BigInteger.ONE) == 0) {
                if (each.compareTo(n.subtract(BigInteger.ONE)) == 0) {
                    return (true);
                }
                break;
            }
        }
        return (false);
    }

    public static ArrayList<BigInteger> getDivisors(BigInteger num) {
        ArrayList<BigInteger> divisors = new ArrayList<>();
        for (BigInteger i = BigInteger.ONE; i.compareTo(num.divide(BigInteger.TWO).add(BigInteger.ONE)) < 0; i = i.add(BigInteger.ONE)) {
            if (num.mod(i).compareTo(BigInteger.ZERO) == 0) {
                divisors.add(i);
            }
        }
        divisors.add(num);
        return (divisors);
    }
}