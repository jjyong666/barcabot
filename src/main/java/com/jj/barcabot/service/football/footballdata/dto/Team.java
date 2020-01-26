
package com.jj.barcabot.service.football.footballdata.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Team implements Serializable {

  private int id;
  private Area area;
  private String name;
  private String shortName;
  private String tla;
  private String address;
  private String phone;
  private String website;
  private String email;
  private int founded;
  private String clubColors;
  private Map<String, String> venue;
  private List<Squad> squad = new ArrayList<>();
  private String lastUpdated;

}
