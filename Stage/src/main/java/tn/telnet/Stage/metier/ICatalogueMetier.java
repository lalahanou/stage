package tn.telnet.Stage.metier;

import java.util.List;

public interface ICatalogueMetier {
	public void addProduit(Produit p);
	public List<Produit> listProduits();
	public List<Produit> listProduitsParMC(String mc);
	public Produit getproduit(String ref);
    public void updateProduit(Produit p);
    public void deleteProduit(String ref);

}
