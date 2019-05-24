package pe.edu.idat.appvolleyrecycler.Modelo;

public class Animales {

    private String Nombre;
    private String UrlImagen;

    public Animales(String nombre, String urlImagen) {
        Nombre = nombre;
        UrlImagen = urlImagen;
    }

    public String getNombre() {
        return Nombre;
    }


    public String getUrlImagen() {
        return UrlImagen;
    }


}
