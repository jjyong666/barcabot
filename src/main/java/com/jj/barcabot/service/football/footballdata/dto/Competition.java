
package com.jj.barcabot.service.football.footballdata.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Competition implements Serializable {

  private int id;
  private Area area;
  private String name;
  private String code;
  private String plan;
  private Season currentSeason;
  private List<Season> seasons = new ArrayList<>();
  private String lastUpdated;

}
