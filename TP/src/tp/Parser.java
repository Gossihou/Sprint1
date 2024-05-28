package tp;

import java.io.IOException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Parser {

    public static void main(String[] args) throws IOException, ParseException {

        String myJSON = FileReader.loadFileIntoString("terrain.json", "UTF-8"); //Utilise la méthode pour lire le json.

        JSONObject terrainObject = JSONObject.fromObject(myJSON);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//Respectant la norme ISO 8601 en matière d'analyse des données.

        int terrainType = terrainObject.getInt("type_terrain");
        double prixMin = Double.parseDouble(terrainObject.getString("prix_m2_min").replace("$", "").trim()); //Cela supprimera le signe dollar et stockera les données.
        double prixMax = Double.parseDouble(terrainObject.getString("prix_m2_max").replace("$", "").trim());

        List<Lot> lotissementsArray = new ArrayList<>();  //Ce tableau est un tableau Java dans lequel nous stockerons les données à utiliser pour calculer.
        JSONArray lotissementsJsonArray = terrainObject.getJSONArray("lotissements"); //Accéder au tableau en json - les lotissemenets.

        for (int j = 0; j < lotissementsJsonArray.size(); j++) {
            JSONObject lotissementObject = lotissementsJsonArray.getJSONObject(j); //Récupère un objet Json spécifique du tableau lotissementsJsonArray en fonction de l'index j.

            String description = lotissementObject.getString("description");
            int nombreDroitsPassage = lotissementObject.getInt("nombre_droits_passage");
            int nombreServices = lotissementObject.getInt("nombre_services");
            int superficie = lotissementObject.getInt("superficie");

            String dateMesureString = lotissementObject.getString("date_mesure");
            Date dateMesure = dateFormat.parse(dateMesureString); //L'analyse en objet date.

            Lot lotissements = new Lot(terrainType, description, superficie, nombreServices, nombreDroitsPassage);
            //Crée une classe pour stocker les données de lot à utiliser pour les calculs.
            lotissementsArray.add(lotissements); //Les détails de chaque lot seront ajoutés à la liste.
        }

        Terrain terrain = new Terrain(terrainType, prixMin, prixMax, lotissementsArray);

        Calculator.calcul(terrain, prixMin, prixMax); //Appelle la méthode pour effectuer invocation de méthode dans la classe de calcul.

        Sortie.sortie(terrain, "sortie.json");

    }

}
