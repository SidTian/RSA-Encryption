import java.math.BigInteger;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("enter your message");
        String msg = input.nextLine();
        input.close();
        // 创建RAS对象
        // 34981 5897 65537
        // 899
        BigInteger p = new BigInteger("31");
        BigInteger q = new BigInteger("29");
        BigInteger e = new BigInteger("23");
        RAS res = new RAS(p, q, e);
        // 调用加密方法
        // 打印加密后的数组结果
        String[] arr = res.encrypt(msg);
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");

        System.out.println();

        // 打印解密的结果
        String s = res.decrypt(arr);
        System.out.println(s);
    }
}
