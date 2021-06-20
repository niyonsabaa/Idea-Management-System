<template>

  <CDropdown
    inNav
    class="c-header-nav-items"
    placement="bottom-end"
    add-menu-classes="pt-0"
  >
    <template #toggler>
      <CHeaderNavLink>        
        <div class="c-avatar">          
          <img
            src="img/avatars/1.jpg"
            class="c-avatar-img "
          />
        </div>
      </CHeaderNavLink>
    </template>
    <CDropdownHeader tag="div" class="text-center" color="light">
      My Account
    </CDropdownHeader>
    
    
    <CDropdownItem @click="viewProfile">
      <CIcon name="cil-user" /> Profile
    </CDropdownItem>
    <CDropdownItem>
      <CIcon name="cil-settings" /> Change Password
    </CDropdownItem>
    
    
    <CDropdownDivider/>
   
    <CDropdownItem @click="logout">
      <CIcon name="cil-lock-locked" /> Logout
    </CDropdownItem>
  </CDropdown>
</template>

<script>
export default {
  name: 'TheHeaderDropdownAccnt',
  data () {
    return { 
      
    }
  },
  methods:{
    logout(){
      sessionStorage.clear();      
      this.$router.push("/") 
      document.location.reload(true)
    },
    viewProfile(){      
      this.$router.push("/profile");
      document.location.reload(true);
      }
  }
}
</script>

<style scoped>
  .c-icon {
    margin-right: 0.3rem;
  }
</style>