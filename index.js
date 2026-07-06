const AmapClient = require('./integrations/amap-deepseek/amap');

async function main() {
  const key = process.env.AMAP_KEY;
  if (!key) {
    console.error('请先设置环境变量 AMAP_KEY');
    process.exit(1);
  }
  const amap = new AmapClient(key);
  try {
    const res = await amap.geocode('北京市朝阳区阜通东大街6号');
    console.log(JSON.stringify(res, null, 2));
  } catch (err) {
    console.error('调用失败：', err.message || err);
  }
}

main();
