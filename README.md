# AdoptPet

Project Android Studio untuk mata kuliah Praktikum Mobile Programming dengan tema aplikasi adopsi hewan. Project ini dikembangkan secara bertahap sebagai implementasi materi praktikum di setiap pertemuan, dengan tetap mempertahankan identitas aplikasi `AdoptPet`. Pada tahap saat ini, dokumentasi README berfokus pada hasil pengerjaan Pertemuan 2 sampai Pertemuan 10.

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
- Pertemuan 10: Implementasi RecyclerView dengan Tiga Mode Tampilan (List, Grid, dan Card View) serta Filter Kategori

## Implementasi Pertemuan 2

Pada pertemuan ini aplikasi mulai dibangun dengan membuat tampilan awal dan splash screen. Project memiliki `SplashActivity` sebagai halaman pembuka dan `MainActivity` sebagai halaman utama. Layout splash disusun menggunakan komponen Android seperti `ConstraintLayout`, `LinearLayout`, `TextView`, `View`, dan elemen drawable sederhana. Setelah splash screen tampil beberapa saat, aplikasi akan berpindah ke halaman utama secara otomatis.

## Implementasi Pertemuan 3

Pada pertemuan ini struktur tampilan dirapikan agar lebih modular dan mudah dikembangkan. File `activity_main.xml` tidak hanya berisi satu layout besar, tetapi disusun dari beberapa komponen yang dipisahkan ke file terpisah lalu digabungkan menggunakan `include`. Selain itu, project juga menggunakan `dimens.xml` untuk ukuran-ukuran umum dan `styles.xml` untuk style yang dapat dipakai ulang, sehingga tampilan aplikasi menjadi lebih konsisten.

## Implementasi Pertemuan 4

Pada pertemuan ini aplikasi mulai memiliki alur program yang lebih jelas. Data hewan direpresentasikan dengan model `PetItem`, lalu disediakan melalui `PetRepository` sebagai dummy data. Dashboard pada `MainActivity` menampilkan beberapa hewan yang tersedia untuk diadopsi. Ketika pengguna memilih salah satu hewan, aplikasi akan membuka `PetDetailActivity` menggunakan `Intent` dan mengirim data `id` hewan melalui `Intent Extras`. Activity detail kemudian mengambil data yang sesuai dari repository dan menampilkannya ke layar.

## Implementasi Pertemuan 10

Pada pertemuan ini aplikasi dikembangkan secara signifikan dengan menerapkan **RecyclerView dinamis** menggantikan data statis (hardcoded) yang ada di halaman utama sebelumnya:
1. **Multi-Layout Mode:** RecyclerView diimplementasikan dengan mendukung **tiga tipe tampilan yang berbeda secara interaktif**:
   - **Mode List:** Menampilkan daftar hewan peliharaan secara vertikal dengan baris horizontal ramping (gambar di kiri, teks di kanan).
   - **Mode Grid:** Menampilkan daftar hewan peliharaan dalam bentuk 2 kolom sejajar (gambar di atas, info ringkas di bawah).
   - **Mode Card View:** Menampilkan daftar hewan peliharaan dengan kartu besar premium vertikal secara utuh (dilengkapi info umur, jenis kelamin, dan tombol favorit).
2. **Pengalih Mode Interaktif:** Ditambahkan **PopupMenu** pada ikon menu header (`imgMenu` di pojok kanan atas) untuk mengizinkan pengguna memilih dan berganti mode layout RecyclerView secara real-time.

## Fitur yang Ada Saat Ini

- Splash screen sebagai tampilan pembuka aplikasi
- Dashboard/beranda bertema adopsi hewan dengan RecyclerView dinamis
- Menu peralihan 3 Mode Tampilan (List, Grid, dan Card View) secara instan
- Penyaringan (Filter) Kategori dinamis (Semua, Kucing, Anjing)
- Halaman detail hewan lengkap dengan format Rupiah dinamis
- Navigasi antar activity menggunakan `Intent` & `Intent Extras`

## Struktur Utama Project

- `SplashActivity.kt` : activity pembuka aplikasi
- `MainActivity.kt` : dashboard utama aplikasi dengan kontrol RecyclerView & Kategori
- `PetDetailActivity.kt` : halaman detail hewan
- `ui/PetAdapter.kt` : adapter RecyclerView dengan logika multi-view holder
- `model/PetItem.kt` : model data hewan yang telah diperluas
- `data/PetRepository.kt` : sumber dummy data terpusat
- `res/layout/` : layout activity, item RecyclerView (`item_pet_list`, `item_pet_grid`, `item_pet_card`), dan komponen modular lainnya
- `res/menu/menu_view_mode.xml` : resource menu untuk PopupMenu pengalih mode tampilan
- `res/values/` : warna, string, dimensi, style, dan theme

## Catatan

Project ini akan terus dilanjutkan hingga pertemuan-pertemuan berikutnya sebagai satu aplikasi yang berkembang secara bertahap. Pada versi sekarang, dokumentasi dan implementasi yang dirangkum di README telah difokuskan dari hasil praktikum Pertemuan 2 hingga Pertemuan 10 dengan tema aplikasi adopsi hewan.
