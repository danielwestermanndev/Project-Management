<script setup>
import { ref, onMounted, watch } from 'vue';
import { taskService} from "@/services/taskAPI.js";
import TaskItem from "@/components/TaskItem.vue";

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

  <div>
    <div v-if="loading">Loading Tasks...</div>
    <div v-else-if="error">{{ error }}</div>
    <ul v-else>
      <li v-for="task in tasks" :key="task.id">
        <TaskItem
          :id="task.id"
          :name="task.name"
          @update-task="updateTask"
        >

        </TaskItem>
      </li>
    </ul>
  </div>

</template>

<style scoped>

</style>
