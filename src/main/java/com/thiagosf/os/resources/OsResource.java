package com.thiagosf.os.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.thiagosf.os.dtos.OsDTO;
import com.thiagosf.os.service.OsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/os")
public class OsResource {

  @Autowired
  private OsService service;

  @GetMapping(value = "/{id}")
  public ResponseEntity<OsDTO> findById(@PathVariable Integer id) {
    OsDTO osDTO = new OsDTO(service.findById(id));
    return ResponseEntity.ok().body(osDTO);
  }

  @GetMapping
  public ResponseEntity<List<OsDTO>> findByAll() {
    List<OsDTO> osDTOList = service.findByAll().stream().map(os -> new OsDTO(os)).collect(Collectors.toList());
    return ResponseEntity.ok().body(osDTOList);
  }

  @PostMapping
  public ResponseEntity<OsDTO> create(@Valid @RequestBody OsDTO newOsDTO) {
    newOsDTO = new OsDTO(service.create(newOsDTO));
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newOsDTO.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @PutMapping
  public ResponseEntity<OsDTO> update(@Valid @RequestBody OsDTO newOsDTO) {
    newOsDTO = new OsDTO(service.update(newOsDTO));
    return ResponseEntity.ok().body(newOsDTO);
  }

}