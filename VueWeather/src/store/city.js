import { defineStore } from "pinia";
// import { Update } from "vite";

export const useCityCodeStore = defineStore("city", {
  state: () => ({ citycode: "101010100", cityName: "北京" }),
  // getters:{
  //   doubleCount:(state)=>state.count*2
  actions: {
    async saveCity(code, name) {
      this.citycode = code;
      this.cityName = name;
    },
    async getCityCode() {
      return this.citycode;
    },
  },
});
