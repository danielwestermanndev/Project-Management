<script setup>

import { onMounted } from 'vue';
import TaskList from "@/components/TaskList.vue";
import AddTaskButton from "@/components/AddTaskButton.vue";
import { useTaskStore } from '@/stores/taskStore';
import { storeToRefs } from 'pinia';

const taskStore = useTaskStore();
const { loading, error } = storeToRefs(taskStore);

// Fetch tasks only once in the parent component
onMounted(() => taskStore.fetchTasksForProjectId(1));
</script>

<template>
  <div class="container h-100">
    <div v-if="loading" class="text-center py-4">Loading tasks...</div>
    <div v-else-if="error" class="alert alert-danger">{{ error }}</div>
    <div v-else>
      <div class="row mb-4">
        <AddTaskButton class="d-flex justify-content-end" />
      </div>
      <div class="row flex-grow-1">
        <div class="col-md-4 d-flex flex-column">
          <h5 class="kanban-title">Not Started</h5>
          <TaskList
            status="Not Started"
            :project-id="1"
            class="kanban-column flex-grow-1"
          />
        </div>
        <div class="col-md-4 d-flex flex-column">
          <h5 class="kanban-title">In Progress</h5>
          <TaskList
            status="In Progress"
            :project-id="1"
            class="kanban-column flex-grow-1"
          />
        </div>
        <div class="col-md-4 d-flex flex-column">
          <h5 class="kanban-title">Completed</h5>
          <TaskList
            status="Completed"
            :project-id="1"
            class="kanban-column flex-grow-1"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  margin-top: 2rem;
  min-height: calc(100vh - 4rem);
  display: flex;
  flex-direction: column;
}

.kanban-title {
  text-align: center;
  padding: 0.5rem;
  margin-bottom: 1rem;
  background-color: #f8f9fa;
  border-radius: 0.25rem;
}

.kanban-column {
  padding: 1rem;
  background-color: #f8f9fa;
  border-radius: 0.25rem;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  overflow-y: auto;
  min-height: 850px;  /* Added this line */
  display: flex;      /* Added this line */
  flex-direction: column; /* Added this line */
}

@media (max-width: 768px) {
  .col-md-4 {
    margin-bottom: 1rem;
  }
}
</style>
