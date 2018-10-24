package gastos

class MovimientoController {

    MovimientoService movimientoService

    def postMovimiento(){
        Libro libro = Libro.findById(params.long("idLibro"))
        if(!libro){
            throw new Exception("No se encontro el libro especificado")
        }
        Map body = request.getJSON()
        if(!(body?.descripcion) || !(body?.cantidad) || (body?.es_gasto  == null) || !(body?.metodo_pago)){
            throw new Exception("body invalido")
        }

        movimientoService.postMovimiento(libro, body.descripcion, body.cantidad as Float, body.es_gasto, body.metodo_pago.toUpperCase())
        body.status = "OK"
        render(contentType: "application/json") {
            body
        }
    }

    def getMovimientos(){
        Long idLibro = params.long("idLibro")
        Libro libro = Libro.findById(idLibro)
        List<Movimiento> response = movimientoService.getMovimientos(libro)
        render(contentType: "application/json") {
            response*.getMap()
        }
    }

    def getMovimiento(){
        Long movimientoId = params.long("idMovimiento")
        Movimiento response = movimientoService.getMovimiento(movimientoId)
        render(contentType: "application/json") {
            response.getMap()
        }
    }

    def deleteMovimiento(){
        Long movimientoId = params.long("idMovimiento")
        Movimiento response = movimientoService.deleteMovimiento(movimientoId)
        render(contentType: "application/json") {
            response.getMap()
        }
    }

    def searchMovimientos(){
        Long idLibro = params.long("idLibro")
        params.get
        String fecha = params["fecha"] ?: null
        Libro libro = Libro.findById(idLibro)
        List<Movimiento> response = movimientoService.searchMovimientos(libro, ["fecha":fecha])
        render(contentType: "application/json") {
            response*.getMap()
        }
    }
}
