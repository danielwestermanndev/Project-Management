import axios from 'axios'

const api = axios.create({
  baseURL: 'http://localhost:8080/api'
})

export const taskService = {
  async getAllTasks() {
    return api.get('/tasks/all')
  },
  async getTasksByStatus(status){
    return api.get('/tasks/status?status=' + status)
  },
  async getTasksByProjectId(projectId) {
    return api.get('/tasks/project?projectId=' + projectId)
  },
  async createTask(task) {
    return api.post('/tasks', task)
  },
  async updateTask(id, task){
    return api.put(`/tasks/${id}`, task)
  },
  async deleteTask(id) {
    return api.delete(`/tasks/${id}`)
  },

}
