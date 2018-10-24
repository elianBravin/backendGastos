package gastos

import grails.transaction.Transactional

@Transactional
class LibroService {

    def postLibro(String descripcion){
        Libro libro = new Libro()
        libro.descripcion = descripcion
        libro.save()
    }

    def getLibros(){
        List<Libro> libros = Libro.findAll()
        return libros
    }

    def getLibro(Long libroId){
        return Libro.findById(libroId)
    }

    def getBalance(Long libroId){
        def movimientos = Libro.findById(libroId)?.movimientos
        Map result = [balanceTotal:0, efectivo:0, banco:0]
        movimientos?.each{ movimiento ->
            if(movimiento.esGasto){
                result.balanceTotal -= movimiento.cantidad
                if(movimiento.metodoPago == MetodosPagoEnum.EFECTIVO){
                    result.efectivo -= movimiento.cantidad
                } else {
                    result.banco -= movimiento.cantidad
                }
            } else {
                result.balanceTotal += movimiento.cantidad
                if(movimiento.metodoPago == MetodosPagoEnum.EFECTIVO){
                    result.efectivo += movimiento.cantidad
                } else {
                    result.banco += movimiento.cantidad
                }
            }
        }

        return result
    }
}
