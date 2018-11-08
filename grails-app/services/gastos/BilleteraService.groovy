package gastos

import grails.transaction.Transactional

@Transactional
class BilleteraService {

    Map crearBilletera(String nombre, Usuario usuario){
        Billetera billetera = new Billetera()
        billetera.nombre = nombre
        billetera.saldo = 0
        billetera.usuario = usuario
        billetera.save(failOnError: true, flush: true)
        return billetera.toMap()
    }

    Map obtenerBilletera(Long id){
        Billetera billetera = Billetera.findById(id)
        return (billetera ? billetera.toMap() : ["status": "Billetera no encontrada"])
    }

    Map modificarBilletera(Map body, Long id){
        Billetera billetera = Billetera.findById(id)
        if(!billetera){
            return ["status": "Billetera no encontrada"]
        }
        if(body.nombre) {
            billetera.nombre = body.nombre
            billetera.save(failOnError: true, flush: true)
        }
        return billetera.toMap()
    }

    Map borrarBilletera(Long id){
        Billetera billetera = Billetera.findById(id)
        if(!billetera) {
            return ["status": "Billetera no encontrada"]
        }
        billetera.delete(failOnError: true, flush: true)
        billetera.toMap()
    }
}
