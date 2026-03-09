interface Discountable {
    fun applyDiscountPersen(persen: Double): Double
}

data class Produk(
    val id: Int,
    val nama: String,
    val harga: Double,
    val kategori: String,
    var stok: Int
) : Discountable {

    override fun applyDiscountPersen(persen: Double): Double {
        return harga - (harga * persen / 100)
    }
}

data class CartItem(
    val produk: Produk,
    var jumlah: Int
)

data class Customer(
    val id: Int,
    val nama: String,
    val email: String,
    val alamat: String?
)

sealed class OrderStatus {
    object Pending : OrderStatus()
    object Processing : OrderStatus()
    object Shipped : OrderStatus()
    object Delivered : OrderStatus()
    object Cancelled : OrderStatus()
}

sealed class PaymentMethod {
    object Cash : PaymentMethod()
    object Transfer : PaymentMethod()
    object EWallet : PaymentMethod()
}

data class Order(
    val id: Int,
    val customer: Customer,
    val items: List<CartItem>,
    var status: OrderStatus,
    val paymentMethod: PaymentMethod,
    val totalHarga: Double
)

class ShoppingCart {

    private val items = mutableListOf<CartItem>()

    fun tambahProduk(produk: Produk, jumlah: Int) {
        items.add(CartItem(produk, jumlah))
        println("${produk.nama} ditambahkan ke keranjang")
    }

    fun hapusProduk(produkId: Int) {
        items.removeIf { it.produk.id == produkId }
        println("Produk dihapus dari keranjang")
    }

    fun tampilkanKeranjang() {
        println("\n=== Keranjang ===")
        items.forEach {
            println("${it.produk.nama} x${it.jumlah}")
        }
    }

    fun hitungTotal(discountCalculator: (Double) -> Double): Double {
        val total = items.sumOf { it.produk.harga * it.jumlah }
        return discountCalculator(total)
    }

    fun getItems(): List<CartItem> = items
}

class TokoOnline {

    private val orders = mutableListOf<Order>()

    fun checkout(customer: Customer, cart: ShoppingCart, payment: PaymentMethod, total: Double) {

        val order = Order(
            orders.size + 1,
            customer,
            cart.getItems(),
            OrderStatus.Pending,
            payment,
            total
        )

        orders.add(order)

        println("Checkout berhasil. Status pesanan: Pending")
    }

    fun tampilkanRiwayat() {
        println("\n=== RIWAYAT PESANAN ===")
        orders.forEach {
            println("Order ${it.id} - ${it.customer.nama} - Total ${it.totalHarga} - Status ${it.status}")
        }
    }
}

fun main() {

    val produkList = listOf(
        Produk(1,"Tas",700000.0,"Aksesoris",9),
        Produk(2,"Tv",20000000.0,"Elektronik",5),
        Produk(3,"Kulkas",4000000.0,"Elektronik",13),
        Produk(4,"Jam",900000.0,"Aksesoris",7)
    )

    val cart = ShoppingCart()
    val toko = TokoOnline()

    val customer = Customer(1,"GaarPotret","f1d02310029@student.unram.ac.id",null)

    while (true) {

        println("\n===== MENU TOKO ONLINE =====")
        println("1. Lihat Produk")
        println("2. Tambah ke Keranjang")
        println("3. Hapus dari Keranjang")
        println("4. Lihat Keranjang")
        println("5. Hitung Total")
        println("6. Checkout")
        println("7. Riwayat Pesanan")
        println("0. Keluar")

        print("Pilih: ")
        val pilih = readLine()?.toIntOrNull()

        when(pilih){

            1 -> {
                println("\n=== DAFTAR PRODUK ===")

                produkList.sortedBy { it.harga }.forEach {
                    println("${it.id}. ${it.nama} - ${it.harga}")
                }

                println("\n=== GROUP KATEGORI ===")

                val group = produkList.groupBy { it.kategori }
                group.forEach { (kategori, list) ->
                    println("$kategori : ${list.size} produk")
                }
            }

            2 -> {
                print("ID Produk: ")
                val id = readLine()?.toIntOrNull()

                print("Jumlah: ")
                val jumlah = readLine()?.toIntOrNull()

                val produk = produkList.find { it.id == id }

                if(produk != null && jumlah != null){
                    cart.tambahProduk(produk,jumlah)
                }else{
                    println("Produk tidak ditemukan")
                }
            }

            3 -> {
                print("ID Produk: ")
                val id = readLine()?.toIntOrNull()

                if(id!=null){
                    cart.hapusProduk(id)
                }
            }

            4 -> cart.tampilkanKeranjang()

            5 -> {

                println("1. Tanpa Diskon")
                println("2. Diskon 10%")
                println("3. Diskon Rp50000")

                val diskon = readLine()?.toIntOrNull()

                val total = when(diskon){

                    2 -> cart.hitungTotal { it * 0.9 }

                    3 -> cart.hitungTotal { it - 50000 }

                    else -> cart.hitungTotal { it }
                }

                println("Total belanja: $total")
            }

            6 -> {

                val total = cart.hitungTotal { it }

                println("Pilih metode pembayaran")
                println("1. Cash")
                println("2. Transfer")
                println("3. EWallet")

                val metode = readLine()?.toIntOrNull()

                val payment = when(metode){

                    2 -> PaymentMethod.Transfer
                    3 -> PaymentMethod.EWallet
                    else -> PaymentMethod.Cash
                }

                toko.checkout(customer,cart,payment,total)
            }

            7 -> toko.tampilkanRiwayat()

            0 -> {
                println("Terima kasih")
                break
            }

            else -> println("Menu tidak tersedia")
        }
    }
}