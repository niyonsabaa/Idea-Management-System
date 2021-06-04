<template>
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
    </CDataTable>
  </CCardBody>
</template>
<script>
const fields = [
  { label: "#", key: "id" },
  { label: "Notes content", key: "noteContent" },
  { label: "idea Title", key: "idea" },
  { label: "Idea Owner", key: "idea" },
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
    fetch("api/v1/notes")
      .then((response) => response.json())
      .then((data) => {
        this.items = data;
      });
  },
};
</script>
