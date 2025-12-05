# 🎮 Tetris using Java OOP

Program ini dikembangkan oleh saya bersama rekan-rekan saya:

- [@cinsheesh] ([https://github.com/cinsheesh])
- [@kholiqmitra] ([https://github.com/kholiqmitra])

Sebuah game **Tetris sederhana berbasis Java Swing** yang mendukung:

- Input nickname sebelum bermain
- Penyimpanan skor ke database
- Tampilan leaderboard Top 10
- Kontrol Keyboard responsif
- Border visual pada area permainan
- Sistem rotasi tetromino
- Game loop menggunakan Multithreading

---

## 🚀 Fitur Utama

| Fitur           | Deskripsi                                       |
| --------------- | ----------------------------------------------- |
| Gameplay Tetris | Sistem jatuh otomatis, rotasi, gerak kiri/kanan |
| Nickname Input  | Pemain memasukkan nama sebelum game             |
| Database MySQL  | Skor tersimpan secara lokal                     |
| Leaderboard     | Menampilkan Top 10 pemain                       |
| Multithreading  | Game berjalan di thread sendiri                 |
| Border Visual   | Menunjukkan batas area permainan                |

---

## 📦 Teknologi yang digunakan

- **Java 8+**
- **Swing GUI**
- **JDBC**
- **MySQL Connector (mysql-connector-j-9.1.0.jar)**
- **OOP — Abstract Class, Interface, Encapsulation**

---

## 🧩 Kontrol Permainan

| Tombol        | Aksi            |
| ------------- | --------------- |
| ⬅ Left Arrow  | Geser kiri      |
| ➡ Right Arrow | Geser kanan     |
| ⬆ Up Arrow    | Rotasi kiri     |
| ⬇ Down Arrow  | Rotasi kanan    |
| Space         | Drop cepat      |
| D             | Turun 1 langkah |
| P             | Pause           |

---

## 🛠 Cara Instalasi & Menjalankan

### 1️⃣ Pastikan sudah menginstall:

- JDK
- MySQL Server
- MySQL Workbench (opsional)

---

### 2️⃣ Import File `mysql-connector-j-9.1.0.jar`

Jika menggunakan:

- **IDE NetBeans** → Project → Properties → Libraries → Add JAR
- **IDE IntelliJ** → File → Project Structure → Libraries → Add
- **IDE Eclipse** → Build Path → Add External JAR

---

### 3️⃣ Setup Database

Buka MySQL dan jalankan:

```sql
CREATE DATABASE tetris_db;
```

Program otomatis membuat tabel player ketika pertama dijalankan:

player_id | nickname | score | created_at

### 4️⃣ Run Program

Compile seluruh file .java kemudian jalankan:

`java TetrisFrame`

Atau klik Run dari IDE.

🧾 Struktur Folder

```
/src
  Board.java
  GameObject.java
  KoneksiDatabase.java
  LeaderboardPanel.java
  MainMenuPanel.java
  NicknamePanel.java
  Tetromino.java
  TetrominoPieces.java
  Rotatable.java
  TetrisFrame.java
```
