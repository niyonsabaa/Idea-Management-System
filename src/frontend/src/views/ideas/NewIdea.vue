<template>
  <div>
    <span v-if="this.$store.state.token">
      <CContainer fluid>
        <div v-if="createIdeaAlert">
          <CAlert color="primary" closeButton>
            <h4>Idea, # {{ ideaId }} is successfully created.</h4>
          </CAlert>
        </div>
        <CCard class="mx-4 mb-0">
          <CCardBody class="p-4">
            <CForm @submit="createIdea()">
              <h3>Create New Idea</h3>
              <CRow>
                <CCol md="6">
                  <CInput
                    label="Title"
                    type="text"
                    v-model="title"
                    required="required"
                  />
                  <label>Category</label>
                  <select
                    class="form-control"
                    v-model="category"
                    required="required"
                  >
                    <option
                      :value="cat.id"
                      v-for="cat in categoryData"
                      :key="cat.categoryName"
                    >
                      {{ cat.categoryName }}
                    </option>
                  </select>
                  <CTextarea
                    label="Description"
                    v-model="description"
                    required="required"
                  />
                  <label>Priority</label>
                  <select
                    class="form-control"
                    v-model="priority"
                    required="required"
                  >
                    <option
                      :value="prio.id"
                      v-for="prio in priorityData"
                      :key="prio.priorityName"
                    >
                      {{ prio.priorityName }}
                    </option>
                  </select>
                </CCol>
                <CCol md="6">
                  <CTextarea
                    label="Executive Summarry"
                    v-model="executiveSummary"
                    required="required"
                  />
                  <CTextarea
                    label="Background Description"
                    v-model="backgroundDescription"
                    required="required"
                  />
                  <CInput
                    label="Date Of Submission"
                    type="date"
                    v-model="submissionDate"
                    required="required"
                  />
                  <br />
                  <CButton color="success" type="submit" block>Save</CButton>
                </CCol>
              </CRow>
            </CForm>
          </CCardBody>
        </CCard>        
      </CContainer>
    </span>
    <span v-else>
      {{ this.$router.push("/") }}
    </span>
  </div>
</template>
<script>
export default {
  name: "NewIdea",
  mounted() {
    const myHeaders = new Headers({
      "Content-Type": "application/json",
      Authorization: "Bearer " + this.$store.state.token,
    });
    fetch("http://localhost:8080/api/v1/category", {
      method: "GET",
      headers: myHeaders,
    })
      .then((response) => response.json())
      .then((data) => {
        this.categoryData = data;
      });

    fetch("http://localhost:8080/api/v1/priority", {
      method: "GET",
      headers: myHeaders,
    })
      .then((response) => response.json())
      .then((data) => {
        this.priorityData = data;
      });
  },

  data() {
    return {
      createIdeaAlert: false,
      ideaId: "",
      categoryData: [],
      priorityData: [],
      title: "",
      category: "",
      description: "",
      priority: "",
      executiveSummary: "",
      backgroundDescription: "",
      submissionDate: "",
    };
  },

  methods: {
    createIdea() {
      const myHeaders = new Headers({
        "Content-Type": "application/json",
        Authorization: "Bearer " + this.$store.state.token,
      });

      const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: JSON.stringify({
          dateOfSubmission: this.submissionDate,
          ideaDescription: this.description,
          ideaTitle: this.title,
          ideaBackgroundDescription: this.backgroundDescription,
          ideaExecutiveSummary: this.executiveSummary,
        }),
      };
      fetch(
        `http://localhost:8080/api/v1/ideas/?userId=${this.$store.state.userId}&categoryId=${this.category}&priorityId=${this.priority}`,
        requestOptions
      )
        .then((response) => response.json())
        .then((data) => {
          this.ideaId = data.id;
          this.createIdeaAlert = true;          
        });
    },
  },
};
</script>
