package gastos

import utilsElian.CheckUtils

class Billetera {

    static constraints = {
    }

    static hasMany = [
            movimientos : Movimiento,
            usuarios: Usuario
    ]

    static belongsTo = Usuario

    String nombre
    Double saldo
    Date dateCreated
    Date lastUpdated


    Billetera(){
        this.usuarios = []
        this.movimientos = []
        this.saldo = 0
    }

    def toMap(){
        def result = [:]
        result.id = this.id
        result.date_created = this.dateCreated
        result.last_update = this.lastUpdated
        result.nombre = this.nombre
        result.saldo = this.saldo
        result.usuarios = this.usuarios*.toMapForBilletera()
        return result
    }

    def toMapForUsuario(){
        def result = [:]
        result.id = this.id
        result.nombre = this.nombre
        result.saldo = this.saldo
        return result
    }

    String toString(){
        return this.id.toString()
    }

    static Billetera fromMap(Map map){
        Billetera billetera = new Billetera()
        if(map.nombre) billetera.nombre = map.nombre;
        if(map.users){
            map.users.each{ Usuario usuario ->
                billetera.addToUsuarios(usuario)
            }
        }
        return billetera
    }

    void updateFromMap(Map body){
        if(body.nombre) this.nombre = body.nombre;
        if(body.saldo) this.saldo = body.saldo;
        if(body.users){
            this.cleanUsers()
            body.users.each{ String usuarioGoogleId ->
                Usuario usuario = Usuario.findByGoogleId(usuarioGoogleId)
                CheckUtils.checkIfExist(usuario, "El usuario $usuarioGoogleId no existe")
                this.addToUsuarios(usuario)
            }
        }
    }

    void cleanUsers(){
        List usuariosCopia = this.usuarios as List
        usuariosCopia.each { Usuario usuarioParaBorrar ->
            this.removeFromUsuarios(usuarioParaBorrar)
        }
    }
}
