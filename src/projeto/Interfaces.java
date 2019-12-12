package projeto;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

import modules.BloomFilter;
import modules.MinHash;

public class Interfaces extends JFrame {
	
	private String[] labels;
	private String[] labelP = {"2", "3", "4"};
	private String[] label4 = {"Inglaterra","Estados Unidos da América","Alemanha","China","Portugal","Itália","Holanda","Espanha","França"};
	private JButton but;
	private JComboBox comboboxNP;
	private JComboBox comboboxP1;
	private JComboBox comboboxP2;
	private JComboBox comboboxP3;
	private JComboBox comboboxP4;
	
	private static LinkedList<String> listCodePercursos;
	private static int[][] minHash;
	private int num;
	private String pais1;
	private String pais2;
	private String pais3;
	private String pais4;
	private static BloomFilter bf = new BloomFilter(15020);
	private static MinHash mH;
	
	private JTextField txtIni;
	private JTextField txtP2;

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public Interfaces (String[] labels) throws IOException {
		super("Gestão otimizada de percursos");
		readFile();
		
		bf.initialize();
		
		this.labels=labels;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		getContentPane().setLayout(null);
		
		comboboxNP = new JComboBox(labelP);
		comboboxNP.setBounds(366, 174, 95, 27);
		getContentPane().add(comboboxNP);
		
		but = new JButton("Continuar!");
		but.setBounds(558, 499, 95, 33);
		getContentPane().add(but);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(10, 499, 95, 33);
		getContentPane().add(btnSair);
		
		txtIni = new JTextField();
		txtIni.setText("Introduza o número de países do percurso desejado: ");
		txtIni.setBounds(138, 130, 323, 33);
		getContentPane().add(txtIni);
		txtIni.setColumns(10);
		txtIni.setEditable(false);
		
		
		
		
		
		btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
		
		
		
		but.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			     
				num = Integer.parseInt((String)comboboxNP.getSelectedItem());
				
				
				if(num==2) {
					getContentPane().removeAll();
					txtP2 = new JTextField();
					txtP2.setText("Introduza os países de origem e destino:");
					txtP2.setBounds(216, 130, 245, 33);
					getContentPane().add(txtP2);
					txtP2.setColumns(10);
					txtP2.setEditable(false);
					comboboxP1.setBounds(182, 306, 120, 33);
					comboboxP2.setBounds(337, 306, 120, 33);
					getContentPane().add(comboboxP1);
					getContentPane().add(comboboxP2);
					
					JButton btnVoltar = new JButton("Voltar");
					btnVoltar.setBounds(10, 499, 95, 33);
					getContentPane().add(btnVoltar);
					
					btnVoltar.addActionListener(new ActionListener() {
			            @Override
			            public void actionPerformed(ActionEvent actionEvent) {
			                reInitialize();
			            }
			        });
					
					JButton btnBloom = new JButton("Add percurso");
					btnBloom.setBounds(399, 504, 125, 33);
					getContentPane().add(btnBloom);
					
					btnBloom.addActionListener(new ActionListener() {
			            @Override
			            public void actionPerformed(ActionEvent actionEvent) {
			                pais1 = (String)comboboxP1.getSelectedItem();
			                pais2 = (String)comboboxP2.getSelectedItem();
			                
			                String addBf="";
							try {
								addBf = Percurso.getCodePais(pais1)+Percurso.getCodePais(pais2);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
			                
			                if(bf.contains(addBf)) {
			                	JFrame popup = new JFrame();
			                	bf.addToSet(addBf);
			                	JOptionPane.showMessageDialog(popup, "Já existe");
			                }
			                else {
			                	JFrame popup = new JFrame();
			                	bf.addToSet(addBf);
			                	JOptionPane.showMessageDialog(popup, "Adicionado");
			                	listCodePercursos.add(addBf);
			                }
			                
			                
			            }
			        });
					
					
					JButton btnMin = new JButton("Check similares");
					btnMin.setBounds(545, 504, 130, 33);
					getContentPane().add(btnMin);
					
					btnMin.addActionListener(new ActionListener() {
						@Override
			            public void actionPerformed(ActionEvent actionEvent) {
							 pais1 = (String)comboboxP1.getSelectedItem();
							 pais2 = (String)comboboxP2.getSelectedItem();
				               
				             String addMh="";
							try {
								addMh = Percurso.getCodePais(pais1)+Percurso.getCodePais(pais2);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
							
							listCodePercursos.add(addMh);
							
							addMinHash();
							
							List<String> listSim = new ArrayList<String>();
							
							for(int i=0; i<listCodePercursos.size()-1; i++) {
								if(mH.checkSimilaraty(addMh, listCodePercursos.get(i))<=0.40) {
									try {
										listSim.add(Percurso.getPercurso(listCodePercursos.get(i)));
									} catch (FileNotFoundException e) {
										e.printStackTrace();
									}
								}
							}
							if(listSim.size()!=0) {
								JFrame popup = new JFrame();
								String s="";
								for(String str : listSim) {
									s += str +"\n";
								}
			                	JOptionPane.showMessageDialog(popup, "Existem "+ listSim.size() +" percuros similares : " + s);
							}
							else {
								JFrame popup = new JFrame();
			                	JOptionPane.showMessageDialog(popup, "Não existem percursos similares");
							}
			            }
			        });
					
					repaint();
					printAll(getGraphics());
					
				}
				else if(num==3) {
					getContentPane().removeAll();
					txtP2 = new JTextField();
					txtP2.setText("Introduza os países de origem e destino:");
					txtP2.setBounds(216, 130, 245, 33);
					getContentPane().add(txtP2);
					txtP2.setColumns(10);
					txtP2.setEditable(false);
					comboboxP1.setBounds(71, 317, 125, 22);
					comboboxP2.setBounds(239, 317, 125, 22);
					comboboxP3.setBounds(409, 317, 125, 22);
					getContentPane().add(comboboxP1);
					getContentPane().add(comboboxP2);
					getContentPane().add(comboboxP3);
					
					JButton btnVoltar = new JButton("Voltar");
					btnVoltar.setBounds(10, 499, 95, 33);
					getContentPane().add(btnVoltar);
					
					btnVoltar.addActionListener(new ActionListener() {
			            @Override
			            public void actionPerformed(ActionEvent actionEvent) {
			                reInitialize();
			            }
			        });
					
					JButton btnBloom = new JButton("Add percurso");
					btnBloom.setBounds(399, 504, 125, 33);
					getContentPane().add(btnBloom);
					
					btnBloom.addActionListener(new ActionListener() {
			            @Override
			            public void actionPerformed(ActionEvent actionEvent) {
			                pais1 = (String)comboboxP1.getSelectedItem();
			                pais2 = (String)comboboxP2.getSelectedItem();
			                pais3 = (String)comboboxP3.getSelectedItem();
			                
			                String addBf="";
							try {
								addBf = Percurso.getCodePais(pais1)+Percurso.getCodePais(pais2)+Percurso.getCodePais(pais3);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
			                
			                if(bf.contains(addBf)) {
			                	JFrame popup = new JFrame();
			                	bf.addToSet(addBf);
			                	JOptionPane.showMessageDialog(popup, "Já existe");
			                }
			                else {
			                	JFrame popup = new JFrame();
			                	bf.addToSet(addBf);
			                	JOptionPane.showMessageDialog(popup, "Adicionado");
			                	listCodePercursos.add(addBf);
			                }
			                
			                
			            }
			        });
					
					JButton btnMin = new JButton("Check similares");
					btnMin.setBounds(545, 504, 130, 33);
					getContentPane().add(btnMin);
					
					btnMin.addActionListener(new ActionListener() {
						@Override
			            public void actionPerformed(ActionEvent actionEvent) {
							 pais1 = (String)comboboxP1.getSelectedItem();
							 pais2 = (String)comboboxP2.getSelectedItem();
							 pais3 = (String)comboboxP3.getSelectedItem();
				               
				             String addMh="";
							try {
								addMh = Percurso.getCodePais(pais1)+Percurso.getCodePais(pais2)+Percurso.getCodePais(pais3);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
							
							listCodePercursos.add(addMh);
							
							addMinHash();
							
							List<String> listSim = new ArrayList<String>();
							
							for(int i=0; i<listCodePercursos.size()-1; i++) {
								if(mH.checkSimilaraty(addMh, listCodePercursos.get(i))<=0.40) {
									try {
										listSim.add(Percurso.getPercurso(listCodePercursos.get(i)));
									} catch (FileNotFoundException e) {
										e.printStackTrace();
									}
								}
							}
							if(listSim.size()!=0) {
								JFrame popup = new JFrame();
								String s="";
								for(String str : listSim) {
									s += str +"\n";
								}
			                	JOptionPane.showMessageDialog(popup, "Existem "+ listSim.size() +" percuros similares : " + s);
							}
							else {
								JFrame popup = new JFrame();
			                	JOptionPane.showMessageDialog(popup, "Não existem percursos similares");
							}
			            }
			        });
					
					repaint();
					printAll(getGraphics());
				}
				else if(num==4) {
					getContentPane().removeAll();
					txtP2 = new JTextField();
					txtP2.setText("Introduza os países de origem e destino:");
					txtP2.setBounds(216, 130, 245, 33);
					getContentPane().add(txtP2);
					txtP2.setColumns(10);
					txtP2.setEditable(false);
					comboboxP4.setBounds(37, 327, 144, 22);
					comboboxP2.setBounds(182, 327, 144, 22);
					comboboxP3.setBounds(336, 327, 144, 22);
					comboboxP1.setBounds(490, 327, 144, 22);
					getContentPane().add(comboboxP4);
					getContentPane().add(comboboxP2);
					getContentPane().add(comboboxP3);
					getContentPane().add(comboboxP1);
					
					JButton btnVoltar = new JButton("Voltar");
					btnVoltar.setBounds(10, 499, 95, 33);
					getContentPane().add(btnVoltar);
					
					btnVoltar.addActionListener(new ActionListener() {
			            @Override
			            public void actionPerformed(ActionEvent actionEvent) {
			                reInitialize();
			            }
			        });
					
					JButton btnBloom = new JButton("Add percurso");
					btnBloom.setBounds(399, 504, 125, 33);
					getContentPane().add(btnBloom);
					
					btnBloom.addActionListener(new ActionListener() {
			            @Override
			            public void actionPerformed(ActionEvent actionEvent) {
			                pais1 = (String)comboboxP4.getSelectedItem();
			                pais2 = (String)comboboxP2.getSelectedItem();
			                pais3 = (String)comboboxP3.getSelectedItem();
			                pais4 = (String)comboboxP1.getSelectedItem();
			                
			                String addBf="";
							try {
								addBf = Percurso.getCodePais(pais1)+Percurso.getCodePais(pais2)+Percurso.getCodePais(pais3)+Percurso.getCodePais(pais4);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
			                
			                if(bf.contains(addBf)) {
			                	JFrame popup = new JFrame();
			                	bf.addToSet(addBf);
			                	JOptionPane.showMessageDialog(popup, "Já existe");
			                }
			                else {
			                	JFrame popup = new JFrame();
			                	bf.addToSet(addBf);
			                	JOptionPane.showMessageDialog(popup, "Adicionado");
			                	listCodePercursos.add(addBf);
			                }
			                
			                
			            }
			        });
					
					JButton btnMin = new JButton("Check similares");
					btnMin.setBounds(545, 504, 130, 33);
					getContentPane().add(btnMin);
					
					btnMin.addActionListener(new ActionListener() {
						@Override
			            public void actionPerformed(ActionEvent actionEvent) {
							 pais1 = (String)comboboxP4.getSelectedItem();
							 pais2 = (String)comboboxP2.getSelectedItem();
							 pais3 = (String)comboboxP3.getSelectedItem();
							 pais4 = (String)comboboxP1.getSelectedItem();
				               
				             String addMh="";
							try {
								addMh = Percurso.getCodePais(pais1)+Percurso.getCodePais(pais2)+Percurso.getCodePais(pais3)+Percurso.getCodePais(pais4);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
							
							listCodePercursos.add(addMh);
							
							addMinHash();
							
							List<String> listSim = new ArrayList<String>();
							
							for(int i=0; i<listCodePercursos.size()-1; i++) {
								if(mH.checkSimilaraty(addMh, listCodePercursos.get(i))<=0.40) {
									try {
										listSim.add(Percurso.getPercurso(listCodePercursos.get(i)));
									} catch (FileNotFoundException e) {
										e.printStackTrace();
									}
								}
							}
							if(listSim.size()!=0) {
								JFrame popup = new JFrame();
								String s="";
								for(String str : listSim) {
									s += str +"\n";
								}
			                	JOptionPane.showMessageDialog(popup, "Existem "+ listSim.size() +" percuros similares : " + s);
							}
							else {
								JFrame popup = new JFrame();
			                	JOptionPane.showMessageDialog(popup, "Não existem percursos similares");
							}
			            }
			        });
					
					repaint();
					printAll(getGraphics());
				}
			}
		});
		
		comboboxP1 = new JComboBox(labels); 
		comboboxP2 = new JComboBox(labels);
		comboboxP3 = new JComboBox(labels);
		comboboxP4 = new JComboBox(label4);
		
		
		

	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,600);
        setLocationRelativeTo(null);
        setVisible(true);
		
	}
	
	//Função para pudermos voltar ao painel inicial
	public void reInitialize() {
		getContentPane().removeAll();
		repaint();
		printAll(getGraphics());
		initialize();
	}
	
	
	//Lê o ficheiro e adiciona os percursos ao BloomFilter
	public static void readFile() throws IOException {
		
		List<String> linhas = new ArrayList<String>();
		listCodePercursos = new LinkedList<String>();
		
		Scanner scanner = new Scanner(new File("src/projeto/Percursos.txt"));
		
		scanner.nextLine();
		while(scanner.hasNextLine()) {
			linhas.add(scanner.nextLine());
		}
		
		scanner.close();
		
		for(String l : linhas) {
			
			String [] li = l.split("\t");
			bf.addToSet(li[0]);
			listCodePercursos.add(li[0]);
		}
		
	}
	
	
	//Adiciona os percuros à MinHash
	public static void addMinHash () {
		mH = new MinHash(listCodePercursos, bf.getK());
		minHash = mH.applyMinHash();
	}

}
