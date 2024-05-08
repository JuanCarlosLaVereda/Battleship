public class Tools {

    public static boolean checkOrientationShip(char[][] tablero, char orientacion, int coordL, int coordN, int barco){
        //Con este metodo coomprobamos que la coordenada dada y la orientacion dada cumplen todas las normas.
        if (orientacion=='V'){ //Si es v la orientacion es vertical.
            if (coordN+(barco-1)>=tablero.length){ //Aqui miro a ver que el barco cabe en el tablero
                return false;
            }
            if (coordN-1 >= 0){ // Tengo que comprobar primero que la anterior posicion en los numeros existe en el tablero
                if (tablero[coordN-1][coordL] == 'B'){ // Y ahora compruebo que al exisitir esa posicion si hay o no un barco
                    return false;
                }
            }
            if (coordN+barco<tablero.length){ // Compruebo que la posicion posterior al final del barco existe
                if (tablero[coordN+(barco)][coordL] == 'B'){// Y lo mismo que antes, al saber que existe tengo que mirar si hay o no un barco.
                    return false;
                }
            }
            for (int i=coordN; i<coordN+barco; i++){ //Ahora recorro las posiciones vacias de una en una y mirando sus adyacentes
                if (tablero[i][coordL] == 'B'){ //Miro la posicion principal a ver si esta o no vacia
                    return false;
                }
                if (coordL-1 >= 0){ //Ahora miro la adyacente hacia la izquierda ya que estamos en la V de los numeros. Miro a ver si existe o no esa posicion en tablero
                    if (tablero[i][coordL-1] == 'B'){// Existe asi que miro a ver si hay o no un barquito.
                        return false;
                    }
                }
                if (coordL+1<tablero[0].length){//Ahora miro la adyacente hacia la derecha y si existe esa posicion en el tablero.
                    if (tablero[i][coordL+1] == 'B'){//Como existe miro e aver si hay un barco o no.
                        return false;
                    }
                }
            }
        } else {
            if (coordL+(barco-1)>=tablero[0].length){//Ahora ya sabemos que la posicion es H de horizontal, entonces miramos las columnas osea las letras hacia la derecha. Miro a ver si cabe o no en el tablero
                return false;
            }
            if (coordL-1 >= 0){//Miro a ver que la anterior a la posicion inical existe en el tablero.
                if (tablero[coordN][coordL-1] == 'B'){//Si existe miro a ver si hay o no un barco.
                    return false;
                }
            }
            if (coordL+barco < tablero[0].length){//Miro a ver que la posterior a la ultima existe.
                if (tablero[coordN][coordL+barco] == 'B'){//Como existe miro a ver si hay o no un barco
                    return false;
                }
            }
            for (int i=coordL; i<coordL+barco; i++){//Ahora recorro las posiciones vacias de una en una y mirando sus adyacentes
                if (tablero[coordN][i] == 'B'){
                    return false;
                }
                if (coordN-1 >= 0){//Compruebo si existe la casilla que hay justo encima
                    if (tablero[coordN-1][i] == 'B'){//Si existe miro a ver si hay un barco
                        return false;
                    }
                }
                if (coordN+1<tablero.length){//Compruebo que la casilla de abajo existe
                    if (tablero[coordN+1][i] == 'B'){//Si existe miro a ver si hay un barco
                        return false;
                    }
                }

            }
        }

        //Si ha cumplido todas las normas retornamos true para que de paso a la colocacion del barco
        return true;
    }

    public static void placeShip(char[][] tablero, char orientacion, int coordL, int coordN, int barco){
        //Este metodo pone un barco en la matriz segun su orientacion, hay que tener en cuenta que las coordenadas ya se han comprobado.
        if (orientacion=='V'){
            for (int i=0; i<barco; i++){
                tablero[i+coordN][coordL] = 'B';
            }
        } else {
            for (int i=0; i<barco; i++){
                tablero[coordN][i+coordL] = 'B';
            }
        }
    }

    public static boolean comprobarCoord(String coord){
        //Este metodo comprueba que la coordenada dada es una coordenada y ademas esta en el rango de la matriz.
        if (coord.length()>2 || coord.length()==1){
            return false;
        } else if (coord.charAt(1)>='A' && coord.charAt(1)<='J'){
            if (coord.charAt(0)<='9'){
                return true;
            }
        }

        return false;

    }

    public static boolean shootJ(char[][] tableroPC, char[][] tableroDJ, int coordL, int coordN, int[] hitsJ, char[][] tableroDPC, char[][] tableroJ, boolean showPC){
        //Este metodo es la accion de dispara al tablero del PC
        if (tableroPC[coordN][coordL]=='~'){//Si es agua cambiará ~ por A y ademas el turno se habra terminado
            System.out.println("WATER!");
            tableroPC[coordN][coordL]='A';
            tableroDJ[coordN][coordL]='A';
            return true;
        } else if (tableroPC[coordN][coordL]=='B'){//Si hay un barco sin tocar cambiará B por X pero el turno aun no habra terminado
            tableroPC[coordN][coordL]='X';
            tableroDJ[coordN][coordL]='X';
            hitsJ[0]++; //Este valor aumenta ya que se le ha disparado a un barco
            if (showPC){
                Display.showTablerosPC(tableroPC, tableroDPC);
            }
            Display.showTablerosJ(tableroJ, tableroDJ);
            System.out.println("HII!");
            return false;
        } else if (tableroPC[coordN][coordL]=='A'){//Si ya se habia disparado en esa coordenada se le informara al usuario y el turno aun no habra terminado.
            System.out.println("The coord you shoot is alredy water, try again.");
            return false;
        }
        System.out.println("The coord you shoot is alredy a hit, try again.");
        return false;
    }

    public static boolean shootPC(char[][] tableroJ, char[][] tableroDPC, int coordL, int coordN, int [] hitsPC, int[] ships){
        ///Este metodo realiza el disparo del PC (igual que el turnoJ)
        if (tableroJ[coordN][coordL]=='~'){
            System.out.println("THE PC MISSED!");
            tableroJ[coordN][coordL]='A';
            tableroDPC[coordN][coordL]='A';
            return true;
        } else if (tableroJ[coordN][coordL]=='B'){
            tableroJ[coordN][coordL]='X';
            tableroDPC[coordN][coordL]='X';
            hitsPC[0]++;
            System.out.println("THE PC HIT ONE OF YOUR SHIPS :O!");
            if (hitsPC[0]==sumArray(ships)){
                return true;
            }
            return false;
        } else if (tableroJ[coordN][coordL]=='A'){
            return false;
        }
        return false;
    }

    public static int sumArray(int[] array){
        //Este metodo suma los valores de un array de enteros.
        int suma=0;
        for (int i=0; i<array.length; i++){
            suma += array[i];
        }
        return suma;
    }
}
