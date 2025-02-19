<script setup>
import {computed, ref} from "vue";
import {taskService} from "@/services/taskAPI.js";

const emit = defineEmits(['task-created'])

const name = ref("");
const description = ref("");
const status = ref("");
const error = ref(null)

const submit = async () => {
  try {
    const newTask = {
      name: name.value,
      description: description.value,
      projectId: 1,
      status: status.value,
      userId: 1,
      dueDate: "2025-02-15"
    };
    await taskService.createTask(newTask);
    name.value = "";
    description.value = "";
    status.value ="";
    emit('task-created');
  } catch (err) {
    error.value = "Error creating task: " + (err.response?.data?.message || err.message);
  }
}
const isFormValid = computed(() => {
  return name.value && description.value && status.value;
  })
</script>

<template>
  <form>
    <div class="form-group">
      <div class="form-group">
        <label for="name">Name</label>
        <input type="text" class="form-control" id="name" v-model="name" required>
      </div>
      <div class="form-group">
        <label for="description">Description</label>
        <input type="text" class="form-control" id="description" v-model="description" required>
      </div>
      <div class="form-group">
        <label for="status">Status</label>
        <select v-model="status" id="status" class="form-select">
          <option disabled value="">Please select status</option>
          <option>Not Started</option>
          <option>In Progess</option>
          <option>Completed</option>
        </select>
      </div>
      <div class="form-group">
        <button type="button" class="btn btn-primary" @click="submit" :disabled="!isFormValid">Create Task</button>
      </div>
    </div>
  </form>
</template>

<style scoped>

</style>
