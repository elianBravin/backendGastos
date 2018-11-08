package gastos

import sun.security.provider.MD5

class Usuario {

    static constraints = {
        nombre(nullable: false)
    }
    static mapping = {
        billeteras cascade: 'all-delete-orphan'
        categorias cascade: 'all-delete-orphan'
    }

    static hasMany = [
            billeteras : Billetera,
            categorias : Categoria
    ]

    String nombre
    MD5 password
    Date dateCreated
    Date lastUpdated


    def toMap(){
        def result = [:]
        result.nombre = this.nombre
        result.date_created = this.dateCreated
        result.last_updated = this.lastUpdated
        return result
    }
}
