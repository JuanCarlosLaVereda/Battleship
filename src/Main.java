public class Main {
    public static void main(String[] args) {
        //Por aqui declaro las variables pricipales y las matrices de los tableros del juego.
        char[][] tableroJ = new char[10][10];
        char[][] tableroPC = new char[10][10];
        char[][] tableroDJ = new char[10][10];
        char[][] tableroDPC = new char[10][10];
        boolean showPC = true;
        boolean fin = false;
        String ganador = "";
        int[] hitsJ = new int[1];
        int[] hitsPC = new int[1];
        int[] ships = {4,3,3,2,2,1};

        /*Lo primero es lo primero, mostramos un pequeño menú y ademas le preguntamos
         al usuario si queremos ver el tablero del PC,
         llenamos las matrices y empezamos preguntando al usuario coordenadas.*/
        showPC = Display.menu(showPC);
        fillTableros(tableroJ, tableroPC, tableroDJ, tableroDPC);
        Input.askShipsJ(tableroJ, ships, tableroDJ); //Llamamos al metodo para poner los barcos del usuario.
        fillShipsPC(tableroPC, ships);//Llamamos al metodo para poner los barcos del PC

        //El juego ya puede comenzar
        while (!fin){//hasta que uno de los dos no hunda todos los barcos el juego no termina
            if (showPC){
                Display.showTablerosPC(tableroPC, tableroDPC);
            }
            Display.showTablerosJ(tableroJ, tableroDJ);
            Input.turnoJ(tableroPC, tableroDJ, hitsJ, tableroDPC, tableroJ, ships, showPC); //Llamamos al metodo del turno del jugador
            if (hitsJ[0]==Tools.sumArray(ships)){ //Si el jugador le ha dado a todas las posiciones con barco el juego habra terminado y él sera el ganador
                ganador = "Player";
                fin = true;
            }
            if (!fin){//Esta condicion se pone porque si el jugador en el anterior turno ha ganado el PC no puede disparar
                Input.turnoPC(tableroJ, tableroDPC, hitsPC, ships);//Llamamos al metodo del turno del PC
                if (hitsPC[0]==Tools.sumArray(ships)){ //Si el PC ha disparado a todas las posiciones con barco habra ganado el y el juego termianra
                    ganador = "PC";
                    fin = true;
                }
            }

        }
        //Se imprime el nombre del ganador, ya que en este juego no se puede empatar.
        System.out.println("******************************************** THE WINNER IS THE " + ganador + " ********************************************");



    }

    public static void fillShipsPC(char[][] tableroPC, int[] ships){
        //Este metodo rellena el tablero del PC con barcos de manera aleatoria
        boolean comprobar = false;
        int i = 0;
        int coordL = 0;
        int coordN = 0;
        char orientacion;
        while (i<ships.length){ //Buscamos orientaciones validas hasta que esten todos los barcos
            //Lo bueno de hacerlo con el PC es que no hace falta comprobar coordenadas porque los limites los establecemos nosotros con el Math.random
            coordL=(int)(Math.random()*10);
            coordN=(int)(Math.random()*10);
            int orientacion_r = (int)(Math.random()*2);
            if (orientacion_r==0){
                orientacion = 'V';
            } else {
                orientacion = 'H';
            }
            comprobar = Tools.checkOrientationShip(tableroPC, orientacion, coordL, coordN, ships[i]);//Llamamos al metodo para coomprobar que las coordenada y su orientacion son validas.
            if (comprobar){//Si son validas llamamos al metodo para poner el barco en cuestion y pasar al siguiente
                Tools.placeShip(tableroPC, orientacion, coordL, coordN, ships[i]);
                i++;
            }


        }
    }

    public static void fillTableros(char[][] tableroJ, char[][] tableroPC, char[][] tableroDJ, char[][] tableroDPC){
        //Rellenamos las matrices de los tableros con todo agua.
        for (int i=0; i<tableroDJ.length; i++){
            for (int j=0; j<tableroDJ[0].length; j++){
                tableroJ[i][j]='~';
                tableroPC[i][j]='~';
                tableroDJ[i][j]='~';
                tableroDPC[i][j]='~';
            }
        }
    }
}