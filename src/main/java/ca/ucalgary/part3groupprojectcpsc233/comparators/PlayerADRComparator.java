package ca.ucalgary.part3groupprojectcpsc233.comparators;

import ca.ucalgary.part3groupprojectcpsc233.objects.Player;

import java.util.Comparator;

/**
 * CPSC233 Group Project Part 2
 * Valorant eSports statistics tracker
 * Members:
 *  Sebastian Diaz
 *  Daniel Zhang
 *  Brian Chhan
 *  Tutorial 08 March 24th
 */
public class PlayerADRComparator implements Comparator<Player> {

    /**
     * Compares two players based on ADR, ACS if both are tied
     *
     * @param p1 the first Player to be compared.
     * @param p2 the second Player to be compared.
     * @return 0 if both Players are tied, -1 or 1 depending on result
     */
    @Override
    public int compare(Player p1, Player p2) {
        int comp = Integer.compare(p1.getAdr(),p2.getAdr());

        if (comp == 0){
            return Integer.compare(p1.getAcs(),p2.getAcs());
        }
        return comp;
    }
}
