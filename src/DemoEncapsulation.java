/** Nomor 1(c): private + pemeriksaan peran, untuk ilustrasi saja. */
public class DemoEncapsulation {
    enum Peran { DOKTER, PERAWAT, TAMU }
    static final class RekamMedis {
        private String diagnosis;
        private String resep;
        RekamMedis(String diagnosis, String resep) {
            this.diagnosis = diagnosis;
            this.resep = resep;
        }
        void ubahData(Peran peran, String diagnosisBaru, String resepBaru) {
            if (peran != Peran.DOKTER) throw new SecurityException("Akses ditolak");
            this.diagnosis = diagnosisBaru;
            this.resep = resepBaru;
        }
        String bacaDiagnosis(Peran peran) {
            if (peran != Peran.DOKTER) throw new SecurityException("Akses ditolak");
            return diagnosis;
        }
        String bacaResep(Peran peran) {
            if (peran != Peran.DOKTER) throw new SecurityException("Akses ditolak");
            return resep;
        }
    }
    public static void main(String[] args) {
        RekamMedis r = new RekamMedis("Observasi", "Istirahat");
        r.ubahData(Peran.DOKTER, "Pemulihan", "Istirahat dan kontrol");
        System.out.println("Dokter: " + r.bacaDiagnosis(Peran.DOKTER));
        try {
            System.out.println("Tamu: " + r.bacaResep(Peran.TAMU));
        } catch (SecurityException ex) {
            System.out.println("Tamu: " + ex.getMessage());
        }
    }
}
