package com.jj.barcabot.service.serializer;

import com.jj.barcabot.service.football.dto.NextMatchDto;
import java.util.List;

public interface NextMatchesSerializer {

  String serialize(List<NextMatchDto> matches);

}
