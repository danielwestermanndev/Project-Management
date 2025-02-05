<script setup>
import {ref} from "vue";
import {taskService} from "@/services/taskAPI.js";

const emit = defineEmits(['task-created'])

const name = ref("");
const description = ref("");
const error = ref(null)

const submit = async () => {
  try{
    const newTask = {
      name: name.value,
      description: description.value,
      projectId: 1,
      status: "TODO",
      userId: 1,
      dueDate: "2025-02-15"
    };
    await taskService.createTask(newTask);
    name.value="";
    description.value="";
    emit('task-created');
  } catch(err){
    error.value = "Error creating task: " + (err.response?.data?.message || err.message);
  }
}
</script>

<template>
  <form>
    <div class="form-group">
      <label for="name">Name</label>
      <input type="text" class="form-control" id="name" v-model="name" required>
      <label for="description">Description</label>
      <input type="text" class="form-control" id="description" v-model="description" required>

      <button type="button" class="btn btn-primary" @click="submit">Create Task</button>
    </div>
  </form>
</template>

<style scoped>

</style>
