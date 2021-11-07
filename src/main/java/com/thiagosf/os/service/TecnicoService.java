package com.thiagosf.os.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.thiagosf.os.domain.Pessoa;
import com.thiagosf.os.domain.Tecnico;
import com.thiagosf.os.dtos.TecnicoDTO;
import com.thiagosf.os.repositories.PessoaRepository;
import com.thiagosf.os.repositories.TecnicoRepository;
import com.thiagosf.os.service.exceptions.DataIntegrityViolationException;
import com.thiagosf.os.service.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public List<Tecnico> findAll() {
		List<Tecnico> tecnicoOptional = repository.findAll();
		return tecnicoOptional;
	}

	public Tecnico findById(Integer id) {
		Optional<Tecnico> tecnicoOptional = repository.findById(id);
		return tecnicoOptional.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto de ID [" + id + "] não encontrado! Tipo: " + Tecnico.class.getSimpleName()));
	}

	public Tecnico create(TecnicoDTO tecnicoDTO) {
		if (this.findByCpf(tecnicoDTO) != null) {
			throw new DataIntegrityViolationException("CPF já cadastrado na base de dados!");
		}
		return repository.save(new Tecnico(null, tecnicoDTO.getNome(), tecnicoDTO.getCpf(), tecnicoDTO.getTelefone()));
	}

	public Tecnico update(Integer id, @Valid TecnicoDTO newTecnico) {
		Tecnico oldTecnico = this.findById(id);
		if (this.findByCpf(newTecnico) != null && this.findByCpf(newTecnico).getId() != id) {
			throw new DataIntegrityViolationException("CPF já cadastrado na base de dados!");
		}
		oldTecnico.setCpf(newTecnico.getCpf());
		oldTecnico.setNome(newTecnico.getNome());
		oldTecnico.setTelefone(newTecnico.getTelefone());
		return repository.save(oldTecnico);
	}

	public void delete(Integer id) {
		Tecnico tecnico = this.findById(id);
		if (tecnico.getList().size() > 0) {
			throw new DataIntegrityViolationException("Técnico que possui Ordem de Serviço não pode ser deletado!");
		}
		repository.deleteById(id);
	}

	private Pessoa findByCpf(TecnicoDTO tecnicoDTO) {
		return pessoaRepository.findByCpf(tecnicoDTO.getCpf()).orElse(null);
	}

}
