package com.thiagosf.os.resources.exceptions;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StandardError implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long timestamp;
  private Integer status;
  private String error;

  public StandardError(Long timestamp, Integer status, String error) {
    this.timestamp = timestamp;
    this.status = status;
    this.error = error;
  }

}
