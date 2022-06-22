import java.math.BigInteger;

public class RAS {
    // N的因数
    private BigInteger p;
    private BigInteger q;

    // 解密钥匙
    private BigInteger d;

    // 加密钥匙 对外提供
    public BigInteger N;
    public BigInteger e;

    // 构造函数
    public RAS(BigInteger p, BigInteger q, BigInteger e) {
        this.p = p;
        this.q = q;
        this.N = this.p.multiply(this.q);
        // e的值不能是 p-1 or q-1
        this.e = e;
        // 解密钥匙
        this.d = setD();
    }

    // 求d要通过扩展欧几里得算法
    public BigInteger setD() {
        ExtendedEuclideanAlgorithm getd = new ExtendedEuclideanAlgorithm();
        // φ(n)
        BigInteger OLN = this.p.subtract(new BigInteger("1")).multiply(this.q.subtract(new BigInteger("1")));
        // 使用算法得到x和y的值
        getd.e_gcd(OLN.intValue(), e.intValue());
        // 返回最终的解密钥匙
        return getd.y > 0 ? new BigInteger("" + getd.y) : new BigInteger("" + OLN.add(new BigInteger("" + getd.y)));
    }

    // 加密
    public String[] encrypt(String str) {
        String arr[] = new String[str.length()];
        for (int i = 0; i < arr.length; i++)
            arr[i] = new BigInteger("" + new BigInteger("" + (int) str.charAt(i)).pow(e.intValueExact()).remainder(N))
                    .toString();
        return arr;
    }

    // 解密
    public String decrypt(String[] arr) {
        String result = "";
        // 解密钥匙
        for (int i = 0; i < arr.length; i++) {
            result += (char) new BigInteger("" + new BigInteger(arr[i]).pow(this.d.intValue()).remainder(N)).intValue();
        }

        return result;
    }
}

class ExtendedEuclideanAlgorithm {
    public int x;
    public int y;

    int e_gcd(int a, int b) {
        if (b == 0) {
            x = 1;
            y = 0;
            return a;
        }
        int gcd = e_gcd(b, a % b);
        swapXY();
        y -= a / b * x;
        return gcd;
    }

    void swapXY() {
        int tem = x;
        x = y;
        y = tem;
    }
}