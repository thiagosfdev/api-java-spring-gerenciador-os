package com.thiagosf.os.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.thiagosf.os.domain.Tecnico;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TecnicoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Campo NOME é obrigatório")
	private String nome;

	@CPF
	@NotEmpty(message = "Campo CPF é obrigatório")
	private String cpf;

	@NotEmpty(message = "Campo TELEFONE é obrigatório")
	private String telefone;

	public TecnicoDTO(Tecnico tecnico) {
		super();
		this.id = tecnico.getId();
		this.nome = tecnico.getNome();
		this.cpf = tecnico.getCpf();
		this.telefone = tecnico.getTelefone();
	}

}
