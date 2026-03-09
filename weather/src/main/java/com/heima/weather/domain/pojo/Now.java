package com.heima.weather.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实时天气数据 POJO 类
 * 用于封装当前天气的实时观测数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Now {

    /**
     * 数据观测时间
     * API 返回的实时天气数据的采集时间点
     * 格式示例：2020-06-30T21:40+08:00
     */
    private String obsTime;

    /**
     * 温度（摄氏度）
     * 当前环境气温，单位：°C
     */
    private Integer temp;

    /**
     * 体感温度（摄氏度）
     * 人体实际感受到的温度，考虑了湿度、风速等因素，单位：°C
     */
    private Integer feelsLike;

    /**
     * 天气图标代码
     * 表示当前天气状况的图标标识符，可参考天气图标项目获取对应图标
     */
    private String icon;

    /**
     * 天气状况文字描述
     * 对当前天气状态的文本描述，如晴、阴、雨、雪等
     */
    private String text;

    /**
     * 风向 360 度角度
     * 风的来源方向，以角度值表示（0-360 度）
     */
    private Integer wind360;

    /**
     * 风向
     * 风的来源方向的文字描述，如东风、西风、西北风等
     */
    private String windDir;

    /**
     * 风力等级
     * 根据蒲福风级或其他标准划分的风力强度等级
     */
    private Integer windScale;

    /**
     * 风速（公里/小时）
     * 风的移动速度，单位：km/h
     */
    private Double windSpeed;

    /**
     * 相对湿度（百分比）
     * 空气中水汽含量与饱和水汽含量的比值，以百分比表示
     */
    private Integer humidity;

    /**
     * 过去 1 小时降水量（毫米）
     * 最近 1 小时内的累计降水量，单位：mm
     */
    private Double precip;

    /**
     * 大气压强（百帕）
     * 大气压力值，单位：hPa（百帕）
     */
    private Integer pressure;

    /**
     * 能见度（公里）
     * 正常视力能够辨认目标物的最大水平距离，单位：km
     */
    private Integer vis;

    /**
     * 云量（百分比）
     * 天空被云遮蔽的比例，以百分比表示
     * 注意：该字段可能为空
     */
    private Integer cloud;

    /**
     * 露点温度（摄氏度）
     * 空气冷却到饱和状态时的温度，单位：°C
     * 注意：该字段可能为空
     */
    private Integer dew;
}
