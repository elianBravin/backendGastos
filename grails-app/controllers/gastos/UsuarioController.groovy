package gastos



import grails.transaction.Transactional
import utilsElian.CheckUtils

@Transactional(readOnly = true)
class UsuarioController {

    UsuarioService usuarioService
    private static final String EMAIL_FIELD = "email"
    private static final String GOOGLE_ID_FIELD = "google_id"
    public static final String GOOGLE_PHOTO_URL_BODY = "url_imagen_perfil"

    def crearUsuario(){
        Map body = request.getJSON()
        CheckUtils.checkAndGetBody(body, EMAIL_FIELD, "Email no encontrado")
        CheckUtils.checkAndGetBody(body, GOOGLE_ID_FIELD, "Google id no encontrado")
        CheckUtils.checkAndGetBody(body, GOOGLE_PHOTO_URL_BODY, "Url foto no encontrada")
        render(contentType: "application/json") {
            usuarioService.crearUsuario(body)
        }
    }

    def obtenerUsuario(){
        String googleId = CheckUtils.checkAndGetParameter(params, "googleId", "Google id no encontrado")
        render(contentType: "application/json") {
            usuarioService.obtenerUsuario(googleId)
        }
    }

    def modificarUsuario(){
        Map body = request.getJSON()
        String googleId = CheckUtils.checkAndGetParameter(params, "googleId", "Google id no encontrado")
        render(contentType: "application/json") {
            usuarioService.modificarUsuario(body, googleId)
        }
    }

    def borrarUsuario(){
        String googleId = CheckUtils.checkAndGetParameter(params, "googleId", "Google id no encontrado")
        render(contentType: "application/json") {
            usuarioService.borrarUsuario(googleId)
        }
    }

    def prueba(){
        render(contentType: "application/json") {
            ["server_status":"OK"]
        }
    }
}
