<template>
  <div>
    <span v-if="this.$store.state.token">
      <div>
        <template>
          <div v-if="updateAlert">
            <CAlert color="primary" closeButton>
              <h4>Idea Record # {{ ideaId }} , successfully updated.</h4>
            </CAlert>
          </div>
          <div v-if="addNotesAlert">
            <CAlert color="primary" closeButton>
              <h4>Note Record # {{ noteId }} , successfully saved.</h4>
            </CAlert>
          </div>
          <div v-if="addAttachmentAlert">
            <CAlert color="primary" closeButton>
              <h4>
                Attachment Record # {{ attachmentId }} , successfully saved.
              </h4>
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
              <template #category="{ item }">
                <td>
                  {{ item.category.categoryName }}
                </td>
              </template>
              <template #priority="{ item }">
                <td>
                  {{ item.priority.priorityName }}
                </td>
              </template>
              <template #user="{ item }">
                <td>{{ item.user.firstName }} {{ item.user.lastName }}</td>
              </template>

              <template #actions="{ item, index }">
                <td class="d-flex py-2">
                  <CButton
                    color="danger"
                    variant="outline"
                    square
                    size="sm"
                    @click="deleteIdea(item, index)"
                  >
                    Delete
                  </CButton>
                  <span style="margin-left: 10px"></span>
                  <CButton
                    color="primary"
                    variant="outline"
                    square
                    size="sm"
                    @click="loadIdeaModel(item, index)"
                  >
                    Edit
                  </CButton>
                  <span style="margin-left: 10px"></span>
                  <CButton
                    color="primary"
                    variant="outline"
                    square
                    size="sm"
                    @click="loadAddNotesModal(item, index)"
                  >
                    Add Notes
                  </CButton>
                  <span style="margin-left:10px"></span>
                  <CButton
                    color="primary"
                    variant="outline"
                    square
                    size="sm"
                    @click="loadAttachmentModal(item, index)"
                  >
                    Attach File
                  </CButton>

                  <CModal
                    title="Update Idea"
                    :show.sync="ideaModal"
                    color="primary"
                  >
                    <CForm @submit="updateIdea()">
                      <CInput
                        label="Title"
                        type="text"
                        v-model="title"
                        :placeholder="title"
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
                        :placeholder="description"
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

                      <CTextarea
                        label="Executive Summarry"
                        v-model="executiveSummary"
                        placeholder="executiveSummary"
                        required="required"
                      />
                      <CTextarea
                        label="Background Description"
                        v-model="backgroundDescription"
                        :placeholder="backgroundDescription"
                        required="required"
                      />

                      <div class="form-actions">
                        <CButton type="submit" color="primary">Update</CButton>
                      </div>
                    </CForm>
                    <template #footer>
                      <br />
                    </template>
                  </CModal>

                  <CModal
                    title="Add Notes"
                    :show.sync="addNotesModal"
                    color="primary"
                  >
                    <CForm @submit="saveNote()">
                      <CTextarea
                        label="Notes Content"
                        v-model="content"
                        required="required"
                      />

                      <div class="form-actions">
                        <CButton type="submit" color="primary">Save</CButton>
                      </div>
                    </CForm>
                    <template #footer>
                      <br />
                    </template>
                  </CModal>

                  <CModal
                    title="Add Attachment"
                    :show.sync="attachmentModal"
                    color="primary"
                  >
                    <form ref="uploadForm" @submit="saveAttachment">
                      <label>Attach File</label><br />
                      <input
                        type="file"
                        ref="uploadFile"
                        @change="handleFileUpload()"
                        required="required"
                      />

                      <div class="form-actions">
                        <br />
                        <CButton
                          type="button"
                          @click="saveAttachment()"
                          name="upload"
                          value="upload"
                          color="primary"
                          >Upload</CButton
                        >
                      </div>
                    </form>
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
import axios from "axios";
const fields = [
  { label: "#", key: "id" },
  { label: "Category", key: "category" },
  { label: "Title", key: "ideaTitle" },
  { label: "Submitted on", key: "dateOfSubmission" },
  { label: "Description", key: "ideaDescription" },
  { label: "Background", key: "ideaBackgroundDescription" },
  { label: "Executive Summary", key: "ideaExecutiveSummary" },
  { label: "Priority", key: "priority" },
  { label: "Submitted By", key: "user" },
  {
    key: "actions",
    label: "Actions",
    _style: "width:1%",
    sorter: false,
    filter: false,
  },
];

