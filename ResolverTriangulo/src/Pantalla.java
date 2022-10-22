package resolverTriangulo.src;

import java.awt.Color;
import java.awt.Font;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import resolverTriangulo.src.Components.CajaDatos;
import resolverTriangulo.src.Components.GridDatos;

public class Pantalla extends JFrame{
    JPanel fondo, display, bottomPanel;
    KeyListener input;
    GridDatos gridEntrada, gridResultado;
    ActionListener Confirm;
    JButton Boton;
    Calculos solucion;
    boolean vacio;
    JTextField etiqueta[];
    CajaDatos entrada[];
    int i, j;


    public Pantalla(){
        setIconImage(new ImageIcon(getClass().getResource("/resolverTriangulo/icon/logoTriangule.png")).getImage());
        setTitle("Resolver Triangulo");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        componentes();
    }

    private void componentes(){
        panelDatos();
        panelBoton();
        boton();
        cajas();
        cuadrosResultados();
        confirm();
    }

    private void panelDatos(){
        display = new JPanel();

        display.setLayout(new FlowLayout());

        gridEntrada = new GridDatos("Datos");
        gridResultado = new GridDatos("Resultados");

        display.add(gridEntrada);
        display.add(gridResultado);

        add(display);
    }

    private void panelBoton(){
        bottomPanel = new JPanel();

        display.setLayout(new FlowLayout());

        add(bottomPanel);
    }

    private void boton(){
        Boton = new JButton();
        // Boton.setBounds(175, 120, 150, 30);
        Boton.setSize(150, 30);
        Boton.setText("calcular");
        Boton.setFont(new Font("Liberation Serif", Font.PLAIN, 20));
        bottomPanel.add(Boton);
    }

    private void cajas(){
        entrada = new CajaDatos[6];
        for(i = 0; i < 6; i++){
            entrada[i] = new CajaDatos();
        }

        gridEntrada.add(new JLabel("Angulos"));
        gridEntrada.add(new JLabel("Lados"));
        gridEntrada.add(new JLabel("A"));
        gridEntrada.add(new JLabel("a"));
        gridEntrada.add(entrada[0]);
        gridEntrada.add(entrada[3]);
        gridEntrada.add(new JLabel("B"));
        gridEntrada.add(new JLabel("b"));
        gridEntrada.add(entrada[1]);
        gridEntrada.add(entrada[4]);
        gridEntrada.add(new JLabel("C"));
        gridEntrada.add(new JLabel("c"));
        gridEntrada.add(entrada[2]);
        gridEntrada.add(entrada[5]);
    }

    private void cuadrosResultados(){
        etiqueta = new JTextField[6];
        for(i = 0; i < 6; i++){
            etiqueta[i] = new JTextField(8);
        }

        gridResultado.add(new JLabel("Angulos"));
        gridResultado.add(new JLabel("Lados"));
        gridResultado.add(new JLabel("A"));
        gridResultado.add(new JLabel("a"));
        gridResultado.add(etiqueta[0]);
        gridResultado.add(etiqueta[3]);
        gridResultado.add(new JLabel("B"));
        gridResultado.add(new JLabel("b"));
        gridResultado.add(etiqueta[1]);
        gridResultado.add(etiqueta[4]);
        gridResultado.add(new JLabel("C"));
        gridResultado.add(new JLabel("c"));
        gridResultado.add(etiqueta[2]);
        gridResultado.add(etiqueta[5]);

    }


    private void confirm(){

        Confirm = new ActionListener(){
            double dato;
            @Override
            public void actionPerformed(ActionEvent e){
                boolean conLados = false;
                int nDatos = 0;

                for(i = 0; i < 6 ; i++){
                    dato = 0;
                    if(!entrada[i].getText().isEmpty()){
                        dato = Double.parseDouble(entrada[i].getText());
                    }
                    if(!entrada[i].getText().isEmpty() || dato != 0){
                        if(i > 2){
                            nDatos++;
                            conLados = true;
                        }
                        else{
                            nDatos++;
                        }
                    }
                }

                // primero, verificar que los angulos no excedan de 180
                double verificar_angulos = 0;
                boolean tresLados = true;
                for(i = 0; i < 3; i++){
                    dato = 0;

                    if(!entrada[i].getText().isEmpty()){
                        dato = Double.parseDouble(entrada[i].getText());
                    }
                    if(entrada[i].getText().isEmpty() || dato == 0){
                        tresLados = false;
                    }
                    if(!entrada[i].getText().isEmpty() || dato != 0){
                        verificar_angulos += Double.parseDouble(entrada[i].getText());
                    }

                }

                if(verificar_angulos >= 180 && !tresLados){
                    JOptionPane.showMessageDialog(null, "Los angulos no pueden sumar mas de 180");
                }
                else if(verificar_angulos != 180 && tresLados){
                    JOptionPane.showMessageDialog(null, "La suma de los angulos de un triangulo debe ser igual a 180 ");
                }
                // verificar numero de datos y por lo menos un lado
                else if(nDatos < 3){
                    JOptionPane.showMessageDialog(null, "se necesitan por lo menos 3 datos");
                }
                else if(!conLados){
                    JOptionPane.showMessageDialog(null, "se necesita por lo menos un lado");
                    for(i = 3; i < 6; i++){
                        //marcar con rojo los textfield de los lados
                        entrada[i].setBackground(new Color(255, 172, 153));
                    }
                }
                else if(nDatos>2 && conLados){
                    double datos [][] = new double[3][2];

                    for(i = 0; i < 6; i++){
                        if(i < 3){
                            if(entrada[i].getText().isEmpty()){
                                datos[i][0] = 0;
                            }
                            else{
                                datos[i][0] = Double.parseDouble(entrada[i].getText());
                            }
                        }
                        if(i > 2){
                            if(entrada[i].getText().isEmpty()){
                                datos[(i-3)][1] = 0;
                            }
                            else{
                                datos[(i-3)][1] = Double.parseDouble(entrada[i].getText());
                            }
                        }
                    }

                    solucion = new Calculos(datos);

                    for(i = 3; i < 6; i++){
                        entrada[i].setBackground(Color.white);
                    }

                    solucion.resolver();
                    mostrarResultados();
                }
            }
        };
        Boton.addActionListener(Confirm);
    }

    private void mostrarResultados(){
        double rFinal[][];

        rFinal = solucion.getResultados();

        for(int i = 0; i < 6; i++){
            if(i < 3){
                etiqueta[i].setText(String.valueOf(rFinal[i][0]));
            }
            else{
                etiqueta[i].setText(String.valueOf(rFinal[(i-3)][1]));
            }
        }
    }
}