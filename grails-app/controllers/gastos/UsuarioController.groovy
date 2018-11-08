package gastos



import grails.transaction.Transactional

@Transactional(readOnly = true)
class UsuarioController {

    UsuarioService usuarioService

    def crearUsuario(){
        Map body = request.getJSON()
        if(!(body?.name)){
            throw new Exception("nombre no encontrado")
        }
        if(!(body?.password)){
            throw new Exception("password no encontrado")
        }
        render(contentType: "application/json") {
            usuarioService.crearUsuario(body.nombre, body.password)
        }
    }

    def obtenerUsuario(){
        String usuarioUuid = params.getProperty("idUsuario")
        render(contentType: "application/json") {
            usuarioService.obtenerUsuario(usuarioUuid)
        }
    }

    def modificarUsuario(){
        Map body = request.getJSON()
        String usuarioUuid = params.getProperty("idUsuario")
        render(contentType: "application/json") {
            usuarioService.modificarUsuario(body, usuarioUuid)
        }
    }

    def borrarUsuario(){
        String usuarioUuid = params.getProperty("idUsuario")
        render(contentType: "application/json") {
            usuarioService.borrarUsuario(usuarioUuid)
        }
    }
}
