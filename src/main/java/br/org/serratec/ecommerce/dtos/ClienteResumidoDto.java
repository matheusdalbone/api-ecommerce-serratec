package br.org.serratec.ecommerce.dtos;

public class ClienteResumidoDto {
	
	private String email;
	
	private String nomeCompleto;
	
	private String cpf;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "ClienteResumidoDto [email=" + email + ", nomeCompleto=" + nomeCompleto + ", cpf=" + cpf + "]";
	}
	
	
}	
