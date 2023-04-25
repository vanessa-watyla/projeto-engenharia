
package ProjectFi;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Cadastro {

	private static JFrame frmCadastro;
	private JTextField Usuario;
	private JTextField Senha1;
	private JTextField Senha2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Cadastro window = new Cadastro();
					window.frmCadastro.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public Cadastro() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmCadastro = new JFrame();
		frmCadastro.getContentPane().setFont(new Font("Avignon Pro Medium", Font.PLAIN, 10));
		frmCadastro.setTitle("ZEUZ ACADEMIA");
		frmCadastro.setBounds(100, 100, 500, 300);
		frmCadastro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCadastro.getContentPane().setLayout(null);

		JLabel Usuario1 = new JLabel("Nome de Usuário:");
		Usuario1.setBackground(new Color(0, 0, 0));
		Usuario1.setFont(new Font("Avignon Pro Xlight", Font.PLAIN, 12));
		Usuario1.setBounds(181, 45, 110, 14);
		frmCadastro.getContentPane().add(Usuario1);

		Usuario = new JTextField();
		Usuario.setFont(new Font("Avignon Pro Medium", Font.BOLD | Font.ITALIC, 11));
		Usuario.setBounds(181, 69, 110, 20);
		frmCadastro.getContentPane().add(Usuario);
		Usuario.setColumns(10);

		JLabel NomeSenha1 = new JLabel("Senha:");
		NomeSenha1.setFont(new Font("Avignon Pro Xlight", Font.PLAIN, 12));
		NomeSenha1.setBounds(214, 102, 46, 14);
		frmCadastro.getContentPane().add(NomeSenha1);

		Senha1 = new JTextField();
		Senha1.setFont(new Font("Avignon Pro Medium", Font.PLAIN, 11));
		Senha1.setBounds(181, 126, 110, 20);
		frmCadastro.getContentPane().add(Senha1);
		Senha1.setColumns(10);

		Senha2 = new JTextField();
		Senha2.setFont(new Font("Avignon Pro Medium", Font.PLAIN, 11));
		Senha2.setBounds(181, 191, 110, 20);
		frmCadastro.getContentPane().add(Senha2);
		Senha2.setColumns(10);

		JLabel lblNewLabel = new JLabel("Confirmar senha:");
		lblNewLabel.setFont(new Font("Avignon Pro Xlight", Font.PLAIN, 12));
		lblNewLabel.setBounds(192, 167, 99, 14);
		frmCadastro.getContentPane().add(lblNewLabel);

		JButton Confirma = new JButton("Confirmar");
		Confirma.setFont(new Font("Avignon Pro Xlight", Font.PLAIN, 12));
		Confirma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//Evitar erros .
				if (Usuario.getText().isEmpty() || Senha1.getText().isEmpty() || Senha2.getText().isEmpty()
						|| !Senha1.getText().equals(Senha2.getText())) {
					if (Usuario.getText().isEmpty()) {
						
						JOptionPane.showMessageDialog(frmCadastro, "Campo usuario está vazio ", null,
								JOptionPane.PLAIN_MESSAGE);
					}
					if (Senha1.getText().isEmpty()) {
						JOptionPane.showMessageDialog(frmCadastro, "Campo Senha 1 está vazio ", null,
								JOptionPane.PLAIN_MESSAGE);
					}
					if (Senha2.getText().isEmpty()) {
						JOptionPane.showMessageDialog(frmCadastro, "Campo senha 2 está vazio ", null,
								JOptionPane.PLAIN_MESSAGE);
					}
					if (!Senha1.getText().equals(Senha2.getText())) {
						//Compara as duas senhas, só irá passar se forem iguais.
						JOptionPane.showMessageDialog(frmCadastro, "Senhas diferentes", null,
								JOptionPane.PLAIN_MESSAGE);
						Senha1.setText("");
						Senha2.setText("");
					}
//Salva somente se não tiver nenhum erro.
				} else {
					// Salvando as variaveis no banco de dados.
					DadosCliente Salvar = new DadosCliente();
					Salvar.setUsuario(Usuario.getText());
					Salvar.setSenha(Senha2.getText());
					Salvar.SalvarServidor();// ação para salvar no banco de dados
					JOptionPane.showMessageDialog(frmCadastro, "Cadastro realizado. ", null, JOptionPane.PLAIN_MESSAGE);

					// Após o cadastro, os campos são limpos
					Usuario.setText("");
					Senha1.setText("");
					Senha2.setText(""); // Fim da ação do btn Salvar

				}
			}
		});
		Confirma.setBounds(181, 230, 110, 23);
		frmCadastro.getContentPane().add(Confirma);
		
		JButton btnNewButton_1 = new JButton("Voltar Menu");
		btnNewButton_1.setFont(new Font("Avignon Pro Xlight", Font.PLAIN, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Diario.main(null);	
				Cadastro.frmCadastro.setVisible(false);
				
			}
		});
		btnNewButton_1.setBounds(343, 230, 115, 23);
		frmCadastro.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("CADASTRO DE USUÁRIO");
		lblNewLabel_1.setFont(new Font("Avignon Pro Xlight", Font.BOLD, 14));
		lblNewLabel_1.setBounds(150, 10, 163, 14);
		frmCadastro.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("- Senha com números e letras.");
		lblNewLabel_3.setFont(new Font("Avignon Pro Medium", Font.PLAIN, 10));
		lblNewLabel_3.setBounds(0, 230, 153, 13);
		frmCadastro.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("- Salve sua senha.");
		lblNewLabel_4.setFont(new Font("Avignon Pro Medium", Font.PLAIN, 10));
		lblNewLabel_4.setBounds(0, 240, 153, 13);
		frmCadastro.getContentPane().add(lblNewLabel_4);
		
	
	
	}
}
