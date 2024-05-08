import java.util.Scanner;

public class Input {
    public static void askShipsJ(char[][] tableroJ, int[] ships, char[][] tableroDJ) {
        //Con este metodo vamos a poner todos los barcos del jugador en su respectivo tablero
        boolean comprobar = false;
        int i = 0;
        int coordL = 0;
        int coordN = 0;
        while (i<ships.length){ //Hasta que no esten todos los barcos puestos en el tablero el bucle no va a parar
            Display.showTablerosJ(tableroJ, tableroDJ);
            String coord = getString("Let's place the ship with " + ships[i] + " size (Valid coords 0A-9J): ").toUpperCase(); //Preguntamos la coordenada al usuario
            comprobar = Tools.comprobarCoord(coord); //LLamamos al metodo para comprobar que lo que nos ha dado el usuario es correcto
            if (comprobar){ //Si la coordenada es la correcta preguntaremos la orientacion deseada
                //Aqui cambio char por int ya que sera mas facil a la hora de gestionar las coordenadas
                coordL = coord.charAt(1)-'A';
                coordN = coord.charAt(0)-'0';
                String orientacion_s = getString("Wich orientation do you want? (h-->horizontal  v-->vertical): "); //Pregunto una orientacion deseada al usuario
                char orientacion = orientacion_s.toUpperCase().charAt(0);
                if (orientacion=='H' || orientacion=='V'){//Compruebo que el dato dado es 'H' o 'V'
                    comprobar = Tools.checkOrientationShip(tableroJ, orientacion, coordL, coordN, ships[i]); //Llamo al metodo para coomprobar que la orientacion.
                    if (comprobar){//Se compruebra que el metodo anterior nos haya devuelto true o false
                        Tools.placeShip(tableroJ, orientacion, coordL, coordN, ships[i]); //Como la posicion y la orientacion son buenas ponemos el barco.
                        i++; //Sumamos el indice y por tanto cambiamos de barco al siguiente
                    } else {//Si la orientacion no es valida volvemos al bucle
                        System.out.println("Orientation not possible try again.");
                    }
                }else { //Si la orientacion dada no es valida volvemos al bucle
                    System.out.println("Something went wrong try again.");
                }
            } else { //Si la coorddenada dada no es lo que queremos volvemos al bucle
                System.out.println("Something went wrong try again.");
            }


        }

    }
    public static void turnoJ(char[][] tableroPC, char[][]tableroDJ, int[] hitsJ, char[][] tableroDPC, char[][]tableroJ, int[] ships, boolean showPC){
        //Este metodo consiste en el turno del jugador
        boolean fin = false;
        boolean comprobar = false;
        int coordL = 0;
        int coordN = 0;
        while (!fin && hitsJ[0] != Tools.sumArray(ships)){ //Hasta que el jugador no haya disparado al agua o haya ganado el turno no acaba.
            String coord = getString("Turn of Player, where do you wanna shoot? (If you hit a ship, you will shoot again)(Valid coords 0A-9J): ").toUpperCase();
            comprobar = Tools.comprobarCoord(coord);//Llamamos al metodo para coomprobar que la coordena dada es coorecta
            if (comprobar){//Si la coordenada es buena vamos a disparar
                coordL = coord.charAt(1)-'A';
                coordN = coord.charAt(0)-'0';
                fin = Tools.shootJ(tableroPC, tableroDJ, coordL, coordN, hitsJ, tableroDPC, tableroJ, showPC);//Llamamos al metodo para disparar en esa coordenada
            } else {//Si la coordenada dada no es buena volvera a pedirla
                System.out.println("Something went wrong, try again.");
            }
        }
    }

    public static void turnoPC(char[][] tableroJ, char[][]tableroDPC, int[] hitsPC, int[] ships){
        //Este metodo realiza el turno del PC de manera aleatoria
        boolean fin = false;
        boolean comprobar = false;
        int coordL = 0;
        int coordN = 0;
        while (!fin){//El turno no acaba hasta que el PC haya disparado al agua o haya ganado
            coordL = (int)(Math.random()*10);
            coordN = (int)(Math.random()*10);
            fin = Tools.shootPC(tableroJ, tableroDPC, coordL, coordN, hitsPC, ships);//Llamamos al metodo para que el PC diapre
        }
    }

    public static String getString(String message){
        //Este metodo pide por pantalla un String y hasta que no se le ofrece no termina
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        return sc.next();
    }

    public static boolean askShowPC(){
        //Este metodo pregunta al usuario si se va a querer mostrar el tablero del PC durante toda la partida.
        String respuesta = getString("Do you wanna show the PC board during the entire game?: (Yes/No): ").toUpperCase();
        if (respuesta.equals("YES")){
            return true;
        }
        return false;
    }

}
