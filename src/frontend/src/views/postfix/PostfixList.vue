<template>
  <div>
    <span v-if="this.$store.state.token">
      <div>
        <template>
          <div v-if="updateAlert">
            <CAlert color="primary" closeButton>
              <h4>Postfix Record # {{ postfixId }} , successfully updated.</h4>
            </CAlert>
          </div>
          <CModal
            title="Delete Postfix"
            :show.sync="deleteModal"
            color="danger"
          >
            <h5>Are you sure you want to delete postfix {{ postfixName }} ?</h5>
            <div class="form-actions text-right">
              <CButton @click="closeDeleteModal" color="danger">No</CButton>
              <span style="margin-left: 10px" />
              <CButton @click="deletePostfix" color="success">Yes</CButton>
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
                    title="Update Postfix"
                    :show.sync="updateModal"
                    color="primary"
                  >
                    <CForm @submit="updatePostfix">
                      <CInput
                        label="Postfix"
                        required="required"
                        v-model="postfixName"
                        :placeholder="postfixName"
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

  { label: "Postfix", key: "postfixName", _style: "min-width:100px;" },

  {
    key: "actions",
    label: "Actions",
    _style: "width:1%",
    sorter: false,
    filter: false,
  },
];
export default {
  name: "PostfixList",
  data() {
    return {
      items: [],
      fields,
      deleteModal: false,
      updateModal: false,
      postfixId: "",
      postfixName: "",
      updateAlert: false,
    };
  },
  mounted() {
    const myHeaders = new Headers({
      "Content-Type": "application/json",
    });

    fetch("http://localhost:8080/api/v1/postfix", {
      method: "GET",
      headers: myHeaders,
    })
      .then((response) => response.json())
      .then((data) => {
        this.items = data;
      });
  },
  methods: {
    loadDeleteModal(item) {
      this.postfixId = item.id;
      this.postfixName = item.postfixName;
      this.deleteModal = true;
    },

    loadUpdateModal(item) {
      this.postfixId = item.id;
      this.postfixName = item.postfixName;
      this.updateModal = true;
    },

    closeDeleteModal() {
      this.deleteModal = false;
    },

    deletePostfix() {
      const myHeaders = new Headers({
        "Content-Type": "application/json",
        Authorization: "Bearer " + this.$store.state.token,
      });
      const requestOptions = {
        method: "DELETE",
        headers: myHeaders,
      };
      fetch(
        `http://localhost:8080/api/v1/postfix/${this.postfixId}`,
        requestOptions
      );
      this.deleteModal = false;
      document.location.reload(true);
    },

    updatePostfix() {
      const myHeaders = new Headers({
        "Content-Type": "application/json",
        Authorization: "Bearer " + this.$store.state.token,
      });
      const requestOptions = {
        method: "PUT",
        headers: myHeaders,
        body: JSON.stringify({
          postfixName: this.postfixName,
        }),
      };
      fetch(`http://localhost:8080/api/v1/postfix/${this.postfixId}`, requestOptions)
        .then((response) => response.json())
        .then((data) => {
          this.postfixId = data.id;
          this.updateAlert = true;
          document.location.reload(true);
        });
    },
  },
};
</script>