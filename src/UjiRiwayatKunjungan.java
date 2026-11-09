/** Pengujian otomatis tanpa memakai Java Collections Framework. */
public class UjiRiwayatKunjungan {
    private static int jumlahUji = 0;

    private static void cek(boolean kondisi, String pesan) {
        jumlahUji++;
        if (!kondisi) throw new AssertionError("GAGAL: " + pesan);
    }

    private static void cekCatatan(RiwayatKunjunganLengkap daftar,
                                  String yangDiharapkan, String pesan) {
        String aktual = daftar.catatanSaatIni();
        cek(yangDiharapkan == null ? aktual == null : yangDiharapkan.equals(aktual), pesan);
    }

    public static void main(String[] args) {
        RiwayatKunjunganLengkap daftar = new RiwayatKunjunganLengkap();
        cek(!daftar.sebelumnya(), "Tidak boleh mundur ketika kosong");
        cek(!daftar.berikutnya(), "Tidak boleh maju ketika kosong");
        cek(!daftar.hapusSaatIni(), "Tidak boleh menghapus ketika kosong");
        cekCatatan(daftar, null, "Current kosong");

        for (int i = 1; i <= 4; i++) daftar.tambahKunjungan("Kunjungan " + i);
        cekCatatan(daftar, "Kunjungan 4", "Current setelah append");
        cek(!daftar.berikutnya(), "Tidak boleh maju melewati tail");
        cek(daftar.sebelumnya(), "Mundur pertama");
        cekCatatan(daftar, "Kunjungan 3", "Setelah mundur pertama");
        cek(daftar.sebelumnya(), "Mundur kedua");
        cekCatatan(daftar, "Kunjungan 2", "Setelah mundur kedua");
        cek(daftar.berikutnya(), "Maju pertama");
        cekCatatan(daftar, "Kunjungan 3", "Setelah maju pertama");

        cek(daftar.hapusSaatIni(), "Hapus node tengah");
        cekCatatan(daftar, "Kunjungan 4", "Current setelah menghapus tengah");
        cek(daftar.sebelumnya(), "Navigasi dari K4 ke K2 setelah hapus K3");
        cekCatatan(daftar, "Kunjungan 2", "Node kiri tersambung ke node kanan");
        cek(daftar.berikutnya(), "Navigasi dari K2 ke K4 setelah hapus K3");
        cekCatatan(daftar, "Kunjungan 4", "Node kanan tersambung ke node kiri");

        cek(daftar.hapusSaatIni(), "Hapus tail K4");
        cekCatatan(daftar, "Kunjungan 2", "Current setelah hapus tail");
        cek(!daftar.berikutnya(), "Tail baru tidak punya next");
        cek(daftar.sebelumnya(), "Pindah ke head K1");
        cek(daftar.hapusSaatIni(), "Hapus head K1");
        cekCatatan(daftar, "Kunjungan 2", "Head baru K2");
        cek(!daftar.sebelumnya(), "Head baru tidak punya prev");
        cek(daftar.hapusSaatIni(), "Hapus node satu-satunya K2");
        cekCatatan(daftar, null, "Kosong setelah semua node dihapus");
        cek(!daftar.hapusSaatIni(), "Hapus kedua kali saat kosong");

        boolean exception = false;
        try { daftar.tambahKunjungan("  "); }
        catch (IllegalArgumentException ex) { exception = true; }
        cek(exception, "Catatan blank harus ditolak");
        System.out.println("LULUS: " + jumlahUji + " pemeriksaan.");
    }
}
