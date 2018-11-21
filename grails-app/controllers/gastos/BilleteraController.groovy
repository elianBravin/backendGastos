package gastos



import grails.transaction.Transactional
import org.hibernate.annotations.Check
import utilsElian.CheckUtils

@Transactional(readOnly = true)
class BilleteraController {

    public static final String NAME = "name"
    public static final String  USERS = "users"
    BilleteraService billeteraService

    def crearBilletera(){
        Map body = request.getJSON()
        String nombreBilletera = CheckUtils.checkAndGetBody(body, NAME, "Nombre de billetera invalido")
        List<String> usuarios = CheckUtils.checkAndGetBody(body, USERS, "Se necesita saber los usuarios dueños de la billetera")
        render(contentType: "application/json") {
            billeteraService.crearBilletera(usuarios as List, nombreBilletera)
        }
    }

    def obtenerBilletera(){
        Long billeteraId = Long.parseLong(CheckUtils.checkAndGetParameter(params, "idBilletera", "Billetera Id no encontrado"))
        String token = CheckUtils.checkAndGetHeader(request, "Token")
        render(contentType: "application/json") {
            billeteraService.obtenerBilletera(billeteraId, token)
        }
    }

    def modificarBilletera(){
        Map body = request.getJSON()
        Long billeteraId = Long.parseLong(CheckUtils.checkAndGetParameter(params, "idBilletera", "Billetera Id no encontrado"))
        String token = CheckUtils.checkAndGetHeader(request, "Token")
        render(contentType: "application/json") {
            billeteraService.modificarBilletera(body, billeteraId, token)
        }
    }

    def borrarBilletera(){
        Long billeteraId = Long.parseLong(CheckUtils.checkAndGetParameter(params, "idBilletera", "Billetera Id no encontrado"))
        String token = CheckUtils.checkAndGetHeader(request, "Token")
        render(contentType: "application/json") {
            billeteraService.borrarBilletera(billeteraId, token)
        }
    }

    def agregarUsuarioABilletera(){
        Map body = request.getJSON()
        Long billeteraId = Long.parseLong(CheckUtils.checkAndGetParameter(params, "idBilletera", "Billetera Id no encontrado"))
        String email = CheckUtils.checkAndGetBody(body, "email", "Email no encontrado")
        String token = CheckUtils.checkAndGetHeader(request, "Token")
        render(contentType: "application/json") {
            billeteraService.agregarUsuarioABilletera(email, billeteraId, token)
        }
    }

    def getMovimientos(){
        Long idBilletera = Long.parseLong(CheckUtils.checkAndGetParameter(params, "idBilletera", "Se nececita en id de la billetera"))
        String token = CheckUtils.checkAndGetHeader(request, "Token")
        render(contentType: "application/json") {
            billeteraService.getMovimientos(idBilletera, token)
        }
    }

    def searchMovimientos(){
        Long idBilletera = Long.parseLong(CheckUtils.checkAndGetParameter(params, "idBilletera", "Se nececita en id de la billetera"))
        String token = CheckUtils.checkAndGetHeader(request, "Token")
        Map requestParams = requestParamsToMap(params)
        render(contentType: "application/json") {
            billeteraService.searchMovimientos(idBilletera, requestParams, token)
        }
    }

    def requestParamsToMap(def params){
        Map response = [:]
        response.desde = params.desde
        response.hasta = params.hasta
        return response
    }
}
