package gastos

import utilsElian.CheckUtils

class Movimiento {

    static constraints = {
        descripcion(nullable: false)
        cantidad(nullable: false)
    }

    static belongsTo = [
            billetera : Billetera
    ]

    String descripcion
    Double cantidad
    Date dateCreated
    Date lastUpdated
    //posicion geográfica del gasto

    def toMap(){
        def result = [:]
        result.id = this.id
        result.date_created = this.dateCreated
        result.last_update = this.lastUpdated
        result.billetera = this.billetera.toString()
        result.descripcion = this.descripcion
        result.cantidad = this.cantidad
        //posicion geográfica del gasto
        return result
    }

    String toString(){
        return this.descripcion
    }

    static Movimiento fromMap(Map map){
        Movimiento movimiento = new Movimiento()
        if(map.descripcion) movimiento.descripcion = map.descripcion
        if(map.cantidad) movimiento.cantidad = map.cantidad
        if(map.billetera) {
            Billetera billetera = Billetera.findById(map.billetera)
            CheckUtils.checkIfExist(billetera, "Billetra ${map.billetera} no existe")
            billetera.addToMovimientos(movimiento)
        }
        return movimiento
    }

    void updateFromMap(Map map){
        if(map.descripcion) this.descripcion = map.descripcion
        if(map.cantidad) this.cantidad = map.cantidad
    }
}
