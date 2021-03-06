package br.com.tkmanager.api;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.crypto.bcrypt.BCrypt;

import br.com.tkmanager.models.*;
import br.com.tkmanager.util.Bcrypt;
import br.com.tkmanager.dbmanip.*;

@Path("/loginValidation")
public class loginValidation {
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response loginValidation(@FormParam("username") String usuario, @FormParam("password") String senha) 
									throws JSONException{
		JSONObject json = new JSONObject();
		
		try {
			Tecnico tec = Database.validateTecnico(usuario);
			
			if (tec != null) {
				if (Bcrypt.passwordVerifier(senha, tec.getSenha())) {
					json.put("existresp", true);
					json.put("idtec", tec.getId());
					json.put("processOK", true);
				}
				else {
					json.put("existresp", false);
					json.put("idtec", "null");
					json.put("processOK", true);
				}
			}
			else {
				json.put("existresp", false);
				json.put("idtec", "null");
				json.put("processOK", true);
			}
		}
		catch(Exception e) {
			json.put("existresp", false);
			json.put("idtec", "null");
			json.put("processOK", false);
			e.printStackTrace();
		}
		
		return Response.status(200).entity(json.toString(2)).build();	
	}
	
}
