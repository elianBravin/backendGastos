package gastos

import grails.transaction.Transactional

@Transactional
class UsuarioService {

    Map crearUsuario(String nombre, String password) {
        Usuario usuario = Usuario.findByNombre(nombre)
        if(usuario){
            return (["status":"Usuario ya existe"])
        }
        usuario = new Usuario()
        usuario.nombre = nombre
        usuario.password = password.encodeAsMD5()
        usuario.save(failOnError: true, flush: true)
        return usuario.toMap()
    }

    Map obtenerUsuario(String nombre){
        Usuario usuario = Usuario.findByNombre(nombre)
        return (usuario ? usuario.toMap() : ["status":"Usuario no existe"])
    }

    Map modificarUsuario(Map nuevosDatos, String nombre){
        Usuario usuario = Usuario.findByNombre(nombre)
        if(!usuario){
            return ["status":"Usuario no existe"]
        }
        usuario.nombre = nuevosDatos.nombre ?: usuario.nombre
        usuario.password = nuevosDatos.password ? nuevosDatos.password.encodeAsMD5() : usuario.password
        usuario.save(failOnError: true, flush: true)
        return usuario.toMap()
    }

    Map borrarUsuario(String nombre){
        Usuario usuario = Usuario.findByNombre(nombre)
        if(!usuario){
            return ["status":"Usuario no existe"]
        }
        usuario.delete(failOnError: true, flush: true)
        usuario.toMap()
    }
}
