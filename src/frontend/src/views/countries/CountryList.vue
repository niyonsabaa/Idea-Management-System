<template>
  <div>
    <span v-if="this.$store.state.token">
      <div>
        <template>
          <div v-if="updateAlert">
            <CAlert color="primary" closeButton>
              <h4>Country Record # {{ countryId }} , successfully updated.</h4>
            </CAlert>
          </div>
          <CModal
            title="Delete Country"
            :show.sync="deleteModal"
            color="danger"
          >
            <h5>Are you sure you want to delete {{ countryName }} ?</h5>
            <div class="form-actions text-right">
              <CButton @click="closeDeleteModal" color="danger">No</CButton>
              <span style="margin-left: 10px" />
              <CButton @click="deleteCountry" color="success">Yes</CButton>
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
                    @click="loadUpdateModel(item, index)"
                  >
                    Edit
                  </CButton>

                  <CModal
                    title="Update Country"
                    :show.sync="updateModal"
                    color="primary"
                  >
                    <CForm @submit="updateCountry">
                      <CInput
                        label="Country"
                        required="required"
                        v-model="countryName"
                        :placeholder="countryName"
                        type="text"
                      />
                      <CInput
                        label="Code"
                        required="required"
                        v-model="countryCode"
                        :placeholder="countryCode"
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

  { label: "Country", key: "countryName", _style: "min-width:100px;" },
  { label: "Code", key: "countryCode", _style: "min-width:100px;" },

  {
    key: "actions",
    label: "Actions",
    _style: "width:1%",
    sorter: false,
    filter: false,
  },
];
export default {
  name: "CountryList",
  data() {
    return {
      items: [],
      fields,
      deleteModal: false,
      countryId: "",
      countryName: "",
      countryCode: "",
      updateAlert: false,
      updateModal: false,
      countryModal: false,
    };
  },
  mounted() {
    const myHeaders = new Headers({
      "Content-Type": "application/json",
    });

    fetch("http://localhost:8080/api/v1/countries", {
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
      this.countryId = item.id;
      this.countryName = item.countryName;
      this.deleteModal = true;
    },

    loadUpdateModel(item) {
      this.countryId = item.id;
      this.countryName = item.countryName;
      this.countryCode = item.countryCode;
      this.updateModal = true;
    },
    closeDeleteModal() {
      this.deleteModal = false;
    },
    deleteCountry() {
      const myHeaders = new Headers({
        "Content-Type": "application/json",
        Authorization: "Bearer " + this.$store.state.token,
      });
      const requestOptions = {
        method: "DELETE",
        headers: myHeaders,
      };
      fetch(
        `http://localhost:8080/api/v1/countries/${this.countryId}`,
        requestOptions
      );
      this.deleteModal = false;
      document.location.reload(true);
    },

    updateCountry() {
      const myHeaders = new Headers({
        "Content-Type": "application/json",
        Authorization: "Bearer " + this.$store.state.token,
      });

      const requestOptions = {
        method: "PUT",
        headers: myHeaders,
        body: JSON.stringify({
          countryName: this.countryName,
          countryCode: this.countryCode,
        }),
      };

      this.genderModal = false;
      fetch(
        `http://localhost:8080/api/v1/countries/${this.countryId}`,
        requestOptions
      )
        .then((response) => response.json())
        .then((data) => {
          this.countryId = data.id;
          this.updateAlert = true;
          document.location.reload(true);
        });
    },
  },
};
</script>