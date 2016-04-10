

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AffichageAuteur
 */
@WebServlet("/AffichageAuteur")
public class AffichageAuteur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AffichageAuteur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String VUE="/AffichageAuteur.jsp";
		Gallerie g=Gallerie.getInstance();
		String nom=request.getParameter( "nomAuteur" );
		String prenom=request.getParameter( "prenomAuteur" );
		
		Set<Entry<String, List<Photo>>> setHm = g.getPhotosAuteur().entrySet();
		for(Entry<String, List<Photo>> e :setHm){
			if(e.getKey().equals(prenom+nom)){
				request.setAttribute( "listePhotos",e.getValue());
				request.setAttribute( "auteur",e.getKey());
				break;
			}
		}
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

}
