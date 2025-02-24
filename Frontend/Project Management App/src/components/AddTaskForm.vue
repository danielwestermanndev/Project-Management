<script setup>
import { computed, ref } from "vue";
import { useTaskStore } from '@/stores/taskStore';

const taskStore = useTaskStore();
const emit = defineEmits(['success'])

const title = ref("");
const description = ref("");
const status = ref("");
const projectId = ref("");
const userId = ref("");
const dueDate = ref("");
const error = ref(null)

const submit = async () => {
  try {
    const newTask = {
      title: title.value,
      description: description.value,
      projectId: Number(projectId.value),
      status: status.value,
      userId: Number(userId.value),
      dueDate: dueDate.value,
    };

    await taskStore.createTask(newTask);

    // Reset form
    title.value = "";
    description.value = "";
    status.value = "";
    projectId.value = "";
    userId.value = "";
    dueDate.value = "";

    emit('success'); // Emit success after task is created
  } catch (err) {
    error.value = "Error creating task: " + (err.response?.data?.message || err.message);
  }
}

const isFormValid = computed(() => {
  return title.value && description.value && status.value &&
    projectId.value && userId.value && dueDate.value;
})
</script>

<template>
  <form>
    <div class="form-group">
      <div class="form-group">
        <label for="title">Title</label>
        <input type="text" class="form-control" id="title" v-model="title" required>
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
          <option>In Progress</option>
          <option>Completed</option>
        </select>
      </div>
      <div class="form-group">
        <label for="projectId">Project</label>
        <select v-model="projectId" id="projectId" class="form-select">
          <option disabled value="">Please select project</option>
          <option value="1">Test project</option>
        </select>
      </div>
      <div class="form-group">
        <label for="userId">User</label>
        <select v-model="userId" id="userId" class="form-select">
          <option disabled value="">Please select user</option>
          <option value="1">Dan</option>
        </select>
      </div>
      <div class="form-group">
        <label for="dueDate">Due Date</label>
        <input type="date" class="form-control" id="dueDate" v-model="dueDate" required>
      </div>

      <div class="form-group-submitButton">
        <button type="button" class="btn btn-primary" @click="submit" :disabled="!isFormValid">Create Task</button>
      </div>
    </div>
  </form>
</template>

<style scoped>
.form-group-submitButton {
  margin-top: 25px;
}
</style>
