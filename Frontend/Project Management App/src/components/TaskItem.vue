<script setup>
import { ref } from 'vue';
import { BModal } from 'bootstrap-vue-next';

const props = defineProps({
  id: {
    type: Number,
    required: true
  },
  name: {
    type: String,
    required: true
  }
});

const isModalVisible = ref(false);
</script>

<template>
  <div class="task">
    <h3>{{ id }}</h3>
    <button
      id="button-1"
      class="btn btn-primary"
      @click="isModalVisible = true">
      Open Task
    </button>

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
