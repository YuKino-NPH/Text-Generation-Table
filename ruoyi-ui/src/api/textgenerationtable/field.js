import request from '@/utils/request'

// 查询字段管理列表
export function listField(query) {
  return request({
    url: '/textgenerationtable/field/list',
    method: 'get',
    params: query
  })
}

// 查询字段管理详细
export function getField(id) {
  return request({
    url: '/textgenerationtable/field/' + id,
    method: 'get'
  })
}

// 新增字段管理
export function addField(data) {
  return request({
    url: '/textgenerationtable/field',
    method: 'post',
    data: data
  })
}

// 修改字段管理
export function updateField(data) {
  return request({
    url: '/textgenerationtable/field',
    method: 'put',
    data: data
  })
}

// 删除字段管理
export function delField(id) {
  return request({
    url: '/textgenerationtable/field/' + id,
    method: 'delete'
  })
}
