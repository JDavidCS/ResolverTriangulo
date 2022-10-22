/* 

  Matriz de datos 3x2:
    Angulo  Lado
    A       a
    B       b
    C       c

 */

package resolverTriangulo.src;

import javax.swing.JOptionPane;


public class Calculos {
    private double datos[][];
    private int i, j, u;
    private int lineaOcupada;
    private int angulosOcupados[] = new int [3];
    private int anguloVacio;
    private int ladosOcupados[] = new int [2];
    private boolean NaN;

    public Calculos(double datos[][]){
        this.datos = datos;
    }
    public void resolver(){
        for(u = 0; u < 3; u++){
            NaN = false;

            if(tres_lados()){
                datos[0][0] = coseno_angulo(datos[0][1], datos[1][1], datos[2][1]);
                datos[1][0] = coseno_angulo(datos[1][1], datos[0][1], datos[2][1]);
                datos[2][0] = coseno_angulo(datos[2][1], datos[0][1], datos[1][1]);
            }
            if(dos_angulos()){
                datos[anguloVacio][0] = (180-(datos[angulosOcupados[0]][0]+datos[angulosOcupados[1]][0]));
            }
            if(anguloYLado()){
                for(i = 0; i < 3; i++){
                    if(i == lineaOcupada){/* no hacer nada */}
                    else if(datos[i][0] == 0 && datos[i][1] == 0){/* no hacer nada */}
                    else if(datos[i][0] != 0 && datos[i][1] != 0){/* no hacer nada */}
                    else{
                        if(datos[i][0] != 0){
                            datos[i][1] = seno_lado(datos[lineaOcupada][0], datos[i][0], datos[lineaOcupada][1]);
                            break;
                        }
                        else if(datos[i][1] != 0){
                            datos[i][0] = seno_angulo(datos[lineaOcupada][0], datos[lineaOcupada][1], datos[i][1]);
                            break;
                        }
    
                    }
                }
            }
            else{
                j = 0;
                for(i = 0; i < 3; i++){
                    if(datos[i][0] != 0){
                        lineaOcupada = i;
                    }
                }
                for(i = 0; i < 3; i++){
                    if(i == lineaOcupada){
                        
                    }
                    else{
                        ladosOcupados[j] = i;
                        j++;
                    }
                }
                datos[lineaOcupada][1] = coseno_Lado(datos[lineaOcupada][0], datos[ladosOcupados[0]][1], datos[ladosOcupados[1]][1]);
            }
        }
        if(NaN){
            JOptionPane.showMessageDialog(null, "ERROR \nCon los datos dados no se puede formar un resolverTriangulo\n");
        }
    }

    // Evaluacion de datos
    private boolean tres_lados(){
        boolean lados = true;
        for(i = 0; i < 3; i++){
            if(datos[i][1] == 0){
                lados = false;
            }
        }
       return lados; 
    }
    private boolean dos_angulos(){
        boolean dosAngulos = false;
        j = 0;
        for(i = 0; i < 3; i++){
            if(datos[i][0] != 0){
                angulosOcupados[j] = i;
                j++;
            }
            else{
                anguloVacio = i;
            }
        }
        if(j == 2){
            dosAngulos = true;
        }
        return dosAngulos;
    }
    public boolean datos_completos(){
        boolean datosCompletos = true;
        
        for(i = 0; i <3; i++){
            for(j = 0; j < 2; j++){
                if(datos[i][j] == 0){
                    datosCompletos = false;
                }
                if(Double.isNaN(datos[i][j])){
                    NaN = true;
                }
            }
        }
        return datosCompletos;
    }
    private boolean anguloYLado(){
        boolean filaLlena = false;
        for(i = 0; i < 3; i++){
            if(datos[i][0] != 0 && datos[i][1] != 0){
                lineaOcupada = i;
                filaLlena = true;
                break;
            }
        }
        return filaLlena;
    }



    ///  CALCULO DE LOS TEOREMAS
    public double seno_angulo(double angulo1, double lado1, double lado2){
        double angulo2;
        angulo2 = Math.toDegrees(Math.asin((Math.sin(Math.toRadians(angulo1))*lado2)/lado1));
        if(Double.isNaN(angulo2)){
            NaN = true;
        }
        return angulo2;
        
    }
    public double seno_lado(double angulo1, double angulo2, double lado1){
        double lado2;
        lado2 = ((Math.sin(Math.toRadians(angulo2))*lado1)/Math.sin(Math.toRadians(angulo1)));
        if(Double.isNaN(lado2)){
            NaN = true;
        }
        return lado2;
        
    }

    public double coseno_Lado(double angulo1, double lado2, double lado3){
        double lado1;
        lado1 = Math.sqrt((Math.pow(lado2, 2)+Math.pow(lado3, 2))-(2*(lado2*lado3)*Math.cos(Math.toRadians(angulo1))));

        if(Double.isNaN(lado1)){
            NaN = true;
        }
        return lado1;                                                                                                                                                                                                                                                                                                                       
    }

    public double coseno_angulo(double lado1, double lado2, double lado3){
        double angulo1;
        angulo1 = Math.toDegrees(Math.acos(((Math.pow(lado2, 2)+Math.pow(lado3, 2))-(Math.pow(lado1, 2)))/(2*(lado2*lado3))));
        if(Double.isNaN(angulo1)){
            NaN = true;
        }
        return angulo1;
    }

    public double[][] getResultados(){
        double ValorFinal [][] = new double[3][2];
        for(i = 0; i < 2; i++){
            for(j = 0; j < 3; j++){
                ValorFinal[j][i] = Math.round(datos[j][i]*10000);
                ValorFinal[j][i] /= 10000;
            }
        }
        return ValorFinal;
    }
}
