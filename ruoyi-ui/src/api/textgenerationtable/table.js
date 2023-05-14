import request from '@/utils/request'

// 查询表结构管理列表
export function listTable(query) {
  return request({
    url: '/textgenerationtable/table/list',
    method: 'get',
    params: query
  })
}

// 查询表结构管理详细
export function getTable(id) {
  return request({
    url: '/textgenerationtable/table/' + id,
    method: 'get'
  })
}

// 新增表结构管理
export function addTable(data) {
  return request({
    url: '/textgenerationtable/table',
    method: 'post',
    data: data
  })
}

// 修改表结构管理
export function updateTable(data) {
  return request({
    url: '/textgenerationtable/table',
    method: 'put',
    data: data
  })
}

// 删除表结构管理
export function delTable(id) {
  return request({
    url: '/textgenerationtable/table/' + id,
    method: 'delete'
  })
}
