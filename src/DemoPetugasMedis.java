/** Nomor 1(a): abstract class untuk data bersama; interface khusus tugas. */
public class DemoPetugasMedis {
    interface PenulisResep {
        void tulisResep(String isi);
    }
    interface PencatatTandaVital {
        void catatTandaVital(int denyutNadi);
    }
    static abstract class PetugasMedis {
        private final String nama;
        PetugasMedis(String nama) { this.nama = nama; }
        void login() { System.out.println(nama + " berhasil login."); }
        void lihatJadwal() { System.out.println(nama + " melihat jadwal."); }
    }
    static final class Dokter extends PetugasMedis implements PenulisResep {
        Dokter(String nama) { super(nama); }
        public void tulisResep(String isi) {
            System.out.println("Dokter menulis resep: " + isi);
        }
    }
    static final class Perawat extends PetugasMedis implements PencatatTandaVital {
        Perawat(String nama) { super(nama); }
        public void catatTandaVital(int denyutNadi) {
            System.out.println("Perawat mencatat nadi: " + denyutNadi + " bpm");
        }
    }
    public static void main(String[] args) {
        Dokter dokter = new Dokter("Dr. Tia");
        Perawat perawat = new Perawat("Ners Rina");
        dokter.login(); dokter.lihatJadwal(); dokter.tulisResep("Istirahat");
        perawat.login(); perawat.lihatJadwal(); perawat.catatTandaVital(78);
    }
}
