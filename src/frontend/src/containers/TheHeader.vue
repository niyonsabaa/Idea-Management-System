<template>
  <CHeader fixed with-subheader light>
    <CToggler
      in-header
      class="ml-3 d-lg-none"
      @click="$store.commit('toggleSidebarMobile')"
    />
    <CToggler
      in-header
      class="ml-3 d-md-down-none"
      @click="$store.commit('toggleSidebarDesktop')"
    />
    <CHeaderBrand class="mx-auto d-lg-none" to="/">
      <div>
          <img
            src="img/logo.jpg"
            height="55"
            width="200"
          />
        </div>
    </CHeaderBrand>
    <CHeaderNav class="d-md-down-none mr-auto">
      <CHeaderNavItem class="px-3">
        <CHeaderNavLink to="/dashboard">
          <h2>Idea Management System</h2>
        </CHeaderNavLink>
      </CHeaderNavItem>
    </CHeaderNav>

     <CHeaderNav class="mr-4"> 
      <CHeaderNavItem class="d-md-down-none mx-2">
        <span>{{this.$store.state.username}}</span>
      </CHeaderNavItem>
      <TheHeaderDropdownAccnt/>
    </CHeaderNav>
    
    
    <CSubheader class="px-3">
      <CBreadcrumbRouter class="border-0 mb-0" />
    </CSubheader>
  </CHeader>
</template>

<script>
import TheHeaderDropdownAccnt from "./TheHeaderDropdownAccnt";

export default {
  name: "TheHeader",
  components: {
    TheHeaderDropdownAccnt,
  },
};
</script>
