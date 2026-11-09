/** Nomor 2(c): hash table custom, key gabungan dokter|tanggal, separate chaining. */
public class DemoJadwalDokter {
    private static final class Entri {
        final String key;
        final boolean[] slot = new boolean[10]; // true=terisi
        Entri next;
        Entri(String key, Entri next) { this.key = key; this.next = next; }
    }
    private final Entri[] buckets = new Entri[2048];
    private int count;
    private static String key(String dokter, String tanggal) {
        return dokter + "|" + tanggal; // tanggal memakai ISO YYYY-MM-DD
    }
    private int index(String key) {
        int h = 0;
        for (int i = 0; i < key.length(); i++) h = 31 * h + key.charAt(i);
        return (h & 0x7fffffff) % buckets.length;
    }
    private Entri cari(String key) {
        for (Entri p = buckets[index(key)]; p != null; p = p.next)
            if (p.key.equals(key)) return p;
        return null;
    }
    void pesan(String dokter, String tanggal, int slotKe) {
        if (slotKe < 0 || slotKe >= 10) throw new IllegalArgumentException("Slot 0-9");
        String k = key(dokter,tanggal);
        int posisi = index(k);
        Entri e = cari(k);
        if (e == null) { e = new Entri(k,buckets[posisi]); buckets[posisi] = e; count++; }
        if (e.slot[slotKe]) throw new IllegalStateException("Slot sudah terisi");
        e.slot[slotKe] = true;
    }
    void batalkan(String dokter, String tanggal, int slotKe) {
        if (slotKe < 0 || slotKe >= 10) throw new IllegalArgumentException("Slot 0-9");
        Entri e = cari(key(dokter,tanggal));
        if (e != null) e.slot[slotKe] = false;
    }
    void tampilkan(String dokter, String tanggal) {
        Entri e = cari(key(dokter,tanggal));
        System.out.println("Jadwal " + dokter + " pada " + tanggal + ":");
        for (int s = 0; s < 10; s++)
            System.out.println("Slot " + (s+1) + ": " + (e!=null && e.slot[s] ? "Terisi" : "Kosong"));
    }
    int jumlahKey() { return count; }
    public static void main(String[] args) {
        DemoJadwalDokter jadwal = new DemoJadwalDokter();
        jadwal.pesan("D-07", "2026-11-10", 1);
        jadwal.pesan("D-07", "2026-11-10", 4);
        jadwal.tampilkan("D-07", "2026-11-10");
        jadwal.batalkan("D-07", "2026-11-10", 1);
        System.out.println("Setelah pembatalan slot 2:");
        jadwal.tampilkan("D-07", "2026-11-10");
        System.out.println("Jumlah key: " + jadwal.jumlahKey());
    }
}
