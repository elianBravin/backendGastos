class UrlMappings {

    static mappings = {
        //por ahora no hay usuarios

        //"/gastos"(controller: "libro", action: "getLibros")

        "/libro"(controller: "libro", parseRequest: true) {
            action = [POST: 'postLibro']
        }

        "/libro/$idLibro"(controller: "libro", parseRequest: true) {
            action = [GET: 'getLibro']
        }

        "/libros"(controller: "libro", parseRequest: true) {
            action = [GET: 'getLibros']
        }

        "/$idLibro/movimiento"(controller: "movimiento", parseRequest: true) {
            action = [POST: 'postMovimiento']
        }

        "/movimiento/$idMovimiento"(controller: "movimiento", parseRequest: true) {
            action = [GET: 'getMovimiento', DELETE: "deleteMovimiento"]
        }

        "/libro/$idLibro/movimientos"(controller: "movimiento", parseRequest: true) {
            action = [GET: 'getMovimientos']
        }
        "/libro/$idLibro/balance"(controller: "libro", parseRequest: true){
            action = [GET: 'getBalance']
        }
        "/ping"(controller: "libro", parseRequest: true){
            action = [GET: 'ping']
        }

        "/libro/$idLibro/movimientos/search"(controller: "movimiento", parseRequest: true) {
            action = [GET: 'searchMovimientos']
        }
    }
}
