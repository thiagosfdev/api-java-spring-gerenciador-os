package com.thiagosf.os.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.thiagosf.os.domain.Cliente;
import com.thiagosf.os.domain.Os;
import com.thiagosf.os.domain.Tecnico;
import com.thiagosf.os.domain.enums.Prioridade;
import com.thiagosf.os.domain.enums.Status;
import com.thiagosf.os.dtos.OsDTO;
import com.thiagosf.os.repositories.OsRepository;
import com.thiagosf.os.service.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OsService {

  @Autowired
  private OsRepository repository;

  @Autowired
  private TecnicoService tecnicoService;

  @Autowired
  private ClienteService clienteService;

  public Os findById(Integer id) {
    Optional<Os> optionalOs = repository.findById(id);
    return optionalOs.orElseThrow(() -> new ObjectNotFoundException(
        "Objeto de ID [" + id + "] não encontrado! Tipo: " + Os.class.getSimpleName()));
  }

  public List<Os> findByAll() {
    return repository.findAll();
  }

  public Os create(@Valid OsDTO newOsDTO) {
    return repository.save(this.fromDTO(newOsDTO));
  }

  public Os update(@Valid OsDTO newOsDTO) {
    this.findById(newOsDTO.getId());
    return repository.save(this.fromDTO(newOsDTO));
  }

  private Os fromDTO(OsDTO newOsDTO) {
    Os newOs = new Os();
    newOs.setId(newOsDTO.getId());
    newOs.setObservacoes(newOsDTO.getObservacoes());
    newOs.setPrioridade(Prioridade.toEnum(newOsDTO.getPrioridade()));
    newOs.setStatus(Status.toEnum(newOsDTO.getStatus()));

    Cliente cliente = clienteService.findById(newOsDTO.getClienteId());
    newOs.setCliente(cliente);

    Tecnico tecnico = tecnicoService.findById(newOsDTO.getTecnicoId());
    newOs.setTecnico(tecnico);

    if (newOs.getStatus().getCod() == 2) {
      newOs.setDataFechamento(LocalDateTime.now());
    }

    return newOs;
  }

}
