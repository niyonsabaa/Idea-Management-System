<template>
  <CRow>
    <CCol sm="6" lg="3">
      <CWidgetDropdown color="primary" :header=number text="Total Users">      
        </CWidgetDropdown>
    </CCol>
    <CCol sm="6" lg="3">
      <CWidgetDropdown color="info" :header=number text="Total Ideas">
       
      </CWidgetDropdown>
    </CCol>
    <CCol sm="6" lg="3">
      <CWidgetDropdown
        color="warning"
        :header=number
        text="Total notes"
      >        
      </CWidgetDropdown>
    </CCol>
    <CCol sm="6" lg="3">
      <CWidgetDropdown
        color="success"
        :header=number
        text="Total Attachments"
      >        
      </CWidgetDropdown>
    </CCol>
  </CRow>
</template>

<script>


export default {
  name: 'WidgetsDropdown',
  data(){
    return{
      number:50
    }

  }
  
}
</script>
