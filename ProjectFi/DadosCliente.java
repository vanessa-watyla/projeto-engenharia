package ProjectFi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



public class DadosCliente {
	protected String ID,Nome, CPF,Horas,Atualiz,Usuario,Senha,Status,Cartao;

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String Usuario) {
		this.Usuario = Usuario;
	}
	public String getSenha() {
	
		return Senha;
	}

	public void setSenha(String Senha) {
		this.Senha = Senha;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String Nome) {
		this.Nome = Nome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String CPF) {
		this.CPF = CPF;
	}
	public String setHoras() {
		return Horas;
	}

	public void setHoras(String Horas) {
		this.Horas = Horas;
	}

	public String getCartao() {
		return Cartao;
	}

	public void setCartao(String Cartao) {
		this.Cartao = Cartao;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String Status) {
		this.Status = Status;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}
	public String getAtualiz(String Atualiz) {
		return Atualiz;
	}

	public void setAtualiz(String Atualiz) {
		this.Atualiz = Atualiz;
	}

	public static void main(String[] args) {

	}
	
	public String SalvarServidor() {
		// Bloco de exceção
		// Testar: bloco que irá tentar executar o código a seguir
		try {
			// FileWriter: Cria um arquivo, append true.
			FileWriter JJ = new FileWriter("src/ProjectFi/Cadastro.dat", true);
			// PrintWriter: Imprime uma String no arquivo
			PrintWriter Salvar = new PrintWriter(JJ);
			
			Salvar.println( this.Usuario);
			Salvar.println(this.Senha);
			Salvar.flush();
			Salvar.close();
			// Pegar: bloco de tratamento associado à condição da exceção
		} catch (IOException ex) {
			// Método da classe Throwable. O método exibe mensagem de erro no console.
			ex.printStackTrace();
		}
		return null; // Retorna nulo para evitar erro
	
	}
	//Adicionar mais um cliente no banco de dados.
	public String Salvar() {
		// Bloco de exceção
		// Testar: bloco que irá tentar executar o código a seguir
		try {
			// FileWriter: Cria um arquivo, append true.
			FileWriter JJ = new FileWriter("src/ProjectFi/CadastroClienteBC.dat", true);
			// PrintWriter: Imprime uma String no arquivo
			PrintWriter Salvar = new PrintWriter(JJ);
		
			Salvar.print( this.Nome+",");
			Salvar.print(this.CPF+",");
			Salvar.print(this.ID+",");
			Salvar.print(this.Horas+",");
			Salvar.print(this.Status+",");
			Salvar.print(this.Cartao+",");
			Salvar.println();
			Salvar.flush();
			Salvar.close();
			// Pegar: bloco de tratamento associado à condição da exceção
		} catch (IOException ex) {
			// Método da classe Throwable. O método exibe mensagem de erro no console.
			ex.printStackTrace();
		}
		return null; // Retorna nulo para evitar erro
	
	}
	//Atulizar o banco de dados
	public String Atualizar() {

		// Bloco de exceção
		// Testar: bloco que irá tentar executar o código a seguir
		try {
			// FileWriter: Cria um arquivo, append true.
			File file = new File("src/ProjectFi/CadastroClienteBC.dat");
			file.delete();
			FileWriter JJ = new FileWriter("src/ProjectFi/CadastroClienteBC.dat", true);
			// PrintWriter: Imprime uma String no arquivo
			PrintWriter Salvar = new PrintWriter(JJ);
			Salvar.println(this.Atualiz);
			Salvar.flush();
			Salvar.close();
			// Pegar: bloco de tratamento associado à condição da exceção
		} catch (IOException ex) {
			// Método da classe Throwable. O método exibe mensagem de erro no console.
			ex.printStackTrace();
		}
		return null; // Retorna nulo para evitar erro

	}
	public String Deletar() {

		// Bloco de exceção
		// Testar: bloco que irá tentar executar o código a seguir
		try {
			// FileWriter: Cria um arquivo, append true.
			File file = new File( "src/ProjectFi/CadastroClienteBC.dat" ); file.delete();
			FileWriter JJ = new FileWriter("src/ProjectFi/CadastroClienteBC.dat", true);
			// PrintWriter: Imprime uma String no arquivo
			PrintWriter Salvar = new PrintWriter(JJ);
			Salvar.println(this.Atualiz);
			Salvar.flush();
			Salvar.close();
			// Pegar: bloco de tratamento associado à condição da exceção
		} catch (IOException ex) {
			// Método da classe Throwable. O método exibe mensagem de erro no console.
			ex.printStackTrace();
		}
		return null; // Retorna nulo para evitar erro

	}



}
