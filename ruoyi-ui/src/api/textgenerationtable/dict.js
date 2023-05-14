import request from '@/utils/request'

// 查询字典管理列表
export function listDict(query) {
  return request({
    url: '/textgenerationtable/dict/list',
    method: 'get',
    params: query
  })
}

// 查询字典管理详细
export function getDict(id) {
  return request({
    url: '/textgenerationtable/dict/' + id,
    method: 'get'
  })
}

// 新增字典管理
export function addDict(data) {
  return request({
    url: '/textgenerationtable/dict',
    method: 'post',
    data: data
  })
}

// 修改字典管理
export function updateDict(data) {
  return request({
    url: '/textgenerationtable/dict',
    method: 'put',
    data: data
  })
}

// 删除字典管理
export function delDict(id) {
  return request({
    url: '/textgenerationtable/dict/' + id,
    method: 'delete'
  })
}
