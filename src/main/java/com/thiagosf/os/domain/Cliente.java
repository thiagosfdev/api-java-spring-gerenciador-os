package com.thiagosf.os.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Pessoa {

	@OneToMany(mappedBy = "cliente")
	private List<Os> list = new ArrayList<>();

	public Cliente(Integer id, String cpf, String nome, String telefone) {
		super(id, cpf, nome, telefone);
	}

}
