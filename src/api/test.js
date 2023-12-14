export default function (jsonData, fileName) {

  // 创建一个Blob对象，用于表示二进制数据
  const blob = new Blob([JSON.stringify(jsonData, null, 2)], {type: 'application/json'});
// 创建一个a标签，用于触发下载
  const a = document.createElement('a');
  a.href = URL.createObjectURL(blob);
  a.download = fileName + ".json";
  document.body.appendChild(a);
  a.click();
  document.body.removeChild(a);
}
