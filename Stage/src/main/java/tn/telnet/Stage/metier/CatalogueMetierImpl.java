package tn.telnet.Stage.metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogueMetierImpl implements ICatalogueMetier{

	public void addProduit(Produit p) {
		// TODO Auto-generated method stub
		Connection con =SingletonConnection.getConnection();
		try {
			PreparedStatement st = con.prepareStatement("insert into PRODUITS (REF_PROD,DESIGNATION,PRIX,QUANTITE) values(?,?,?,?);");
		    st.setString(1, p.getReference());
		    st.setString(2, p.getDesignation());
		    st.setDouble(3, p.getPrix());
		    st.setInt(4, p.getQuantite());
		    st.executeUpdate();
		    st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public List<Produit> listProduits() {
		// TODO Auto-generated method stub
		List<Produit> prods = new ArrayList<Produit>();
		Connection con =SingletonConnection.getConnection();
		try {
			PreparedStatement st = con.prepareStatement("select * from PRODUITS ;");
		    ResultSet rs = st.executeQuery();
		    while(rs.next())
		    {
		    	Produit p = new Produit();
		    	p.setReference(rs.getString("REF_PROD"));
		    	p.setDesignation(rs.getString("DESIGNATION"));
		    	p.setPrix(rs.getDouble("PRIX"));
		    	p.setQuantite(rs.getInt("QUANTITE"));
		    	prods.add(p);
		    }
		    st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prods;
	}

	public List<Produit> listProduitsParMC(String mc) {
		// TODO Auto-generated method stub
		List<Produit> prods = new ArrayList<Produit>();
		Connection con =SingletonConnection.getConnection();
		try {
			PreparedStatement st = con.prepareStatement("select * from PRODUITS where DESIGNATION like ? ;");
		    st.setString(1, "%"+mc+"%");
			ResultSet rs = st.executeQuery();
		    while(rs.next())
		    {
		    	Produit p = new Produit();
		    	p.setReference(rs.getString("REF_PROD"));
		    	p.setDesignation(rs.getString("DESIGNATION"));
		    	p.setPrix(rs.getDouble("PRIX"));
		    	p.setQuantite(rs.getInt("QUANTITE"));
		    	prods.add(p);
		    }
		    st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prods;
	}

	public Produit getproduit(String ref) {
		// TODO Auto-generated method stub
		Produit p = null;
		Connection con =SingletonConnection.getConnection();
		try {
			PreparedStatement st = con.prepareStatement("select * from PRODUITS where REF_PROD = ? ;");
		    st.setString(1, ref);
			ResultSet rs = st.executeQuery();
		    if(rs.next())
		    {
		        p = new Produit();
		    	p.setReference(rs.getString("REF_PROD"));
		    	p.setDesignation(rs.getString("DESIGNATION"));
		    	p.setPrix(rs.getDouble("PRIX"));
		    	p.setQuantite(rs.getInt("QUANTITE"));
		    	
		    }
		    st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(p == null) throw new RuntimeException("Produit introuvable");
		return p;
	}

	public void updateProduit(Produit p) {
		// TODO Auto-generated method stub
		Connection con =SingletonConnection.getConnection();
		try {
			PreparedStatement st = con.prepareStatement("update  PRODUITS set DESIGNATION =?,PRIX =?, QUANTITE = ? where REF_PROD =?;");
		    st.setString(1, p.getDesignation());
		    st.setDouble(2, p.getPrix());
		    st.setInt(3, p.getQuantite());
		    st.setString(4, p.getReference());
		    st.executeUpdate();
		    st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void deleteProduit(String ref) {
		// TODO Auto-generated method stub
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("delete from produits where REF_PROD = ?;");
			ps.setString(1, ref);
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		
	}
		
}
