import { defineStore } from 'pinia';  // Beachte die geschweifte Klammer
import { taskService } from "@/services/taskAPI.js";

export const useTaskStore = defineStore('tasks', {
  state: () => ({
    tasks: [],
    loading: false,
    error: null
  }),

  actions: {
    async createTask(newTask) {
      this.loading = true;
      try {
        const { data } = await taskService.createTask(newTask);
        this.tasks.push(data); // Add the new task to the state
        return data;
      } catch (err) {
        this.error = err.message;
        throw err;
      } finally {
        this.loading = false;
      }
    },

    async fetchTasksForProjectId(projectId) {
      this.loading = true;
      try{
        const { data } = await taskService.getTasksByProjectId(projectId);
        this.tasks = data;
      } catch(err){
        this.error = err.message;
      } finally {
        this.loading = false;
      }
    },

    async updateTaskStatus(taskId, newStatus) {
      try {
        console.log('Current tasks in store:', this.tasks); // Debug current state
        console.log('Attempting to update taskId:', taskId, 'to status:', newStatus); // Debug input

        const taskIndex = this.tasks.findIndex(t => t.id === taskId);
        console.log('Found task index:', taskIndex); // Debug found index

        if (taskIndex === -1) {
          // Add more context to the error
          console.log('Available task IDs:', this.tasks.map(t => t.id)); // Debug available IDs
          throw new Error(`Task with ID ${taskId} not found. Available tasks: ${this.tasks.length}`);
        }

        const task = this.tasks[taskIndex];

        if(task.status === newStatus){
          console.log("Trying to update to the Same Status so exit update")
          return
        }

        const updatedTask = { ...task, status: newStatus };

        await taskService.updateTask(taskId, updatedTask);
        this.tasks[taskIndex] = updatedTask;
      } catch (error) {
        console.error('Error updating task status:', error);
        this.error = error.message;
        throw error;
      }
    }
  },

  getters: {
    getTasksByStatus: (state) => (status) => {
      return state.tasks.filter(task => task.status === status);
    },
  }
});
