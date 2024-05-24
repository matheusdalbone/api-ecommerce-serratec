package br.org.serratec.ecommerce.dtos;

import java.sql.Date;
import java.time.LocalDate;

import br.org.serratec.ecommerce.entities.Cliente;
import br.org.serratec.ecommerce.entities.Endereco;

public class ClienteDto {

    private Integer clienteId;
    private String nomeCompleto;
    private String cpf;
    private String telefone;
    private Date dataNascimento;
    private Endereco endereco;

    public ClienteDto(Cliente cliente) {
        super();
        this.clienteId = cliente.getClienteId();
        this.nomeCompleto = cliente.getNomeCompleto();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTefelone();
        this.dataNascimento = cliente.getDataNascimento();
        this.endereco = cliente.getEndereco();
    }


	public Integer getClienteId() {
		return clienteId;
	}


	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
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


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public Date getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	

	


}