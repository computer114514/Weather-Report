<script setup>
import { onMounted, watch } from "vue";
import { getRealTimeWeather } from "./api/WeatherApi";
import SelectArea from "./components/SelectArea.vue";
import { ref } from "vue";
import { useCityCodeStore } from "@/store/city";
import { storeToRefs } from "pinia";

const cityCodeStore = useCityCodeStore();

let { citycode, cityName } = storeToRefs(cityCodeStore);
let weather = ref({});
watch(citycode, async () => {
  weather.value = await getRealTimeWeather(citycode.value);
  console.log("變化了");
});
onMounted(async () => {
  weather.value = await getRealTimeWeather(citycode.value);
});

const changeDialogTableVisible = (bool) => {
  if (!bool) {
    dialogTableVisible.value = false;
    console.log("114514");
  } else {
    dialogTableVisible.value = true;
  }
};

const dialogTableVisible = ref(false);
</script>

<template>
  <el-dialog v-model="dialogTableVisible" width="800">
    <select-area v-model="weather" @changeDialogTableVisible="changeDialogTableVisible" />
  </el-dialog>
  <div class="weather-container">
    <div class="header">
      <h1>欢迎使用computer114514的天气预办</h1>
      <p>实时更新的天气信息，助您轻松规划每一天</p>
    </div>
    <div class="current-weather">
      <span class="city-name"> <i class="fas fa-map-marker-alt"></i> 当前城市：{{ cityName }} </span>
      <el-button @click="dialogTableVisible = true">选择城市</el-button>

      <div class="weather-icon">
        <i class="fas fa-sun"></i>
      </div>
      <div class="temperature">{{ weather.now?.temp }}°</div>
      <div class="feels-like">体感 {{ weather.now?.feelsLike }}°</div>
      <div class="weather-note">
        {{ weather.now?.text || "天气数据加载中..." }}
      </div>
    </div>

    <div class="weather-details">
      <div class="detail-card">
        <h3><i class="fas fa-wind"></i> 天气详情</h3>
        <div class="detail-item">
          <span class="detail-label">风向：</span>
          <span class="detail-value">{{ weather.now?.windDir }}，{{ weather.now?.wind360 }}度</span>
        </div>
        <div class="detail-item">
          <span class="detail-label">风力等级：</span>
          <span class="detail-value">{{ weather.now?.windScale }}级</span>
        </div>
        <div class="detail-item">
          <span class="detail-label">风速：</span>
          <span class="detail-value">{{ weather.now?.windSpeed }}km/h</span>
        </div>
        <div class="detail-item">
          <span class="detail-label">相对湿度：</span>
          <span class="detail-value">{{ weather.now?.humidity }}%</span>
        </div>
        <div class="detail-item">
          <span class="detail-label">一小时降雨量：</span>
          <span class="detail-value">{{ weather.now?.precip }}mm</span>
        </div>
      </div>

      <div class="detail-card">
        <h3><i class="fas fa-cloud"></i> 环境指标</h3>
        <div class="detail-item">
          <span class="detail-label">大气压强：</span>
          <span class="detail-value">{{ weather.now?.pressure }}kpa</span>
        </div>
        <div class="detail-item">
          <span class="detail-label">能见度：</span>
          <span class="detail-value">{{ weather.now?.vis }}km</span>
        </div>
        <div class="detail-item">
          <span class="detail-label">云量：</span>
          <span class="detail-value">{{ weather.now?.cloud }}%</span>
        </div>
        <div class="detail-item">
          <span class="detail-label">露点温度：</span>
          <span class="detail-value">{{ weather.now?.dew }}°</span>
        </div>
      </div>
    </div>

    <div class="footer">
      <p>天气数据更新时间：{{ weather.updateTime }} | 数据来源：气象局</p>
    </div>
  </div>
</template>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  background: linear-gradient(135deg, #1a2a6c, #2c3e50);
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
  color: #333;
}

.weather-container {
  margin: 0 auto;
  background: rgba(255, 255, 255, 0.92);
  border-radius: 24px;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.3);
  width: 100%;
  max-width: 900px;
  overflow: hidden;
  position: relative;
  border: 1px solid rgba(0, 0, 0, 0.1);
}

.header {
  background: linear-gradient(to right, #4b6cb7, #182848);
  color: white;
  padding: 25px 30px;
  text-align: center;
  position: relative;
}

.header h1 {
  font-size: 2.2rem;
  font-weight: 600;
  margin-bottom: 10px;
  letter-spacing: 0.5px;
  text-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
}

.header p {
  font-size: 1.1rem;
  opacity: 0.9;
  max-width: 600px;
  margin: 0 auto;
  line-height: 1.5;
}

.current-weather {
  padding: 35px 30px;
  text-align: center;
  background: white;
}

.city-name {
  font-size: 1.8rem;
  font-weight: 600;
  color: #2c3e50;
  margin-bottom: 15px;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;
}

.weather-icon {
  font-size: 5rem;
  margin: 15px 0;
  color: #4a90e2;
  text-shadow: 0 2px 4px rgba(74, 144, 226, 0.3);
}

.temperature {
  font-size: 4.5rem;
  font-weight: 700;
  color: #e74c3c;
  line-height: 1;
  margin: 10px 0;
}

.feels-like {
  font-size: 1.8rem;
  color: #2c3e50;
  opacity: 0.8;
}

.weather-note {
  font-size: 1.2rem;
  color: #e74c3c;
  margin-top: 20px;
  font-style: italic;
  padding: 12px;
  background: rgba(231, 76, 60, 0.08);
  border-radius: 12px;
  border-left: 4px solid #e74c3c;
}

.weather-details {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
  padding: 30px 30px 40px;
  background: #f8f9fa;
}

.detail-card {
  background: white;
  border-radius: 16px;
  padding: 25px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08);
  border: 1px solid #eee;
}

.detail-card h3 {
  font-size: 1.4rem;
  color: #2c3e50;
  margin-bottom: 20px;
  padding-bottom: 12px;
  border-bottom: 2px solid #eee;
  display: flex;
  align-items: center;
  gap: 10px;
}

.detail-card h3 i {
  color: #4a90e2;
}

.detail-item {
  font-size: 1.1rem;
  margin: 12px 0;
  display: flex;
  justify-content: space-between;
  padding: 8px 0;
  border-bottom: 1px dashed #eee;
}

.detail-item:last-child {
  border-bottom: none;
}

.detail-label {
  color: #555;
}

.detail-value {
  font-weight: 600;
  color: #2c3e50;
}

.footer {
  text-align: center;
  padding: 20px 30px;
  color: #7f8c8d;
  font-size: 0.95rem;
  border-top: 1px solid #eee;
}

/* 响应式调整 */
@media (max-width: 768px) {
  .weather-details {
    grid-template-columns: 1fr;
  }

  .temperature {
    font-size: 3.5rem;
  }

  .city-name {
    font-size: 1.5rem;
  }
}

@media (max-width: 480px) {
  .header h1 {
    font-size: 1.8rem;
  }

  .weather-icon {
    font-size: 4rem;
  }

  .temperature {
    font-size: 2.8rem;
  }
}
</style>
