import { defineStore } from 'pinia';
import { eventBus } from '@/eventBus';

export const useVisibilityStore = defineStore('visibility', {
  state: () => ({
    showTaskOverview: true,
    // Add more component visibility states as needed
  }),
  actions: {
    openTaskOverview() {
      this.showTaskOverview = true;
      eventBus.emit('refresh-tasklist');
    },
    closeTaskOverview() {
      this.showTaskOverview = false;
      eventBus.emit('refresh-tasklist');
    },
    // Add more actions for other components
  },
});
