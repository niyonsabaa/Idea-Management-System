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
      <template #actions="{ item, index }">
        <td class="d-flex py-2">
          <CButton
            color="danger"
            variant="outline"
            square
            size="sm"
            @click="downloadAttachment(item, index)"
          >
            Download
          </CButton>
        </td>
      </template>
    </CDataTable>
  </CCardBody>
</template>
<script>
const fields = [
  { label: "#", key: "id" },
  { label: "Attachment Name", key: "attachmentName" },
  { label: "idea Title", key: "idea" },
  { label: "Idea Owner", key: "idea" },
  {
    key: "actions",
    label: "Actions",
    _style: "width:1%",
    sorter: false,
    filter: false,
  },
];

export default {
  name: "Attachments",

  data() {
    return {
      items: "",
      fields,
    };
  },
  mounted() {
    fetch("api/v1/attachments")
      .then((response) => response.json())
      .then((data) => {
        this.items = data;
      });
  },

  methods: {
    downloadAttachment(item) {
      const requestOptions = {
        method: "GET",
        headers: { "Content-Type": "application/json" },
      };
      fetch(`api/v1/attachments/download?fileName=${item.attachmentName}`, requestOptions).
      then((response) =>{
        alert(JSON.stringify(response.data));
      });      
    },
  },
};
</script>
