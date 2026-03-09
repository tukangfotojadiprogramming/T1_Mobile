fun main() {

    println("===== SISTEM PENILAIAN =====")

    print("Masukkan Nama Mahasiswa: ")
    val nama = readLine()!!

    print("Masukkan Nilai UTS (0-100): ")
    val uts = readLine()!!.toDouble()

    print("Masukkan Nilai UAS (0-100): ")
    val uas = readLine()!!.toDouble()

    print("Masukkan Nilai Tugas (0-100): ")
    val tugas = readLine()!!.toDouble()

    // Akumulasi nilai akhir
    val nilaiAkhir = (uts * 0.3) + (uas * 0.4) + (tugas * 0.3)

    // Menentukan grade
    val grade: String
    val keterangan: String
    val status: String

    when (nilaiAkhir) {
        in 85.0..100.0 -> {
            grade = "A"
            keterangan = "Sangat Baik"
            status = "LULUS"
        }
        in 70.0..84.9 -> {
            grade = "B"
            keterangan = "Baik"
            status = "LULUS"
        }
        in 60.0..69.9 -> {
            grade = "C"
            keterangan = "Cukup"
            status = "LULUS"
        }
        in 50.0..59.9 -> {
            grade = "D"
            keterangan = "Kurang"
            status = "TIDAK LULUS"
        }
        else -> {
            grade = "E"
            keterangan = "Sangat Kurang"
            status = "TIDAK LULUS"
        }
    }

    println("\n===== HASIL PENILAIAN =====")
    println("Nama        : $nama")
    println("Nilai UTS   : $uts (Bobot 30%)")
    println("Nilai UAS   : $uas (Bobot 40%)")
    println("Nilai Tugas : $tugas (Bobot 30%)")
    println("---------------------------")
    println("Nilai Akhir : $nilaiAkhir")
    println("Grade       : $grade")
    println("Keterangan  : $keterangan")
    println("Status      : $status")

    if (status == "LULUS") {
        println("\nSelamat! Anda dinyatakan LULUS.")
    } else {
        println("\nMaaf, Anda dinyatakan TIDAK LULUS.")
    }
}