package business.mappage;

public class MapPOI {
	
	public String mapAddPOi(){
		return "INSERT INTO tb_poi(Libelle_POI, Description_POI, X_POI, Y_POI, NombreClic_POI, ID_Lieu) VALUES(?, ?, ?, ?, ?, ?)";
	}
	public String mapRemovePOi(){
		return "DELETE FROM tb_poi WHERE Libelle_POI = ? AND Description_POI = ? AND X_POI = ? AND Y_POI = ?, NombreClic_POI = ?, ID_Lieu = ?";
	}
	public String mapUpdateLieu(){
		return "UPDATE tb_lieu SET Libelle_Lieu = ?, Description_Lieu = ?, Image_Lieu = ? WHERE Libelle_Lieu = ? AND Description_Lieu = ? AND Image_Lieu = ?";
	}
	public String mapSelectLieu(){
		return "";
	}
	public String mapListAllLieu(){
		return "SELECT * FROM tb_lieu";
	}

}
