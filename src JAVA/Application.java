

import java.io.IOException;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * Servlet implementation class Application
 */
@WebServlet("/Application")

//@WebService(name = "Application")

public class Application extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String VUE="/AffichageGallerie.jsp";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Application() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* Stockage du formulaire et du bean dans l'objet request */
//      request.setAttribute( "message", "creation avec succes");
		Gallerie gallerie=Gallerie.getInstance();
		
		String choix = request.getParameter( "action" );
		switch (choix){
			case "supprimerCatalogue":
				gallerie.supprimerCatalogue(request.getParameter( "themeCatalogue" ));
				break;
			case "supprimerPhoto":
				gallerie.supprimerPhoto(request.getParameter( "themeCatalogue" ), request.getParameter( "titrePhoto" ));
				break;
			case "nouveauCatalogue":
				Catalogue c = new Catalogue();
				c.creerCatalogue(request.getParameter( "theme" ),request.getParameter( "nom" ),request.getParameter( "prenom" ));
				gallerie.ajouterCatalogue(c);
				break;
			case "ajoutPhoto":
				VUE="/AjoutPhoto.jsp";
				request.setAttribute( "theme" ,request.getParameter( "themeCatalogue" ));
			case "lancement":
				VUE="/AffichageGallerie.jsp";
				break;
			case "modifierPhoto":
				gallerie.supprimerPhoto(request.getParameter( "themeCatalogue" ), request.getParameter( "titrePhoto" ));
				Photo p=new Photo();
				p.setCat(request.getParameter( "themeCatalogue" ));
				p.setDatePrise(request.getParameter( "datePrise"));
				p.setLieu(request.getParameter( "lieu"));
				p.getAuteur().setNomP(request.getParameter( "nomAuteur"));
				p.getAuteur().setPrenomP(request.getParameter( "prenomAuteur"));
				p.setCommentaire(request.getParameter( "commentaire"));
				 
				gallerie.ajouterPhoto(request.getParameter( "themeCatalogue" ),p);
				break;
			default:
				break;
		}
		request.setAttribute( "gallerie", gallerie);
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}	
	
	
	
	//@WebMethod
    
	
}