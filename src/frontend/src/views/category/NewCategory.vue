<template>
  <span v-if="this.$store.state.token">
    <div>
      <div v-if="updateAlert">
        <CAlert color="primary" closeButton>
          <h4>Category Record # {{ categoryId }} is successfully created.</h4>
        </CAlert>
      </div>
      <CRow>
        <CCol lg="12">
          <transition name="fade">
            <CCard v-if="show">
              <CCardHeader>
                <CIcon name="cil-pencil" /> New Category
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
                  <CForm @submit="saveCategory">
                    <CInput
                      label="Category"
                      required="required"
                      v-model="category_name"
                      description="Enter the category name such as Finance, Technology etc."
                      type="text"
                    />
                    <CInput
                      label="Description"
                      required="required"
                      v-model="category_description"
                      description="Enter the category description."
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
  name: "NewCategory",
  data() {
    return {
      show: true,
      formCollapsed: true,
      category_name: "",
      category_description: "",
      categoryId: "",
      updateAlert: false,
    };
  },
  methods: {
    saveCategory: function () {
      const myHeaders = new Headers({
        "Content-Type": "application/json",
        Authorization: "Bearer " + this.$store.state.token,
      });
      const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: JSON.stringify({
          categoryName: this.category_name,
          categoryDescription: this.category_description,
        }),
      };
      fetch("http://localhost:8080/api/v1/category", requestOptions)
        .then((response) => response.json())
        .then((data) => {
          this.categoryId = data.id;
          this.updateAlert = true;
        });
    },
  },
};
</script>