<template>
  <div>
    <span v-if="this.$store.state.token">
      <div>
        <template>       
          <div v-if="updateAlert">
            <CAlert color="primary" closeButton>
              <h4>Gender Record # {{ genderId }} , successfully updated.</h4>
            </CAlert>
          </div>
          <CModal title="Delete Gender" :show.sync="deleteModal" color="danger">
            <h5>Are you sure you want to delete Gender {{ gender_name }} ?</h5>
            <div class="form-actions text-right">
              <CButton @click="closeDeleteModal" color="danger">No</CButton>
              <span style="margin-left: 10px" />
              <CButton @click="deleteGender" color="success">Yes</CButton>
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
                    @click="loadGenderModel(item, index)"
                  >
                    Edit
                  </CButton>

                  <CModal
                    title="Update Gender"
                    :show.sync="genderModal"
                    color="primary"
                  >
                    <CForm @submit="updateGender">
                      <CInput
                        label="Gender"
                        required="required"
                        v-model="gender_name"
                        :placeholder="gender_name"
                        description="Enter the gender name such as Male, Female, etc."
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
  { key: "id", _style: "min-width:200px" },
  { key: "genderName", _style: "min-width:100px;" },
  {
    key: "actions",
    label: "Actions",
    _style: "width:1%",
    sorter: false,
    filter: false,
  },
];

export default {
  name: "GenderList",
  data() {
    return {
      items: [],
      fields:"",
      genderModal: false,
      deleteModal: false,
      genderId: "",
      gender_name: "",
      updateAlert: false,
      decodedToken,
    };
  },
  mounted() {  
    const myHeaders = new Headers({
      "Content-Type": "application/json",
      Authorization: "Bearer " + this.$store.state.token,
    });

    fetch("http://localhost:8080/api/v1/gender", {
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
      this.genderId = item.id;
      this.gender_name = item.genderName;
      this.deleteModal = true;
    },
    closeDeleteModal() {
      this.deleteModal = false;
    },
    deleteGender() {
      const myHeaders = new Headers({
        "Content-Type": "application/json",
        Authorization: "Bearer " + this.$store.state.token,
      });
      const requestOptions = {
        method: "DELETE",
        headers: myHeaders,
      };
      fetch(
        `http://localhost:8080/api/v1/gender/${this.genderId}`,
        requestOptions
      );
      this.deleteModal = false;
      document.location.reload(true);
    },

    loadGenderModel(item) {
      this.genderId = item.id;
      this.gender_name = item.genderName;
      this.genderModal = true;
    },
    updateGender() {
      const myHeaders = new Headers({
        "Content-Type": "application/json",
        Authorization: "Bearer " + this.$store.state.token,
      });

      const requestOptions = {
        method: "PATCH",
        headers: myHeaders,
        body: JSON.stringify({ genderName: this.gender_name }),
      };

      this.genderModal = false;
      fetch(
        `http://localhost:8080/api/v1/gender/${this.genderId}`,
        requestOptions
      )
        .then((response) => response.json())
        .then((data) => {
          this.genderId = data.id;
          this.updateAlert = true;
        });
    },
  },
};
</script>
