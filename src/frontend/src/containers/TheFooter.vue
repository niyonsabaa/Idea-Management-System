<template>
  <CFooter :fixed="false">   
    <h5 class="mfs-auto">
      <a href="#" target="_blank">FlyHub</a>
      <span class="ml-1">&copy; {{new Date().getFullYear()}}</span>
    </h5>
  </CFooter>
</template>

<script>
export default {
  name: 'TheFooter'
}
</script>
