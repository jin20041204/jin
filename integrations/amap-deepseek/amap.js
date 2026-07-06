/**
 * Amap (高德) 简单封装
 * 依赖：axios
 * 用法：
 *   const AmapClient = require('./amap');
 *   const amap = new AmapClient(process.env.AMAP_KEY);
 *   await amap.geocode('北京市朝阳区阜通东大街6号');
 */

const axios = require('axios');

const AMAP_BASE = 'https://restapi.amap.com/v3';

class AmapClient {
  constructor(key) {
    this.key = key || process.env.AMAP_KEY;
    if (!this.key) {
      throw new Error('AMAP key missing. Provide key as constructor arg or set process.env.AMAP_KEY');
    }
    this.http = axios.create({ baseURL: AMAP_BASE, timeout: 8000 });
  }

  // 地址解析（正向地理编码）
  // address: 完整地址或关键字; city: 可选（城市名或国标码）
  async geocode(address, city) {
    const params = { key: this.key, address, city, output: 'JSON' };
    const res = await this.http.get('/geocode/geo', { params });
    return res.data;
  }

  // 逆地理编码：传入纬度、经度（lat, lng）
  // 注意高德 location 为 "lng,lat"
  async reverseGeocode(lat, lng, radius = 1000) {
    const location = `${lng},${lat}`;
    const params = { key: this.key, location, radius, extensions: 'all', output: 'JSON' };
    const res = await this.http.get('/geocode/regeo', { params });
    return res.data;
  }

  // 周边搜索（place/around）
  // location: "lng,lat" 或数组 [lat, lng]，keyword: 查询词，radius 单位米
  async searchAround({ keyword, location, radius = 1000, types = '', page = 1, city = '' } = {}) {
    let loc;
    if (Array.isArray(location)) loc = `${location[1]},${location[0]}`; // [lat, lng] -> "lng,lat"
    else loc = location;
    const params = {
      key: this.key,
      location: loc,
      keywords: keyword,
      radius,
      types,
      offset: 20,
      page,
      city,
      output: 'JSON',
    };
    const res = await this.http.get('/place/around', { params });
    return res.data;
  }

  // 关键字搜索（文本搜索）
  async searchText({ keywords, city = '', page = 1, offset = 20 } = {}) {
    const params = { key: this.key, keywords, city, page, offset, output: 'JSON' };
    const res = await this.http.get('/place/text', { params });
    return res.data;
  }
}

module.exports = AmapClient;
