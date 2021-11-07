package com.thiagosf.os.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.thiagosf.os.domain.Cliente;
import com.thiagosf.os.domain.Pessoa;
import com.thiagosf.os.dtos.ClienteDTO;
import com.thiagosf.os.repositories.ClienteRepository;
import com.thiagosf.os.repositories.PessoaRepository;
import com.thiagosf.os.service.exceptions.DataIntegrityViolationException;
import com.thiagosf.os.service.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public List<Cliente> findAll() {
		List<Cliente> clienteOptional = repository.findAll();
		return clienteOptional;
	}

	public Cliente findById(Integer id) {
		Optional<Cliente> clienteOptional = repository.findById(id);
		return clienteOptional.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto de ID [" + id + "] não encontrado! Tipo: " + Cliente.class.getSimpleName()));
	}

	public Cliente create(ClienteDTO clienteDTO) {
		if (this.findByCpf(clienteDTO) != null) {
			throw new DataIntegrityViolationException("CPF já cadastrado na base de dados!");
		}
		return repository.save(new Cliente(null, clienteDTO.getNome(), clienteDTO.getCpf(), clienteDTO.getTelefone()));
	}

	public Cliente update(Integer id, @Valid ClienteDTO newCliente) {
		Cliente oldCliente = this.findById(id);
		if (this.findByCpf(newCliente) != null && this.findByCpf(newCliente).getId() != id) {
			throw new DataIntegrityViolationException("CPF já cadastrado na base de dados!");
		}
		oldCliente.setCpf(newCliente.getCpf());
		oldCliente.setNome(newCliente.getNome());
		oldCliente.setTelefone(newCliente.getTelefone());
		return repository.save(oldCliente);
	}

	public void delete(Integer id) {
		Cliente cliente = this.findById(id);
		if (cliente.getList().size() > 0) {
			throw new DataIntegrityViolationException("Cliente que possui Ordem de Serviço não pode ser deletado!");
		}
		repository.deleteById(id);
	}

	private Pessoa findByCpf(ClienteDTO clienteDTO) {
		return pessoaRepository.findByCpf(clienteDTO.getCpf()).orElse(null);
	}

}
