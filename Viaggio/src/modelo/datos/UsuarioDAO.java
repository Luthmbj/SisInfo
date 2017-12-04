
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
	
	public void insertarUsuario (UsuarioVO usuario, Connection connection) {               
        try{
            /* Create "preparedStatement". */
            String queryString = "INSERT INTO Usuario " +
                "(email, nombre, apellidos, password, fNacimiento) " +
            		"VALUES (?,?,?,?,?)";
            
            PreparedStatement preparedStatement = 
                connection.prepareStatement(queryString);
            
            /* Fill "preparedStatement". */    
            preparedStatement.setString(1, usuario.getEmail());
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setString(3, usuario.getApellidos());
            preparedStatement.setString(4, usuario.getPass());
			preparedStatement.setString(5, usuario.getFecha());
            
            /* Execute query. */                    
            int insertedRows = preparedStatement.executeUpdate();
                
            if (insertedRows != 1) {
                throw new SQLException( "Error en la inserción de usuario");
            }              
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }                
    } 
	
	public void modificarUsuario (UsuarioVO usuario, Connection connection) {     
        try{
            /* Create "preparedStatement". */
            String queryString = "UPDATE Usuario " +
                "SET nombre = ?, apellidos = ?, password = ?, " +
            	" fnacimiento = ? WHERE  email = ?";                    
            PreparedStatement preparedStatement = 
                connection.prepareStatement(queryString);
            
            /* Fill "preparedStatement". */    
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getApellidos());
            preparedStatement.setString(3, usuario.getPass());
            preparedStatement.setString(4, usuario.getFecha());
			preparedStatement.setString(5, usuario.getEmail());
              
            /* Execute query. */                    
            int insertedRows = preparedStatement.executeUpdate();
                
            if (insertedRows != 1) {
                throw new SQLException( "Error en la actualización de usuario");
            }                
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }                
    } 
	
	public UsuarioVO encontrarDatosUsuario (String email, Connection connection){     
    	UsuarioVO usuarioVO = null;
    	try{
            /* Create "preparedStatement". */
            String queryString = "SELECT nombre,  apellidos, password, " +
            		" fnacimiento FROM Usuario WHERE  email = ?";                    
            PreparedStatement preparedStatement = 
                connection.prepareStatement(queryString);
            
            /* Fill "preparedStatement". */    
            preparedStatement.setString(1, email);
                   
            /* Execute query. */                    
            ResultSet resultSet = preparedStatement.executeQuery();
               
            if (!resultSet.first()) {
                throw new SQLException( "Error: Usuario no encontrado");
            }
            
            /* Execute query. */                    
            String nombre = resultSet.getString(1);
            String apellidos = resultSet.getString(2);
            String password = resultSet.getString(3);
            String fecha = resultSet.getString(4);
              
            usuarioVO = new UsuarioVO (email, nombre,
            		apellidos, password, fecha);
                
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    	return usuarioVO;
    } 
	
    public void borrarUsuario(String email, Connection connection) {
		try {
			 /* Create "preparedStatement". */
			String queryString = "DELETE FROM Usuario WHERE email = ?";                    
            PreparedStatement preparedStatement = 
                connection.prepareStatement(queryString);
			
			/* Fill "preparedStatement". */  
			preparedStatement.setString(1, email);
			
			/* Execute query. */                    
            preparedStatement.executeUpdate();
		} catch (Exception e) {
            e.printStackTrace(System.err);
		}
    }


}