export default {
  name: "IdeaList",
  data() {
    return {
      items: "",
      fields,
      ideaModal: false,
      ideaId: "",
      updateAlert: false,
      addNotesAlert: false,
      addAttachmentAlert: false,
      title: "",
      description: "",
      executiveSummary: "",
      backgroundDescription: "",
      category: "",
      priority: "",
      categoryData: [],
      priorityData: [],
      addNotesModal: false,
      attachmentModal: false,
      content: "",
      noteId: "",
      attachmentId: "",
      formData: null,
    };
  },
  mounted() {
    const myHeaders = new Headers({
      "Content-Type": "application/json",
      Authorization: "Bearer " + this.$store.state.token,
    });

    fetch("http://localhost:8080/api/v1/ideas", {
      method: "GET",
      headers: myHeaders,
    })
      .then((response) => response.json())
      .then((data) => {
        this.items = data;
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

  methods: {
    deleteIdea(item) {
      const myHeaders = new Headers({
        "Content-Type": "application/json",
        Authorization: "Bearer " + this.$store.state.token,
      });
      const requestOptions = {
        method: "DELETE",
        headers: myHeaders,
      };
      fetch(`http://localhost:8080/api/v1/ideas/${item.id}`, requestOptions);
    },

    loadIdeaModel(item) {
      this.ideaId = item.id;
      this.ideaModal = true;
      this.title = item.ideaTitle;
      this.description = item.ideaDescription;
      this.executiveSummary = item.ideaExecutiveSummary;
      this.backgroundDescription = item.ideaBackgroundDescription;
    },

    loadAddNotesModal(item) {
      this.ideaId = item.id;
      this.addNotesModal = true;
    },

    loadAttachmentModal(item) {
      this.ideaId = item.id;
      this.attachmentModal = true;
    },

    updateIdea() {
      const myHeaders = new Headers({
        "Content-Type": "application/json",
        Authorization: "Bearer " + this.$store.state.token,
      });
      const requestOptions = {
        method: "PUT",
        headers: myHeaders,
        body: JSON.stringify({
          dateOfSubmission: this.submissionDate,
          ideaDescription: this.description,
          ideaTitle: this.title,
          ideaBackgroundDescription: this.backgroundDescription,
          ideaExecutiveSummary: this.executiveSummary,
        }),
      };
      this.ideaModal = false;
      fetch(
        `http://localhost:8080/api/v1/ideas/${this.ideaId}/?userId=1&categoryId=${this.category}&priorityId=${this.priority}`,
        requestOptions
      )
        .then((response) => response.json())
        .then((data) => {
          this.ideaId = data.id;
          this.updateAlert = true;
        });
    },

    saveNote() {
      const myHeaders = new Headers({
        "Content-Type": "application/json",
        Authorization: "Bearer " + this.$store.state.token,
      });
      const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: JSON.stringify({
          noteContent: this.content,
        }),
      };
      this.addNotesModal = false;
      fetch(
        `http://localhost:8080/api/v1/notes?ideaId=${this.ideaId}`,
        requestOptions
      )
        .then((response) => response.json())
        .then((data) => {
          this.noteId = data.id;
          this.addNotesAlert = true;
        });
    },

    saveAttachment() {
      const myHeaders = new Headers({
        "Content-Type": "multipart/form-data",
        Authorization: "Bearer " + this.$store.state.token,
      });
      axios({
        url: "http://localhost:8080/api/v1/attachments",
        method: "POST",
        headers: myHeaders,
        data: this.formData,
      }).then((response) => {
        this.addAttachmentAlert = true;
        this.attachmentId = response.data.id;
        this.attachmentModal = false;
      });
    },

    handleFileUpload() {
      let file = this.$refs.uploadFile.files[0];
      this.formData = new FormData();
      this.formData.append("file", file);
      this.formData.append("ideaId", this.ideaId);
    },
  },
};
</script>
