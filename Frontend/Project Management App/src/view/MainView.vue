<script setup>
import TaskList from "@/components/TaskList.vue";
import AddTaskButton from "@/components/AddTaskButton.vue";
import {ref, onMounted, onUnmounted} from 'vue';
import {eventBus} from '@/eventBus';

const taskListKey = ref(0);

const refreshTaskList = () => {
  taskListKey.value += 1;
};

onMounted(() => {
  eventBus.on('refresh-tasklist', refreshTaskList);
});

onUnmounted(() => {
  eventBus.off('refresh-tasklist', refreshTaskList);
});
</script>

<template>
  <div class="container">
    <div class="row">
      <AddTaskButton class="d-flex justify-content-end"
                     @refresh-tasklist="refreshTaskList"></AddTaskButton>
    </div>
    <div class="row">
      <TaskList class="col_4" :key="taskListKey"></TaskList>
    </div>
  </div>
</template>

<style scoped>
.container {
  margin-top: 5%;
}
</style>
