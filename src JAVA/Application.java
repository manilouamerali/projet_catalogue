

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
			case "nouvellePhoto":
				Photo nvelleP=new Photo();
				nvelleP.creerPhoto(request.getParameter( "datePrise"), request.getParameter( "lieu"), request.getParameter( " nomAuteur"),request.getParameter( "prenomAuteur"),request.getParameter( "emailAuteur"), 
						request.getParameter( "titre"), request.getParameter( "dimension"), Integer.parseInt(request.getParameter( "resolution")), request.getParameter( "categorie"),
						request.getParameter( "commentaire"),request.getParameter("fichier"),request.getParameter("themeCatalogue"));
				System.out.println("Application nouvellePhoto : "+ nvelleP.getImg());
				gallerie.ajouterPhoto(request.getParameter( "themeCatalogue" ),nvelleP);
				break;
			case "lancement":
				VUE="/AffichageGallerie.jsp";
				break;
			case "modifierPhoto":
				gallerie.supprimerPhoto(request.getParameter( "themeCatalogue" ), request.getParameter( "titrePhoto" ));
				Photo p=new Photo();
				p.creerPhoto(request.getParameter( "datePrise"), request.getParameter( "lieu"), request.getParameter( " nomAuteur"),request.getParameter( "prenomAuteur"),request.getParameter( "emailAuteur"), 
						request.getParameter( "titre"), request.getParameter( "dimension"), Integer.parseInt(request.getParameter( "resolution")), request.getParameter( "categorie"),
						request.getParameter( "commentaire"),request.getParameter("fichier"),request.getParameter("themeCatalogue"));
				
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