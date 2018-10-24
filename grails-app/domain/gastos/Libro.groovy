package gastos

class Libro {

    static constraints = {
    }

    static hasMany = [
            movimientos : Movimiento,
    ]

    String descripcion
    Date dateCreated
    Date lastUpdated


    def getMap(){
        def result = [:]
        result.id = this.id
        result.date_created = this.dateCreated
        result.descripcion = this.descripcion
        return result
    }
}
