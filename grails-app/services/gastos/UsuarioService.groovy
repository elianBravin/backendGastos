package gastos

import utilsElian.CheckUtils

class UsuarioService {

    Map crearUsuario(Map body) {
        Usuario usuario = Usuario.findByGoogleId(body.google_id)
        CheckUtils.checkIfNotExist(usuario, "Usuario ya existe")
        usuario = Usuario.findByEmail(body.email)
        CheckUtils.checkIfNotExist(usuario, "Ya existe un usuario con este email")
        usuario = Usuario.createFromMap(body)
        usuario.save(failOnError: true, flush: true)
        return usuario.toMap()
    }

    Map obtenerUsuario(String googleId){
        Usuario usuario = Usuario.findByGoogleId(googleId)
        CheckUtils.checkIfExist(usuario, "Usuario no existe")
        return usuario.toMap()
    }

    Map modificarUsuario(Map nuevosDatos, String googleId){
        Usuario usuario = Usuario.findByGoogleId(googleId)
        CheckUtils.checkIfExist(usuario, "Usuario no existe")
        usuario.updateFromMap(nuevosDatos)
        usuario.save(failOnError: true, flush: true)
        return usuario.toMap()
    }

    Map borrarUsuario(String googleId){
        Usuario usuario = Usuario.findByGoogleId(googleId)
        CheckUtils.checkIfExist(usuario, "Usuario no existe")
        usuario.delete(failOnError: true, flush: true)
        return ["status": "OK"]
    }
}
