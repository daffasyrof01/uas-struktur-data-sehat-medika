/**
 * Versi pengembangan dari RiwayatKunjungan.java untuk mendukung
 * pembahasan nomor 3(c): penghapusan node pada Doubly Linked List.
 * Semua node dibuat sendiri, tanpa Java Collections Framework.
 */
public class RiwayatKunjunganLengkap {
    private static final class Node {
        final String catatan;
        Node prev;
        Node next;

        Node(String catatan) {
            this.catatan = catatan;
        }
    }

    private Node pertama;  // head
    private Node terakhir; // tail
    private Node saatIni;  // node yang sedang ditampilkan

    public void tambahKunjungan(String catatan) {
        if (catatan == null || catatan.isBlank()) {
            throw new IllegalArgumentException("Catatan tidak boleh kosong.");
        }

        Node baru = new Node(catatan);
        baru.prev = terakhir;
        if (terakhir == null) {
            pertama = baru;            // list awalnya kosong
        } else {
            terakhir.next = baru;      // sambungkan node lama ke node baru
        }
        terakhir = baru;
        saatIni = baru;
    }

    public boolean sebelumnya() {
        if (saatIni == null || saatIni.prev == null) return false;
        saatIni = saatIni.prev;
        return true;
    }

    public boolean berikutnya() {
        if (saatIni == null || saatIni.next == null) return false;
        saatIni = saatIni.next;
        return true;
    }

    public String catatanSaatIni() {
        return saatIni == null ? null : saatIni.catatan;
    }

    public void tampilkanSaatIni() {
        System.out.println(saatIni == null ? "Riwayat kosong." : saatIni.catatan);
    }

    /**
     * Hapus node yang sedang dipilih (saatIni).
     * Setelah penghapusan, pilih node berikutnya; jika tidak ada,
     * pilih node sebelumnya. Jika list menjadi kosong, saatIni = null.
     */
    public boolean hapusSaatIni() {
        if (saatIni == null) return false;

        Node dihapus = saatIni;
        Node kiri = dihapus.prev;
        Node kanan = dihapus.next;

        if (kiri == null) {
            pertama = kanan;           // menghapus head
        } else {
            kiri.next = kanan;         // putuskan hubungan kiri -> dihapus
        }

        if (kanan == null) {
            terakhir = kiri;           // menghapus tail
        } else {
            kanan.prev = kiri;         // putuskan hubungan kanan -> dihapus
        }

        saatIni = (kanan != null) ? kanan : kiri;
        dihapus.prev = null;
        dihapus.next = null;
        return true;
    }

    /** Menampilkan seluruh daftar dari head; hanya untuk demonstrasi. */
    public void tampilkanSemua() {
        Node node = pertama;
        System.out.print("Isi riwayat: ");
        if (node == null) {
            System.out.println("(kosong)");
            return;
        }
        while (node != null) {
            System.out.print(node.catatan);
            node = node.next;
            if (node != null) System.out.print(" <-> ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        RiwayatKunjunganLengkap riwayat = new RiwayatKunjunganLengkap();
        for (int i = 1; i <= 4; i++) {
            riwayat.tambahKunjungan("Kunjungan " + i);
            System.out.print("Tambah " + i + "  : ");
            riwayat.tampilkanSaatIni();
        }
        for (int i = 1; i <= 2; i++) {
            riwayat.sebelumnya();
            System.out.print("Mundur " + i + "  : ");
            riwayat.tampilkanSaatIni();
        }
        riwayat.berikutnya();
        System.out.print("Maju 1    : ");
        riwayat.tampilkanSaatIni();

        System.out.println("\nDemo penghapusan node tengah (Kunjungan 3):");
        riwayat.hapusSaatIni();
        riwayat.tampilkanSemua();
        System.out.print("Saat ini  : ");
        riwayat.tampilkanSaatIni();
        riwayat.sebelumnya();
        System.out.print("Mundur    : ");
        riwayat.tampilkanSaatIni();

        System.out.println("\nDemo kondisi batas:");
        RiwayatKunjunganLengkap kosong = new RiwayatKunjunganLengkap();
        System.out.println("Mundur saat kosong: " + kosong.sebelumnya());
        System.out.println("Hapus saat kosong : " + kosong.hapusSaatIni());
        kosong.tambahKunjungan("Kunjungan tunggal");
        System.out.println("Maju di node akhir: " + kosong.berikutnya());
        kosong.hapusSaatIni();
        System.out.print("Setelah hapus satu-satunya node: ");
        kosong.tampilkanSaatIni();
    }
}
