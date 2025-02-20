<script setup>
import { ref, onMounted } from 'vue';
import { taskService } from "@/services/taskAPI.js";
import TaskItem from "@/components/TaskItem.vue";
import { BCardGroup } from "bootstrap-vue-next";
import { useVisibilityStore } from "@/stores/visibility.js";

const tasks = ref([]);
const loading = ref(false);
const error = ref(null);

const visibilityStore = useVisibilityStore();
const { showTaskOverview, openTaskOverview, closeTaskOverview } = visibilityStore;

const fetchTasks = async () => {
  console.log("Fetching tasks...");
  try {
    loading.value = true;
    const {data} = await taskService.getTasks();
    tasks.value = data;
  } catch (err) {
    error.value = 'Error fetching tasks: ' + (err.response?.data?.message || err.message);
  } finally {
    loading.value = false;
  }
  console.log("Fetched tasks");
};

onMounted(fetchTasks);
</script>

<template>
  <div class="container">
    <div v-if="loading">Loading Tasks...</div>
    <div v-else-if="error">{{ error }}</div>
    <button @click="openTaskOverview">Show</button>
    <button @click="closeTaskOverview">Hide</button>

    <BCardGroup class="card-group" v-if="showTaskOverview">
      <TaskItem
        v-for="task in tasks"
        :key="task.id"
        :task="task"
        @update-task="updateTask"
      />
    </BCardGroup>
  </div>
</template>

<style scoped>
.container {
  margin-top: 5%;
}

.card-group {
  display: flex;
  flex-wrap: wrap;
  gap: 16px; /* Abstand zwischen den Karten */
}
</style>
