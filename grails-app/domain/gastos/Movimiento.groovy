package gastos

class Movimiento {

    static constraints = {
        descripcion(nullable: false)
        cantidad(nullable: false)
    }

    static belongsTo = [
            billetera : Billetera,
            categoria: Categoria
    ]

    String descripcion
    Float cantidad
    Boolean esGasto
    Date dateCreated
    Date lastUpdated

    def getMap(){
        def result = [:]
        result.id = this.id
        result.libro = this.billetera.nombre
        result.date_created = this.dateCreated
        result.last_update = this.lastUpdated
        result.descripcion = this.descripcion
        result.cantidad = this.cantidad
        result.billetera = this.billetera.toString()
        result.categoria = this.categoria
        return result
    }

    String toString(){
        return this.descripcion
    }
}
