package com.gustavoblima.company.dto;

import javax.sound.sampled.Line;
import java.util.List;

public class RUWrapperDTO {

    private List<RUResultDTO> results = null;
    private RUInfoDTO info;

    public List<RUResultDTO> getResults() {
        return results;
    }

    public void setResults(List<RUResultDTO> results) {
        this.results = results;
    }

    public RUInfoDTO getInfo() {
        return info;
    }

    public void setInfo(RUInfoDTO info) {
        this.info = info;
    }
}
