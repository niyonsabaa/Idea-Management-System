<template>
  <div class="d-flex align-items-center min-vh-100">
    <CContainer fluid>
      <CModal title="Account Creation Failed" :show.sync="errorModal" color="danger">
        <h6>Account Already exists, or the username is Already taken.</h6>        
        <template #footer>
          <br />
        </template>
      </CModal>
      <CCard class="mx-4 mb-0">
        <CCardBody class="p-4">
          <CForm @submit="registerUser()">
            <h1 class="text-center">Register</h1>
            <p class="text-muted text-center">Create your account</p>
            <CRow>
              <CCol md="6">
                <CInput
                  label="Email Address"
                  type="email"
                  v-model="email"
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
                  v-model="fName"
                  required="required"
                />

                <CInput
                  label="Last Name"
                  type="text"
                  v-model="lName"
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
                    {{ country.countryCode }} {{ country.countryName }}
                  </option>
                </select>

                <CInput
                  label="Password"
                  required="required"
                  v-model="password"
                  type="password"
                >
                  <!-- <template #prepend-content
                    ><CIcon name="cil-lock-locked"
                  /></template> -->
                </CInput>
                <CInput
                  label="Username"
                  required="required"
                  v-model="username"
                  type="text"
                />
                <CButton color="success" type="submit" block
                  >Create Account</CButton
                >
                <br />
                <CButton href="/" color="link"
                  ><h5>
                    Already have an account? Click here to login
                  </h5></CButton
                >
              </CCol>
            </CRow>
          </CForm>
        </CCardBody>
      </CCard>
    </CContainer>
  </div>
</template>

<script>
export default {
  name: "Register",
  data() {
    return {
      prefixData: [],
      postfixDta: [],
      genderData: [],
      countryData: [],
      gender: "",
      email: "",
      prefix: "",
      fName: "",
      lName: "",
      country: "",
      password: "",
      username: "",
      errorModal:false
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
    registerUser() {
      const myHeaders = new Headers({
        "Content-Type": "application/json",
        Authorization: "Bearer " + this.$store.state.token,
      });
      const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: JSON.stringify({
          email: this.email,
          password: this.password,
          firstName: this.fName,
          lastName: this.lName,
          username: this.username,
        }),
      };
      fetch(
        `http://localhost:8080/api/v1/users?genderId=${this.gender}&countryId=${this.country}&postfixId=${this.postfix}&prefixId=${this.prefix}`,
        requestOptions
      )
        .then((response) => response.json())
        .then(() => {
          this.$store.commit("setSuccessRegistration");
          this.$router.push("/");
        })
        .catch(() =>{
          this.errorModal =true;
        });
    },
  },
};
</script>
