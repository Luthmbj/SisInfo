
public class BusquedaVO {
	
	private String usuario;
    private String lugar;
    private String fInicio;
    private String fFin;
	private int nHabitaciones;
    private int nPersonas;

    public BusquedaVO(String usuario, String lugar, String fInicio, String fFin, int nHabitaciones, int nPersonas){
        this.usuario = usuario;
		this.lugar = lugar;
        this.fInicio = fInicio;
        this.fFin = fFin;
		this.nHabitaciones = nHabitaciones;
		this.nPersonas = nPersonas;
    }

	//GET
	public String getUsuario() {
        return usuario;
    }
	
    public String getLugar() {
        return lugar;
    }

    public String getFInicio() {
        return fInicio;
    }

    public String getFFin() {
        return fFin;
    }
	
	public int getNHabitaciones() {
        return nHabitaciones;
    }
	
	public int getNPersonas() {
        return nPersonas;
    }
	
	//SET
	public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
	
	public void setLugar(String lugar) {
        this.lugar = lugar;
    }
	
	public void setFInicio(String fInicio) {
        this.fInicio = fInicio;
    }
	
	public void setFFin(String fFin) {
        this.fFin = fFin;
    }

	public void setNHabitaciones(int nHabitaciones) {
        this.nHabitaciones = nHabitaciones;
    }
	
	public void setNPersonas(int nPersonas) {
        this.nPersonas = nPersonas;
    }
}