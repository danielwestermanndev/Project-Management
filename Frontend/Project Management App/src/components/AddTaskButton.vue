<script setup>
import { BButton, BModal } from "bootstrap-vue-next";
import { ref } from 'vue'
import AddTaskForm from "@/components/AddTaskForm.vue";

const isModalVisible = ref(false)

const handleSuccess = () => {
  isModalVisible.value = false;
}
</script>

<template>
  <div class="btnContainer">
    <BButton class="btn-success" @click="isModalVisible = true">Add Task</BButton>
    <BModal v-model="isModalVisible" title="New Task">
      <div class="modal-body">
        <AddTaskForm @success="handleSuccess"></AddTaskForm>
      </div>
      <template #footer>
        <div>
          <BButton class="btn btn-secondary" @click="isModalVisible=false">Cancel</BButton>
        </div>
      </template>
    </BModal>
  </div>
</template>

<style scoped>

</style>
