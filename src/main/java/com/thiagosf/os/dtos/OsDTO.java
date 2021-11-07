package com.thiagosf.os.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thiagosf.os.domain.Os;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OsDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;

  @JsonFormat(pattern = "dd/MM/yyyy 'às' HH:mm")
  private LocalDateTime dataAbertura;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
  private LocalDateTime dataFechamento;

  private Integer prioridade;

  @NotEmpty(message = "Campo OBSERVACOES deve ser preenchido!")
  private String observacoes;

  private Integer status;

  @NotNull(message = "Campo TECNICO_ID deve ser preenchido!")
  private Integer tecnicoId;

  @NotNull(message = "Campo CLIENTE_ID deve ser preenchido!")
  private Integer clienteId;

  public OsDTO(Os os) {
    super();
    this.id = os.getId();
    this.dataAbertura = os.getDataAbertura();
    this.dataFechamento = os.getDataFechamento();
    this.prioridade = os.getPrioridade().getCod();
    this.observacoes = os.getObservacoes();
    this.status = os.getStatus().getCod();
    this.tecnicoId = os.getTecnico().getId();
    this.clienteId = os.getCliente().getId();
  }

}
