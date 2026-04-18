# AdoptPet

Project Android Studio untuk mata kuliah Praktikum Mobile Programming dengan tema aplikasi adopsi hewan. Project ini dikembangkan secara bertahap sebagai implementasi materi praktikum di setiap pertemuan, dengan tetap mempertahankan identitas aplikasi `AdoptPet`. Pada tahap saat ini, dokumentasi README berfokus pada hasil pengerjaan Pertemuan 2 sampai Pertemuan 4.

## Informasi Project

- Nama project: `AdoptPet`
- Tema aplikasi: Adopsi hewan peliharaan
- Platform: Android
- Bahasa: Kotlin
- Tools: Android Studio

## Anggota Kelompok 5

- Heru Chandra `(2410501094)`
- Muhammad Farrel Fauzan `(2410501092)`
- Radinka Alifasya Dinova `(2410501073)`
- Tebing Rizky Tsaniansyah `(2410501080)`

## Cakupan Praktikum

README ini saat ini mencakup hasil pengerjaan materi:

- Pertemuan 2: Layout dan Splash Screen
- Pertemuan 3: View, ViewGroup, Style, dan Theme
- Pertemuan 4: Merancang Program Aplikasi dan Intent pada Android

## Implementasi Pertemuan 2

Pada pertemuan ini aplikasi mulai dibangun dengan membuat tampilan awal dan splash screen. Project memiliki `SplashActivity` sebagai halaman pembuka dan `MainActivity` sebagai halaman utama. Layout splash disusun menggunakan komponen Android seperti `ConstraintLayout`, `LinearLayout`, `TextView`, `View`, dan elemen drawable sederhana. Setelah splash screen tampil beberapa saat, aplikasi akan berpindah ke halaman utama secara otomatis.

## Implementasi Pertemuan 3

Pada pertemuan ini struktur tampilan dirapikan agar lebih modular dan mudah dikembangkan. File `activity_main.xml` tidak hanya berisi satu layout besar, tetapi disusun dari beberapa komponen yang dipisahkan ke file terpisah lalu digabungkan menggunakan `include`. Selain itu, project juga menggunakan `dimens.xml` untuk ukuran-ukuran umum dan `styles.xml` untuk style yang dapat dipakai ulang, sehingga tampilan aplikasi menjadi lebih konsisten.

## Implementasi Pertemuan 4

Pada pertemuan ini aplikasi mulai memiliki alur program yang lebih jelas. Data hewan direpresentasikan dengan model `PetItem`, lalu disediakan melalui `PetRepository` sebagai dummy data. Dashboard pada `MainActivity` menampilkan beberapa hewan yang tersedia untuk diadopsi. Ketika pengguna memilih salah satu hewan, aplikasi akan membuka `PetDetailActivity` menggunakan `Intent` dan mengirim data `id` hewan melalui `Intent Extras`. Activity detail kemudian mengambil data yang sesuai dari repository dan menampilkannya ke layar.

## Fitur yang Ada Saat Ini

- Splash screen sebagai tampilan pembuka aplikasi
- Dashboard/beranda bertema adopsi hewan
- Layout modular pada halaman utama
- Data dummy untuk beberapa hewan peliharaan
- Halaman detail hewan
- Navigasi antar activity menggunakan `Intent`
- Pengiriman data antar activity menggunakan `Intent Extras`

## Struktur Utama Project

- `SplashActivity.kt` : activity pembuka aplikasi
- `MainActivity.kt` : dashboard utama aplikasi
- `PetDetailActivity.kt` : halaman detail hewan
- `model/PetItem.kt` : model data hewan
- `data/PetRepository.kt` : sumber dummy data
- `res/layout/` : file-file layout activity dan komponen modular
- `res/values/` : warna, string, dimensi, style, dan theme

## Catatan

Project ini akan terus dilanjutkan hingga pertemuan-pertemuan berikutnya sebagai satu aplikasi yang berkembang secara bertahap. Pada versi sekarang, dokumentasi dan implementasi yang dirangkum di README masih difokuskan pada hasil praktikum Pertemuan 2 sampai Pertemuan 4 dengan tema aplikasi adopsi hewan.
