<template>
  <span v-if="this.$store.state.token">    
    <CCardBody>
      <CDataTable
        :items="items"
        :fields="fields"        
        column-filter
        table-filter
        items-per-page-select
        :items-per-page="5"
        hover
        sorter
        pagination
      >
      <template #idea="{item}">
      <td>
          {{item.idea.ideaTitle}}
      </td>      
    </template>
    <template #user="{item}">
      <td>
          {{item.idea.user.firstName}} {{item.idea.user.lastName}}
      </td>      
    </template>
      </CDataTable>
    </CCardBody>
  </span>
  <span v-else>
    {{ this.$router.push("/") }}
  </span>
</template>
<script>
const fields = [
  { label: "#", key: "id" },
  { label: "Notes content", key: "noteContent" },
  { label: "idea Title", key: "idea" },
  { label: "Idea Owner", key:"user" },
];

export default {
  name: "Notes",

  data() {
    return {
      items: "",
      fields,
    };
  },
  mounted() {
    const myHeaders = new Headers({
      "Content-Type": "application/json",
      Authorization: "Bearer " + this.$store.state.token,
    });
    fetch("http://localhost:8080/api/v1/notes", {
      method: "GET",
      headers: myHeaders,
    })
      .then((response) => response.json())
      .then((data) => {
        this.items = data;
      });
  },
};
</script>
