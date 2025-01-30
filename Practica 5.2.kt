// Clase Cliente
class Cliente(val nombre: String, val direccion: String, val email: String)

// Clase Producto
class Producto(val nombre: String, val descripcion: String, var precio: Double, var impuestos: Double, var stock: Int) {
    fun actualizarStock(cantidad: Int) {
        stock += cantidad
    }
}

// Clase Pago
abstract class Pago(val tipo: String) {
    abstract fun procesarPago()
}

class PagoTarjeta(val numero: String, val fechaCaducidad: String, val tipoTarjeta: String) : Pago("Tarjeta") {
    override fun procesarPago() {
        // Lógica para procesar pago con tarjeta
    }
}

class PagoEfectivo(val moneda: String) : Pago("Efectivo") {
    override fun procesarPago() {
        // Lógica para procesar pago en efectivo
    }
}

class PagoCheque(val nombre: String, val banco: String) : Pago("Cheque") {
    override fun procesarPago() {
        // Lógica para procesar pago con cheque
    }
}

// Clase Pedido
class Pedido(val id: Int, val fecha: String, val cliente: Cliente) {
    val productos: MutableList<Producto> = mutableListOf()
    var estado: String = "pdte"
    var pagos: MutableList<Pago> = mutableListOf()

    fun agregarProducto(producto: Producto, cantidad: Int) {
        productos.add(producto)
        producto.actualizarStock(-cantidad)
    }

    fun calcularCosteTotal(): Double {
        var total = 0.0
        for (producto in productos) {
            total += producto.precio * cantidad + producto.impuestos
        }
        return total
    }

    fun registrarPago(pago: Pago) {
        pagos.add(pago)
        if (pagos.size == 1) {
            estado = "pgdo"
        } else {
            estado = "pcdo"
        }
    }

    fun actualizarEstado(nuevoEstado: String) {
        estado = nuevoEstado
    }
}