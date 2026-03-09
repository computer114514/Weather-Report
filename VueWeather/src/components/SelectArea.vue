<template>
  <div class="location-selector-container">
    <h3>请选择所在地区</h3>

    <div class="select-group">
      <!-- 省份选择 -->
      <el-select
        v-model="selectedProvinceId"
        placeholder="选择省份"
        style="width: 100%"
        sss
        @change="handleProvinceChange"
        clearable
      >
        <el-option v-for="item in provinces" :key="item.provinceId" :label="item.name" :value="item.provinceId" />
      </el-select>

      <!-- 城市选择 -->
      <el-select
        v-model="selectedCity"
        placeholder="选择城市"
        style="width: 100%"
        :disabled="!selectedProvinceId"
        @change="handleCityChange"
        clearable
      >
        <el-option v-for="item in filteredCities" :key="item.code" :label="item.name" :value="item" />
      </el-select>

      <!-- 区县选择 -->
      <!-- <el-select 
        v-model="selectedDistrict" 
        placeholder="选择区县" 
        style="width: 100%;"
        :disabled="!selectedCity"
        clearable
      >
        <el-option
          v-for="item in districtList"
          :key="item.code"
          :label="item.name"
          :value="item.code"
        />
      </el-select> -->
    </div>

    <!-- 展示选中结果 -->
    <!-- <div class="result-display" v-if="fullAddress">
      <el-tag type="success" effect="plain">选中区域: {{ fullAddress }}</el-tag>
      <div class="code-display">
        <small>代码: 省({{ selectedProvince }}) - 市({{ selectedCity }}) - 区({{ selectedDistrict }})</small>
        <small>代码: 省({{ selectedProvince }}) - 市({{ selectedCity }})</small>
      </div>
    </div>-->
    <div><el-button type="success" class="confirmButtom" @Click="confirm">确认选择</el-button></div>
  </div>
  {{ selectedCity }}
</template>

<script setup>
import { ref } from "vue";
import { cities } from "@/assets/cities";
import { provinces } from "@/assets/provinces";

import { useCityCodeStore } from "@/store/city";

// // --- 状态定义 ---
// const selectedProvince = ref("");
// const selectedCity = ref("");
// const selectedDistrict = ref("");

// const provinceList = ref([]);
// const cityList = ref([]);
// const districtList = ref([]);

// // --- 模拟数据 (实际项目中建议从后端 API 获取) ---
// // 这里只列出了部分数据作为演示，完整数据请替换为真实的 JSON
// const mockData = {
//   110000: {
//     name: "北京市",
//     code: "110000",
//     children: [
//       {
//         code: "110100",
//         name: "市辖区",
//         children: [
//           { code: "110101", name: "东城区" },
//           { code: "110102", name: "西城区" },
//           { code: "110105", name: "朝阳区" },
//           { code: "110106", name: "丰台区" },
//         ],
//       },
//     ],
//   },
//   440000: {
//     name: "广东省",
//     code: "440000",
//     children: [
//       {
//         code: "440100",
//         name: "广州市",
//         children: [
//           { code: "440103", name: "荔湾区" },
//           { code: "440104", name: "越秀区" },
//           { code: "440105", name: "海珠区" },
//           { code: "440106", name: "天河区" },
//         ],
//       },
//       {
//         code: "440300",
//         name: "深圳市",
//         children: [
//           { code: "440303", name: "罗湖区" },
//           { code: "440304", name: "福田区" },
//           { code: "440305", name: "南山区" },
//           { code: "440306", name: "宝安区" },
//         ],
//       },
//     ],
//   },
//   330000: {
//     name: "浙江省",
//     code: "330000",
//     children: [
//       {
//         code: "330100",
//         name: "杭州市",
//         children: [
//           { code: "330102", name: "上城区" },
//           { code: "330105", name: "拱墅区" },
//           { code: "330106", name: "西湖区" },
//         ],
//       },
//       {
//         code: "330200",
//         name: "宁波市",
//         children: [
//           { code: "330203", name: "海曙区" },
//           { code: "330205", name: "江北区" },
//         ],
//       },
//     ],
//   },
// };

// // --- 方法逻辑 ---

// // 初始化加载省份
// const loadProvinces = () => {
//   const list = [];
//   for (const key in mockData) {
//     list.push({
//       code: mockData[key].code,
//       name: mockData[key].name,
//     });
//   }
//   provinceList.value = list;
// };

// // 选择省份后触发
// const handleProvinceChange = (provinceCode) => {
//   // 重置下级选择
//   selectedCity.value = "";
//   selectedDistrict.value = "";
//   cityList.value = [];
//   districtList.value = [];

//   if (!provinceCode) return;

//   const provinceData = mockData[provinceCode];
//   if (provinceData && provinceData.children) {
//     cityList.value = provinceData.children.map((city) => ({
//       code: city.code,
//       name: city.name,
//     }));
//   }
// };

// // 选择城市后触发
// const handleCityChange = (cityCode) => {
//   // 重置下级选择
//   selectedDistrict.value = "";
//   districtList.value = [];

//   if (!selectedProvince.value || !cityCode) return;

//   const provinceData = mockData[selectedProvince.value];
//   const cityData = provinceData.children.find((c) => c.code === cityCode);

//   if (cityData && cityData.children) {
//     districtList.value = cityData.children.map((district) => ({
//       code: district.code,
//       name: district.name,
//     }));
//   }
// };

// // 计算完整地址文本
// const fullAddress = computed(() => {
//   const pName = provinceList.value.find((p) => p.code === selectedProvince.value)?.name || "";
//   const cName = cityList.value.find((c) => c.code === selectedCity.value)?.name || "";
//   const dName = districtList.value.find((d) => d.code === selectedDistrict.value)?.name || "";

//   if (!pName) return "";
//   return `${pName}${cName}${dName}`;
// });

// // 生命周期挂载
// onMounted(() => {
//   loadProvinces();
// });

const selectedProvinceId = ref(); //选中的省份
const selectedCity = ref(); //选中的城市省份代码

const filteredCities = ref([]);

const cityCodeStore = useCityCodeStore();

const emit = defineEmits(["changeDialogTableVisible"]);

//确定地区选择之后
const confirm = () => {
  emit("changeDialogTableVisible", false);
  console.log("confirm");
  cityCodeStore.saveCity(selectedCity.value.code, selectedCity.value.name);
};

// let { citycode } = storeToRefs(cityCodeStore);

//点击省份改变---->过滤相关的城市
const handleProvinceChange = () => {
  filteredCities.value = cities.filter((city) => city.provinceCode == selectedProvinceId.value);
};
</script>

<style scoped>
.confirmButtom {
  margin: 0 auto;
  display: block;
}

.location-selector-container {
  max-width: 800px;
  margin: 40px auto;
  padding: 30px;
  background: #ffffff;
  border-radius: 12px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", Arial, sans-serif;
}

h3 {
  text-align: center;
  color: #303133;
  margin-bottom: 25px;
  font-weight: 600;
  font-size: 1.5rem;
}

.select-group {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 20px;
  margin-bottom: 30px;
}

.result-display {
  text-align: center;
  padding-top: 20px;
  border-top: 1px solid #ebeef5;
}

.code-display {
  margin-top: 10px;
  color: #909399;
}

/* 响应式调整 */
@media (max-width: 600px) {
  .select-group {
    grid-template-columns: 1fr;
  }
}
</style>
