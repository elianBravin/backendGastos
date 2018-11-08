package gastos

class Categoria {

    static constraints = {
        nombre(nullable: false)
    }

    static belongsTo = [
            usuario : Usuario
    ]

    String nombre

    String toString(){
        return this.nombre
    }
}
