data class NilaiMahasiswa(
    val nim: String,
    val nama: String,
    val mataKuliah: String,
    val nilai: Int
)

fun getGrade(nilai: Int): String {
    return when (nilai) {
        in 85..100 -> "A"
        in 70..84 -> "B"
        in 60..69 -> "C"
        in 50..59 -> "D"
        else -> "E"
    }
}

fun main() {

    val mahasiswa = listOf(
        NilaiMahasiswa("2024001","Budi Santoso","Pemrograman",85),
        NilaiMahasiswa("2024002","Ani Wijaya","Pemrograman",92),
        NilaiMahasiswa("2024003","Citra Dewi","Pemrograman",68),
        NilaiMahasiswa("2024004","Dani Pratama","Pemrograman",45),
        NilaiMahasiswa("2024005","Eka Putri","Pemrograman",74),
        NilaiMahasiswa("2024006","Fajar Nugroho","Pemrograman",88),
        NilaiMahasiswa("2024007","Gita Lestari","Pemrograman",59),
        NilaiMahasiswa("2024008","Hadi Saputra","Pemrograman",77),
        NilaiMahasiswa("2024009","Indra Gunawan","Pemrograman",83),
        NilaiMahasiswa("2024010","Joko Susanto","Pemrograman",65)
    )

    println("===== DATA NILAI MAHASISWA =====")
    println("No   NIM      Nama            MataKuliah    Nilai")

    mahasiswa.forEachIndexed { index, m ->
        println("${index+1}   ${m.nim}   ${m.nama}   ${m.mataKuliah}   ${m.nilai}")
    }

    println("\n===== STATISTIK =====")

    val rata = mahasiswa.map { it.nilai }.average()

    val tertinggi = mahasiswa.maxByOrNull { it.nilai }

    val terendah = mahasiswa.minByOrNull { it.nilai }

    println("Total Mahasiswa : ${mahasiswa.size}")
    println("Rata-rata Nilai : $rata")
    println("Nilai Tertinggi : ${tertinggi?.nilai} (${tertinggi?.nama})")
    println("Nilai Terendah  : ${terendah?.nilai} (${terendah?.nama})")

    println("\n===== MAHASISWA LULUS =====")

    val lulus = mahasiswa.filter { it.nilai >= 70 }

    lulus.forEachIndexed { index, m ->
        println("${index+1}. ${m.nama} - ${m.nilai} (${getGrade(m.nilai)})")
    }

    println("\n===== MAHASISWA TIDAK LULUS =====")

    val tidakLulus = mahasiswa.filter { it.nilai < 70 }

    tidakLulus.forEachIndexed { index, m ->
        println("${index+1}. ${m.nama} - ${m.nilai} (${getGrade(m.nilai)})")
    }

    println("\n===== SORT NILAI ASCENDING =====")

    mahasiswa.sortedBy { it.nilai }.forEach {
        println("${it.nama} - ${it.nilai}")
    }

    println("\n===== SORT NILAI DESCENDING =====")

    mahasiswa.sortedByDescending { it.nilai }.forEach {
        println("${it.nama} - ${it.nilai}")
    }

    println("\n===== JUMLAH PER GRADE =====")

    val grupGrade = mahasiswa.groupBy { getGrade(it.nilai) }

    grupGrade.forEach { (grade, list) ->
        println("Grade $grade : ${list.size} mahasiswa")
    }
println("\n===== CARI MAHASISWA BERDASARKAN NAMA =====")

print("Masukkan nama yang ingin dicari: ")
val keyword = readLine() ?: ""

val hasil = mahasiswa.filter { it.nama.contains(keyword, ignoreCase = true) }

if (hasil.isEmpty()) {
    println("Mahasiswa tidak ditemukan.")
} else {
    println("Hasil pencarian:")
    hasil.forEach {
        println("${it.nama} - ${it.nilai}")
    }
}
}
