<template>
  <span v-if="this.$store.state.token">
    <div>
      <div v-if="profileAlert">
        <CAlert color="primary" closeButton>
          <h4>Your profile has been, successfully updated.</h4>
        </CAlert>
      </div>
      <CModal
        title="Edit Your Profile"
        :show.sync="profileModal"
        color="primary"
      >
        <CForm @submit="updateUser()">
          <h4 class="text-center">Update Your Profile</h4>
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
              <select class="form-control" v-model="prefix" required="required">
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
              <select class="form-control" v-model="gender" required="required">
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
                  {{ country.countryCode }} {{ country.countryName }}
                </option>
              </select>
              <br /><br /><br />
              <CButton color="success" type="submit" block>Update</CButton>
            </CCol>
          </CRow>
        </CForm>

        <template #footer>
          <br />
        </template>
      </CModal>
      <CRow style="font-size: 18px">
        <CCol md="6">
          <br />
          <table>
            <tr>
              <td><strong>Email: </strong></td>
              <td class="text-right">{{ userdata.email }}</td>
              <br />
              <br />
            </tr>
            <tr>
              <td><strong>Username: </strong></td>
              <td>{{ userdata.username }}</td>
              <br />
              <br />
            </tr>
            <tr>
              <td><strong>Gender: </strong></td>
              <td>{{ userdata.gender.genderName }}</td>
              <br />
              <br />
            </tr>
            <tr>
              <td><strong>Country:</strong></td>
              <td>
                {{ userdata.country.countryCode }}
                {{ userdata.country.countryName }}
              </td>
              <br />
              <br />
            </tr>
          </table>
        </CCol>
        <CCol md="6">
          <div class="text-center">
            <img
              src="img/avatars/1.jpg"
              height="150"
              width="150"
              class="rounded-circle"
            />
          </div>
          <table>
            <tr>
              <td><strong>Name: </strong></td>
              <td class="text-right">
                {{ userdata.prefix.prefixName }} {{ userdata.firstName }}
                {{ userdata.lastName }} {{ userdata.postfix.postfixName }}
              </td>
              <br />
              <br />
            </tr>
            <tr>
              <td><strong>Roles: </strong></td>
              <td>
                <li type="1" v-for="role in userdata.roles" :key="role.name">
                  {{ role.name }}
                </li>
              </td>
              <br />
              <br />
            </tr>
          </table>

          <CButton @click="loadProfileModal()" color="success" block
            >Edit Profile</CButton
          >
        </CCol>
      </CRow>
    </div>
  </span>
  <span v-else>
    {{ this.$router.push("/") }}
  </span>
</template>
<script>
export default {
  name: "Profile",
  data() {
    return {
      prefixData: [],
      postfixDta: [],
      genderData: [],
      countryData: [],
      userdata: "",
      email: "",
      username: "",
      firstName: "",
      lastName: "",
      prefix: "",
      postfix: "",
      gender: "",
      country: "",
      profileModal: false,
      profileAlert: false,
    };
  },
  mounted() {
    const myHeaders = new Headers({
      "Content-Type": "application/json",
      Authorization: "Bearer " + this.$store.state.token,
    });
    fetch(
      `http://localhost:8080/api/v1/users/user?username=${this.$store.state.username}`,
      {
        method: "GET",
        headers: myHeaders,
      }
    )
      .then((response) => response.json())
      .then((data) => {
        this.userdata = data;
        this.email = data.email;
        this.username = data.username;
        this.firstName = data.firstName;
        this.lastName = data.lastName;
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
    loadProfileModal() {
      this.profileModal = true;
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
        `http://localhost:8080/api/v1/users/${this.$store.state.userId}?genderId=${this.gender}&countryId=${this.country}&postfixId=${this.postfix}&prefixId=${this.prefix}`,
        requestOptions
      )
        .then((response) => response.json())
        .then(() => {
          this.profileModal = false;
          this.profileAlert = true;
          document.location.reload(true);
        });
    },
  },
};
</script>
