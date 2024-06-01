package tp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Calculator {

    public static double calculLot(Lot lotissements, double prixMin, double prixMax) {
        double valeur = 0.0;

        switch (lotissements.getTypeTerrain()) {
            case 0: // Agricole
                valeur = lotissements.getSuperficie() * prixMin;
                break;
            case 1: // Résidentiel
                double prixMoyen = (prixMin + prixMax) / 2.0;
                valeur = lotissements.getSuperficie() * prixMoyen;
                break;
            case 2: // Commercial
                valeur = lotissements.getSuperficie() * prixMax;
                break;
            default:
                break;
        }

        return valeur;
    }

    private static double calculDroitsDePassage(Lot lotissements, double prixMin, double prixMax) {
        double droitsDePassage = 0.0;
        double valeurLot = calculLot(lotissements, prixMin, prixMax);

        switch (lotissements.getTypeTerrain()) {
            case 0: // Agricole
                droitsDePassage = 500 - (lotissements.getNombreDroitsPassage() * (0.05 * valeurLot));
                break;
            case 1: // Résidentiel
                droitsDePassage = 500 - (lotissements.getNombreDroitsPassage() * (0.10 * valeurLot));
                break;
            case 2: // Commercial
                droitsDePassage = 500 - (lotissements.getNombreDroitsPassage() * (0.15 * valeurLot));
                break;
            default:
                break;
        }

        return droitsDePassage;
    }

    private static double calculServices(Lot lotissements) {
        double services = 0.0;
        int typeTerrain = lotissements.getTypeTerrain();

        switch (typeTerrain) {
            case 1: // Résidentiel
                if (lotissements.getSuperficie() > 500 && lotissements.getSuperficie() <= 10000) {
                    services = Math.min(5000, lotissements.getNombreServices() * 500.0);
                } else if (lotissements.getSuperficie() > 10000) {
                    services = Math.min(5000, lotissements.getNombreServices() * 1000.0);
                }
                break;
            case 2: // Commercial
                if (lotissements.getSuperficie() <= 500) {
                    services = Math.min(5000, lotissements.getNombreServices() * 500.0);
                } else {
                    services = Math.min(5000, lotissements.getNombreServices() * 1500.0);
                }
                break;
            default:
                break;
        }

        return services;
    }

   public static void calcul(Terrain terrain, double prixMin, double prixMax) {
    double valeurFonciereTotale = 0.0;

    for (Lot lot : terrain.getLotissements()) {
        double valeurParLot = calculLot(lot, prixMin, prixMax);
        double droitsDePassage = calculDroitsDePassage(lot, prixMin, prixMax);
        double services = calculServices(lot);

        BigDecimal bdValue = new BigDecimal(valeurParLot);
        bdValue = bdValue.divide(BigDecimal.valueOf(0.05), 0, RoundingMode.CEILING).multiply(BigDecimal.valueOf(0.05));
        valeurParLot = bdValue.doubleValue();

        valeurFonciereTotale += valeurParLot + droitsDePassage + services;

        String formattedValeurParLot = formatCurrency(valeurParLot);
        lot.setValeurParLot(formattedValeurParLot);
    }

    String formattedValeurFonciereTotale = formatCurrency(valeurFonciereTotale);
    terrain.setValeurFonciereTotale(formattedValeurFonciereTotale);

    double taxeScolaire = valeurFonciereTotale * 0.012;
    String formattedTaxeScolaire = formatCurrency(taxeScolaire);

    double taxeMunicipale = valeurFonciereTotale * 0.025;
    String formattedTaxeMunicipale = formatCurrency(taxeMunicipale);

    terrain.setTaxeScolaire(formattedTaxeScolaire);
    terrain.setTaxeMunicipale(formattedTaxeMunicipale);
}

private static String formatCurrency(double value) {
    DecimalFormat decimalFormat = new DecimalFormat("0.00 $");
    return decimalFormat.format(value);
}


    }

