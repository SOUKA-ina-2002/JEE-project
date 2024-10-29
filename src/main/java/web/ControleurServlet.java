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
		
		else if (path.equals("/AccueilSaisie.as")){
		    req.setAttribute("produit", new Produit());
			req.getRequestDispatcher("Saisie.jsp").forward(req,resp);
		}
				
		else if(path.equals("/Saisie.as")&&(req.getMethod().equals("POST"))) {
			Produit p=new Produit();
			
			p.setDesignation(req.getParameter("designation"));
			p.setPrix(Double.parseDouble(req.getParameter("prix")));
			p.setQuantity(Integer.parseInt(req.getParameter("quantity")));
			
			metier.save(p);
			
			req.setAttribute("produit", p);
			req.getRequestDispatcher("Confirmation.jsp").forward(req,resp);
			
		}
		
		else if(path.equals("/Supprimer.as")) {
			Long id=Long.parseLong(req.getParameter("id"));
			metier.deleteProduit(id);
			//req.getRequestDispatcher("produits.jsp").forward(req, resp);
			//faire une redirection vers une autre action
			resp.sendRedirect("chercher.as?motCle=");
		}
		
		else if(path.equals("/Edit.as")) {
			Long id=Long.parseLong(req.getParameter("id"));
			req.setAttribute("produit",metier.getProduit(id));
			req.getRequestDispatcher("Edit.jsp").forward(req, resp);
		}
		
		else if(path.equals("/Editer.as")&&(req.getMethod().equals("POST"))) {
			String id= req.getParameter("id");
			
			
			Produit p=new Produit(req.getParameter("designation"),Double.parseDouble(req.getParameter("prix")),Integer.parseInt(req.getParameter("quantity")));
			
			if (id != null && !id.isEmpty()) {
				p.setId(Long.parseLong(id));
			} else {
				resp.sendRedirect("chercher.as?motCle=");
			}
			
            metier.update(p);
			
			req.setAttribute("produit", p);
			req.getRequestDispatcher("Confirmation.jsp").forward(req,resp);			
			
		}
 
		else {
			resp.sendError(resp.SC_NOT_FOUND);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
