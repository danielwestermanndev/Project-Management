<script setup>
import { ref, onMounted, watch } from 'vue';
import { taskService} from "@/services/taskAPI.js";
import TaskItem from "@/components/TaskItem.vue";
import {BCard, BCardGroup} from "bootstrap-vue-next";

const tasks = ref([])
const loading = ref(false)
const error = ref(null)

const fetchTasks = async () => {
  console.log("Fetching tasks...");
  try {
    loading.value = true
    const { data } = await taskService.getTasks()
    tasks.value = data
  }catch( err ){
    error.value = 'Error fetching tasks: ' + err.response?.data?.message ||err.message;
  } finally {
    loading.value = false
  }
  console.log("Fetched tasks");
}

const updateTask = (updatedTask) => {
  const index = tasks.value.findIndex((task) => task.id === updatedTask.id)
  if(index !== -1){
    tasks.value[index] = updatedTask
  }
}

onMounted(fetchTasks)
</script>

<template>
  <div class="container">
    <div v-if="loading">Loading Tasks...</div>
    <div v-else-if="error">{{ error }}</div>
        <BCardGroup class="card-group">
            <TaskItem
              v-for="task in tasks"
              :id="task.id"
              :name="task.name"
              :status="task.status"
              @update-task="updateTask"
            >
            </TaskItem>
        </BCardGroup>
  </div>
</template>

<style scoped>
.container {
  margin-top: 5%
}

.card-group {
  display: flex;
  flex-wrap: wrap;
  gap: 16px; /* Abstand zwischen den Karten */
}
</style>
