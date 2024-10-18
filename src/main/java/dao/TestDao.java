package dao;

import metier.entities.Produit;

public class TestDao {

	public static void main(String[] args) {
		ProduitDaoImplem dao = new ProduitDaoImplem();
		
		//Produit p1=dao.save(new Produit("jacket",1000,4));
		//System.out.println(p1.toString());
		
		System.out.println(dao.produitsParMc("com%"));
		

	}

}
