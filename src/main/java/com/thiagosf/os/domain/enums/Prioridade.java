package com.thiagosf.os.domain.enums;

public enum Prioridade {

	BAIXA(0, "baixa"), MEDIA(1, "media"), ALTA(2, "alta");

	private Integer cod;
	private String descricao;

	private Prioridade(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Prioridade toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (Prioridade prioridade : Prioridade.values()) {
			if (cod.equals(prioridade.cod)) {
				return prioridade;
			}
		}

		throw new IllegalArgumentException("Prioridade [" + cod + "] é inválida!");
	}

}
