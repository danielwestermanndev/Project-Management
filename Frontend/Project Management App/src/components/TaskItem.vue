<script setup>
import { ref } from 'vue';
import {BCard, BModal, BButton} from 'bootstrap-vue-next';

const props = defineProps({
  id: {
    type: Number,
    required: true
  },
  name: {
    type: String,
    required: true
  },
  status: {
    type: String,
    required: true
  }
});

const isModalVisible = ref(false);
</script>

<template>
  <div class="task">
    <BCard v-if="status==='TODO'">
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
