<script setup>
import { defineProps, ref } from 'vue';
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

const modalRef = ref(null);

const showModal = () => {
  modalRef.value.show();
};

const hideModal = () => {
  modalRef.value.hide();
};

const toggleModal = () => {
  modalRef.value.toggle();
};
</script>

<template>
  <div class="task">
    <h3>{{ name }}</h3>
    <button
      id="button-1"
      class="btn btn-primary"
      @click="showModal">
      Open Modal
    </button>

    <b-modal ref="modalRef" title="Modal Title">
      <template #default>
        <p>This is a modal!</p>
      </template>
      <template #footer>
        <button class="btn btn-secondary" @click="hideModal">Close</button>
      </template>
    </b-modal>
  </div>
</template>

<style scoped>
/* Deine CSS-Stile hier */
</style>
