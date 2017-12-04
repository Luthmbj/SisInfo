
public class HotelVO {

    private String url;
    private String nombre;

    public HotelVO(String url, String nombre){
        this.url = url;
        this.nombre = nombre;
    }

	//GET
    public String getUrl() {
        return url;
    }

    public String getNombre() {
        return nombre;
    }
	
	//SET
    public void setUrl(String url) {
        this.url = url;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}