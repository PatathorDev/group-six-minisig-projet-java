package com.minisig.userinterfacelayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Component;

import javax.swing.Box;

import java.awt.GridLayout;

import javax.swing.SwingConstants;

import com.minisig.businesslayer.processus.*;
import com.minisig.businesslayer.table.*;


import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModeConsultation extends JFrame {

	private JPanel contentPane;
	JPanel panelMAP;
	JComboBox comboBoxLieu;
	JComboBox comboBoxParcours;
	JButton btnGoLieu;
	JButton btnGoParcours;
	JButton btnPrevious;
	JButton btnNext;
	
	boolean lieuSelected = false;
	boolean parcoursSelected = false;
	
	//Fonction main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModeConsultation frame = new ModeConsultation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Constructeur
	public ModeConsultation() {
		newComponents();
		newListeners();
	}
	
	public void newComponents()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCenter = new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new BorderLayout(0, 0));
		
		JPanel panelPOI = new JPanel();
		panelPOI.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCenter.add(panelPOI, BorderLayout.EAST);
		panelPOI.setLayout(new BorderLayout(0, 0));
		
		JPanel panelPoiNORTH = new JPanel();
		panelPOI.add(panelPoiNORTH, BorderLayout.NORTH);
		panelPoiNORTH.setLayout(new BoxLayout(panelPoiNORTH, BoxLayout.PAGE_AXIS));
		
		JPanel panelPusher = new JPanel();
		panelPoiNORTH.add(panelPusher);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(150);
		panelPusher.add(horizontalStrut_1);
		
		JPanel panelNavigate = new JPanel();
		panelPoiNORTH.add(panelNavigate);
		
		btnPrevious = new JButton("<");
		
		panelNavigate.add(btnPrevious);
		
		btnNext = new JButton(">");
		
		panelNavigate.add(btnNext);
		
		JPanel panelPoiCENTER = new JPanel();
		panelPOI.add(panelPoiCENTER, BorderLayout.CENTER);
		panelPoiCENTER.setLayout(new BoxLayout(panelPoiCENTER, BoxLayout.PAGE_AXIS));
		
		JPanel panelTitle = new JPanel();
		panelPoiCENTER.add(panelTitle);
		
		JLabel lblTitre = new JLabel("Titre");
		lblTitre.setVerticalAlignment(SwingConstants.TOP);
		lblTitre.setHorizontalAlignment(SwingConstants.LEFT);
		panelTitle.add(lblTitre);
		
		JPanel panelDescription = new JPanel();
		panelPoiCENTER.add(panelDescription);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setVerticalAlignment(SwingConstants.TOP);
		lblDescription.setHorizontalAlignment(SwingConstants.LEFT);
		panelDescription.add(lblDescription);
		
		JPanel panelImage = new JPanel();
		panelPoiCENTER.add(panelImage);
		
		JPanel panelPoiSOUTH = new JPanel();
		panelPOI.add(panelPoiSOUTH, BorderLayout.SOUTH);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panelPoiSOUTH.add(verticalStrut);
		
		panelMAP = new JPanel()
		{
			//Taille d'origine de la carte.
			int originWidht = 512;
			int originHeight = 408;
			
			//PARTIE DRAW
			public void paintComponent(Graphics g){
				try 
				{
					//Cr�ation de l'objet Image
				Image img = ImageIO.read(new File("C:\\Users\\Nico\\Desktop\\stationnement-payant-paris.jpg"));
					//Draw l'image avec comme taille, la taille du panel
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
				} 
				catch (IOException e) 
				{
				e.printStackTrace();
				}
				
				//Draw un rond (position X, position Y, largeur, hauteur);
				g.fillOval(300 * getWidth()/originWidht, 300 * getHeight()/originHeight, 10, 10);
			}
		};
		
		
		panelMAP.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCenter.add(panelMAP, BorderLayout.CENTER);
		
		Component horizontalStrut = Box.createHorizontalStrut(400);
		panelMAP.add(horizontalStrut);

		JPanel panelNorth = new JPanel();
		panelNorth.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new BorderLayout(0, 0));
		
		JPanel panelMode = new JPanel();
		panelNorth.add(panelMode, BorderLayout.NORTH);
		
		JLabel lblModeConsultation = new JLabel("Mode Consultation");
		panelMode.add(lblModeConsultation);
		
		JPanel panelMenu = new JPanel();
		panelNorth.add(panelMenu, BorderLayout.SOUTH);
		
		comboBoxLieu = new JComboBox();
		comboBoxLieu.setModel(new DefaultComboBoxModel());
		comboBoxLieu.addItem("Choisir Lieu");
		panelMenu.add(comboBoxLieu);
		FillcomboBoxLieu(comboBoxLieu);
		
		btnGoLieu = new JButton("Go");
		
		btnGoLieu.setEnabled(false);
		panelMenu.add(btnGoLieu);
		
		comboBoxParcours = new JComboBox();
		comboBoxParcours.setModel(new DefaultComboBoxModel());
		comboBoxParcours.setEnabled(false);
		panelMenu.add(comboBoxParcours);
		
		btnGoParcours = new JButton("Go");
		
		panelMenu.add(btnGoParcours);
	}
	
	
	public void FillcomboBoxLieu(JComboBox comboBoxLieu){
		List<Lieu> lieus = null;
		LieuProcessus li = new LieuProcessus();
		lieus = li.ListAllLieu();
		for(Lieu e : lieus){
			comboBoxLieu.addItem(e.getNameLieu());
			
		}
	}
	public void FillcomboBoxParcours(JComboBox comboBoxParcours, String nameLieu){
		List<Parcours> parcours = null;
		LieuProcessus li = new LieuProcessus();
		parcours = li.ListAllParcoursOfLieu(nameLieu);
		for(Parcours e: parcours) comboBoxParcours.addItem(e.getLibelleParcours());
	}
	public String FillCarteLieu(String nameLieu){
		String url;
		LieuProcessus li = new LieuProcessus();
		url = li.getImageForLieu(nameLieu);
		return url;
		}
	public List<Poi> getPoiInLieu(String nameLieu){
		List<Poi> pois = null;
		PoiProcessus li = new PoiProcessus();
		pois = li.listAllPoiForLieu(nameLieu);
		return pois;
		// Renvoie List avec tout les object POI du lieu
	}
	public List<Poi> getPoiInParcours(String nameParcours){
		List<Poi> pois = null;
		PoiProcessus li = new PoiProcessus();
		pois = li.listAllPoiForParcours(nameParcours);
		return pois;

	}
	public void newListeners()
	{
	//LISTENERS - ItemStateChanged
		//ComboBoxLieu
		comboBoxLieu.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
					lieuSelected = true;
				System.out.println(lieuSelected);
				if(comboBoxLieu.getSelectedItem().toString() != "Choisir Lieu")btnGoLieu.setEnabled(true);
				else btnGoLieu.setEnabled(false);
			}
		});
		
		//ComboBoxParcours
		comboBoxParcours.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			}
		});
		
	//LISTENERS - ActionPerformed (Bouton)
		//Bouton Go Lieu
		btnGoLieu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBoxParcours.removeAllItems();
				FillcomboBoxParcours(comboBoxParcours, comboBoxLieu.getSelectedItem().toString());
				
				try {
					Image img = ImageIO.read(new File(FillCarteLieu(comboBoxLieu.getSelectedItem().toString())));
				} catch (IOException e) {
					e.printStackTrace();
				}
				comboBoxParcours.setEnabled(true);
				
			}
		});
		//Bouton Go Parcours
		btnGoParcours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		//Bouton Fl�che Pr�c�dent POI
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		//Bouton Fl�che Suivant POI
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
	//LISTENERS - MouseClicked on MAP
		panelMAP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("X = "+arg0.getX());
				System.out.println("y = "+arg0.getY());
				arg0.getX();
			}
		});
		
	}
}
