<script setup>
import { computed, ref } from 'vue';
import { BCard, BModal, BButton } from 'bootstrap-vue-next';
import { defineProps, defineEmits } from 'vue';

const props = defineProps({
  task: {
    type: Object,
    required: true
  },
});

const emit = defineEmits(['update-task']);

const cardVariant = computed(() => {
  return props.task.status === "Completed" ? 'light' : 'secondary';
});

const finishTask = () => {
  props.task.status = 'Completed';
  emit('update-task', props.task);
};

const isModalVisible = ref(false);
</script>

<template>
  <div class="task">
    <BCard :bg-variant="cardVariant">
      <h3>{{ task.title }}</h3>
      <p>{{ task.status }}</p>
      <BButton
        id="button-1"
        class="btn btn-info"
        @click="isModalVisible = true">
        Open
      </BButton>
      <BButton
        id="button-2"
        class="btn btn-success"
        @click="finishTask">
        Finish
      </BButton>
    </BCard>

    <BModal
      v-model="isModalVisible"
      title="Task">
      <template #default>
        <p>{{ task.title }}</p>
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
