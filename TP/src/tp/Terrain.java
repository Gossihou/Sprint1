
package tp;

import net.sf.json.JSONObject;

/**
 *Classe terrain contenant les constantes neccesaire au clauls faite dans 
 * classe calculator
 * @author gloireossihou
 */
public class Terrain {

    //Constructeur object Terrain
public Terrain(int terrainType, double prixMin, double prixMax, JSONArray lotissementsArray){

this.setTerrainType(terrainType);
this.setprixMin(prixMin);
this.setprixMax(prixMax);
this.setJSONARAY(lotissementsJsonArray);

}
  
    // methode contenant les objet JSON
    public static void ConstanteTerrain(){
    
        {
  "type_terrain": 0,
  "prix_m2_min": "2.50 $",
  "prix_m2_max": "5.50 $",
  "lotissements": [
    {
      "description": "lot 1",
      "nombre_droits_passage": 2,
      "nombre_services": 0,
      "superficie": 900,
      "date_mesure": "2011-08-25"},
    
     {
         
      "description": "lot 2",
      "nombre_droits_passage": 1,
      "nombre_services": 0,
      "superficie": 1300,
      "date_mesure": "2013-04-17"},
   
      
      {
      "description": "lot 3",
      "nombre_droits_passage": 3,
      "nombre_services": 1,
      "superficie": 1700,
      "date_mesure": "2015-10-03"}]
}

    
    }
    
    
}
