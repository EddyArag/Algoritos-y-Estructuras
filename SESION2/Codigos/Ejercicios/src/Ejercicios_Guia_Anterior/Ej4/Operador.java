package Ej4;

// Clase Genérica Operador
class Operador<T extends Number> {
    // Calcular interés simple
    public double calcularInteresSimple(OperacionFinanciera<T> operacion) {
        return operacion.getMonto().doubleValue()
                * (1 + operacion.getTasaInteres().doubleValue() * operacion.getPlazo());
    }

    // Calcular interés compuesto
    public double calcularInteresCompuesto(OperacionFinanciera<T> operacion) {
        return operacion.getMonto().doubleValue()
                * Math.pow((1 + operacion.getTasaInteres().doubleValue()), operacion.getPlazo());
    }

    // Conversión de moneda
    public double convertirMoneda(double monto, String de, String a) {
        double tasa = obtenerTasaConversion(de, a);
        return monto * tasa;
    }

    // Tasas de conversión de moneda
    private double obtenerTasaConversion(String de, String a) {
        if (de.equalsIgnoreCase("Soles") && a.equalsIgnoreCase("Dólares"))
            return 0.27;
        if (de.equalsIgnoreCase("Soles") && a.equalsIgnoreCase("Euros"))
            return 0.25;
        if (de.equalsIgnoreCase("Dólares") && a.equalsIgnoreCase("Soles"))
            return 3.7;
        if (de.equalsIgnoreCase("Dólares") && a.equalsIgnoreCase("Euros"))
            return 0.92;
        if (de.equalsIgnoreCase("Euros") && a.equalsIgnoreCase("Soles"))
            return 4.0;
        if (de.equalsIgnoreCase("Euros") && a.equalsIgnoreCase("Dólares"))
            return 1.09;
        return 1;
    }
}