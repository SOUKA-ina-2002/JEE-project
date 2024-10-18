package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.entities.Produit;

public class ProduitDaoImplem implements IProduitDao {

	@Override
	public Produit save(Produit p) {
		Connection conn=SingletonConn.getConn();
		try {
			PreparedStatement ps= conn.prepareStatement("INSERT INTO PRODUITS (Designation,prix,quantity) VALUES (?,?,?)");
			ps.setString(1, p.getDesignation());
			ps.setDouble(2, p.getPrix());
			ps.setInt(3,p.getQuantity());
			ps.executeUpdate();
			
			PreparedStatement ps2= conn.prepareStatement("SELECT MAX(ID) AS MAX_ID FROM PRODUITS");
			ResultSet rs=ps2.executeQuery();
			
			if(rs.next()) {
				p.setId(rs.getLong("MAX_ID"));
			}
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return p;
	}

	@Override
	public List<Produit> produitsParMc(String mc) {
		List<Produit> produits= new ArrayList<Produit>();
	   Connection conn=SingletonConn.getConn();
	   try {
		PreparedStatement ps=conn.prepareStatement("SELECT *FROM PRODUITS WHERE Designation LIKE ?");
		ps.setString(1, mc);
		
		ResultSet rs=ps.executeQuery();
		
		while (rs.next()) {
			Produit p=new Produit();
			p.setId(rs.getLong("id"));
			p.setDesignation(rs.getString("Designation"));
			p.setPrix(rs.getDouble("prix"));
			p.setQuantity(rs.getInt("quantity"));
			
			produits.add(p);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
		return produits;
	}

	@Override
	public Produit getProguit(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produit update(Produit p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProduit(Long id) {
		// TODO Auto-generated method stub
		
	}

}
