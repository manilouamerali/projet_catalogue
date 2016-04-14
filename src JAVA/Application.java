

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;

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
				Photo p=null;
				for(Catalogue ca: gallerie.getCatalogues())
					if(ca.getTheme().equals(request.getParameter( "themeCatalogue" ))){
						for(Photo ph: ca.getPhotos())
							if(ph.getTitre().equals(request.getParameter( "titre" ))){		
								p=ph;
								break;
							}
						break;
					}
				p.setDatePrise(request.getParameter( "datePrise"));
				p.setLieu(request.getParameter( "lieu"));
				p.setCommentaire(request.getParameter( "commentaire"));
				//on verifie si l'auteur a changé
				if(p.getAuteur().getNomP()!=request.getParameter( "nomAuteur") || p.getAuteur().getPrenomP()!=request.getParameter( "prenomAuteur")){
					Set<Entry<String, List<Photo>>> setHm = gallerie.getPhotosAuteur().entrySet();
					for(Entry<String, List<Photo>> e :setHm){
						if(e.getKey().equals(p.getAuteur().getPrenomP().concat(p.getAuteur().getNomP())))
							e.getValue().remove(p);
					}
					p.getAuteur().setNomP(request.getParameter( "nomAuteur"));
					p.getAuteur().setPrenomP(request.getParameter( "prenomAuteur"));
					if(gallerie.getPhotosAuteur().containsKey(p.getAuteur().getPrenomP()+p.getAuteur().getNomP())){
						gallerie.getPhotosAuteur().get(p.getAuteur().getPrenomP()+p.getAuteur().getNomP()).add(p);
					}
					else{
						List<Photo> l = new CopyOnWriteArrayList<Photo>();
						l.add(p);
						gallerie.getPhotosAuteur().put(p.getAuteur().getPrenomP()+p.getAuteur().getNomP(), l);
					}
				}
				break;
			default:
				break;
		}
		request.setAttribute( "gallerie", gallerie);
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}	
	
	
	
	//@WebMethod
    
	
}