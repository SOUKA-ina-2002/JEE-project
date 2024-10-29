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
			
			ps2.close();
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
		
		  ps.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
		return produits;
	}

	@Override
	public Produit getProduit(Long id) {
	       Produit p=null;
		   Connection conn=SingletonConn.getConn();
		   
		   try {
			PreparedStatement ps=conn.prepareStatement("SELECT *FROM PRODUITS WHERE id=?");
			ps.setLong(1, id);
			
			ResultSet rs=ps.executeQuery();
			
			if (rs.next()) {
			    p= new Produit();
				p.setId(rs.getLong("id"));
				p.setDesignation(rs.getString("Designation"));
				p.setPrix(rs.getDouble("prix"));
				p.setQuantity(rs.getInt("quantity"));
				
			}
			
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
			return p;
	}

	@Override
	public Produit update(Produit p) {
	    Connection conn = SingletonConn.getConn();
	    try {
	        PreparedStatement ps = conn.prepareStatement("UPDATE PRODUITS SET Designation = ?, prix = ?, quantity = ? WHERE id = ?");
	        ps.setString(1, p.getDesignation());
	        ps.setDouble(2, p.getPrix());
	        ps.setInt(3, p.getQuantity());
	        ps.setLong(4, p.getId());

	        ps.executeUpdate();
	        ps.close();

	    } catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }

	    return p;
	}


	@Override
	public void deleteProduit(Long id) {
         Connection connection=SingletonConn.getConn();
         
        try {
			PreparedStatement ps=connection.prepareStatement("DELETE FROM PRODUITS WHERE id=?");
			ps.setLong(1, id);
			
			ps.executeUpdate();
			
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
