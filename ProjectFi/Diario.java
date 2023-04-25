
package ProjectFi;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Diario {

	private JFrame Diario;
	private JTextField ID;
	private int Confirmar = 0;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Diario window = new Diario();
					window.Diario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Diario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Diario = new JFrame();
		Diario.setFont(new Font("Arial Narrow", Font.PLAIN, 12));
		Diario.setTitle("ZEUZ ACADEMIA");
		Diario.setBounds(100, 100, 450, 300);
		Diario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Diario.getContentPane().setLayout(null);

		String Lista = null;
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

		JButton Proximo = new JButton("Proximo");
		Proximo.setFont(new Font("Avignon Pro Demi", Font.PLAIN, 11));
		Proximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!ID.getText().isEmpty()) {
					for (int Repetir = 2; Repetir < F; Repetir = Repetir + 6) {
						Confirmar = 0;
						if (ID.getText().equals(Check[Repetir])) {
							Confirmar = 1;
							Repetir = Repetir - 2;
							double Hora = Double.parseDouble(Check[Repetir + 3]);
							if (Check[Repetir + 4].equals("FECHADO")) {
								if (Hora > 0) {
									JOptionPane.showMessageDialog(null,
											"Seja bem vindo " + Check[Repetir] + " Bom treino");
									ID.setText("");

									Date Data = new Date();
									SimpleDateFormat FormatarHora = new SimpleDateFormat("h.m");
									String HoraFormatada = FormatarHora.format(Data);
									Double HoraExata = Double.parseDouble(HoraFormatada);

									Check[Repetir + 3] = String.valueOf((Hora * 60 + HoraExata * 60) / 60);
									Check[Repetir + 4] = "ABERTO";
									DadosCliente Pagar = new DadosCliente();
									Pagar.setAtualiz(String.join(",", Check));
									Pagar.Atualizar();

								} else {

									JOptionPane.showMessageDialog(null, "Entrada negada " + Hora + " horas pedentes.");
								}
								break;

							} else {
								JOptionPane.showMessageDialog(null, "Obrigado " + Check[Repetir] + " bom descanço");
								ID.setText("");
								Date Data = new Date();
								SimpleDateFormat FormatarHora = new SimpleDateFormat("h.m");
								String HoraFormatada = FormatarHora.format(Data);
								Double HoraExata = Double.parseDouble(HoraFormatada);

								Check[Repetir + 3] = String.valueOf((Hora * 60 - HoraExata * 60) / 60);
								Check[Repetir + 4] = "FECHADO";
								DadosCliente Pagar = new DadosCliente();
								Pagar.setAtualiz(String.join(",", Check));
								Pagar.Atualizar();
								break;

							}

						}
						if (F < Repetir) {
							break;
						}
					}
					if (Confirmar == 0) {
						JOptionPane.showMessageDialog(null, "ID não existente");
						ID.setText("");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Campo ID vazio.");
					ID.setText("");
				}

			}
		});
		Proximo.setBounds(187, 109, 89, 23);
		Diario.getContentPane().add(Proximo);

		JButton Pagamento = new JButton("Pagamento");
		Pagamento.setFont(new Font("Avignon Pro Xlight", Font.PLAIN, 11));
		Pagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Codico = "0000";
				String CodicoOp = JOptionPane.showInputDialog("Digite o senha de acesso:");

				if (Codico.equals(CodicoOp)) {
					// Chamando a Pagina Cadastro.
					ProjectFi.Pagar.main(null);
					Diario.setVisible(false);
				} else {

					JOptionPane.showMessageDialog(null, "Senha incorreta");
				}

			}
		});
		Pagamento.setBounds(187, 184, 110, 23);
		Diario.getContentPane().add(Pagamento);

		JButton Dados = new JButton("Cadastrar");
		Dados.setFont(new Font("Avignon Pro Xlight", Font.PLAIN, 11));
		Dados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Codico = "0000";
				String CodicoOp = JOptionPane.showInputDialog("Digite o senha de acesso:");

				if (Codico.equals(CodicoOp)) {
					// Chamando a Pagina Cadastro.
					ProjectFi.CadastroCliente.main(null);
					Diario.setVisible(false);
				} else {

					JOptionPane.showMessageDialog(null, "Senha incorreta");
				}

			}
		});
		Dados.setBounds(187, 217, 110, 23);
		Diario.getContentPane().add(Dados);

		JLabel lblNewLabel_2 = new JLabel("ID:");
		lblNewLabel_2.setFont(new Font("Avignon Pro Xlight", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(158, 84, 46, 14);
		Diario.getContentPane().add(lblNewLabel_2);

		ID = new JTextField();
		ID.setFont(new Font("Avignon Pro Medium", Font.PLAIN, 11));
		ID.setBounds(190, 81, 86, 20);
		Diario.getContentPane().add(ID);
		ID.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("DIÁRIO DE CLIENTE");
		lblNewLabel_3.setFont(new Font("Avignon Pro Xlight", Font.BOLD, 14));
		lblNewLabel_3.setBounds(158, 10, 139, 13);
		Diario.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Mais opções :");
		lblNewLabel_4.setFont(new Font("Avignon Pro Xlight", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(94, 154, 163, 13);
		Diario.getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("- Digite seu nome corretamente.\r\n");
		lblNewLabel_5.setFont(new Font("Avignon Pro Medium", Font.PLAIN, 10));
		lblNewLabel_5.setBounds(0, 240, 180, 13);
		Diario.getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("- Digite seu ID corretamente\r\n");
		lblNewLabel_6.setFont(new Font("Avignon Pro Medium", Font.PLAIN, 10));
		lblNewLabel_6.setBounds(0, 227, 156, 13);
		Diario.getContentPane().add(lblNewLabel_6);

	}
}
