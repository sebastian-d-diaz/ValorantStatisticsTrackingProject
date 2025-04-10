package ca.ucalgary.part3groupprojectcpsc233.enums;

/**
 * CPSC233 Group Project Part 2
 * Valorant eSports statistics tracker
 * Members:
 *  Sebastian Diaz
 *  Daniel Zhang
 *  Brian Chhan
 *  Tutorial 08 March 24th
 */
public enum Nationality {
    /**
     * Enums for use in defining all Nationalities
     */
    CAN("Canada"), USA("USA"), BRA("Brazil"), ARG("Argentina"),
    CHILE("Chile"),EURO("Europe"),ASIA("Asia");

    /**
     * String representation of Nationalities
     */
    private final String nationality;

    /**
     * Constructor, setting Nationality
     *
     * @param nationality Nationality one wishes to use
     */
    Nationality(String nationality){
        this.nationality = nationality;
    }

    /**
     * Returns Nationality given a String representation
     *
     * @param nationalityString String representation of the Nationality one wishes to get
     * @return Nationality of String given, null if no Nationality found
     */
    public static Nationality getNationality(String nationalityString) {
        Nationality origin = null; //nationality is null if no valid nationality is provided
        //assign the respective enum for the provided string
        if (nationalityString.equalsIgnoreCase("canada")) { //if the user inputs canada in any case, Nationality.CAN will be assigned as that persons nationality
            origin = CAN;
        } else if (nationalityString.equalsIgnoreCase("usa")) { //all nationalities follow the same process as the first
            origin = USA;
        } else if (nationalityString.equalsIgnoreCase("brazil")) {
            origin = BRA;
        } else if (nationalityString.equalsIgnoreCase("argentina")) {
            origin = ARG;
        } else if (nationalityString.equalsIgnoreCase("chile")) {
            origin = CHILE;
        } else if (nationalityString.equalsIgnoreCase("europe")) {
            origin = EURO;
        } else if (nationalityString.equalsIgnoreCase("asia")) {
            origin = ASIA;
        }
        return origin;
    }

    /**
     * Simple toString returning a Nationality's string representation
     *
     * @return String representation of current Nationality's Nationality
     */
    @Override
    public String toString() {
        return this.nationality;
    }
}
