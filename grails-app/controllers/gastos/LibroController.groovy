package gastos

class LibroController {

    LibroService libroService

    def postLibro(){
        Map body = request.getJSON()
        if(!(body?.descripcion)){
            throw new Exception("descripcion no encontrada")
        }
        libroService.postLibro(body.descripcion)
        body.status = "OK"
        render(contentType: "application/json") {
            body
        }
    }

    def getLibros(){
        List<Libro> response = libroService.getLibros()
        render(contentType: "application/json") {
            response*.getMap()
        }
    }

    def getLibro(){
        Long libroId = params.long("idLibro")
        Libro response = libroService.getLibro(libroId)
        render(contentType: "application/json") {
            response.getMap()
        }
    }

    def getBalance(){
        Long libroId = params.long("idLibro")
        Map response = libroService.getBalance(libroId)
        render(contentType: "application/json") {
            response
        }
    }

    def ping(){
        Map response = [prueba:"Ok"]
        render(contentType: "application/json") {
            response
        }
    }
}
