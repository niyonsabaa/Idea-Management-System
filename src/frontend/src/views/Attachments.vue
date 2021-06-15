<template>
  <span v-if="this.$store.state.token">
     <div v-if="downloadAlert" style="font-size:18px">
        <CAlert color="primary" closeButton>
          <p>Check Your Downloads Folder. Attachment <strong>" {{attachName}} "</strong> has been, successfully downloaded.</p>
        </CAlert>
      </div>
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
        <template #idea="{ item }">
          <td>
            {{ item.idea.ideaTitle }}
          </td>
        </template>
        <template #user="{ item }">
          <td>{{ item.idea.user.firstName }} {{ item.idea.user.lastName }}</td>
        </template>
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
  </span>
  <span v-else>
    {{ this.$router.push("/") }}
  </span>
</template>
<script>
import axios from "axios";
const fields = [
  { label: "#", key: "id" },
  { label: "Attachment Name", key: "attachmentName" },
  { label: "idea Title", key: "idea" },
  { label: "Idea Owner", key: "user" },
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
      downloadAlert:false,
      attachName:""
    };
  },
  mounted() {
    const myHeaders = new Headers({
      "Content-Type": "application/json",
      Authorization: "Bearer " + this.$store.state.token,
    });
    fetch("http://localhost:8080/api/v1/attachments", {
      method: "GET",
      headers: myHeaders,
    })
      .then((response) => response.json())
      .then((data) => {
        this.items = data;
        console.log(data);
      });
  },

  methods: {
    downloadAttachment(item) {
      const myHeaders = new Headers({
        "Content-Type": "application/json",
        Authorization: "Bearer " + this.$store.state.token,
      });

      axios
        .get(
          `http://localhost:8080/api/v1/attachments/download?fileName=${item.attachmentName}`,
          { responseType: "blob" }
        )
        .then((res) => {
          this.attachName = item.attachmentName;
          this.downloadAlert = true;
          const blob = new Blob([res.data]);
          const fileName = item.attachmentName;
          if ("download" in document.createElement("a")) {
            const link = document.createElement("a");
            link.download = fileName;
            link.style.display = "none";
            link.href = URL.createObjectURL(blob);
            document.body.appendChild(link);
            link.click();
            URL.revokeObjectURL(link.href);
            document.body.removeChild(link);
          } else {
            navigator.msSaveBlob(blob, fileName);
          }
        });
    },
  },
};
</script>
