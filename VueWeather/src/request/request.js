import axios from 'axios'
import {ElMessage} from 'element-plus'

const request=axios.create({
    Headers:{"content-type":"application/json"}
})

request.interceptors.response.use(
    (response)=>{
        const {code,message,data} =response.data;
        if(code===200){
            return data;
            //直接返回业务数据
        }else{
            ElMessage.error(`请求失败:${message||"未知错误"}`)
            //要么提升msg，要么提示error
            return Promise.reject(new Error(`${message||"请求失败"}`));
        }
    },
    (error)=>{
        if(error.message.includes("timeout")){
            ElMessage.error("数据超时！")
        }else if(error.response){
            ElMessage.error(`服务器错误: ${error.response.status}`)
        }
        return Promise.reject(error);
    }

)

export default request;