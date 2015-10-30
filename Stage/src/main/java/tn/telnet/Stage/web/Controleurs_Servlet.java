package tn.telnet.Stage.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tn.telnet.Stage.metier.CatalogueMetierImpl;
import tn.telnet.Stage.metier.ICatalogueMetier;
import tn.telnet.Stage.metier.Produit;




/**
 * Servlet implementation class Controleurs_Servlet
 */
public class Controleurs_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ICatalogueMetier metier;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controleurs_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		metier = new CatalogueMetierImpl();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProduitModel model = new ProduitModel();
		List<Produit> produits = metier.listProduits();
		model.setProduits(produits);
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action= request.getParameter("action");
		ProduitModel model = new ProduitModel();
		request.setAttribute("model", model);
		if(action != null)
		{
			if(action .equals("chercher"))
			{
				model.setMotCle(request.getParameter("motCle"));
				List<Produit> produits = metier.listProduitsParMC(model.getMotCle());
				model.setProduits(produits);
			}
			else if(action.equals("delete"))
			{
				String ref = request.getParameter("ref");
				metier.deleteProduit(ref);
				model.setProduits(metier.listProduits());
			}
			else if(action.equals("edit"))
			{
				String ref = request.getParameter("ref");
				Produit p = metier.getproduit(ref);
				model.setProduit(p);
				model.setMode("edit");
				model.setProduits(metier.listProduits());	
			}
			else if(action.equals("save"))
			{
				try{
				model.getProduit().setReference(request.getParameter("reference"));
				model.getProduit().setDesignation(request.getParameter("designation"));
				model.getProduit().setPrix(Double.parseDouble(request.getParameter("prix")));
				model.getProduit().setQuantite(Integer.parseInt(request.getParameter("quantite")));
				model.setMode(request.getParameter("mode"));
				if(model.getMode().equals("ajout"))
				metier.addProduit(model.getProduit());
				else if(model.getMode().equals("edit"))
					metier.updateProduit(model.getProduit());
				model.setProduits(metier.listProduits());
				}catch(Exception e){
					model.setErrors(e.getMessage());
				}
			}
			
		}
         request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
