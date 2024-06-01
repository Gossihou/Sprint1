
package tp;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 *classe d'affichage des information de sortie demander par le client 
 * @author gloireossihou
 */
public class Sortie {
    //Methode affichage
    public static void Afficher(){
        
JSONObject sortie = new JSONObject();

 sortie.accumulate("valeur_fonciere_totale", terrainObject.getvaleurFronciere());
 sortie.accumulate("taxe_scolaire",terrainObject.gettaxeMunicipale());
 sortie.accumulate("taxe_municipale", terrainObject.getTaxeMunicipale());
 
 JSONArray lotissements = new JSONArray();
 
 for (Lot lot : terrainObject.getLotissements()) {
             JSONObject lotissement = new JSONObject();
            lotissement.add("description", lot.getDescription());
            lotissement.add("valeur_par_lot", lot.getValeurParLot());
            lotissements.add(lotissement);
        }
sortie.accumulate("lotissements", lotissements);

 try { //Le bloc de code lève l'exception au cas où l'écriture serait erronée.
            FileWriter.saveStringIntoFile(filepath, sortie.toString()); //Utilise la méthode du Filewriter.
        } catch (IOException e) {
            System.err.println("Erreur lors de l'enregistrement du fichier de sortie:" + e.getMessage());
        }
}
   
}
