<template>
  <div>
    <div>
      <template>
        <div v-if="updateAlert">
          <CAlert color="primary" closeButton>
            <h4>Gender Record # {{ genderId }} , successfully updated.</h4>
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
            <template #actions="{ item, index }">
              <td class="d-flex py-2">
                <CButton
                  color="danger"
                  variant="outline"
                  square
                  size="sm"
                  @click="deleteGender(item, index)"
                >
                  Delete
                </CButton>

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
      fields,
      genderModal: false,
      genderId: "",
      gender_name: "",
      updateAlert: false,
    };
  },
  mounted() {
    const myHeaders = new Headers({
      "Content-Type": "application/json",
      Authorization:
        "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbGV4bnNhYmEiLCJleHAiOjE2MjMwODI1NjksImlhdCI6MTYyMzA2NDU2OX0.-e3VjnBgR8g-xiAVgxqeVPXv-OgnHQ3FE8KdUGbcrtwAR67AuTYQ3AvBIxYS4xCHH-aFrFUkZVXXQ30Hd6Qp3g",
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
    deleteGender(item) {
      const requestOptions = {
        method: "DELETE",
        headers: { "Content-Type": "application/json" },
      };
      fetch(`api/v1/gender/${item.id}`, requestOptions);
    },

    loadGenderModel(item) {
      this.genderId = item.id;
      this.gender_name = item.genderName;
      this.genderModal = true;
    },
    updateGender() {
      const requestOptions = {
        method: "PATCH",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ genderName: this.gender_name }),
      };
      this.genderModal = false;
      fetch(`api/v1/gender/${this.genderId}`, requestOptions)
        .then((response) => response.json())
        .then((data) => {
          this.genderId = data.id;
          this.updateAlert = true;
        });
    },
  },
};
</script>
