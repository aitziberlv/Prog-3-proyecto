package Ventanas;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Ventana_Pagar extends JFrame {

	private JPanel pnl_center;
	private JPanel pnl_titulo;

	private JLabel NumeroTarjeta;
	private JLabel FechaVencimiento;
	private JLabel CVV;
	private JLabel Direccion;
	private JLabel Titulo;
	private JLabel Descripcion;
	
	private JTextField aNumeroTarjeta;
	private JTextField aFechaVencimiento;
	private JTextField aCVV;
	private JTextField aDireccion;
	
	private JButton Pagar;
	
	public Ventana_Pagar() {
		configurarVentana();
		inicilizarVentana();
	}
	
	private void configurarVentana() {
        this.setTitle("PAGAR");                  
        this.setSize(500, 500);                                 
        this.setLocationRelativeTo(null);                       
        this.setLayout(null);                                   
        this.setResizable(true);                               
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
    }
	
	private void inicilizarVentana() {
		this.setSize (500,500);
		this.setTitle("PAGAR");
		this.setLayout(new GridLayout(3,1));
		
		pnl_center = new JPanel ();
		pnl_center.setLayout(new GridLayout(2,2));
		pnl_titulo = new JPanel();
		
		Descripcion = new JLabel ("Ingrese los datos de su tarjeta: ");
		NumeroTarjeta = new JLabel ("NUMERO DE TARJETA");
		FechaVencimiento = new JLabel ("FECHA DE VENCIMIENTO");
		CVV = new JLabel ("CVV");
		Direccion = new JLabel ("Direccion de Facturacion");
		
		aNumeroTarjeta = new JTextField(16);
		aFechaVencimiento = new JTextField(4);
		aCVV = new JTextField(3);
		aDireccion = new JTextField(50);
		
		NumeroTarjeta.setHorizontalAlignment(JLabel.CENTER);
		FechaVencimiento.setHorizontalAlignment(JLabel.CENTER);
		CVV.setHorizontalAlignment(JLabel.CENTER);
		Direccion.setHorizontalAlignment(JLabel.CENTER);
		
		Pagar = new JButton ("Pagar");
		JPanel pnl_btn = new JPanel ();
		pnl_btn.setLayout(new FlowLayout());
		pnl_btn.add(Pagar);
		Pagar.setForeground(Color.BLACK);
		Pagar.setBackground(Color.white);
		Border line = new LineBorder(Color.BLACK);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(line, margin);
		Pagar.setBorder(compound);
		
		pnl_center.add(Descripcion);
		pnl_center.add(NumeroTarjeta);
		pnl_center.add(aNumeroTarjeta);
		pnl_center.add(FechaVencimiento);
		pnl_center.add(aFechaVencimiento);
		pnl_center.add(CVV);
		pnl_center.add(aCVV);
		pnl_center.add(Direccion);
		pnl_center.add(aDireccion);
		
		this.add(pnl_titulo);
		this.add(pnl_center);
		this.add(pnl_btn);
		
		Pagar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) { 
				String NumeroTarjeta= "";
				String FechaVencimiento= "";
				String CVV= "";
				String Direccion= "";

				NumeroTarjeta=aNumeroTarjeta.getText();
				FechaVencimiento=aFechaVencimiento.getText();
				CVV=aCVV.getText();
				Direccion=aDireccion.getText();

				if(NumeroTarjeta.equals("1") && FechaVencimiento.equals("2") && CVV.equals("3") && Direccion.equals("3")){

				
					JOptionPane.showMessageDialog( null, "Gracias por su compra en Deusto outlet");
					getFrame().dispose();
					
				
				}else{

					JOptionPane.showMessageDialog( null, "ERROR"); 
					
				}

				}

		});
		
	}
		public static void main(String[] args) {
			Ventana_Pagar ventana_Pagar = new Ventana_Pagar();
			ventana_Pagar.setExtendedState(Ventana_Pagar.MAXIMIZED_BOTH);
			
			ventana_Pagar.setVisible(true);
			ventana_Pagar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
