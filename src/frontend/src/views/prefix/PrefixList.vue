<template>
  <div>
    <span v-if="this.$store.state.token">
      <div>
        <template>
          <div v-if="updateAlert">
            <CAlert color="primary" closeButton>
              <h4>Prefix Record # {{ prefixId }} , successfully updated.</h4>
            </CAlert>
          </div>
          <CModal title="Delete Prefix" :show.sync="deleteModal" color="danger">
            <h5>Are you sure you want to delete prefix {{ prefixName }} ?</h5>
            <div class="form-actions text-right">
              <CButton @click="closeDeleteModal" color="danger">No</CButton>
              <span style="margin-left: 10px" />
              <CButton @click="deletePrefix" color="success">Yes</CButton>
            </div>
            <template #footer>
              <br />
            </template>
          </CModal>
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
                    @click="loadDeleteModal(item, index)"
                  >
                    Delete
                  </CButton>
                  <span style="margin-left: 10px"></span>
                  <CButton
                    color="primary"
                    variant="outline"
                    square
                    size="sm"
                    @click="loadUpdateModal(item, index)"
                  >
                    Edit
                  </CButton>

                  <CModal
                    title="Update Prefix"
                    :show.sync="updateModal"
                    color="primary"
                  >
                    <CForm @submit="updatePrefix">
                      <CInput
                        label="Prefix"
                        required="required"
                        v-model="prefixName"
                        :placeholder="prefixName"
                        type="text"
                      />
                      <div class="form-actions">
                        <CButton type="submit" color="primary">Update</CButton>
                      </div>
                    </CForm>
                    <template #footer>
                      <br />
                    </template>
                  </CModal>
                </td>
              </template>
            </CDataTable>
          </CCardBody>
        </template>
      </div>
    </span>
    <span v-else>
      {{ this.$router.push("/") }}
    </span>
  </div>
</template>
<script>
const fields = [
  { label: "#", key: "id", _style: "min-width:200px" },

  { label: "Prefix", key: "prefixName", _style: "min-width:100px;" },

  {
    key: "actions",
    label: "Actions",
    _style: "width:1%",
    sorter: false,
    filter: false,
  },
];
export default {
  name: "PrefixList",
  data() {
    return {
      items: [],
      fields,
      deleteModal: false,
      updateModal: false,
      prefixId: "",
      prefixName: "",
      updateAlert: false,
    };
  },
  mounted() {
    const myHeaders = new Headers({
      "Content-Type": "application/json",
      Authorization: "Bearer " + this.$store.state.token,
    });

    fetch("http://localhost:8080/api/v1/prefix", {
      method: "GET",
      headers: myHeaders,
    })
      .then((response) => response.json())
      .then((data) => {
        //alert(JSON.stringify(data))
        this.items = data;
      });
  },

  methods: {
    loadDeleteModal(item) {
      this.prefixId = item.id;
      this.prefixName = item.prefixName;
      this.deleteModal = true;
    },

    loadUpdateModal(item) {
      this.prefixId = item.id;
      this.prefixName = item.prefixName;
      this.updateModal = true;
    },

    closeDeleteModal() {
      this.deleteModal = false;
    },
    deletePrefix() {
      const myHeaders = new Headers({
        "Content-Type": "application/json",
        Authorization: "Bearer " + this.$store.state.token,
      });
      const requestOptions = {
        method: "DELETE",
        headers: myHeaders,
      };
      fetch(
        `http://localhost:8080/api/v1/prefix/${this.prefixId}`,
        requestOptions
      );
      this.deleteModal = false;
      document.location.reload(true);
    },

    updatePrefix() {
      const myHeaders = new Headers({
        "Content-Type": "application/json",
        Authorization: "Bearer " + this.$store.state.token,
      });
      const requestOptions = {
        method: "PUT",
        headers: myHeaders,
        body: JSON.stringify({
          prefixName: this.prefixName,
        }),
      };
      fetch(
        `http://localhost:8080/api/v1/prefix/${this.prefixId}`,
        requestOptions
      )
        .then((response) => response.json())
        .then((data) => {
          this.preId = data.id;
          this.updateAlert = true;
          document.location.reload(true);
        });
    },
  },
};
</script>