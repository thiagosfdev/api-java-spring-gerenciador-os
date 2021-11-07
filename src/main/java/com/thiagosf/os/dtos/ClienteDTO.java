package com.thiagosf.os.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.thiagosf.os.domain.Cliente;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Campo NOME é obrigatório")
	private String nome;

	@CPF
	@NotEmpty(message = "Campo CPF é obrigatório")
	private String cpf;

	@NotEmpty(message = "Campo TELEFONE é obrigatório")
	private String telefone;

	public ClienteDTO(Cliente cliente) {
		super();
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.telefone = cliente.getTelefone();
	}

}
