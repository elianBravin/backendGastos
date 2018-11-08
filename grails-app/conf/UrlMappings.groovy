class UrlMappings {

    static mappings = {
        //por ahora no hay usuarios

        //"/gastos"(controller: "libro", action: "getLibros")

        "/usuario"(controller: "usuario", parseRequest: true) {
            action = [POST: 'crearUsuario']
        }

        "/usuario/$idUsuario"(controller: "usuario", parseRequest: true) {
            action = [GET: 'obtenerUsuario', PUT:'modificarUsuario', DELETE:'borrarUsuario']
        }

        "/usuario/$idUsuario/billetera"(controller: "billetera", parseRequest: true) {
            action = [POST: 'crearBilletera']
        }

        "/billetera/$idBilletera"(controller: "billetera", parseRequest: true) {
            action = [GET: 'obtenerBilletera', PUT:'modificarBilletera', DELETE:'borrarBilletera']
        }

        //"/libros"(controller: "libro", parseRequest: true) {
        //    action = [GET: 'getLibros']
        //}
        //
        //"/$idLibro/movimiento"(controller: "movimiento", parseRequest: true) {
        //    action = [POST: 'postMovimiento']
        //}
        //
        //"/movimiento/$idMovimiento"(controller: "movimiento", parseRequest: true) {
        //    action = [GET: 'getMovimiento', DELETE: "deleteMovimiento"]
        //}
        //
        //"/libro/$idLibro/movimientos"(controller: "movimiento", parseRequest: true) {
        //    action = [GET: 'getMovimientos']
        //}
        //"/libro/$idLibro/balance"(controller: "libro", parseRequest: true){
        //    action = [GET: 'getBalance']
        //}
        //"/ping"(controller: "libro", parseRequest: true){
        //    action = [GET: 'ping']
        //}
        //
        //"/libro/$idLibro/movimientos/search"(controller: "movimiento", parseRequest: true) {
        //    action = [GET: 'searchMovimientos']
        //}
    }
}
