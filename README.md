# POSTTEST4-PBO-PROGRES-GYM

## Deskripsi 
Program ini adalah Sistem Manajemen Progress Gym berbasis console yang menerapkan pola MVC (Model–View–Controller) sederhana. Program mendukung dua jenis akun, yaitu Admin dan User:
Admin dapat mengelola data Member Gym melalui fitur CRUD (Create, Read, Update, Delete) dan pencarian member berdasarkan ID.
User dapat mencatat dan mengelola data Progress Latihan harian dengan fitur CRUD dan pencarian progress berdasarkan ID.
Program juga dilengkapi validasi input, auto-generate ID, data dummy (untuk testing), serta tampilan menu yang rapi dengan frame.

## 🏗 Struktur MVC
### MVC (Model–View–Controller) adalah pola arsitektur pemrograman yang memisahkan program menjadi tiga bagian utama:

* Model (M) → merepresentasikan data dan logika bisnis inti. Model bertugas menyimpan serta mengelola data, misalnya menambah, mengubah, menghapus, atau mengambil data.
* View (V) → bagian yang bertugas menampilkan data kepada pengguna serta menerima input. View tidak menyimpan logika bisnis, hanya fokus pada antarmuka.
* Controller (C) → menjadi penghubung antara View dan Model. Controller menerima input dari pengguna melalui View, kemudian memanggil fungsi yang ada di Model, lalu hasilnya dikembalikan lagi ke View untuk ditampilkan.

<img width="399" height="322" alt="image" src="https://github.com/user-attachments/assets/a3465d1e-ba41-4abd-aa80-6b9fa67b8e8d" />

Program Sistem Manajemen Progress Gym menggunakan pola MVC sederhana berbasis console.

* Bagian Model dikelompokkan ke dalam package model/, berisi class User, Member, dan Progress yang menyimpan data inti program.
* Bagian Controller/Service dikelompokkan ke dalam package service/, berisi class AuthService, MemberService, ProgressService, dan Validator yang menangani logika CRUD, login, registrasi, serta validasi input.
* Bagian View direpresentasikan oleh class Main.java di package com.mycompany.main, yang berfungsi menampilkan menu di console, menerima input dari user/admin, dan memanggil service yang sesuai.

Dengan adanya pemisahan ini, program lebih terstruktur dan mudah dipelihara. Misalnya, jika suatu saat ingin mengubah tampilan menjadi berbasis GUI atau web, maka cukup mengganti bagian View tanpa harus mengubah Model maupun Service/Controller.

## INHERITANCE
<img width="990" height="600" alt="image" src="https://github.com/user-attachments/assets/2146d930-ec3f-48cb-8987-bab3be789967" />

Disini saya menambahkan class "orang", yang dimana class orang ini bertindak sebagai superclass untuk user dan member

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/e25bb524-5f2f-47a9-9ee2-f8b31e363ce4" />

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/b8222cdd-bfc9-4162-8c17-d29e98dbefed" />

## INHERTANCE + ABSTRACTION 
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/b2bfdad9-8604-47b0-8efd-3f9c64f7a63a" />

Kode User extends Orang menunjukkan inheritance, yaitu class User mewarisi atribut dan method dari class Orang. Sementara itu, karena Orang adalah abstract class yang memiliki abstract method introduce(), maka class User wajib memberikan implementasinya sendiri. Hal ini merupakan penerapan abstraction, di mana method abstrak yang hanya dideklarasikan di superclass diwujudkan secara nyata di subclass.

## ABSTRACTION
<img width="689" height="312" alt="image" src="https://github.com/user-attachments/assets/53ae7f8c-4fc9-498b-9b8b-185f61d77ed3" />

Penerapan abstraction dilakukan dengan membuat sebuah abstract class bernama Orang yang memiliki atribut dasar name serta abstract method introduce(). Abstract method ini wajib dioverride di setiap subclass, sehingga baik User maupun Member harus menyediakan implementasi masing-masing dari method tersebut.

## INTERFACE
<img width="328" height="155" alt="image" src="https://github.com/user-attachments/assets/75a4f0eb-6361-486e-be15-f2aadfecdde9" />

Penerapan interface bernama Printable yang berisi method printData(). Interface ini kemudian diimplementasikan oleh class User, Member, dan Progress sehingga setiap class bisa menampilkan datanya dengan format yang berbeda sesuai kebutuhan.

## POLYMORPHISM 

### OVERRIDING

<img width="862" height="295" alt="image" src="https://github.com/user-attachments/assets/66f310db-632a-44e9-bace-a9a47b811ffe" />

<img width="1224" height="276" alt="image" src="https://github.com/user-attachments/assets/230eaec1-65a3-461b-aaf9-4bc53c41305c" />

Overriding ini berguna untuk mengganti perilaku bawaan dari method yang diwarisi superclass agar lebih sesuai dengan kebutuhan subclass.
Dalam program ini, overriding toString() membuat object User dan Member saat ditampilkan ke console tidak lagi berupa alamat memori, melainkan informasi detail seperti username, role, ID, atau masa aktif.

<img width="1107" height="238" alt="image" src="https://github.com/user-attachments/assets/105c6e10-ffd3-4c5f-9f29-2a52206d35a0" />

penerapan polymorphism (overriding) terlihat pada class User dan Member yang keduanya meng-override method introduce() dari abstract class Orang, serta meng-override printData() dari interface Printable. Dengan overriding ini, objek User dan Member bisa menggunakan method dengan nama yang sama tetapi menghasilkan output berbeda sesuai konteksnya.

## OVERLOADING

<img width="725" height="266" alt="image" src="https://github.com/user-attachments/assets/19516d4d-6009-46d7-b6d3-a01d4cee5028" />

