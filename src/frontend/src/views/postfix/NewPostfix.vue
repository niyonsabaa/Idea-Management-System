<template>
   <span v-if="this.$store.state.token">
    <div>
      <div v-if="updateAlert">
        <CAlert color="primary" closeButton>
          <h4>Postfix Record # {{ postfixId }} is successfully created.</h4>
        </CAlert>
      </div>
      <CRow>
        <CCol lg="12">
          <transition name="fade">
            <CCard v-if="show">
              <CCardHeader>
                <CIcon name="cil-pencil" /> New Postfix
                <div class="card-header-actions">
                  <CLink
                    class="card-header-action btn-minimize"
                    @click="formCollapsed = !formCollapsed"
                  >
                    <CIcon
                      :name="`cil-chevron-${formCollapsed ? 'bottom' : 'top'}`"
                    />
                  </CLink>
                </div>
              </CCardHeader>
              <CCollapse :show="formCollapsed">
                <CCardBody>
                  <CForm @submit="savePostfix">
                    <CInput
                      label="Postfix"
                      required="required"
                      v-model="postfix_name"
                      description="Enter the postfix name such as Jr., Sr., etc."
                      type="text"
                    />
                    <div class="form-actions">
                      <CButton type="submit" color="primary">Save</CButton>
                    </div>
                  </CForm>
                </CCardBody>
              </CCollapse>
            </CCard>
          </transition>
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
    name:"NewPostfix",
     data() {
    return {
      show: true,
      formCollapsed: true,
      postfix_name: "",
      postfixId: "",
      updateAlert: false,
    };
  },
  methods: {
    savePostfix: function () {
      const myHeaders = new Headers({
        "Content-Type": "application/json"
      });
      const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: JSON.stringify({ postfixName: this.postfix_name }),
      };
      fetch("http://localhost:8080/api/v1/postfix", requestOptions)
        .then((response) => response.json())
        .then((data) => {
          this.postfixId = data.id;
          this.updateAlert = true;
        });
    },
  },
}
</script>