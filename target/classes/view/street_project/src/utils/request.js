import axios from 'axios';

// 创建一个 Axios 实例
const request = axios.create({
    baseURL: 'http://localhost:9999',  // 设置后端接口的基准 URL
    timeout: 5000,  // 设置请求超时时间为 5000 毫秒
});

// 请求拦截器
request.interceptors.request.use(config => {
    // 设置请求头的 Content-Type
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    
    // 如果需要，可以在这里设置 Token
    config.headers['token'] = user.token || ''; // 设置请求头的 token，确保有默认值以防未定义
    return config;  // 返回修改后的 config
}, error => {
    return Promise.reject(error);  // 拒绝请求错误
});

// 响应拦截器
request.interceptors.response.use(
    response => {
        let res = response.data;  // 获取响应数据
        // 如果返回的是文件（blob）
        if (response.config.responseType === 'blob') {
            return res;  // 直接返回文件数据
        }
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res;  // 如果是字符串，解析为 JSON
        }
        return res;  // 返回处理后的响应数据
    },
    error => {
        console.error('Error: ', error);  // 输出错误信息，便于调试
        return Promise.reject(error);  // 拒绝响应错误
    }
);

export default request;  // 导出 Axios 实例
