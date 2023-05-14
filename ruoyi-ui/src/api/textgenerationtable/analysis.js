import request from '@/utils/request'


// 解析文本
export function analysisText(text) {
    return request({
      url: '/textgenerationtable/analysis',
      method: 'post',
      data: text
    })
}


// 生成表
export function doCreateTable(tableName,primaryKey,dictList,desc) {
  const data={
    "tableName":tableName,
    "primaryKey":primaryKey,
    "dictList":dictList,
    "desc":desc
  }
  return request({
    url: '/textgenerationtable/analysis/createTable',
    method: 'post',
    data: data
  })
}


