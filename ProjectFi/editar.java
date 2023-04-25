package ProjectFi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class editar {

	private JFrame frame;
	private JTextField ID;
	private static String Lista;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editar window = new editar();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public editar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		ID = new JTextField();
		ID.setBounds(50, 65, 86, 20);
		frame.getContentPane().add(ID);
		ID.setColumns(10);
		
		JButton btnNewButton = new JButton("Excluir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Scanner BD;
				try {
					BD = new Scanner(new File("src/ProjectFi/CadastroClienteBC.dat"), "UTF-8").useDelimiter("\\A");
					Lista = BD.next();
					BD.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String[] Check = Lista.split("\\r?\\n");
				String Excluir = "";
				int F = Check.length;

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
				DadosCliente novo = new DadosCliente();

				novo.setAtualiz(Excluir);
				novo.Deletar();
			}

		});
		btnNewButton.setBounds(47, 199, 89, 23);
		frame.getContentPane().add(btnNewButton);

	}
}
