<template>
  <div>
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

                <CButton
                  color="primary"
                  variant="outline"
                  square
                  size="sm"
                  @click="loadIdeaModel(item, index)"
                >
                  Edit
                </CButton>

                <CButton
                  color="primary"
                  variant="outline"
                  square
                  size="sm"
                  @click="loadAddNotesModal(item, index)"
                >
                  Add Notes
                </CButton>

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
  </div>
</template>
<script>
import axios from "axios";
const fields = [
  { label: "#", key: "id" },
  { label: "Title", key: "ideaTitle" },
  { label: "Category", key: "category" },
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
    fetch("api/v1/ideas")
      .then((response) => response.json())
      .then((data) => {
        this.items = data;
      });

    fetch("api/v1/category")
      .then((response) => response.json())
      .then((data) => {
        this.categoryData = data;
      });

    fetch("api/v1/priority")
      .then((response) => response.json())
      .then((data) => {
        this.priorityData = data;
      });
  },

  methods: {
    deleteIdea(item) {
      const requestOptions = {
        method: "DELETE",
        headers: { "Content-Type": "application/json" },
      };
      fetch(`api/v1/ideas/${item.id}`, requestOptions);
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
      const requestOptions = {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
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
        `api/v1/ideas/${this.ideaId}/?userId=1&categoryId=${this.category}&priorityId=${this.priority}`,
        requestOptions
      )
        .then((response) => response.json())
        .then((data) => {
          this.ideaId = data.id;
          this.updateAlert = true;
        });
    },

    saveNote() {
      const requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          noteContent: this.content,
        }),
      };
      this.addNotesModal = false;
      fetch(`api/v1/notes?ideaId=${this.ideaId}`, requestOptions)
        .then((response) => response.json())
        .then((data) => {
          this.noteId = data.id;
          this.addNotesAlert = true;
        });
    },

    saveAttachment() {
      axios({
        url: "api/v1/attachments",
        method: "POST",
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
