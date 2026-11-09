# Struktur Data Sehat Medika

Sembilan program Java pendukung untuk LO1–LO3. Semua contoh memakai data simulasi dan struktur berbasis array atau node buatan sendiri. Repositori ini tidak memuat dokumen ujian, deklarasi, atau data pasien nyata.

## Menjalankan

Gunakan JDK 17 atau lebih baru. Dari direktori repositori:

```sh
mkdir build
javac -encoding UTF-8 -Xlint:all -d build src/*.java
java -cp build RiwayatKunjungan
java -cp build RiwayatKunjunganLengkap
java -cp build UjiRiwayatKunjungan
```

- `RiwayatKunjungan`: program utama 3b, empat penambahan, dua kali mundur, dan satu kali maju.
- `RiwayatKunjunganLengkap`: demo penghapusan node 3c.
- `UjiRiwayatKunjungan`: pengujian otomatis (29 pemeriksaan).
- `Demo*`: contoh pendukung LO1 dan LO2. Ganti nama kelas pada perintah `java -cp build` untuk menjalankannya.

Sembilan program dikompilasi dan dijalankan ulang; keluaran cocok dengan paket sumber.
