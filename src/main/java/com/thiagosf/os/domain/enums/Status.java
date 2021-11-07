package com.thiagosf.os.domain.enums;

public enum Status {

	ABERTO(0, "aberto"), ANDAMENTO(1, "andamento"), ENCERRADO(2, "encerrado");

	private Integer cod;
	private String descricao;

	private Status(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Status toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (Status status : Status.values()) {
			if (cod.equals(status.cod)) {
				return status;
			}
		}

		throw new IllegalArgumentException("Status [" + cod + "] é inválido!");
	}

}
