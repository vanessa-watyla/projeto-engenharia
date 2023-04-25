
package ProjectFi;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PagLogin {

	protected static final Object Lista = null;
	private JFrame frmLoginPag;
	private JTextField Usuario;
	private JTextField Senha;
	private JButton Logar;
	private JButton Cadastro;
	private int Confirmar = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PagLogin window = new PagLogin();
					window.frmLoginPag.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public PagLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoginPag = new JFrame();
		frmLoginPag.setTitle("LoginPagamento");
		frmLoginPag.setBounds(100, 100, 408, 235);
		frmLoginPag.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmLoginPag.getContentPane().setLayout(null);

		// Botões e textos.
		JLabel Login1 = new JLabel("Login:");
		Login1.setFont(new Font("Avignon Pro Xlight", Font.PLAIN, 12));
		Login1.setBounds(120, 48, 46, 14);
		frmLoginPag.getContentPane().add(Login1);

		JLabel Senha1 = new JLabel("Senha:\r\n");
		Senha1.setFont(new Font("Avignon Pro Xlight", Font.PLAIN, 12));
		Senha1.setBounds(120, 73, 46, 14);
		frmLoginPag.getContentPane().add(Senha1);

		Usuario = new JTextField();
		Usuario.setFont(new Font("Avignon Pro Medium", Font.PLAIN, 11));
		Usuario.setBounds(176, 45, 110, 20);
		frmLoginPag.getContentPane().add(Usuario);
		Usuario.setColumns(10);

		Senha = new JTextField();
		Senha.setFont(new Font("Avignon Pro Medium", Font.PLAIN, 11));
		Senha.setBounds(176, 70, 110, 20);
		frmLoginPag.getContentPane().add(Senha);
		Senha.setColumns(10);
//Botão de acessar o menu.
		Logar = new JButton("Acessar");
		Logar.setFont(new Font("Avignon Pro Xlight", Font.PLAIN, 11));
		Logar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Metodo de ler o banco de dados de usuario e login.
				String Lista = null;
				try {
					Scanner useDelimiter = new Scanner(new File("src/ProjectFi/Cadastro.dat"), "UTF-8")
							.useDelimiter("\\A");
					Lista = useDelimiter.next();
				} catch (FileNotFoundException b) {
					// TODO Auto-generated catch block
					b.printStackTrace();
				}
				// Metodo de comparar os dados obtidos no banco de dados, com as variaveis
				// obtida na pagina login.
				String[] Check = Lista.split("\\r?\\n");

				int F = Check.length;
				if (!Usuario.getText().isEmpty() && !Senha.getText().isEmpty()) {
					for (int Repetir = 0; Repetir < F; Repetir++) {
						if (Usuario.getText().equals(Check[Repetir]) && Senha.getText().equals(Check[Repetir + 1])) {
							Confirmar = 1;
							CadastroCliente.main(null);
							frmLoginPag.setVisible(false);
							break;
						} else {
							Repetir++;
						}

					}
					if (Confirmar == 0) {
						JOptionPane.showMessageDialog(null, "Usuario ou senha incorreto.");
						Senha.setText("");
						Usuario.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Campo em branco, tente novamente.");
				}

			}
		});
		Logar.setBounds(132, 118, 89, 23);
		frmLoginPag.getContentPane().add(Logar);

		Cadastro = new JButton("Cadastrar");
		Cadastro.setFont(new Font("Avignon Pro Xlight", Font.PLAIN, 11));
		Cadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Codico = "0000";
				String CodicoOp = JOptionPane.showInputDialog("Digite o senha de acesso:");

				if (Codico.equals(CodicoOp)) {
					// Chamando a Pagina Cadastro.
					ProjectFi.Cadastro.main(null);
					frmLoginPag.setVisible(false);
				} else {

					JOptionPane.showMessageDialog(null, "Senha incorreta");
				}

			}
		});
		Cadastro.setBounds(264, 118, 102, 23);
		frmLoginPag.getContentPane().add(Cadastro);

		JLabel lblNewLabel_1 = new JLabel("Login para Pagamento");
		lblNewLabel_1.setFont(new Font("Avignon Pro Xlight", Font.BOLD, 14));
		lblNewLabel_1.setBounds(120, 10, 172, 13);
		frmLoginPag.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("- Se for primeiro acesso clique em \"CADASTRAR\".");
		lblNewLabel_2.setFont(new Font("Avignon Pro Medium", Font.PLAIN, 10));
		lblNewLabel_2.setBounds(0, 175, 252, 13);
		frmLoginPag.getContentPane().add(lblNewLabel_2);

		JButton voltar = new JButton("Voltar");
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Diario.main(null);
				frmLoginPag.setVisible(false);
			}
		});
		voltar.setFont(new Font("Dialog", Font.PLAIN, 11));
		voltar.setBounds(10, 119, 89, 23);
		frmLoginPag.getContentPane().add(voltar);
	}
}
