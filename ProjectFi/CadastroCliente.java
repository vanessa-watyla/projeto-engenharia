
package ProjectFi;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CadastroCliente {

	private static JFrame frmZeuzAcademia;
	private JTextField CPF;
	private JTextField Nome;
	private int ID = -1;
	private JTextField Cartão;
	private double Horas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					CadastroCliente window = new CadastroCliente();
					window.frmZeuzAcademia.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CadastroCliente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frmZeuzAcademia = new JFrame();
		frmZeuzAcademia.setTitle("ZEUZ ACADEMIA\r\n");
		frmZeuzAcademia.setBounds(100, 100, 450, 300);
		frmZeuzAcademia.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmZeuzAcademia.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Avignon Pro Xlight", Font.PLAIN, 13));
		lblNewLabel.setBounds(93, 47, 46, 14);
		frmZeuzAcademia.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("CPF:");
		lblNewLabel_1.setFont(new Font("Avignon Pro Xlight", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(93, 91, 46, 14);
		frmZeuzAcademia.getContentPane().add(lblNewLabel_1);

		CPF = new JTextField();
		CPF.setBounds(93, 104, 143, 20);
		frmZeuzAcademia.getContentPane().add(CPF);
		CPF.setColumns(10);

		Nome = new JTextField();
		Nome.setColumns(10);
		Nome.setBounds(93, 60, 143, 20);
		frmZeuzAcademia.getContentPane().add(Nome);

		JLabel lblNewLabel_3 = new JLabel("Cartão:");
		lblNewLabel_3.setFont(new Font("Avignon Pro Xlight", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(93, 135, 78, 14);
		frmZeuzAcademia.getContentPane().add(lblNewLabel_3);

		JButton BntID = new JButton("Gerar ID");
		BntID.setFont(new Font("Avignon Pro Xlight", Font.PLAIN, 11));
		BntID.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int min = 0, max = 1000;
				ID = (int) Math.floor(Math.random() * (max - min + 1) + min);
				JOptionPane.showMessageDialog(null, ID);
				String Lista = null;
				try {
					Scanner BD = new Scanner(new File("src/ProjectFi/CadastroClienteBC.dat"), "UTF-8")
							.useDelimiter("\\A");
					Lista = BD.next();
					BD.close();
				} catch (FileNotFoundException b) {
					// TODO Auto-generated catch block
					b.printStackTrace();
				}
				// Metodo de comparar os dados obtidos no banco de dados, com as variaveis
				// obtida na pagina login.
				String[] Check = Lista.split(",");
				int F = Check.length;
				for (int Repetir = 2; Repetir < F; Repetir = Repetir + 6) {
					if (Check[Repetir].equals(String.valueOf(ID))) {
						JOptionPane.showMessageDialog(null, "ID invalido tente gerar o novamente ");
						ID = 0;
						break;
					}

				}

			}
		});
		BntID.setBounds(337, 205, 89, 23);
		frmZeuzAcademia.getContentPane().add(BntID);

		JComboBox HoraAdd = new JComboBox();
		HoraAdd.setFont(new Font("Avignon Pro Demi", Font.BOLD, 12));
		HoraAdd.setModel(new DefaultComboBoxModel(
				new String[] { "Selecione", "10 horas 30 reais", "30 horas 80 reais", "60 horas 140 reais" }));
		HoraAdd.setBounds(93, 190, 143, 22);
		frmZeuzAcademia.getContentPane().add(HoraAdd);

		JButton Salvar = new JButton("Salvar");
		Salvar.setFont(new Font("Avignon Pro Xlight", Font.PLAIN, 12));
		Salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (HoraAdd.getSelectedIndex() == 0) {
					Horas = 0;
				}
				if (HoraAdd.getSelectedIndex() == 1) {
					Horas = 10;
				}
				if (HoraAdd.getSelectedIndex() == 2) {
					Horas = 30;
				}
				if (HoraAdd.getSelectedIndex() == 3) {
					Horas = 60;
				}

				DadosCliente Salvamento = new DadosCliente();

				if (HoraAdd.getSelectedItem().equals("Selecione") || Cartão.getText().isEmpty() || ID == 0 && ID == -1
						|| Nome.getText().isEmpty() || CPF.getText().isEmpty()) {

					if (Nome.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Campo nome vazio está vazio.");
					}
					if (CPF.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Campo CPF vazio está vazio.");
					}
					if (Cartão.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Campo Cartão vazio está vazio.");
					}

					if (HoraAdd.getSelectedItem().equals("Selecione")) {
						JOptionPane.showMessageDialog(null, "Selecione uma opção valida em horas.");
					}
					if (ID == 0 && ID == -1) {
						JOptionPane.showMessageDialog(null, "ID invalido tente gerar o novamente ");

					}

				} else {
//Variavel contendo todos os dados para confirmação;
					String Confirmar = "Nome: " + Nome.getText() + "\nCPF: " + CPF.getText() + "\nID: " + ID
							+ "\nQuantidade de horas: " + Horas + "\nNumero do cartão: " + Cartão.getText();

					int Input = JOptionPane.showConfirmDialog(null, Confirmar);
					if (Input == 0) {
						Salvamento.setNome(Nome.getText());
						Salvamento.setCPF(CPF.getText());
						Salvamento.setStatus("FECHADO");
						Salvamento.setID(String.valueOf(ID));
						Salvamento.setHoras(String.valueOf(Horas));
						Salvamento.setCartao(Cartão.getText());
						Salvamento.Salvar();
						ID = 0;
						Nome.setText("");
						CPF.setText("");
						Cartão.setText("");
						HoraAdd.setSelectedIndex(0);

					}
				}

			}
		});
		Salvar.setBounds(337, 231, 89, 23);
		frmZeuzAcademia.getContentPane().add(Salvar);

		JLabel lblNewLabel_4 = new JLabel("ID:");
		lblNewLabel_4.setFont(new Font("Avignon Pro Xlight", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(318, 209, 32, 14);
		frmZeuzAcademia.getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("CADASTRO DE NOVO CLIENTE");
		lblNewLabel_5.setFont(new Font("Avignon Pro Xlight", Font.BOLD, 14));
		lblNewLabel_5.setBounds(93, 10, 229, 13);
		frmZeuzAcademia.getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Digite seu dados a seguir :");
		lblNewLabel_6.setFont(new Font("Avignon Pro Xlight", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(103, 23, 165, 13);
		frmZeuzAcademia.getContentPane().add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon("src/ProjectFi/IMG/icons8-select-users-48.png"));
		lblNewLabel_7.setBounds(25, 34, 57, 60);
		frmZeuzAcademia.getContentPane().add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("- Informe seus dados corretamente.");
		lblNewLabel_8.setFont(new Font("Avignon Pro Medium", Font.PLAIN, 10));
		lblNewLabel_8.setBounds(0, 250, 186, 13);
		frmZeuzAcademia.getContentPane().add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("- Não perca seu ID.");
		lblNewLabel_9.setFont(new Font("Avignon Pro Medium", Font.PLAIN, 10));
		lblNewLabel_9.setBounds(0, 235, 161, 13);
		frmZeuzAcademia.getContentPane().add(lblNewLabel_9);

		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.setFont(new Font("Avignon Pro Xlight", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Diario.main(null);
				CadastroCliente.frmZeuzAcademia.setVisible(false);
			}
		});
		btnNewButton.setBounds(337, 147, 89, 23);
		frmZeuzAcademia.getContentPane().add(btnNewButton);

		Cartão = new JTextField();
		Cartão.setBounds(93, 147, 143, 20);
		frmZeuzAcademia.getContentPane().add(Cartão);
		Cartão.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Horas");
		lblNewLabel_2.setBounds(93, 176, 46, 14);
		frmZeuzAcademia.getContentPane().add(lblNewLabel_2);

		JButton BntId2 = new JButton("Digitar ID");
		BntId2.setFont(new Font("Avignon Pro Xlight", Font.PLAIN, 11));
		BntId2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Lista = null;
				try {
					ID = Integer.parseInt(JOptionPane.showInputDialog("Digite um numero"));
					Scanner BD = new Scanner(new File("src/ProjectFi/CadastroClienteBC.dat"), "UTF-8")
							.useDelimiter("\\A");
					Lista = BD.next();
					BD.close();
				} catch (FileNotFoundException b) {
					// TODO Auto-generated catch block
					b.printStackTrace();
				}
				// Metodo de comparar os dados obtidos no banco de dados, com as variaveis
				// obtida na pagina login.
				String[] Check = Lista.split(",");
				int F = Check.length;
				for (int Repetir = 2; Repetir < F; Repetir = Repetir + 6) {
					if (Check[Repetir].equals(String.valueOf(ID))) {
						JOptionPane.showMessageDialog(null, "ID invalido tente gerar o novamente ");
						ID = 0;
						break;
					}

				}
			}
		});
		BntId2.setBounds(337, 172, 89, 23);
		frmZeuzAcademia.getContentPane().add(BntId2);

	}
}