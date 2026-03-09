package com.heima.weather.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {
    private Integer code;
    private String updateTime;
    private String fxLink;
    private Now now;
}
