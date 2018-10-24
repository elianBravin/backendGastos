package gastos

import grails.transaction.Transactional

@Transactional
class MovimientoService {

    def postMovimiento(Libro libro, String descripcion, Float cantidad, Boolean esGasto, String metodoPago) {
        Movimiento movimiento = new Movimiento()
        movimiento.descripcion = descripcion
        movimiento.cantidad = cantidad
        movimiento.libro = libro
        movimiento.esGasto = esGasto
        movimiento.metodoPago = MetodosPagoEnum.getEnum(metodoPago)
        movimiento.save()
    }

    Movimiento getMovimiento(Long movimientoId){
        return Movimiento.findById(movimientoId)
    }

    Movimiento deleteMovimiento(Long movimientoId){
        Movimiento movimientoABorrar = Movimiento.findById(movimientoId)
        movimientoABorrar.delete()
        return movimientoABorrar
    }

    List<Movimiento> getMovimientos(Libro libro){
        List<Movimiento> movimientos = Movimiento.findAllByLibro(libro)
        List<Movimiento> movimientosOrdenados = movimientos.sort({a,b ->
            b.id <=> a.id
        })
        return movimientosOrdenados
    }


    //despues hacer un refactor. ahora no chequeo nada
    List<Movimiento> searchMovimientos(Libro libro, Map criterios){
        Long año = this.obtenerAño(criterios.fecha)
        Long mes = this.obtenerMes(criterios.fecha)
        List<Movimiento> movimientos = Movimiento.withCriteria {
            def initDate = new Date(year: año, month: mes, date: 1, hours: 0, minutes: 0, seconds: 0)
            def finishDate = new Date(year: año, month: mes + 1, date: 1, hours: 23, minutes: 59, seconds: 59) - 1
            between('dateCreated', initDate, finishDate)
        }
        List<Movimiento> movimientosOrdenados = movimientos.sort(
        {a,b ->
            b.id <=> a.id
        })
        return movimientosOrdenados
    }


    private Long obtenerMes(String fecha){
        return (fecha.split('/')[0] as Long) - 1
    }

    private Long obtenerAño(String fecha){
        return ((fecha.split('/')[1] as Long) - 1900)
    }

}
