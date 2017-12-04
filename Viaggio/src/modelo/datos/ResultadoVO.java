import java.awt.Image;

public class ResultadoVO {
	
	private String nombre;
    private double precio;
    private Image foto;

    public ResultadoVO(String nombre,double precio,Image foto){
        this.nombre = nombre;
		this.precio = precio;
        this.foto = foto;
    }

	//GET
	public String getNombre() {
        return nombre;
    }
	
    public double getPrecio() {
        return precio;
    }

    public Image getFoto() {
        return foto;
    }

	
	//SET	
	public void setNombre(String nombre) {
        this.nombre = nombre;
    }
	
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }
	
}