polymorphism (overloading) diterapkan pada class ProgressService dengan menambahkan dua versi method searchById(). Versi pertama menerima parameter username dan id untuk pencarian data progress milik user tertentu, sedangkan versi kedua hanya menerima parameter id untuk pencarian global.

## Menu utama
<img width="374" height="149" alt="image" src="https://github.com/user-attachments/assets/61c31e34-dc9b-432b-9f06-725e5981e821" />

#### Pada tampilan awal, user diminta untuk memilih salah satu dari tiga menu yang tersedia.

## Registrasi akun
<img width="328" height="62" alt="image" src="https://github.com/user-attachments/assets/c75a405f-1c59-48bb-9eff-2d41eda7a37c" />

#### User memasukkan username dan password untuk membuat akun baru. Jika berhasil, user diarahkan ke menu login.

## Menu Login
<img width="369" height="126" alt="image" src="https://github.com/user-attachments/assets/c7b7208e-7519-49fb-bd3e-489749631f6a" />

#### Ketika user memilih menu login, akan tersedia tiga pilihan.

## Menu login user
<img width="559" height="91" alt="image" src="https://github.com/user-attachments/assets/cf8f7cfd-a115-4d79-a247-4e74fb7d1375" />

#### user diminta memasukkan username dan password, jika benar maka user akan disambut

<img width="162" height="83" alt="image" src="https://github.com/user-attachments/assets/7e7c5876-9bca-4f8f-af7d-4c5401b82817" />

#### kalau password atau username yang dimasukkan oleh user salah, maka user tidak bisa masuk

## Menu user
<img width="379" height="206" alt="image" src="https://github.com/user-attachments/assets/4f817938-b301-412b-bfb8-e2b075c1eb73" />

### CREATE
<img width="466" height="280" alt="image" src="https://github.com/user-attachments/assets/83a2acac-841c-4a76-9421-d7bc582d42df" />

#### User memasukkan data progres baru berupa tanggal latihan, tipe latihan, gerakan, dan detail lainnya.

### READ
<img width="405" height="465" alt="image" src="https://github.com/user-attachments/assets/1f067aff-129d-4a9e-b62d-c6c23956d47b" />

#### User dapat melihat seluruh progres yang sudah tersimpan.

### UPDATE
<img width="447" height="324" alt="image" src="https://github.com/user-attachments/assets/eea9d1f5-3124-4fb9-9eb0-b1034a016f35" />

#### User memasukkan ID progres yang ingin diperbarui, kemudian mengisi data baru (tanggal, gerakan, dll).

<img width="349" height="134" alt="image" src="https://github.com/user-attachments/assets/c77bf902-7445-407b-9764-ffc554e1bd38" />

#### Data yang sudah diupdate

### DELETE
<img width="430" height="233" alt="image" src="https://github.com/user-attachments/assets/18534321-bde3-4a57-8aa7-4cfa7ef0bff9" />

#### User memasukkan ID progres yang ingin dihapus. Program akan menampilkan detail progres tersebut, lalu meminta konfirmasi. Jika memilih Y, data dihapus; jika N, data batal dihapus.

### SEARCH
<img width="363" height="188" alt="image" src="https://github.com/user-attachments/assets/4d6a090c-312f-49b6-b0fc-6998806cf4a6" />

#### User memasukkan ID progres yang dicari. Jika ditemukan, detail progres akan ditampilkan.

### LOGOUT
<img width="350" height="153" alt="image" src="https://github.com/user-attachments/assets/4676b0b0-2384-4ccd-98dc-54bb2ec745e8" />

#### user akan logout dari akun dan kembali ke menu utama


## Menu login admin
<img width="427" height="68" alt="image" src="https://github.com/user-attachments/assets/64e9aa0a-0c1a-421c-b10a-26776237ec73" />

### Menu admin
<img width="334" height="167" alt="image" src="https://github.com/user-attachments/assets/930869bb-aa2e-4c2b-868e-6a17bd0f2057" />

### CREATE (ADMIN)
<img width="377" height="99" alt="image" src="https://github.com/user-attachments/assets/464269e7-cade-44ab-97ae-2a0670c40244" />

#### Admin akan diminta memasukkan nama dan masa aktif untuk menambahkan user

### READ (ADMIN)
<img width="351" height="336" alt="image" src="https://github.com/user-attachments/assets/97326299-56d3-4f25-b346-a47b736119c8" />

#### admin bisa melihat nama dan masa aktif member.

### UPDATE (ADMIN)
<img width="388" height="195" alt="image" src="https://github.com/user-attachments/assets/be171db8-ef83-44ac-b603-3b603bdfbc2c" />

#### admin juga bisa mengupdate data member, terlihat pada gambar admin mengupdate data dengan id member 3

### DELETE (ADMIN)
<img width="367" height="61" alt="image" src="https://github.com/user-attachments/assets/b2eee855-1382-4760-8bd2-dedbfaebd89e" />

#### admin diminta untuk memasukkan id dari data yang dihapus, jika datany ada maka data akan langsung terhapus

### SEARCH (ADMIN)
<img width="352" height="132" alt="image" src="https://github.com/user-attachments/assets/4a8c80e6-7dc4-4ef7-bd7f-b7a06dc7f990" />

#### admin diminta untuk memasukkan id member yang ingin dicari, jika id member yang dicari ada maka sistem akan menampilkan isi dari data dengan id member tersebut.

### LOGOUT (ADMIN)
<img width="352" height="163" alt="image" src="https://github.com/user-attachments/assets/2da20a32-17fc-4a34-b01e-98d7cc84c136" />

#### admin langsung keluar dan kembali ke menu utama
