<template>
  <div>
    <span v-if="this.$store.state.token">
      <WidgetsDropdown />  
      </span>
    <span v-else>
      {{ this.$router.push("/") }}
    </span>
  </div>
</template>

<script>
import WidgetsDropdown from "./widgets/WidgetsDropdown";
export default {
  name: "Dashboard",
  components: {  
    WidgetsDropdown,    
  },
  data() {
  
  },
  methods: {
   
  },
};
</script>
