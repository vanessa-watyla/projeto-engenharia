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

public class Pagar {

	private JFrame FrmPagar;
	private JTextField ID;
	private int Confirmar = 0;
	private String Lista;
	private JButton BNTPagar;
	private double Horas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pagar window = new Pagar();
					window.FrmPagar.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Pagar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		FrmPagar = new JFrame();
		FrmPagar.setTitle("ZEUZ ACADEMIA");
		FrmPagar.setBounds(100, 100, 450, 300);
		FrmPagar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		DadosCliente Pagar = new DadosCliente();
		try {
			Scanner BD = new Scanner(new File("src/ProjectFi/CadastroClienteBC.dat"), "UTF-8").useDelimiter("\\A");
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
		FrmPagar.getContentPane().setLayout(null);

		ID = new JTextField();
		ID.setBounds(166, 90, 89, 20);
		FrmPagar.getContentPane().add(ID);
		ID.setColumns(10);

		JLabel lblNewLabel = new JLabel("Digite seu ID:");
		lblNewLabel.setBounds(166, 66, 121, 14);
		lblNewLabel.setFont(new Font("Avignon Pro Xlight", Font.PLAIN, 12));
		FrmPagar.getContentPane().add(lblNewLabel);

		JComboBox HoraAdd = new JComboBox();
		HoraAdd.setModel(new DefaultComboBoxModel(
				new String[] { "Selecione", "10 horas 30 reais", "30 horas 80 reais", "60 horas 140 reais" }));
		HoraAdd.setFont(new Font("Dialog", Font.PLAIN, 11));
		HoraAdd.setBounds(143, 141, 143, 22);
		FrmPagar.getContentPane().add(HoraAdd);

		// JButton Pagar;
		BNTPagar = new JButton("Pagar");
		BNTPagar.setBounds(301, 217, 89, 23);
		BNTPagar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		BNTPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Confirmar = 0;
				for (int Repetir = 2; Repetir < F; Repetir = Repetir + 6) {
					if (ID.getText().equals(Check[Repetir])) {

						Confirmar = 1;
						Repetir = Repetir - 2;
						JOptionPane.showMessageDialog(null, "Pagamento efetuado com suesso.");
						System.out.print(HoraAdd.getSelectedIndex());
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

						if (HoraAdd.getSelectedItem().equals("Selecione")) {
							JOptionPane.showMessageDialog(null, "Selecione uma opção valida em Horas.");

						} else {
							Check[Repetir + 3] = String.valueOf(Horas + Double.parseDouble(Check[Repetir + 3]));
							DadosCliente Pagar = new DadosCliente();
							Pagar.setAtualiz(String.join(",", Check));
							Pagar.Atualizar();
							ID.setText("");
							HoraAdd.setSelectedIndex(0);

						}

						break;
					}

					if (F < Repetir) {
						break;
					}
				}
				if (Confirmar == 0) {
					JOptionPane.showMessageDialog(null, "ID não existe");
					ID.setText("");
				}

			}
		});
		FrmPagar.getContentPane().add(BNTPagar);

		JButton btnNewButton = new JButton("Voltar ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmPagar.setVisible(false);
				Diario.main(null);

			}
		});
		FrmPagar.getContentPane().add(BNTPagar);

		JLabel lblNewLabel_1 = new JLabel("Pagamento");
		lblNewLabel_1.setBounds(166, 10, 89, 13);
		lblNewLabel_1.setFont(new Font("Avignon Pro Xlight", Font.BOLD, 14));
		FrmPagar.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(44, 10, 71, 42);
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Gabriel Sacramento\\Downloads\\icons8-user-folder-50.png"));
		FrmPagar.getContentPane().add(lblNewLabel_2);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(32, 215, 100, 23);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Diario.main(null);
				FrmPagar.setVisible(false);
			}
		});
		btnVoltar.setFont(new Font("Dialog", Font.PLAIN, 11));
		FrmPagar.getContentPane().add(btnVoltar);

		JButton Excluir = new JButton("Excluir");
		Excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Excluir = "";

				for (int Repetir = 2; Repetir < F; Repetir = Repetir + 6) {
					if (ID.getText().equals(Check[Repetir])) {
						Check[Repetir - 2] = "";
						Check[Repetir - 1] = "";
						Check[Repetir] = "";
						Check[Repetir + 1] = "";
						Check[Repetir + 2] = "";
						Check[Repetir + 3] = "";
						// Excluir += "\n";
					}

				}

				Pagar.setAtualiz(String.join(",", Check));
				Pagar.Atualizar();
				// Pagar.Deletar();

			}
		});
		Excluir.setBounds(166, 216, 89, 23);
		FrmPagar.getContentPane().add(Excluir);

		JLabel lblNewLabel_3 = new JLabel("Digite as horas:");
		lblNewLabel_3.setBounds(166, 121, 100, 23);
		FrmPagar.getContentPane().add(lblNewLabel_3);

	}
}
