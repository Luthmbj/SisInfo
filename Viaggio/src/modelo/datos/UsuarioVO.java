
public class UsuarioVO{

    private String nombre;
    private String apellidos;
	private String email;
    private String pass;
    private String fecha;

    public UsuarioVO(String email, String nombre, String apellidos, String pass, String fecha) {
		if (email != null){
			this.email = email;
		}	
		if (nombre != null){
			this.nombre = nombre;
		}
		if (apellidos != null){
			this.apellidos = apellido;
		}
		if (pass != null){
			this.pass = pass;
		}	
		if (fecha != null){
			this.fecha = fecha;
		}
    }
	

	//GET
    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }
		
    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }
	
    public String getFecha() {
        return fecha;
    }
	
	//SET
	public void setNombre(String nombre){
		this.nombre = nombre;
	}

	public void setApellidos(String apellidos){
		this.apellidos = apellidos;
	}
	
	public void setPass(String pass){
		this.pass = pass;
	}
	
	public String setFecha() {
        this.fecha = fecha;
    }

}
