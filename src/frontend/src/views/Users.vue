<template>
  <div>
    <span v-if="this.$store.state.token">
      <div>
        <template>
          <div v-if="updateAlert">
            <CAlert color="primary" closeButton>
              <h4>User # {{ userId }} , successfully updated.</h4>
            </CAlert>
          </div>
          <CModal title="Delete User" :show.sync="deleteModal" color="danger">
            <h5>
              Are you sure you want to delete user {{ prefix }} {{ firstName }}
              {{ lastName }} ?
            </h5>
            <div class="form-actions text-right">
              <CButton @click="closeDeleteModal" color="danger">No</CButton>
              <span style="margin-left: 10px" />
              <CButton @click="deleteUser" color="success">Yes</CButton>
            </div>
            <template #footer>
              <br />
            </template>
          </CModal>

          <CModal
            title="Make User Admin"
            :show.sync="makeAdminModal"
            color="success"
          >
            <h5>
              Are you sure you want to make {{ prefix }} {{ firstName }}
              {{ lastName }} an Admin?
            </h5>
            <div class="form-actions text-right">
              <CButton @click="closeMakeAdminModal" color="danger">No</CButton>
              <span style="margin-left: 10px" />
              <CButton @click="makeAdmin" color="success">Yes</CButton>
            </div>
            <template #footer>
              <br />
            </template>
          </CModal>

          <CModal
            title="Remove Admin Role from User"
            :show.sync="removeAdminModal"
            color="success"
          >
            <h5>
              Are you sure you want to remove Admin Role from {{ prefix }}
              {{ firstName }} {{ lastName }} ?
            </h5>
            <div class="form-actions text-right">
              <CButton @click="closeRemoveAdminModal" color="danger"
                >No</CButton
              >
              <span style="margin-left: 10px" />
              <CButton @click="removeAdmin" color="success">Yes</CButton>
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
                    @click="loadUpdateModal(item, index)"
                  >
                    Edit
                  </CButton>

                  <span
                    style="margin-left: 10px"
                    v-if="!item.roles[1] && item.roles[0].name == 'User'"
                  >
                    <CButton
                      color="primary"
                      variant="outline"
                      square
                      size="sm"
                      @click="loadMakeAdminModal(item, index)"
                    >
                      Make Admin
                    </CButton>
                  </span>

                  <span
                    style="margin-left: 5px"
                    type="1"
                    v-for="role in item.roles"
                    :key="role.name"
                  >
                    <span span v-if="role.name == 'Admin'">
                      <CButton
                        color="primary"
                        variant="outline"
                        square
                        size="sm"
                        @click="loadRemoveAdminModal(item, index)"
                      >
                        Remove Admin Role
                      </CButton>
                    </span>
                  </span>

                  <CModal
                    title="Update User"
                    :show.sync="updateModal"
                    color="primary"
                  >
                    <CForm @submit="updateUser">
                      <CRow>
                        <CCol md="6">
                          <CInput
                            label="Email Address"
                            type="email"
                            v-model="email"
                            :placeholder="email"
                            required="required"
                          />
                          <label>Prefix</label>
                          <select
                            class="form-control"
                            v-model="prefix"
                            required="required"
                          >
                            <option
                              :value="pref.id"
                              v-for="pref in prefixData"
                              :key="pref.prefixName"
                            >
                              {{ pref.prefixName }}
                            </option>
                          </select>

                          <CInput
                            label="First Name"
                            type="text"
                            v-model="firstName"
                            :placeholder="firstName"
                            required="required"
                          />

                          <CInput
                            label="Last Name"
                            type="text"
                            v-model="lastName"
                            :placeholder="lastName"
                            required="required"
                          />
                        </CCol>
                        <CCol md="6">
                          <label>Postfix</label>
                          <select
                            class="form-control"
                            v-model="postfix"
                            required="required"
                          >
                            <option
                              :value="postf.id"
                              v-for="postf in postfixData"
                              :key="postf.postfixName"
                            >
                              {{ postf.postfixName }}
                            </option>
                          </select>

                          <label>Gender</label>
                          <select
                            class="form-control"
                            v-model="gender"
                            required="required"
                          >
                            <option
                              :value="gender.id"
                              v-for="gender in genderData"
                              :key="gender.genderName"
                            >
                              {{ gender.genderName }}
                            </option>
                          </select>

                          <label>Country</label>
                          <select
                            class="form-control"
                            v-model="country"
                            required="required"
                          >
                            <option
                              :value="country.id"
                              v-for="country in countryData"
                              :key="country.countryName"
                            >
                              {{ country.countryCode }}
                              {{ country.countryName }}
                            </option>
                          </select>
                          <br /><br /><br />
                          <CButton color="primary" type="submit" block
                            >Update</CButton
                          >
                        </CCol>
                      </CRow>
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
      updateModal: false,
      makeAdminModal: false,
      removeAdminModal: false,
      userId: "",
      firstName: "",
      lastName: "",
      prefix: "",
      email: "",
      updateAlert: false,
      prefixData: [],
      postfixDta: [],
      genderData: [],
      countryData: [],
      prefix: "",
      postfix: "",
      gender: "",
      country: "",
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

    fetch("http://localhost:8080/api/v1/postfix", {
      method: "GET",
      headers: myHeaders,
    })
      .then((response) => response.json())
      .then((data) => {
        this.postfixData = data;
      });

    fetch("http://localhost:8080/api/v1/prefix", {
      method: "GET",
      headers: myHeaders,
    })
      .then((response) => response.json())
      .then((data) => {
        this.prefixData = data;
      });

    fetch("http://localhost:8080/api/v1/countries", {
      method: "GET",
      headers: myHeaders,
    })
      .then((response) => response.json())
      .then((data) => {
        this.countryData = data;
      });

    fetch("http://localhost:8080/api/v1/gender", {
      method: "GET",
      headers: myHeaders,
    })
      .then((response) => response.json())
      .then((data) => {
        this.genderData = data;
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

    loadUpdateModal(item) {
      this.userId = item.id;
      this.firstName = item.firstName;
      this.lastName = item.lastName;
      this.email = item.email;
      this.updateModal = true;
    },

    loadMakeAdminModal(item) {
      this.userId = item.id;
      this.firstName = item.firstName;
      this.lastName = item.lastName;
      this.prefix = item.prefix.prefixName;
      this.makeAdminModal = true;
    },

    loadRemoveAdminModal(item) {
      this.userId = item.id;
      this.firstName = item.firstName;
      this.lastName = item.lastName;
      this.prefix = item.prefix.prefixName;
      this.removeAdminModal = true;
    },

    closeDeleteModal() {
      this.deleteModal = false;
    },

    closeMakeAdminModal() {
      this.makeAdminModal = false;
    },

    closeRemoveAdminModal() {
      this.removeAdminModal = false;
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

    updateUser() {
      const myHeaders = new Headers({
        "Content-Type": "application/json",
        Authorization: "Bearer " + this.$store.state.token,
      });

      const requestOptions = {
        method: "PUT",
        headers: myHeaders,
        body: JSON.stringify({
          email: this.email,
          firstName: this.firstName,
          lastName: this.lastName,
        }),
      };

      fetch(
        `http://localhost:8080/api/v1/users/${this.userId}?genderId=${this.gender}&countryId=${this.country}&postfixId=${this.postfix}&prefixId=${this.prefix}`,
        requestOptions
      )
        .then((response) => response.json())
        .then(() => {
          this.updateModal = false;
          this.updateAlert = true;
          document.location.reload(true);
        });
    },

    makeAdmin() {
      const myHeaders = new Headers({
        "Content-Type": "application/json",
        Authorization: "Bearer " + this.$store.state.token,
      });

      const requestOptions = {
        method: "POST",
        headers: myHeaders,
      };

      fetch(
        `http://localhost:8080/api/v1/users/admin/${this.userId}`,
        requestOptions
      )
        .then((response) => response.json())
        .then(() => {
          this.makeAdminModal = false;
          document.location.reload(true);
        });
    },

    removeAdmin() {
      const myHeaders = new Headers({
        "Content-Type": "application/json",
        Authorization: "Bearer " + this.$store.state.token,
      });

      const requestOptions = {
        method: "POST",
        headers: myHeaders,
      };
      fetch(
        `http://localhost:8080/api/v1/users/user/${this.userId}`,
        requestOptions
      )
        .then((response) => response.json())
        .then(() => {
          this.removeAdminModal = false;
          document.location.reload(true);
        });
    },
  },
};
</script>
