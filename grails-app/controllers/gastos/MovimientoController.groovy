package gastos

import utilsElian.CheckUtils

class MovimientoController {

    MovimientoService movimientoService

    def postMovimiento() {

        Map body = request.getJSON()
        Long billeteraId = Long.parseLong(CheckUtils.checkAndGetParameter(params, "idBilletera", "Se necesita id de billetera"))
        String token = CheckUtils.checkAndGetHeader(request, "Token")
        render(contentType: "application/json") {
            movimientoService.postMovimiento(billeteraId, body, token)
        }
    }

    def getMovimiento() {
        Long idMovimiento = Long.parseLong(CheckUtils.checkAndGetParameter(params, "idMovimiento", "Se nececita en id del movimiento"))
        String token = CheckUtils.checkAndGetHeader(request, "Token")
        render(contentType: "application/json") {
            movimientoService.getMovimiento(idMovimiento, token)
        }
    }

    def deleteMovimiento() {
        Long idMovimiento = Long.parseLong(CheckUtils.checkAndGetParameter(params, "idMovimiento", "Se nececita en id del movimiento"))
        String token = CheckUtils.checkAndGetHeader(request, "Token")
        render(contentType: "application/json") {
            movimientoService.deleteMovimiento(idMovimiento, token)
        }
    }

    def putMovimiento() {
        Map body = request.getJSON()
        Long idMovimiento = Long.parseLong(CheckUtils.checkAndGetParameter(params, "idMovimiento", "Se nececita en id del movimiento"))
        String token = CheckUtils.checkAndGetHeader(request, "Token")
        render(contentType: "application/json") {
            movimientoService.updateMovimiento(body, idMovimiento, token)
        }
    }
}
