<template>
  <div class="c-app flex-row align-items-center">
    <CContainer>
      <CRow class="justify-content-center">
        <div v-if="this.$store.state.successRegistration">
          <CAlert color="success" closeButton>
            <h4>You have successfully Registered, You can now Login.</h4>
          </CAlert>
        </div>
        <CCol md="8">
          <CCardGroup>
            <CCard class="p-4">
              <CCardBody>
                <!-- <p>{{this.$store.state.token}}</p> -->
                <CForm @submit="login">
                  <h1 class="text-center">Login</h1>
                  <h6 class="text-center">Log In to your account</h6>
                  <CInput
                    placeholder="Username"
                    v-model="username"
                    required="required"
                  >
                    <template #prepend-content
                      ><CIcon name="cil-user"
                    /></template>
                  </CInput>
                  <CInput
                    placeholder="Password"
                    type="password"
                    v-model="password"
                    required="required"
                  >
                    <template #prepend-content
                      ><CIcon name="cil-lock-locked"
                    /></template>
                  </CInput>
                  <CRow>
                    <CCol col="6" class="text-left">
                      <CButton type="submit" color="primary" class="px-4"
                        >Login</CButton
                      >
                    </CCol>
                    <CCol col="6" class="text-right">
                      <CButton color="link" class="px-0"
                        ><h5>Forgot password?</h5></CButton
                      >
                      <br />

                      <CButton href="/?#/register" color="link" class="px-0"
                        ><h5>Don't have an account? Register now!</h5></CButton
                      >
                    </CCol>
                  </CRow>
                </CForm>
              </CCardBody>
            </CCard>
          </CCardGroup>
        </CCol>
      </CRow>
    </CContainer>
  </div>
</template>

<script>
import jwt_decode from "jwt-decode";
export default {
  name: "Login",
  data() {
    return {
      username: "",
      password: "",
      decodedToken: "",
    };
  },
  methods: {
    login() {
      const requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          username: this.username,
          password: this.password,
        }),
      };

      fetch(`http://localhost:8080/api/v1/authenticate`, requestOptions)
        .then((response) => response.json())
        .then((data) => {
          this.$store.commit("setToken", data.token);
          this.decodedToken = jwt_decode(data.token);
          this.$store.commit("setUsername", this.decodedToken.sub);
          this.setAuthId();
          this.$router.push("dashboard");
        });
    },

    setAuthId() {
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
          this.$store.commit("setUserId",data.id);
        });
    },
  },
};
</script>
