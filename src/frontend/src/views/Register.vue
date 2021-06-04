<template>
  <div class="d-flex align-items-center min-vh-100">
    <CContainer fluid>
      <CRow class="justify-content-center">
        <CCol md="6">
          <CCard class="mx-4 mb-0">
            <CCardBody class="p-4">
              <div v-if="registerAlert">
                <CAlert color="primary" closeButton>
                  <h4>User, # {{ userId }} is successfully registered.</h4>
                </CAlert>
              </div>
              <CForm @submit="registerUser()">
                <h1>Register</h1>
                <p class="text-muted">Create your account</p>

                <CInput
                  label="Email Address"
                  type="email"
                  v-model="email"
                  required="required"
                  prepend="@"
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
                  prepend="@"
                />

                <CInput
                  label="Last Name"
                  type="text"
                  v-model="lName"
                  required="required"
                  prepend="@"
                />

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
                  <template #prepend-content
                    ><CIcon name="cil-lock-locked"
                  /></template>
                </CInput>
                <CButton color="success" type="submit" block
                  >Create Account</CButton
                >
              </CForm>
            </CCardBody>
          </CCard>
        </CCol>
      </CRow>
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
      registerAlert: false,
      userId:""
    };
  },
  mounted() {
    fetch("api/v1/postfix")
      .then((response) => response.json())
      .then((data) => {
        this.postfixData = data;
      });

    fetch("api/v1/prefix")
      .then((response) => response.json())
      .then((data) => {
        this.prefixData = data;
      });

    fetch("api/v1/countries")
      .then((response) => response.json())
      .then((data) => {
        this.countryData = data;
      });

    fetch("api/v1/gender")
      .then((response) => response.json())
      .then((data) => {
        this.genderData = data;
      });
  },
  methods: {
    registerUser() {
      const requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          email: this.email,
          password: this.password,
          firstName: this.fName,
          lastName: this.lName,
          gender: this.gender,
          countryId: this.country,
          postfix: this.postfix,
          prefix: this.prefix,
        }),
      };
      fetch("api/v1/users", requestOptions)
        .then((response) => response.json())
        .then((data) => {
          this.userId= data.id;
          this.registerAlert = true;
        });
    },
  },
};
</script>
