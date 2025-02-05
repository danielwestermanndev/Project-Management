<script setup>
import TaskList from "@/components/TaskList.vue";
import AddTaskButton from "@/components/AddTaskButton.vue";
import {ref} from 'vue';


const taskListKey = ref(0)

const refreshTaskList = () => {
  taskListKey.value += 1;
}
</script>

<template>
  <div class="container">
    <div class="row">
      <AddTaskButton class="d-flex justify-content-end" @refresh-tasklist="refreshTaskList"></AddTaskButton>
    </div>
    <div class="row">
      <task-list class="col_4" :key="taskListKey"></task-list>
    </div>
  </div>
</template>

<style scoped>

</style>
