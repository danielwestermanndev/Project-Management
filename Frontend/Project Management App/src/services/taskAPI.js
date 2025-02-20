import axios from 'axios'

const api = axios.create({
  baseURL: 'http://localhost:8080/api'
})

export const taskService = {
  async getTasks() {
    return api.get('/tasks')
  },
  async createTask(task) {
    return api.post('/tasks', task)
  },
  async updateTask(id, task){
    return api.put(`/tasks/${id}`, task)
  },
  async deleteTask(id) {
    return api.delete(`/tasks/${id}`)
  }
}
