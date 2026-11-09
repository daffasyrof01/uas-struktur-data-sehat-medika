/** Nomor 2(b): rekurens a(n)=a(n-1)+k, a(0)=0. */
public class DemoSlotRawatJalan {
    static long rekursif(int n, int k) {
        if (n < 0) throw new IllegalArgumentException("n harus >= 0");
        if (n == 0) return 0;
        return rekursif(n-1, k) + k;
    }
    static long iteratif(int n, int k) {
        if (n < 0) throw new IllegalArgumentException("n harus >= 0");
        long hasil = 0;
        for (int i = 0; i < n; i++) hasil += k;
        return hasil;
    }
    static long rumusLangsung(int n, int k) {
        if (n < 0) throw new IllegalArgumentException("n harus >= 0");
        return (long)n * k;
    }
    public static void main(String[] args) {
        int n = 5, k = 8;
        System.out.println("n=" + n + ", k=" + k);
        System.out.println("Rekursif: " + rekursif(n,k));
        System.out.println("Iteratif: " + iteratif(n,k));
        System.out.println("Langsung: " + rumusLangsung(n,k));
        System.out.println("Semua sama: " +
            (rekursif(n,k)==iteratif(n,k) && iteratif(n,k)==rumusLangsung(n,k)));
    }
}
