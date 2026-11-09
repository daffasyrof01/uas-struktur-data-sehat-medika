public class RiwayatKunjungan {
    private static final class Node {
        final String catatan;
        Node prev;
        Node next;

        Node(String catatan) {
            this.catatan = catatan;
        }
    }

    private Node terakhir;
    private Node saatIni;

    public void tambahKunjungan(String catatan) {
        if (catatan == null || catatan.isBlank()) {
            throw new IllegalArgumentException("Catatan tidak boleh kosong.");
        }

        Node baru = new Node(catatan);
        baru.prev = terakhir;
        if (terakhir != null) {
            terakhir.next = baru;
        }
        terakhir = baru;
        saatIni = baru;
    }

    public boolean sebelumnya() {
        if (saatIni == null || saatIni.prev == null) {
            return false;
        }
        saatIni = saatIni.prev;
        return true;
    }

    public boolean berikutnya() {
        if (saatIni == null || saatIni.next == null) {
            return false;
        }
        saatIni = saatIni.next;
        return true;
    }

    public void tampilkanSaatIni() {
        System.out.println(saatIni == null ? "Riwayat kosong." : saatIni.catatan);
    }

    public static void main(String[] args) {
        RiwayatKunjungan riwayat = new RiwayatKunjungan();
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
    }
}
