package com.devsu.utilities.enums;

import com.devsu.utilities.exception.CustomApiException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@AllArgsConstructor
@Getter
public enum ApiException {
  CLI0001("CLI0001", "Cliente no encontrado", BAD_REQUEST),
  CLI0002("CLI0002", "identificador del cliente ya fue registrado ", BAD_REQUEST),
  CLI0003("CLI0003", "El password no coincide con el patron definido", BAD_REQUEST),
  CLI0004("CLI0004", "Campos obligatorios: id", BAD_REQUEST),
  CLI0005("CLI0005", "Campos obligatorios: id, name, gender, age, identification, address, phone, password, status", BAD_REQUEST),
  CLI0006("CLI0006", "Campos obligatorios: name, gender, age, identification, address, phone, password, status", BAD_REQUEST),
  CLI0007("CLI0007", "Documento identificador ya está registrado.", BAD_REQUEST);

  private final String code;
  private final String description;
  private final HttpStatus status;
  private static final List<ApiException> list = new ArrayList<>();

  private static final Map<String, ApiException> lookup = new HashMap<>();

  static {
    for (ApiException s : EnumSet.allOf(ApiException.class)) {
      list.add(s);
      lookup.put(s.getCode(), s);
    }
  }

  public static ApiException get(String code) {
    return lookup.get(code);
  }

  public CustomApiException getException() {
    return new CustomApiException(this.getCode(), this.getDescription(), this.getStatus());
  }
}
