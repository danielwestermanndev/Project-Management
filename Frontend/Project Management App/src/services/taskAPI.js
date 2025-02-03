import axios from 'axios'

const api = axios.create({
  baseURL: 'http://localhost:8080/api'
})

export const taskService = {
  getTasks() {
    return api.get('/tasks')
  }
}
