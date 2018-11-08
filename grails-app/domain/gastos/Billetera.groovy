package gastos

class Billetera {

    static constraints = {
    }

    static hasMany = [
            movimientos : Movimiento
    ]

    static belongsTo = [
            usuario : Usuario
    ]

    String nombre
    Double saldo
    Date dateCreated
    Date lastUpdated


    def toMap(){
        def result = [:]
        result.id = this.id
        result.date_created = this.dateCreated
        result.last_update = this.lastUpdated
        result.nombre = this.nombre
        result.saldo = this.saldo
        return result
    }

    String toString(){
        return this.nombre
    }
}
