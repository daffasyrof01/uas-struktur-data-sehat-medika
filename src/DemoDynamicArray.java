/** Nomor 1(b): dynamic array custom, tidak menggunakan java.util.ArrayList. */
public class DemoDynamicArray {
    static final class RekamMedis {
        final String catatan;
        RekamMedis(String catatan) { this.catatan = catatan; }
    }
    static final class RiwayatArray {
        private RekamMedis[] data = new RekamMedis[2];
        private int size;
        void tambah(RekamMedis catatan) {
            if (size == data.length) {
                RekamMedis[] lebihBesar = new RekamMedis[data.length * 2];
                for (int i = 0; i < size; i++) lebihBesar[i] = data[i];
                data = lebihBesar;
            }
            data[size++] = catatan;
        }
        RekamMedis ambil(int index) {
            if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
            return data[index];
        }
        int ukuran() { return size; }
        int kapasitas() { return data.length; }
    }
    public static void main(String[] args) {
        RiwayatArray r = new RiwayatArray();
        for (int i = 1; i <= 5; i++) {
            r.tambah(new RekamMedis("Kunjungan " + i));
            System.out.println("Tambah " + i + " | ukuran=" + r.ukuran() +
                " | kapasitas=" + r.kapasitas());
        }
        System.out.println("Riwayat kedua: " + r.ambil(1).catatan);
    }
}
