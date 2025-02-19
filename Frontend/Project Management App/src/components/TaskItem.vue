<script setup>
import {computed, ref} from 'vue';
import {BCard, BModal, BButton} from 'bootstrap-vue-next';
import {taskService} from "@/services/taskAPI.js";

const props = defineProps({
  id: {
    type: Number,
    required: true
  },
  name: {
    type: String,
    required: true
  },
  description: {
    type: String,
    required: true
  },
  status: {
    type: String,
    required: true
  }
});

const cardVariant = computed(() => {
  return props.status === "Completed" ? 'light' : 'secondary';
})

const finishTask = async () => {
  try {
    const response = await taskService.createTask(props.id, props.status);
    return response.data;
  } catch (error) {
    console.error("Error updating task status:", error);
    throw error;
  }
}


const isModalVisible = ref(false);
</script>

<template>
  <div class="task">
    <BCard :bg-variant="cardVariant">
      <h3>{{ name }}</h3>
      <p>{{ status }}</p>
      <BButton
        id="button-1"
        class="btn btn-info"
        @click="isModalVisible = true">
        Open
      </BButton>
      <BButton
      id="button-2"
      class="btn btn-success"
      @click="finishTask"
      >
      Finish
      </BButton>
    </BCard>


    <BModal
      v-model="isModalVisible"
      title=Task>
      <template #default>
        <p>{{name}}</p>
      </template>
      <template #footer>
        <button
          class="btn btn-secondary"
          @click="isModalVisible = false">
          Close
        </button>
      </template>
    </BModal>
  </div>
</template>
