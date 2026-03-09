天气预办

3/3

0，在yml配置baseurl和key。这样安全点，记得gitignore不要提交这个文件！

1，apihost+locationid+key(项目中获取)

[m77fc2t3hn.re.qweatherapi.com/v7/weather/now?location=101010100&key=7847144afc7d43f8910d9d862712df70](https://m77fc2t3hn.re.qweatherapi.com/v7/weather/now?location=101010100&key=7847144afc7d43f8910d9d862712df70)

~~~json
{
  "code": "200",
  "updateTime": "2026-03-03T08:43+08:00",
  "fxLink": "https://www.qweather.com/weather/beijing-101010100.html",
  "now": {
    "obsTime": "2026-03-03T08:40+08:00",
    "temp": "3",
    "feelsLike": "0",
    "icon": "501",
    "text": "雾",
    "wind360": "208",
    "windDir": "西南风",
    "windScale": "1",
    "windSpeed": "2",
    "humidity": "84",
    "precip": "0.0",
    "pressure": "1023",
    "vis": "2",
    "cloud": "10",
    "dew": "-2"
  },
  "refer": {
    "sources": [
      "QWeather"
    ],
    "license": [
      "QWeather Developers License"
    ]
  }
}
~~~

2，问题:返回的是json，怎么转换为java对象？用jackson，转换json、或者restTemplate自带jackson，自动转换。
3，开发实时天气接口，返回数据，用result返回，里面是weather，
4，用ai给工具类类型校准
5，返回的数据是gzip的，需要解密

~~~java
    @Bean
    public RestTemplate restTemplate() {

        CloseableHttpClient httpClient = HttpClients.custom().build();
        //创建可以gzip的httpClient

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        //基于httpclient定义工厂

        return new RestTemplate(factory);
        //包装成 Spring 能用的请求工厂
        //将 Apache HttpClient 适配到 Spring 的 RestTemplate 体系中。
        //基于工厂返回新的resttemplate
        

    }
~~~

RestTemplate 本身不直接发 HTTP 请求，而是委托给 ClientHttpRequestFactory.

这样restTemplate就能拥有解析gzip的能力了。
6，后端接口开发完毕

7，设计前端
让ai帮我生成一个前端了，margn:0 auto;可以调整位置
8，基本demo完成，耗时3小时





9，提示参数错误，是因为前端提供的和后端需要的类型不匹配，检查检查类型吧

10，md，前端的问题，get传入的参数是param，需要

~~~js
export const getRealTimeWeather = (location) => request.get("/api/weather", { params: { location } });
~~~

这样后端才能正确接收!！！！！！！！！！！！！！！！！！我现在才知道。get和put有区别，需要params对象
11，该死的ref，返回的是响应式对象，必须加value才能获取值

~~~js
let weather=ref({});和let weather=ref();
//初始值的问题，{}是空对象，不填就是未定义
~~~

12，完成aop的log记录执行时间和耗时。
13，完成地图数据的静态获取，数据放到asset下

~~~json

~~~

14，终于，捣鼓了一小时，终于把这个json数据和我的前端页面对接成功了，选择省份之后过滤城市，过滤出城市，就可以选取了
15,完成了选择城市切换天气功能，中间遇到了不少bug，最重要的是value的bug，再也不要犯了，ok？

16，demo完成，但是有严重的bug:显示不正确，需要修正