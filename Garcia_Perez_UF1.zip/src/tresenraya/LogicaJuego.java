package tresenraya;

import java.awt.Color;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogicaJuego {
    int turno, pX, pO; // Turno del jugador
    boolean habilitado; // Habilita y deshabilita el tablero

    /**
     * Inicializaremos el juego con las siguientes variables_
     * @param turno (Nos indicará el turno del jugador: 0 - X, 1 - O)
     * @param pX (Contiene el valor total de las victorias de este jugador)
     * @param pO (Contiene el valor total de las victorias de este jugador)
     */
    public LogicaJuego(int turno, int pX, int pO) {
        this.turno = turno;
        this.pX = pX;
        this.pO = pO;
    }

    /**
     * Obtener turno
     * @return 
     */
    public int getTurno() {
        return turno;
    }

    /**
     * Insertar turno
     * @param turno 
     */
    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getpX() {
        return pX;
    }

    public void setpX(int pX) {
        this.pX = pX;
    }

    public int getpO() {
        return pO;
    }

    public void setpO(int pO) {
        this.pO = pO;
    }
    
    /**
     * Llamaremos a este método para cambiar de turno
     */
    public void cambioTurno(){
        // Inserta código aquí...
        if (this.turno == 0){
            this.turno = 1;
        }else{
            this.turno = 0;
        }
        
    }
    
    /**
     * Comprobar si se ha conseguido un tres en raya, 
     * en caso que se haya conseguido devolverá 1, en caso contrario retorna 0 y continúa el juego
     * @param matriz (Tablero de juego)
     * @return 
     */
    public int comprobarJuego(int matriz[][]){
        // Inserta código aquí...
        boolean resultado = true;
        
        // Comprobar si existe tres en raya
        // Comprobar horizontal
        for (int i = 0; i < matriz.length; i++)
        {
            resultado = true;
            
            for (int j = 1; j < matriz[i].length; j++)
            {
                if(matriz[i][j] != matriz [i][0]){
                    resultado = false;
                    break;
                }
            }
            if (resultado == true){
                return 1;
            }
        }
        
        //Comprobar vertical
        for (int j = 0; j < matriz.length; j++)
        {
            resultado = true;
            
            for (int i = 1; i < matriz.length; i++)
            {
                if(matriz[i][j] != matriz [0][j]){
                    resultado = false;
                    break;
                }
            }
            
            if (resultado == true){
                return 1;
            }
        }
        
        //Comprobar en diagonal principal
        resultado = true;
        for (int i = 1; i < matriz.length; i++)
        {
            if (matriz[i][i] != matriz[0][0]){
                resultado = false;
                break;
            }
        }
        if (resultado == true){
            return 1;
        }
        
        //Comprobar en diagonal inversa
        resultado = true;
        for (int i = 1; i < matriz.length; i++)
        {
            if(matriz[i][matriz.length-1-i] != matriz[0][matriz.length-1]){
                resultado = false;
                break;
            }
        }
        if (resultado == true){
            return 1;
        }
        
        // Si no hay tres en raya
        return 0;
    }
    
    /**
     * Deshabilitará el botón para evitar que se vuelva a posicionar una ficha en ese hueco
     * @param bt (Botón seleccionado)
     * @param x (Posición x en el tablero)
     * @param y (Posición y en el tablero)
     * @param matriz (Tablero de juego)
     * @param jp (Panel dónde se sitúa el tablero de juego)
     * @param lX (JLabel del jugador X)
     * @param lO (JLabel del jugador O)
     * @return 
     */
    public int tiradaJugador(javax.swing.JButton bt, int x, int y, int matriz[][], javax.swing.JPanel jp, javax.swing.JLabel lX, javax.swing.JLabel lO){
        // Inserta código aquí...
        
        // Deshabilita el botón
        bt.setEnabled(false);
        bt.setContentAreaFilled(true);
        
        // Insertar la ficha en el botón
        this.ponerFicha(matriz, x, y, bt);
               
        // Comprobar si se ha ganado la partida
        if (this.comprobarJuego(matriz)== 1) {
            //incrementar puntuación de jugador
            ganador(lX, lO);
            
            // Deshabilitar tablero
            habilitado = false;
            habilitarTablero(jp);
            resetColor(jp);
            
            
        } else {
            //cambiar de turno
            this.cambioTurno();
        } 

         return 0;
    }
    
public void resetColor( javax.swing.JPanel jp){
        // Inserta código aquí...
        // Bloquea todos los elementos del JPanel
        Component[] c = jp.getComponents();
        for (Component component : c){
            component.setBackground(Color.white);
        }
    }    
    
    
    /**
     * Actualizar la puntuación de cada jugador al ganar la partida
     * Al finalizar el incremento de puntuación es necesario cambiar de turno
     * @param lX (JLabel del jugador X)
     * @param lO (JLabel del jugador O)
     */
    public void ganador(javax.swing.JLabel lX, javax.swing.JLabel lO){
        // Inserta código aquí...
        
        // Incrementa jugador ganador e inserta resultado en jLabel    
        if (this.turno == 0){ //Incrementar puntuación de X
            this.setpX(this.getpX()+1);
            lX.setText(String.valueOf(this.getpX()));
        } else {               //Incrementar puntuación de O
            this.setpO(this.getpO()+1); 
            lO.setText(String.valueOf(this.getpO()));
        }
        //Cambiar de turno para que en la siguiente partida empiece el otro jugador
        this.cambioTurno();
    }
    
    /**
     * Habilitará o deshabilitará el tablero dependiendo del estado de la variable habilitado
     * @param jp  (Panel dónde se sitúa el tablero de juego)
     */
    public void habilitarTablero( javax.swing.JPanel jp){
        // Inserta código aquí...
        // Bloquea todos los elementos del JPanel
        Component[] c = jp.getComponents();
        for (Component component : c){
            component.setEnabled(habilitado);
        }
        jp.setEnabled(habilitado); 
    }
    
    /**
     * Insertaremos la ficha en la posición correspondiente de la matriz
     * Llamaremos al método pintarFicha
     * @param matriz (Tablero de juego)
     * @param t (Turno)
     * @param x (Posición x en el tablero)
     * @param y (Posición y en el tablero)
     * @param bt (Botón pulsado)
     */
    public void ponerFicha(int matriz[][], int x, int y, javax.swing.JButton bt){
        // Inserta código aquí...  
        // Insertar ficha en la posición de la matriz
        matriz[x][y] = this.turno;
        // Llamamos al método para pintar la ficha
        this.pintarFicha(bt);
    }
    
    /**
     * Pintará la ficha en el tablero de juego visual, es decir, en el botón
     * @param bt (Botón pulsado)
     */
    private void pintarFicha(javax.swing.JButton bt){
        // Inserta código aquí...
        
        if (this.turno == 0){               // Si el turno es de 0 pintará una X en rojo
            bt.setForeground(Color.red);
            bt.setText("X");
            
        }else{                              // Si el turno es de 1, pintará una O en azul 
            bt.setForeground(Color.blue);
            bt.setText("O");
        }
    }
    
    /**
     * Inicializa una nueva partida, reinicia la matriz (Tablero de juego) y habilita el tablero
     * 
     * ¡¡¡¡No es necesario modificar este método!!!!.
     * 
     * @param matriz (Tablero de juego)
     * @param jp (Panel dónde se sitúa el tablero de juego)
     */
    public void iniciarPartida(int matriz[][], javax.swing.JPanel jp){
        // Rellenamos la matriz por primera vez, evitando que se repitan los números
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                matriz[x][y]=(x+10)*(y+10);
            }
        }

        // Habilitar tablero
         habilitado = true;
         habilitarTablero(jp);
    }
}
