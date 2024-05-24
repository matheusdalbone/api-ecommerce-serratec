package br.org.serratec.ecommerce.enums;

public enum StatusEnum {
	STATUS_AGUARDANDOPAGAMENTO(1), STATUS_ENVIADO(2), STATUS_EMTRANSITO(3), STATUS_ENTREGUE(4);

	private int codigo;

	private StatusEnum(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public static StatusEnum valorCodigo(int codigo) {
		for (StatusEnum valor : StatusEnum.values()) {
			if (valor.getCodigo() == codigo) {
				return valor;
			}
		}
		throw new IllegalArgumentException("Código de Status Inválido");
	}

}
