package utilsElian

import gastos.Usuario

class CheckUtils {

    static def checkAndGetParameter(def params, String campo, String mensajeError) {

        if (params[campo]){
            return params[campo]
        } else {
            throw new Exception(mensajeError)
        }
    }

    static def checkAndGetBody(Map body, String campo, String mensajeError) {

        if (body[campo]){
            return body[campo]
        } else {
            throw new Exception(mensajeError)
        }
    }

    static def checkAndGetHeader(def request, String headerName){

        if (request.getHeader(headerName)){
            return request.getHeader(headerName)
        } else {
            throw new Exception("$headerName es requerido")
        }
    }

    static def checkIfExist(def object, String mensajeError){
        if(!object){
            throw new Exception(mensajeError)
        }
    }

    static def checkIfNotExist(def object, String mensajeError){
        if(object){
            throw new Exception(mensajeError)
        }
    }

    static def checkAutorization(String token, List<Usuario> usuariosBilletera){
        if(!(token in usuariosBilletera.collect {it.googleId})){
            throw new Exception("Usuario $token no autorizado")
        }
    }

    static def checkCondition(boolean condition, String mensajeError){
        if(!condition){
            throw new Exception(mensajeError)
        }
    }
}
