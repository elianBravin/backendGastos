package gastos

class Movimiento {

    static constraints = {
        descripcion(nullable: false)
        cantidad(nullable: false)
    }

    static belongsTo = [
            libro : Libro
    ]

    String descripcion
    Float cantidad
    MetodosPagoEnum metodoPago //efectivo, tarjeta, transferencia
    Boolean esGasto
    Date dateCreated
    Date lastUpdated

    def getMap(){
        def result = [:]
        result.id = this.id
        result.libro = this.libro.id
        result.date_created = this.dateCreated
        result.descripcion = this.descripcion
        result.cantidad = this.cantidad
        result.es_gasto = this.esGasto
        result.metodo_pago = this.metodoPago.toString()
        return result
    }
}
