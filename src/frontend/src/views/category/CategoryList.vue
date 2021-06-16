<template>
  <div>
    <span v-if="this.$store.state.token">
      <div>
        <template>
          <div v-if="updateAlert">
            <CAlert color="primary" closeButton>
              <h4>Category Record # {{ catId }} , successfully updated.</h4>
            </CAlert>
          </div>
          <CModal
            title="Delete Category"
            :show.sync="deleteModal"
            color="danger"
          >
            <h5>Are you sure you want to delete {{ catName }} Category ?</h5>
            <div class="form-actions text-right">
              <CButton @click="closeDeleteModal" color="danger">No</CButton>
              <span style="margin-left: 10px" />
              <CButton @click="deleteCategory" color="success">Yes</CButton>
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
                    title="Update Category"
                    :show.sync="updateModal"
                    color="primary"
                  >
                    <CForm @submit="updateCategory">
                      <CInput
                        label="Category"
                        required="required"
                        v-model="catName"
                        :placeholder="catName"
                        type="text"
                      />
                      <CInput
                        label="Description"
                        required="required"
                        v-model="categoryDescription"
                        :placeholder="categoryDescription"
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

  { label: "Category", key: "categoryName", _style: "min-width:100px;" },
  {
    label: "Description",
    key: "categoryDescription",
    _style: "min-width:100px;",
  },

  {
    key: "actions",
    label: "Actions",
    _style: "width:1%",
    sorter: false,
    filter: false,
  },
];
export default {
  name: "CategoryList",
  data() {
    return {
      items: [],
      fields,
      deleteModal: false,
      updateModal: false,
      catId: "",
      catName: "",
      categoryDescription: "",
      updateAlert: false,
    };
  },
  mounted() {
    const myHeaders = new Headers({
      "Content-Type": "application/json",
      Authorization: "Bearer " + this.$store.state.token,
    });

    fetch("http://localhost:8080/api/v1/category", {
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
      this.catId = item.id;
      this.catName = item.categoryName;
      this.deleteModal = true;
    },

    loadUpdateModal(item) {
      this.catId = item.id;
      this.catName = item.categoryName;
      this.categoryDescription = item.categoryDescription;
      this.updateModal = true;
    },

    closeDeleteModal() {
      this.deleteModal = false;
    },
    deleteCategory() {
      const myHeaders = new Headers({
        "Content-Type": "application/json",
        Authorization: "Bearer " + this.$store.state.token,
      });
      const requestOptions = {
        method: "DELETE",
        headers: myHeaders,
      };
      fetch(
        `http://localhost:8080/api/v1/category/${this.catId}`,
        requestOptions
      );
      this.deleteModal = false;
      document.location.reload(true);
    },

    updateCategory() {
      const myHeaders = new Headers({
        "Content-Type": "application/json",
        Authorization: "Bearer " + this.$store.state.token,
      });
      const requestOptions = {
        method: "PUT",
        headers: myHeaders,
        body: JSON.stringify({
          categoryName: this.catName,
          categoryDescription: this.categoryDescription,
        }),
      };
      fetch(
        `http://localhost:8080/api/v1/category/${this.catId}`,
        requestOptions
      )
        .then((response) => response.json())
        .then((data) => {
          this.catId = data.id;
          this.updateAlert = true;
          document.location.reload(true);
        });
    },
  },
};
</script>