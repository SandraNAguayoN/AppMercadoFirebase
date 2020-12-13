package mx.edu.utng.appmercadofirebase;

public class Articulo {
    private String articuloId, nombre, precio, cantidad, categoria, envio, garantia,  descripcion;

   /* public Articulo(String articuloId, String nombre, String precio, String cantidad, String categoria, String envio, String garantia, String descripcion) {
        this.articuloId = articuloId;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.envio = envio;
        this.garantia = garantia;
        this.descripcion = descripcion;
    }*/

    public String getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(String articuloId) {
        this.articuloId = articuloId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEnvio() {
        return envio;
    }

    public void setEnvio(String envio) {
        this.envio = envio;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "\n"+
                "                                     "+nombre + "\n"+
                "Precio: $" + precio +
                "\n";
    }
}
