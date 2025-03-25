package EjerciciosFinal;

class Chocolatina {
    private String marca;

    public Chocolatina(String marca) {
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    // equals para comparar Chocolatinas por marca
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Chocolatina that = (Chocolatina) obj;
        return marca.equals(that.marca);
    }

    // para imprimir
    @Override
    public String toString() {
        return "Chocolatina{marca='" + marca + "'}";
    }

}
