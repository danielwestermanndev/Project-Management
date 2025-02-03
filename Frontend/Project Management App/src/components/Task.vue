<script setup>
  import {defineProps, defineEmits} from 'vue'

  const props = defineProps({
    id:{
      type: Number,
      required: true
    },
    name:{
      type: String,
      required: true
    }
  })

  const emit = defineEmits(['update-task'])

  const emitUpdate = () => {
    emit('update-task', {
      id: props.id,
      name: props.name,
    })
  }

</script>

<template>
  <div class="task">
    <h3>{{ name }}</h3>
    <button
      id="button-1"
      class="btn btn-primary"
      data-bs-target="#collapseTarget"
      data-bs-toggle="collapse">
      {{ id }}
    </button>
  </div>
</template>

<style scoped>

</style>
