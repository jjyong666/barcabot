
package com.jj.barcabot.service.football.footballdata.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Assist implements Serializable {

  private int id;
  private String name;

}
