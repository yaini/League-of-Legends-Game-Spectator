package com.yaini.batch.client.web.support.exception;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(@NotEmpty final String message) {
    super(message);
  }

  public ResourceNotFoundException(@NotEmpty final String message, final Throwable cause) {
    super(message, cause);
  }
}
