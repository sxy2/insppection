import request from '@/utils/request'

// 查询机房现场巡检记录列表
export function listRecord(query) {
  return request({
    url: '/system/record/list',
    method: 'get',
    params: query
  })
}

// 查询机房现场巡检记录详细
export function getRecord(inspectionId) {
  return request({
    url: '/system/record/' + inspectionId,
    method: 'get'
  })
}

// 新增机房现场巡检记录
export function addRecord(data) {
  return request({
    url: '/system/record',
    method: 'post',
    data: data
  })
}

// 修改机房现场巡检记录
export function updateRecord(data) {
  return request({
    url: '/system/record',
    method: 'put',
    data: data
  })
}

// 删除机房现场巡检记录
export function delRecord(inspectionId) {
  return request({
    url: '/system/record/' + inspectionId,
    method: 'delete'
  })
}
