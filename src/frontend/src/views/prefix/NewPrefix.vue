<template>
    <span v-if="this.$store.state.token">
    <div>
      <div v-if="updateAlert">
        <CAlert color="primary" closeButton>
          <h4>Prefix Record # {{ prefixId }} is successfully created.</h4>
        </CAlert>
      </div>
      <CRow>
        <CCol lg="12">
          <transition name="fade">
            <CCard v-if="show">
              <CCardHeader>
                <CIcon name="cil-pencil" /> New Prefix
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
                  <CForm @submit="savePrefix">
                    <CInput
                      label="Prefix"
                      required="required"
                      v-model="prefix_name"
                      description="Enter the prefix name such as Eng., Mr., etc."
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
    name:"NewPrefix",
     data() {
    return {
      show: true,
      formCollapsed: true,
      prefix_name: "",
      prefixId: "",
      updateAlert: false,
    };
  },
  methods: {
    savePrefix: function () {
      const myHeaders = new Headers({
        "Content-Type": "application/json"
      });
      const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: JSON.stringify({ prefixName: this.prefix_name }),
      };
      fetch("http://localhost:8080/api/v1/prefix", requestOptions)
        .then((response) => response.json())
        .then((data) => {
          this.prefixId = data.id;
          this.updateAlert = true;
        });
    },
  },
}
</script>