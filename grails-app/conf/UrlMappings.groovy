class UrlMappings {

    static mappings = {

        "/usuario"(controller: "usuario", parseRequest: true) {
            action = [POST: 'crearUsuario']
        }

        "/usuario/$googleId"(controller: "usuario", parseRequest: true) {
            action = [GET: 'obtenerUsuario', PUT:'modificarUsuario', DELETE:'borrarUsuario']
        }

        "/billetera"(controller: "billetera", parseRequest: true) {
            action = [POST: 'crearBilletera']
        }

        "/billetera/$idBilletera"(controller: "billetera", parseRequest: true) {
            action = [GET: 'obtenerBilletera', PUT:'modificarBilletera', DELETE:'borrarBilletera']
        }

        //para agregar un usuario a la billetera
        "/billetera/$idBilletera/usuario"(controller: "billetera", parseRequest: true) {
            action = [PUT:'agregarUsuarioABilletera']
        }

        "/billetera/$idBilletera/movimiento"(controller: "movimiento", parseRequest: true) {
            action = [POST: 'postMovimiento']
        }

        "/movimiento/$idMovimiento"(controller: "movimiento", parseRequest: true) {
            action = [GET: 'getMovimiento', DELETE: 'deleteMovimiento', PUT: 'putMovimiento']
        }

        "/billetera/$idBilletera/movimientos"(controller: "billetera", parseRequest: true) {
            action = [GET: 'getMovimientos']
        }

        "/billetera/$idBilletera/movimientos/search"(controller: "billetera", parseRequest: true) {
            action = [GET: 'searchMovimientos']
        }

        //
        //"/libro/$idLibro/movimientos"(controller: "movimiento", parseRequest: true) {
        //    action = [GET: 'getMovimientos']
        //}
        //"/libro/$idLibro/balance"(controller: "libro", parseRequest: true){
        //    action = [GET: 'getBalance']
        //}
        "/prueba"(controller: "usuario", parseRequest: true){
            action = [GET: 'prueba']
        }
        //
        //"/libro/$idLibro/movimientos/search"(controller: "movimiento", parseRequest: true) {
        //    action = [GET: 'searchMovimientos']
        //}
    }
}
