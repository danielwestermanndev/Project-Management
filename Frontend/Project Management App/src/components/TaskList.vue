<script setup>
import {computed } from 'vue'; // Remove ref since we'll use store
import TaskItem from "@/components/TaskItem.vue";
import { BCardGroup } from "bootstrap-vue-next";
import { useTaskStore } from '@/stores/taskStore'; // Add this
import { storeToRefs } from 'pinia'; // Add this

const props = defineProps({
  status: String,
  projectId: Number,
})

// Initialize stores
const taskStore = useTaskStore();

// Get reactive state from stores using storeToRefs
const { loading, error } = storeToRefs(taskStore);

// Use computed to get tasks for this specific status
const tasks = computed(() => taskStore.getTasksByStatus(props.status))

const startDrag = (event, item) => {
  event.dataTransfer.dropEffect = "move";
  event.dataTransfer.effectAllowed = 'move';
  event.dataTransfer.setData('taskId', item.id.toString()); // Changed 'itemID' to 'taskId'
  console.log('Starting drag with task ID:', item.id); // Debug log
}

const onDrop = async (event, status) => {
  try {
    const taskId = parseInt(event.dataTransfer.getData('taskId'));
    console.log('Dragged taskId:', taskId, 'New status:', status);

    if (isNaN(taskId)) { // Better check for invalid ID
      throw new Error('Invalid task ID in drag event');
    }

    await taskStore.updateTaskStatus(taskId, status); // Remove 'this.'
  } catch (error) {
    console.error('Drop handler error:', error);
  }
}

// Update task handler using store
const updateTask = async (updatedTask) => {
  try {
    await taskStore.updateTaskStatus(updatedTask.id, updatedTask.status);
  } catch (e) {
    console.error(e);
  }
}
</script>

<template>
  <div class="task-list-container h-100">
    <div v-if="loading">Loading Tasks...</div>
    <div v-else-if="error">{{ error }}</div>

    <BCardGroup
      class="card-group h-100"
      @drop="onDrop($event, status)"
      @dragenter.prevent
      @dragover.prevent
    >
      <TaskItem
        v-for="task in tasks"
        :key="task.id"
        :task="task"
        @update-task="updateTask"
        draggable="true"
        @dragstart="startDrag($event, task)"
      />
    </BCardGroup>
  </div>
</template>

<style scoped>
.task-list-container {
  display: flex;
  flex-direction: column;
  height: 100%;
}

.card-group {
  display: flex;
  flex-direction: column;
  gap: 16px;
  background-color: darkgray;
  padding: 1rem;
  min-height: 400px;  /* Added this line */
  flex-grow: 1;
  border-radius: 20px;
}

/* If you want empty columns to still be droppable */
.card-group:empty {
  min-height: 800px; /* Updated this to match */
}
</style>
