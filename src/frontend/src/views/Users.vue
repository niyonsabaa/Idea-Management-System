<template>
  <div>
    <span v-if="this.$store.state.token">
      <div>
        <template>
          <div v-if="updateAlert">
            <CAlert color="primary" closeButton>
              <h4>User # {{ genderId }} , successfully updated.</h4>
            </CAlert>
          </div>
          <CModal
            title="Delete User"
            :show.sync="deleteModal"
            color="danger"
          >
            <h5>Are you sure you want to delete user {{ prefix}} {{firstName}} {{lastName}} ?</h5>
            <div class="form-actions text-right">
              <CButton @click="closeDeleteModal" color="danger">No</CButton>
              <span style="margin-left: 10px" />
              <CButton @click="deleteUser" color="success">Yes</CButton>
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
              <template #gender="{ item }">
                <td>
                  {{ item.gender.genderName }}
                </td>
              </template>
              <template #country="{ item }">
                <td>
                  {{ item.country.countryName }}
                </td>
              </template>
              <template #postfix="{ item }">
                <td>
                  {{ item.postfix.postfixName }}
                </td>
              </template>
              <template #prefix="{ item }">
                <td>
                  {{ item.prefix.prefixName }}
                </td>
              </template>
              <template #roles="{ item }">
                <td>
                  {{ item.roles[0].name }}
                  <span v-if="item.roles[1]">
                    {{ item.roles[1].name }}
                  </span>
                </td>
              </template>
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
  { label: "#", key: "id" },
  { label: "Email Address", key: "email" },
  { label: "First Name", key: "firstName" },
  { label: "Last Name", key: "lastName" },
  { label: "Username", key: "username" },
  { label: "Gender", key: "gender" },
  { label: "Country", key: "country" },
  { label: "Postfix", key: "postfix" },
  { label: "Prefix", key: "prefix" },
  { label: "Roles", key: "roles" },
  {
    key: "actions",
    label: "Actions",
    _style: "width:1%",
    sorter: false,
    filter: false,
  },
];
export default {
  name: "Users",
  data() {
    return {
      items: [],
      fields,
      deleteModal: false,
      userId: "",
      firstName: "",
      lastName:"",
      prefix:"",      
      updateAlert: false, 
    };
  },
  mounted() {
    const myHeaders = new Headers({
      "Content-Type": "application/json",
      Authorization: "Bearer " + this.$store.state.token,
    });

    fetch("http://localhost:8080/api/v1/users", {
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
      this.userId = item.id;
      this.firstName = item.firstName;
      this.lastName = item.lastName;
      this.prefix = item.prefix.prefixName;
      this.deleteModal = true;
    },
    closeDeleteModal() {
      this.deleteModal = false;
    },
    deleteUser() {
      const myHeaders = new Headers({
        "Content-Type": "application/json",
        Authorization: "Bearer " + this.$store.state.token,
      });
      const requestOptions = {
        method: "DELETE",
        headers: myHeaders,
      };
      fetch(
        `http://localhost:8080/api/v1/users/${this.userId}`,
        requestOptions
      );
      this.deleteModal = false;
      document.location.reload(true);
    },
  },
};
</script>
