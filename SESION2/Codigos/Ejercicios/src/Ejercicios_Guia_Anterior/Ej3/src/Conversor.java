public class Conversor<T extends Number> {
    public double convertirLongitud(double valor, String de, String a) {
        switch (de) {
            case "metros":
                if (a.equals("pies")) return valor * 3.28084;
                if (a.equals("pulgadas")) return valor * 39.3701;
                break;
            case "pies":
                if (a.equals("metros")) return valor / 3.28084;
                if (a.equals("pulgadas")) return valor * 12;
                break;
            case "pulgadas":
                if (a.equals("metros")) return valor / 39.3701;
                if (a.equals("pies")) return valor / 12;
                break;
        }
        return valor;
    }

    public double convertirTemperatura(double valor, String de, String a) {
        if (de.equals("Celsius") && a.equals("Fahrenheit")) return (valor * 9/5) + 32;
        if (de.equals("Celsius") && a.equals("Kelvin")) return valor + 273.15;
        if (de.equals("Fahrenheit") && a.equals("Celsius")) return (valor - 32) * 5/9;
        if (de.equals("Fahrenheit") && a.equals("Kelvin")) return (valor - 32) * 5/9 + 273.15;
        if (de.equals("Kelvin") && a.equals("Celsius")) return valor - 273.15;
        if (de.equals("Kelvin") && a.equals("Fahrenheit")) return (valor - 273.15) * 9/5 + 32;
        return valor;
    }
}
