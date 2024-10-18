package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IProduitDao;
import dao.ProduitDaoImplem;
import metier.entities.Produit;

public class ControleurServlet extends HttpServlet{
	private IProduitDao metier;
	
	@Override
	public void init() throws ServletException {
		metier = new ProduitDaoImplem();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path =req.getServletPath();
		
		if(path.equals("/index.as")) {
			req.getRequestDispatcher("produits.jsp").forward(req,resp);

		}
		else if(path.equals("/chercher.as")) {
			String motCle=req.getParameter("motCle");
			
			ProduitModel modele= new ProduitModel();
			modele.setMc(motCle);
			
			List<Produit> produits=metier.produitsParMc("%"+motCle+"%");
			
			modele.setProduits(produits);
			
			req.setAttribute("modele", modele);
			req.getRequestDispatcher("produits.jsp").forward(req,resp);

		}
		else {
			resp.sendError(resp.SC_NOT_FOUND);
		}
	}

}
