<template>
  <span v-if="this.$store.state.token">
    <div>
      <div v-if="updateAlert">
        <CAlert color="primary" closeButton>
          <h4>Priority Record # {{ priorityId }} is successfully created.</h4>
        </CAlert>
      </div>
      <CRow>
        <CCol lg="12">
          <transition name="fade">
            <CCard v-if="show">
              <CCardHeader>
                <CIcon name="cil-pencil" /> New Priority
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
                  <CForm @submit="savePriority">
                    <CInput
                      label="Priority"
                      required="required"
                      v-model="priority_name"
                      description="Enter the Priority name such as High, Low, etc."
                      type="text"
                    />
                    <CInput
                      label="Description"
                      required="required"
                      v-model="priority_description"
                      description="Enter the priority Description."
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
  name: "NewPriority",
  data() {
    return {
      show: true,
      formCollapsed: true,
      priority_name: "",
      priority_description: "",
      priorityId: "",
      updateAlert: false,
    };
  },
  methods: {
    savePriority: function () {
      const myHeaders = new Headers({
        "Content-Type": "application/json",
        Authorization: "Bearer " + this.$store.state.token,
      });
      const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: JSON.stringify({
          priorityName: this.priority_name,
          priorityDescription: this.priority_description,
        }),
      };
      fetch("http://localhost:8080/api/v1/priority", requestOptions)
        .then((response) => response.json())
        .then((data) => {
          this.priorityId = data.id;
          this.updateAlert = true;
        });
    },
  },
};
</script>