package gastos

import utilsElian.CheckUtils

class Usuario {

    static constraints = {
        email(nullable: false)
        googleId(nullable: false)
    }

    static hasMany = [
            billeteras : Billetera,
    ]

    String email
    String googleId
    String urlImagenPerfil
    Date dateCreated
    Date lastUpdated

    Usuario(){
        this.billeteras = []
    }


    def toMap(){
        def result = [:]
        result.date_created = this.dateCreated
        result.last_updated = this.lastUpdated
        result.email = this.email
        result.google_id = this.googleId
        result.url_imagen_perfil = this.urlImagenPerfil
        result.billeteras = this.billeteras*.toMapForUsuario()
        return result
    }

    def toMapForBilletera(){
        def result = [:]
        result.email = this.email
        result.google_id = this.googleId
        result.url_imagen_perfil = this.urlImagenPerfil
        return result
    }

    def updateFromMap(Map nuevosDatos){

        if(nuevosDatos.email) {
            List<Usuario> usuariosConEseEmail = Usuario.findAllByEmail(nuevosDatos.email)
            CheckUtils.checkCondition(usuariosConEseEmail == [], "Ya existe un usuario con este email")
            this.email = nuevosDatos.email
        }
        if(nuevosDatos.url_imagen_perfil) this.urlImagenPerfil = nuevosDatos.url_imagen_perfil
    }

    static Usuario createFromMap(Map body){
        Usuario usuario = new Usuario()
        if(body.email) usuario.email = body.email
        if(body.google_id) usuario.googleId = body.google_id
        if(body.url_imagen_perfil) usuario.urlImagenPerfil = body.url_imagen_perfil
        return usuario
    }
}
