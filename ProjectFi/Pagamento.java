
package ProjectFi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Pagamento {

	private JFrame FrmPagamento;
	private JTextField ID;
	private int Confirmar = 0;
	private String Lista;
	private JButton BNTPagar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pagamento window = new Pagamento();
					window.FrmPagamento.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Pagamento() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		FrmPagamento = new JFrame();
		FrmPagamento.setTitle("ZEUZ ACADEMIA");
		FrmPagamento.setBounds(100, 100, 450, 300);
		FrmPagamento.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
		String[] Check = Lista.split("\\r?\\n");
		int F = Check.length;

		JButton Pagamentos = new JButton("Já está PAGO?");
		Pagamentos.setBounds(283, 169, 113, 23);
		Pagamentos.setFont(new Font("Avignon Pro Xlight", Font.PLAIN, 11));
		Pagamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calendar hoje = Calendar.getInstance();
				int MES = hoje.get(Calendar.MONTH);
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
				String[] Check = Lista.split("\\r?\\n");
				int F = Check.length;
				Confirmar = 0;
				for (int Repetir = 2; Repetir < F; Repetir = Repetir + 7) {
					// JOptionPane.showMessageDialog(null, Check[Repetir]);
					if (ID.getText().equals(Check[Repetir])) {

						Confirmar = 1;
						Repetir = Repetir - 2;
						if (Check[Repetir + 3].equals(String.valueOf(MES + 1))) {
							JOptionPane.showMessageDialog(null, "Pago");
						} else {

							int Meses = Integer.valueOf(Check[Repetir + 3]);

							JOptionPane.showMessageDialog(null, "Não Pago, " + (MES - Meses + 1) + " Messes pedentes.");
						}
						break;
					}
					if (F < Repetir) {
						break;
					}
				}
				if (Confirmar == 0) {
					JOptionPane.showMessageDialog(null, "ID não existe");
				}
				ID.setText("");

			}
		});
		FrmPagamento.getContentPane().setLayout(null);
		FrmPagamento.getContentPane().add(Pagamentos);

		ID = new JTextField();
		ID.setBounds(166, 90, 89, 20);
		FrmPagamento.getContentPane().add(ID);
		ID.setColumns(10);

		JLabel lblNewLabel = new JLabel("Digite seu ID:");
		lblNewLabel.setBounds(166, 66, 121, 14);
		lblNewLabel.setFont(new Font("Avignon Pro Xlight", Font.PLAIN, 12));
		FrmPagamento.getContentPane().add(lblNewLabel);

		// JButton Pagar;
		BNTPagar = new JButton("Pagar");
		BNTPagar.setBounds(166, 169, 89, 23);
		BNTPagar.setFont(new Font("Avignon Pro Xlight", Font.PLAIN, 11));
		BNTPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Calendar hoje = Calendar.getInstance();
				int MES = hoje.get(Calendar.MONTH);
				Confirmar = 0;
				for (int Repetir = 2; Repetir < F; Repetir = Repetir + 7) {
					if (ID.getText().equals(Check[Repetir])) {

						Confirmar = 1;
						Repetir = Repetir - 2;
						JOptionPane.showMessageDialog(null, "Pagamento efetuado com suesso.");
						Check[Repetir + 3] = String.valueOf(MES + 1);

						break;
					}

					if (F < Repetir) {
						break;
					}
				}
				if (Confirmar == 0) {
					JOptionPane.showMessageDialog(null, "ID não existe");
				}
				ID.setText("");
				Pagar.setAtualiz(String.join("\n", Check));
				Pagar.Atualizar();

			}
		});
		FrmPagamento.getContentPane().add(BNTPagar);

		JButton btnNewButton = new JButton("Voltar ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmPagamento.setVisible(false);
				Diario.main(null);

			}
		});
		FrmPagamento.getContentPane().add(BNTPagar);

		JLabel lblNewLabel_1 = new JLabel("Pagamento");
		lblNewLabel_1.setBounds(166, 10, 89, 13);
		lblNewLabel_1.setFont(new Font("Avignon Pro Xlight", Font.BOLD, 14));
		FrmPagamento.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(44, 10, 71, 42);
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Gabriel Sacramento\\Downloads\\icons8-user-folder-50.png"));
		FrmPagamento.getContentPane().add(lblNewLabel_2);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(44, 169, 100, 23);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Diario.main(null);
				FrmPagamento.setVisible(false);
			}
		});
		btnVoltar.setFont(new Font("Dialog", Font.PLAIN, 11));
		FrmPagamento.getContentPane().add(btnVoltar);

		JButton Editar = new JButton("Editar");
		Editar.setBounds(44, 216, 100, 23);
		FrmPagamento.getContentPane().add(Editar);

		JButton Excluir = new JButton("Excluir");
		Excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Excluir = "";

				for (int Repetir = 2; Repetir < F; Repetir = Repetir + 7) {
					if (!ID.getText().equals(Check[Repetir])) {
						Excluir += Check[Repetir - 2] + "\n";
						Excluir += Check[Repetir - 1] + "\n";
						Excluir += Check[Repetir] + "\n";
						Excluir += Check[Repetir + 1] + "\n";
						Excluir += Check[Repetir + 2] + "\n";
						Excluir += Check[Repetir + 3] + "\n";
						Excluir += "\n";
					}

				}
				Pagar.setAtualiz(Excluir);
				Pagar.Deletar();
			}
		});
		Excluir.setBounds(166, 216, 89, 23);
		FrmPagamento.getContentPane().add(Excluir);
	}
}
