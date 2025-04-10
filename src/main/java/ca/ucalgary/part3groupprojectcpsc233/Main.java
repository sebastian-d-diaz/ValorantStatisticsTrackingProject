package ca.ucalgary.part3groupprojectcpsc233;
import java.io.File;

/**
 * CPSC233 Group Project Part 2
 * Valorant eSports statistics tracker
 * Members:
 *  Sebastian Diaz
 *  Daniel Zhang
 *  Brian Chhan
 *  Tutorial 08 March 24th
 */
public class Main {
    public static void main(String[] args){

        if (args.length > 3 || args.length == 1 || args.length ==2 ){
            System.err.println("Amount of arguments given be must three or 0!");
            System.exit(1);
        }
        else if (args.length == 3){
            File file1 = new File(args[0]);
            File file2 = new File(args[1]);
            File file3 = new File(args[2]);
            if (!file1.exists() || !file1.canRead() || !file2.exists() || !file2.canRead() || !file3.canRead() || !file3.exists()){
                System.err.println("Can't load or access file(s) given!");
                System.exit(1);
            }
            Menu.setLoadData(true);
        }

        Menu.printExpositionMessage();
        Menu.menuLoop();
    }

}
