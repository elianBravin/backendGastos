package gastos



import grails.transaction.Transactional

@Transactional(readOnly = true)
class BilleteraController {

    BilleteraService billeteraService

    def crearBilletera(){
        Map body = request.getJSON()
        String usuarioNombre = params.getProperty("idUsuario")
        if(!usuarioNombre){
            throw new Exception("Se necesita usuario name")
        }
        Usuario usuario = Usuario.findByNombre(usuarioNombre)
        if(!usuario){
            throw new Exception("El usuario no existe")
        }
        //nombre de la nueva billetera
        if(!(body?.nombre)){
            throw new Exception("nombre no encontrado")
        }
        render(contentType: "application/json") {
            billeteraService.crearBilletera(body.nombre, usuario)
        }
    }

    def obtenerBilletera(){
        String billeteraId = Long.parseLong(params.getProperty("idBilletera"))
        render(contentType: "application/json") {
            billeteraService.obtenerBilletera(billeteraId)
        }
    }

    def modificarBilletera(){
        Map body = request.getJSON()
        String billeteraId = Long.parseLong(params.getProperty("idBilletera"))
        render(contentType: "application/json") {
            billeteraService.modificarBilletera(body, billeteraId)
        }
    }

    def borrarBilletera(){
        String billeteraId = Long.parseLong(params.getProperty("idBilletera"))
        render(contentType: "application/json") {
            billeteraService.borrarBilletera(billeteraId)
        }
    }

}
