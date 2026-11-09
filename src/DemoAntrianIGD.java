/** Nomor 2(a): binary min-heap dan FIFO queue custom berbasis array. */
public class DemoAntrianIGD {
    static final class Pasien {
        final String nama;
        final int triase; // angka kecil = lebih mendesak
        final int urutan;
        Pasien(String nama, int triase, int urutan) {
            this.nama = nama; this.triase = triase; this.urutan = urutan;
        }
    }
    static int banding(Pasien a, Pasien b) {
        if (a.triase != b.triase) return Integer.compare(a.triase, b.triase);
        return Integer.compare(a.urutan, b.urutan); // urutan tiba jika triase sama
    }
    static final class MinHeap {
        private Pasien[] data = new Pasien[4];
        private int size;
        void tambah(Pasien p) {
            if (size == data.length) {
                Pasien[] besar = new Pasien[data.length * 2];
                for (int k = 0; k < size; k++) besar[k] = data[k];
                data = besar;
            }
            int i = size++;
            data[i] = p;
            while (i > 0) {
                int parent = (i-1)/2;
                if (banding(data[parent], data[i]) <= 0) break;
                Pasien t = data[parent]; data[parent] = data[i]; data[i] = t;
                i = parent;
            }
        }
        Pasien ambil() {
            if (size == 0) throw new IllegalStateException("Antrean kosong");
            Pasien hasil = data[0];
            data[0] = data[--size]; data[size] = null;
            int i = 0;
            while (true) {
                int kiri = 2*i+1, kanan = kiri+1, terkecil = i;
                if (kiri < size && banding(data[kiri], data[terkecil]) < 0) terkecil = kiri;
                if (kanan < size && banding(data[kanan], data[terkecil]) < 0) terkecil = kanan;
                if (terkecil == i) break;
                Pasien t = data[i]; data[i] = data[terkecil]; data[terkecil] = t;
                i = terkecil;
            }
            return hasil;
        }
        boolean kosong() { return size == 0; }
    }
    static final class Fifo {
        private Pasien[] data = new Pasien[4];
        private int depan, size;
        void tambah(Pasien p) {
            if (size == data.length) {
                Pasien[] besar = new Pasien[data.length * 2];
                for (int k = 0; k < size; k++) besar[k] = data[(depan+k)%data.length];
                data = besar; depan = 0;
            }
            data[(depan+size)%data.length] = p; size++;
        }
        Pasien ambil() {
            if (size == 0) throw new IllegalStateException("Antrean kosong");
            Pasien hasil = data[depan]; data[depan] = null;
            depan = (depan+1)%data.length; size--;
            return hasil;
        }
        boolean kosong() { return size == 0; }
    }
    public static void main(String[] args) {
        Pasien[] masuk = {new Pasien("Andi",3,1),new Pasien("Budi",2,2),
            new Pasien("Citra",1,3),new Pasien("Dina",2,4)};
        MinHeap heap = new MinHeap(); Fifo fifo = new Fifo();
        for (Pasien p : masuk) { heap.tambah(p); fifo.tambah(p); }
        System.out.println("Urutan Priority Queue (heap):");
        while (!heap.kosong()) System.out.println(heap.ambil().nama);
        System.out.println("Urutan FIFO:");
        while (!fifo.kosong()) System.out.println(fifo.ambil().nama);
    }
}
