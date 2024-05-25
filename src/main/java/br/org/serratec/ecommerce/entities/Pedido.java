package br.org.serratec.ecommerce.entities;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.org.serratec.ecommerce.enums.StatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private Integer idPedido;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "data_pedido")
	private LocalDateTime dataPedido;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_envio")
	private Date dataEnvio;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_entrega")
	private Date dataEntrega;
	
	@Column(name = "status")
	private StatusEnum status;

	@Column(name = "valor_total")
	private Double valorTotal;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido")
	@JsonIgnore
	private List<ItemPedido> itensPedido;

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public LocalDateTime getDataPedido() {
		return LocalDateTime.now();
	}

	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public StatusEnum getStatus() {
		return validaStatus();
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}
	
	public StatusEnum validaStatus() {
		if (this.status == null) {
			this.status = StatusEnum.PEDIDO_EM_ABERTO;
		}
		switch(this.status) {
		case PEDIDO_REALIZADO:
			if(this.dataEnvio != null) {
				this.status =  StatusEnum.EM_TRANSITO;
			} else {
				 this.status = StatusEnum.PEDIDO_REALIZADO;
			}
		case EM_TRANSITO:
			if(this.dataEntrega != null) {
				this.status =  StatusEnum.PEDIDO_ENTREGUE;
			}else {
				this.status =  StatusEnum.PEDIDO_REALIZADO;
			}
		case PEDIDO_ENTREGUE:
			return this.status;
		default:
			break;
		}
		
		return this.status;
	}
}
