public class Display {

    public static void showTablerosPC(char[][] tableroPC, char[][] tableroDPC){
        //Este metodo muestra por pantalla los tableros del PC
        System.out.println("\n              PC board         \t\t\t            Shooting board of PC");
        System.out.println("     A  B  C  D  E  F  G  H  I  J\t\t\t     A  B  C  D  E  F  G  H  I  J");
        for (int i=0; i<tableroPC.length; i++){
            System.out.print(i + " |");
            for (int j=0; j<tableroPC[0].length; j++){
                System.out.print("  " + tableroPC[i][j]);
            }
            System.out.print("\t\t\t" + i + " |");
            for (int j=0; j<tableroDPC[0].length; j++){
                System.out.print("  " + tableroDPC[i][j]);
            }
            System.out.println();
        }
        System.out.println();

    }

    public static void showTablerosJ(char[][] tableroJ, char[][] tableroDJ){
        //Este metodo muestra por pantalla los tablero del jugador
        System.out.println("\n             Player board       \t\t\t       Shooting board of Player");
        System.out.println("     A  B  C  D  E  F  G  H  I  J\t\t\t     A  B  C  D  E  F  G  H  I  J");
        for (int i=0; i<tableroJ.length; i++){
            System.out.print(i + " |");
            for (int j=0; j<tableroJ[0].length; j++){
                System.out.print("  " + tableroJ[i][j]);
            }
            System.out.print("\t\t\t" + i + " |");
            for (int j=0; j<tableroDJ[0].length; j++){
                System.out.print("  " + tableroDJ[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean menu(boolean showPC){
        //Este metodo muestra por pantalla un menu que habra solo al principio del juego.
        System.out.println("************************************************* BATLLESHIP GAME *************************************************");
        System.out.println("Welcome to battleship game made with java.  Developer: Juan Carlos Santamaria Peris");
        System.out.println("\nBattleship is a strategy type guessing game for two players (In this case 1 player vs PC). \n" +
                "It is played on ruled grids (paper or board) on which each player's \n" +
                "fleet of warships are marked. The locations of the fleets are concealed \n" +
                "from the other player. Players alternate turns calling \"shots\" at the other player's \n" +
                "ships, and the objective of the game is to destroy the opposing player's fleet.");
        System.out.println("\nRules:");
        System.out.println("There are 6 ships: 1 Battleship(4 holes), 2 Cruisers(3 holes), 2 Destroyers(2 holes) and 1 Boat(1 hole).");
        System.out.println("-The first who sink all 6 ships is the winner.");
        System.out.println("-The ships must not be aligned with each other.");
        System.out.println("-You can't shoot twice in the same place.");
        showPC = Input.askShowPC();
        System.out.println("*******************************************************************************************************************");
        return showPC;
    }
}
