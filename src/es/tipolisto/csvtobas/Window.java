package es.tipolisto.csvtobas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;



public class Window extends JFrame {


	private JPanel contentPane;
	private File file;
	private JButton btnSeleccionaElArchivo, btnConvertir;
	private JLabel labelLocalizacion;
	private JTextField jTextFieldLinea;

	/**
	 * Create the frame.
	 */
	public Window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("MSX Murcia");
		
		
inicializar();
		
		
		//Events
		btnSeleccionaElArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser=new JFileChooser(System.getProperty("user.dir"));
				jFileChooser.setDialogTitle("Selecciona un archivo");
				int result=jFileChooser.showSaveDialog(null);
				if(result==JFileChooser.APPROVE_OPTION) {
					file=jFileChooser.getSelectedFile();
					labelLocalizacion.setText(file.getPath().toString());
				}
			}
		});
		
		btnConvertir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				convertir();
			}
		});
		
	}
	
	private void inicializar() {
		/***
		 * BorderLayout.NORTH
		 */
		JLabel lblAsmToBas = new JLabel("CSV TO BAS");
		lblAsmToBas.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAsmToBas, BorderLayout.NORTH);

		/***
		 * BorderLayout.WEST
		 */
		btnConvertir = new JButton("Convertir");
		contentPane.add(btnConvertir, BorderLayout.WEST);
		
		/***
		 * BorderLayout.CENTER->JPanelCentral
		 */
		JPanel jPanelCentral=new JPanel();
		jPanelCentral.setLayout(new GridLayout(2,1));
		
		btnSeleccionaElArchivo = new JButton("Selecciona el archivo");
		jPanelCentral.add(btnSeleccionaElArchivo);
		
		JPanel jPanelCentralArriba=new JPanel();
		jPanelCentralArriba.setLayout(new FlowLayout());
		JLabel labelNumeroLinea = new JLabel("La linea comienza en:");
		jPanelCentralArriba.add(labelNumeroLinea);
		
		jTextFieldLinea=new JTextField(String.valueOf(Constants.contadorLinea));
		jTextFieldLinea.setHorizontalAlignment(SwingConstants.LEFT);
		jPanelCentralArriba.add(jTextFieldLinea);
		jPanelCentral.add(jPanelCentralArriba);
		
		contentPane.add(jPanelCentral, BorderLayout.CENTER);
		/***
		 * Final JPanelCentral
		 */
		
		
		/**
		 * BorderLayout.SOUTH->JPanel abajo
		 */
		JPanel jPanelAbajo=new JPanel();
		jPanelAbajo.setLayout(new FlowLayout());
		labelLocalizacion = new JLabel("Localizacion");
		labelLocalizacion.setHorizontalAlignment(SwingConstants.CENTER);
		jPanelAbajo.add(labelLocalizacion);	
		contentPane.add(jPanelAbajo, BorderLayout.SOUTH);
		btnConvertir.setHorizontalAlignment(SwingConstants.CENTER);
		/**
		 * Final JPanelAbajo
		 */
	}
	
	
	
	
	
	
	private void convertir() {
		int contadorLinea=Constants.contadorLinea;
		String filePath="";
		if(file==null) {
			JOptionPane.showMessageDialog(null, "Tienes que seleccionar un archivo"); 
		}else {
			try {
				filePath=file.getPath().toString();
				contadorLinea=Integer.parseInt(jTextFieldLinea.getText());
				Conversor conversor=new Conversor(filePath, contadorLinea);
				conversor.convertir();
				
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Tienes introducir un número de línea válido"); 
			}
			
		}
	}

}
