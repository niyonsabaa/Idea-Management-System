<template>
    <div>
    <span v-if="this.$store.state.token">
      <div>
        <template>
          <div v-if="updateAlert">
            <CAlert color="primary" closeButton>
              <h4>Gender Record # {{ genderId }} , successfully updated.</h4>
            </CAlert>
          </div>
          <CCardBody>
            <CDataTable
              :items="items"
              :fields="fields"
              column-filter
              table-filter
              items-per-page-select
              :items-per-page="5"
              hover
              sorter
              pagination
            >
              <template #actions="{ item, index }">
                <td class="d-flex py-2">
                  <CButton
                    color="danger"
                    variant="outline"
                    square
                    size="sm"
                    @click="deleteGender(item, index)"
                  >
                    Delete
                  </CButton>
                  <span style="margin-left: 10px"></span>
                  <CButton
                    color="primary"
                    variant="outline"
                    square
                    size="sm"
                    @click="loadGenderModel(item, index)"
                  >
                    Edit
                  </CButton>

                  <CModal
                    title="Update Gender"
                    :show.sync="genderModal"
                    color="primary"
                  >
                    <CForm @submit="updateGender">
                      <CInput
                        label="Gender"
                        required="required"
                        v-model="gender_name"
                        :placeholder="gender_name"
                        description="Enter the gender name such as Male, Female, etc."
                        type="text"
                      />
                      <div class="form-actions">
                        <CButton type="submit" color="primary">Update</CButton>
                      </div>
                    </CForm>
                    <template #footer>
                      <br />
                    </template>
                  </CModal>
                </td>
              </template>
            </CDataTable>
          </CCardBody>
        </template>
      </div>
    </span>
    <span v-else>
      {{ this.$router.push("/") }}
    </span>
  </div>
</template>
<script>
const fields = [
  { label:"#",key: "id", _style: "min-width:200px" },

  { label:"Prefix",key: "prefixName", _style: "min-width:100px;" },

  {
    key: "actions",
    label: "Actions",
    _style: "width:1%",
    sorter: false,
    filter: false,
  },
];
export default {
    name:"PrefixList",
    data() {
    return {
      items: [],
      fields,      
    };
  },
  mounted() {
    const myHeaders = new Headers({
      "Content-Type": "application/json",
      Authorization: "Bearer " + this.$store.state.token,
    });

    fetch("http://localhost:8080/api/v1/prefix", {
      method: "GET",
      headers: myHeaders,
    })
      .then((response) => response.json())
      .then((data) => {
        //alert(JSON.stringify(data))
        this.items = data;
      });
  },
}
</script>