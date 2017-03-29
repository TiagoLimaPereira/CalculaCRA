package menu;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Menu extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Calcula calcula;
	private Container cards;
	private CardLayout layout;
	private JButton selecArquivo, calcular, sair, ok;
	private JTextField caminho;
	public final String LINHA = System.getProperty("line.separator");

	public Menu() {
		super("Calcula cra | © Tiago Pereira");
		this.layout = new CardLayout();
		

		setLayout(this.layout);
		this.cards = getContentPane();

		// Inicializando botoes
		this.selecArquivo = new JButton("Selecionar arquivo");
		this.calcular = new JButton("Calcular CRA");
		this.sair = new JButton("Sair");
		this.ok = new JButton("Ok");

		// Adicionando cards
		this.layout.show(cards, "inicial");
		this.cards.add(telaInicial(), "inicial");
		
		setSize(500, 250);
		setVisible(true);
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
	}

	private Container telaInicial() {
	
		JLabel msg = new JLabel("Selecione um arquivo .txt com 'disciplina, nota, creditos' em cada linha");

		this.selecArquivo.setPreferredSize(new Dimension(180, 25));
		this.selecArquivo.addActionListener(this);

		this.caminho = new JTextField(20);
		this.caminho.setEditable(false);

		this.calcular.setPreferredSize(new Dimension(150, 25));
		this.calcular.addActionListener(this);

		this.sair.setPreferredSize(new Dimension(100, 25));
		this.sair.addActionListener(this);

		Container principal = new JPanel();
		principal.setLayout(new GridBagLayout());

		GridBagConstraints cont = new GridBagConstraints();
		cont.weightx = 1;
		cont.weighty = 1;

		cont.gridwidth = 2;
		cont.gridx = 0;
		cont.gridy = 0;
		principal.add(msg, cont);

		cont.gridwidth = 1;
		cont.gridx = 0;
		cont.gridy = 1;
		principal.add(this.caminho, cont);

		cont.gridx = 1;
		principal.add(this.selecArquivo, cont);

		cont.gridx = 0;
		cont.gridy = 2;
		principal.add(this.sair, cont);

		cont.gridx = 1;
		principal.add(this.calcular, cont);

		return principal;
	}


	private JFileChooser abrirArquivo() {
		JFileChooser file = new JFileChooser();
		file.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		FileNameExtensionFilter filterFile = new FileNameExtensionFilter("Arquivos TXT", "txt");
		file.addChoosableFileFilter(filterFile);
		file.setAcceptAllFileFilterUsed(false);
		file.setFileFilter(filterFile);
		return file;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.selecArquivo) {
			JFileChooser file = abrirArquivo();
			int retorno = file.showOpenDialog(null);

			if (retorno == JFileChooser.APPROVE_OPTION) {
				this.caminho.setText(file.getSelectedFile().getAbsolutePath());
				try {
					this.calcula = new Calcula(this.caminho.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Arquivo invalido", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		
		} else if (e.getSource() == this.calcular) {
			if (this.caminho.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Nenhum arquivo selecionado", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					JOptionPane.showMessageDialog(null, this.calcula.leArquivo(), "Meu CRA",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (e.getSource() == this.ok) {
			this.layout.show(this.cards, "inicial");
		}
		else if(e.getSource() == this.sair) {
			System.exit(EXIT_ON_CLOSE);
		}
	}
}

