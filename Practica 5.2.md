# Gestión de Pedidos: Diagrama de Clases y Código

## Descripción del Proyecto

El objetivo de este proyecto es diseñar un sistema que gestione los pedidos de una empresa. Para ello, se utilizará un diagrama de clases UML que represente los componentes y relaciones del sistema, y posteriormente se generará un esquema de código basado en este diagrama.

## Requerimientos del Sistema

1. **Pedidos**: Los clientes realizan pedidos que pueden contener uno o varios productos.
2. **Información de Pedidos**: Cada pedido debe registrar la fecha en que se realizó.
3. **Productos**: Los productos deben estar registrados en el sistema con su nombre, descripción, precio e impuestos.
4. **Cálculo de Coste**: El sistema debe calcular el coste total de cada pedido, considerando la cantidad de cada producto, sus precios individuales y los impuestos.
5. **Stock**: Debe haber un registro actualizado del stock de cada producto para informar al cliente sobre posibles retrasos en la entrega.
6. **Pagos**: Cada pedido puede ser pagado de una sola vez o en varios pagos. El sistema debe registrar la información de cada pago.
7. **Formas de Pago**: Las formas de pago aceptadas son: tarjeta (número, fecha de caducidad y tipo de tarjeta), efectivo (tipo de moneda) y cheque (nombre y banco).
8. **Estados del Pedido**: Cada pedido puede estar en uno de los siguientes estados: pendiente (pdte), pagado (pgdo), parcialmente pagado (pcdo), enviado (envdo), entregado (entgdo). El estado debe actualizarse según la evolución del pedido.

## a) Conceptos UML y su Mapeo a Programación Orientada a Objetos

- **Clases**: Representan entidades del sistema, como `Pedido`, `Producto`, `Cliente`, `Pago`, etc.
- **Atributos**: Propiedades de las clases, como `fechaPedido`, `nombreProducto`, `precio`, etc.
- **Métodos**: Funciones que realizan acciones, como `calcularCosteTotal()`, `actualizarStock()`, `registrarPago()`, etc.
- **Relaciones**: Asociaciones entre clases, como la relación de composición entre `Pedido` y `Producto`, y la relación de herencia entre diferentes tipos de `Pago`.
- **Herencia**: Por ejemplo, `PagoTarjeta`, `PagoEfectivo` y `PagoCheque` pueden heredar de una clase base `Pago`.

## b) Herramienta Utilizada para Generar el Diagrama UML

Para generar el diagrama de clases UML, he utilizado la herramienta **GitMind**. Elegí esta herramienta porque ofrece una interfaz de usuario amigable y plantillas personalizables que facilitan la creación rápida de diagramas UML. Además, es gratuita y permite exportar el diagrama en varios formatos.

He comparado GitMind con **Visual Paradigm**, que también es una herramienta potente, pero preferí GitMind por su simplicidad y facilidad de uso. Visual Paradigm, aunque más completo, tiene una curva de aprendizaje más pronunciada y algunas funciones avanzadas que no eran necesarias para este proyecto.

### Ventajas de GitMind:
- **Interfaz Amigable**: Facilita la creación rápida de diagramas.
- **Plantillas Personalizables**: Permite ahorrar tiempo al utilizar plantillas predefinidas.
- **Gratuita**: No tiene costo de uso.

### Conclusión:
GitMind es una herramienta adecuada para proyectos que requieren una herramienta UML sencilla y eficiente.

## c) Conversión del Diagrama UML a Código

La conversión del diagrama UML a código implica mapear las clases, atributos y métodos del diagrama a estructuras de programación. A continuación, se presenta un esquema del código que se podría generar:

```kotlin
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