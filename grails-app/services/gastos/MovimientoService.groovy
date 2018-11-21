package gastos

import grails.transaction.Transactional
import utilsElian.CheckUtils

@Transactional
class MovimientoService {

    Map postMovimiento(Long billeteraId, Map body, String token) {
        Billetera billetera = Billetera.findById(billeteraId)
        CheckUtils.checkIfExist(billetera, "Billetera inexistente")
        CheckUtils.checkAutorization(token,billetera.usuarios as List)
        Movimiento movimiento = Movimiento.fromMap(body << [billetera:billetera.id])
        movimiento.save(failOnError: true, flush: true)
        billetera.saldo += movimiento.cantidad
        return movimiento.toMap()
    }

    Map getMovimiento(Long idMovimiento, String token){
        Movimiento movimiento = Movimiento.findById(idMovimiento)
        CheckUtils.checkIfExist(movimiento, "Movimiento $idMovimiento no existe")
        CheckUtils.checkAutorization(token, movimiento.billetera.usuarios as List)
        return movimiento.toMap()
    }

    Map deleteMovimiento(Long idMovimiento, String token){
        Movimiento movimiento = Movimiento.findById(idMovimiento)
        CheckUtils.checkIfExist(movimiento, "Movimiento $idMovimiento no existe")
        CheckUtils.checkAutorization(token, movimiento.billetera.usuarios as List)
        Map movimientoMap = movimiento.toMap()
        movimiento.delete(failOnError: true, flush: true)
        return movimientoMap
    }

    Map updateMovimiento(Map body, Long idMovimiento, String token) {
        Movimiento movimiento = Movimiento.findById(idMovimiento)
        CheckUtils.checkIfExist(movimiento, "Movimiento $idMovimiento no existe")
        CheckUtils.checkAutorization(token, movimiento.billetera.usuarios as List)
        movimiento.updateFromMap(body)
        movimiento.save(failOnError: true, flush: true)
        return movimiento.toMap()
    }


    //Movimiento getMovimiento(Long movimientoId){
    //    return Movimiento.findById(movimientoId)
    //}
    //
    //Movimiento deleteMovimiento(Long movimientoId){
    //    Movimiento movimientoABorrar = Movimiento.findById(movimientoId)
    //    movimientoABorrar.delete()
    //    return movimientoABorrar
    //}
    //
    //List<Movimiento> getMovimientos(Libro libro){
    //    List<Movimiento> movimientos = Movimiento.findAllByLibro(libro)
    //    List<Movimiento> movimientosOrdenados = movimientos.sort({a,b ->
    //        b.id <=> a.id
    //    })
    //    return movimientosOrdenados
    //}
    //
    //
    ////despues hacer un refactor. ahora no chequeo nada
    //List<Movimiento> searchMovimientos(Libro libro, Map criterios){
    //    Long año = this.obtenerAño(criterios.fecha)
    //    Long mes = this.obtenerMes(criterios.fecha)
    //    List<Movimiento> movimientos = Movimiento.withCriteria {
    //        def initDate = new Date(year: año, month: mes, date: 1, hours: 0, minutes: 0, seconds: 0)
    //        def finishDate = new Date(year: año, month: mes + 1, date: 1, hours: 23, minutes: 59, seconds: 59) - 1
    //        between('dateCreated', initDate, finishDate)
    //    }
    //    List<Movimiento> movimientosOrdenados = movimientos.sort(
    //    {a,b ->
    //        b.id <=> a.id
    //    })
    //    return movimientosOrdenados
    //}
    //
    //
    //private Long obtenerMes(String fecha){
    //    return (fecha.split('/')[0] as Long) - 1
    //}
    //
    //private Long obtenerAño(String fecha){
    //    return ((fecha.split('/')[1] as Long) - 1900)
    //}

}
