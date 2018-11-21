package gastos

import grails.transaction.Transactional
import utilsElian.CheckUtils

import java.sql.Timestamp

@Transactional
class BilleteraService {

    Map crearBilletera(List<String> usuariosIds, String nombreBilletera) {
        List<Usuario> usuarios = []
        usuariosIds.each{ String usuarioGoogleId ->
            Usuario usuario = Usuario.findByGoogleId(usuarioGoogleId)
            CheckUtils.checkIfExist(usuario, "Usuario no existe")
            usuarios.add(usuario)
        }
        //ningun usuario puede tener 2 billeteras con el mismo nombre
        usuarios.each { Usuario usuario ->
            usuario.billeteras.each { Billetera billeteraUsuario ->
                if(billeteraUsuario.nombre == nombreBilletera) throw new Exception("El usuario ${usuario.googleId} ya tiene esta billetera")
            }
        }
        Billetera billetera = Billetera.fromMap([nombre: nombreBilletera, users: usuarios] as Map)
        billetera.save(failOnError: true, flush: true)
        return billetera.toMap()
    }

    Map obtenerBilletera(Long id, String token){
        Billetera billetera = Billetera.findById(id)
        CheckUtils.checkIfExist(billetera, "No se encontro la billetera")
        CheckUtils.checkAutorization(token, billetera.usuarios as List)
        return billetera.toMap()
    }

    Map modificarBilletera(Map body, Long id, String token){
        Billetera billetera = Billetera.findById(id)
        CheckUtils.checkIfExist(billetera, "No se encontro la billetera")
        CheckUtils.checkAutorization(token, billetera.usuarios as List)
        billetera.updateFromMap(body)
        billetera.save(failOnError: true, flush: true)
        return billetera.toMap()
    }

    Map borrarBilletera(Long id, String token){
        Billetera billetera = Billetera.findById(id)
        CheckUtils.checkIfExist(billetera, "No se encontro la billetera")
        CheckUtils.checkAutorization(token, billetera.usuarios as List)
        billetera.cleanUsers()
        billetera.delete(failOnError: true, flush: true)
        return billetera.toMap()
    }

    Map agregarUsuarioABilletera(String email, Long billeteraId, String token){
        Usuario usuario = Usuario.findByEmail(email)
        CheckUtils.checkIfExist(usuario, "Usuario con email $email no existe")
        Billetera billetera = Billetera.findById(billeteraId)
        CheckUtils.checkIfExist(billetera, "Billetera $billeteraId no existe")
        CheckUtils.checkAutorization(token, billetera.usuarios as List)
        billetera.addToUsuarios(usuario)
        billetera.save(failOnError: true, flush: true)
        return billetera.toMap()
    }

    List getMovimientos(Long idBilletera, String token){
        Billetera billetera = Billetera.findById(idBilletera)
        CheckUtils.checkIfExist(billetera, "Billetera $idBilletera no existe")
        CheckUtils.checkAutorization(token, billetera.usuarios as List)
        List movimientosOrdenados = billetera.movimientos.sort { Movimiento a, Movimiento b -> b.id <=> a.id}
        return  movimientosOrdenados
    }

    List searchMovimientos(Long idBilletera, Map requestParams, String token){
        Billetera billetera = Billetera.findById(idBilletera)
        CheckUtils.checkIfExist(billetera, "Billetera $idBilletera no existe")
        CheckUtils.checkAutorization(token, billetera.usuarios as List)
        CheckUtils.checkAndGetBody(requestParams, "desde", "Se necesita fecha de comienzo")
        CheckUtils.checkAndGetBody(requestParams, "hasta", "Se necesita fecha de finalización")
        Date desde = new java.util.Date().parse("yyyy-MM-dd'T'HH:mm:ss'Z'", requestParams.desde)
        Date hasta = new java.util.Date().parse("yyyy-MM-dd'T'HH:mm:ss'Z'", requestParams.hasta)
        //ZonedDateTime.parse("2015-05-03T10:15:30+01:00");
        hasta.setTime(hasta.getTime() + 1000)
        List<Movimiento> movimientos = Movimiento.findAllByDateCreatedBetweenAndBilletera(desde, hasta, billetera)
        return movimientos.sort { Movimiento a, Movimiento b -> b.id <=> a.id}
    }
}
