package gastos

enum MetodosPagoEnum {
    EFECTIVO('Movimiento en efectivo'),
    TARJETA_CREDITO('Movimiento con tarjeta de credito'),
    TARJETA_DEBITO('Movimiento con tarjeta de debito'),
    TRANSFERENCIA('Movimiento por transferencia bancaria')

    String description

    private MetodosPagoEnum(String description) {

        this.description = description
    }

    static MetodosPagoEnum getEnum(String s){
        return (MetodosPagoEnum.valueOf(s.toUpperCase()))
    }
}