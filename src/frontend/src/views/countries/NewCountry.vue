<template>
    <span v-if="this.$store.state.token">
    <div>
      <div v-if="updateAlert">
        <CAlert color="primary" closeButton>
          <h4>Country Record # {{ countryId }} is successfully created.</h4>
        </CAlert>
      </div>
      <CRow>
        <CCol lg="12">
          <transition name="fade">
            <CCard v-if="show">
              <CCardHeader>
                <CIcon name="cil-pencil" /> New Country
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
                  <CForm @submit="saveCountry">
                    <CInput
                      label="Country"
                      required="required"
                      v-model="country_name"
                      description="Enter the country name such as Uganda, Kenya, etc."
                      type="text"
                    />
                    <CInput
                      label="Code"
                      required="required"
                      v-model="country_code"
                      description="Enter the country code such as +256, +254, etc."
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
    name:"NewCountry",
     data() {
    return {
      show: true,
      formCollapsed: true,
      country_name: "",
      country_code:"",
      countryId: "",
      updateAlert: false,
    };
  },
  methods: {
    saveCountry: function () {
      const myHeaders = new Headers({
        "Content-Type": "application/json"        
      });
      const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: JSON.stringify(
            { 
                countryName: this.country_name,
                countryCode: this.country_code
            }            
            ),
      };
      fetch("http://localhost:8080/api/v1/countries", requestOptions)
        .then((response) => response.json())
        .then((data) => {
          this.countryId = data.id;
          this.updateAlert = true;
        });
    },
  },
}
</script>