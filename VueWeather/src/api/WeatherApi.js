import request from "@/request/request";

export const getRealTimeWeather = (location) => request.get("/api/weather", { params: { location } });
