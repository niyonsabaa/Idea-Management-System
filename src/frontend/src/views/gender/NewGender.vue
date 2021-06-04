<template>
  <div>
    <div v-if="updateAlert">
          <CAlert color="primary" closeButton>
           <h4> Gender Record # {{genderId}} is successfully created.</h4>
          </CAlert>
        </div>
    <CRow>
      <CCol lg="12">
        <transition name="fade">
          <CCard v-if="show">
            <CCardHeader>
              <CIcon name="cil-pencil" /> New Gender
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
                <CForm @submit="saveGender">
                  <CInput
                    label="Gender"
                    required="required"
                    v-model="gender_name"
                    description="Enter the gender name such as Male, Female, etc."
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
</template>

<script>
export default {
  name: "NewGender",
  data() {
    return {
      show: true,
      formCollapsed: true,
      gender_name: "",
      genderId: "",
      updateAlert: false
    };
  },
  methods: {
    saveGender: function () {
      const requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ genderName: this.gender_name }),
      };
      fetch("api/v1/gender", requestOptions)
        .then((response) => response.json())
        .then((data) => {
          this.genderId = data.id;
          this.updateAlert = true;
          });
    }
  },
};
</script>
