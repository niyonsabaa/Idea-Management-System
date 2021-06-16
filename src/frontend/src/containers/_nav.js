export default [
  {
    
    _name: 'CSidebarNav',
    _children: [
      {
        _name: 'CSidebarNavItem',
        name: 'Admin Dashboard',
        to: '/dashboard',
        icon: 'cil-speedometer'       
      },
      
      {
        _name: 'CSidebarNavDropdown',
        name: 'Ideas',
        route: '/ideas',
        icon: 'cil-list',
        items: [
          {
            name: 'New Idea',
            to: '/ideas/newIdea'
          },
          {
            name: 'View Ideas',
            to: '/ideas/ideaList'
          }
        ]
      },    

      {
        _name: 'CSidebarNavItem',
        name: 'View Notes',
        to: '/notes',
        icon: 'cil-comment-square',       
      },
      {
        _name: 'CSidebarNavItem',
        name: 'View Attachments',
        to: '/attachments',
        icon: 'cil-file',       
      },
      {
        _name: 'CSidebarNavItem',
        name: 'Users',
        to: '/users',
        icon: 'cil-user',       
      },
      {
        _name: 'CSidebarNavDropdown',
        name: 'Gender',
        route: '/gender',
        icon: 'cil-people',
        items: [
          {
            name: 'New Gender',
            to: '/gender/newGender'
          },
          {
            name: 'View Gender',
            to: '/gender/genderList'
          }
        ]
      },  

      {
        _name: 'CSidebarNavDropdown',
        name: 'Countries',
        route: '/counties',
        icon: 'cil-people',
        items: [
          {
            name: 'New Country',
            to: '/countries/newCountry'
          },
          {
            name: 'View Counties',
            to: '/countries/countryList'
          }
        ]
      },

      {
        _name: 'CSidebarNavDropdown',
        name: 'Prefix',
        route: '/prefix',
        icon: 'cil-people',
        items: [
          {
            name: 'New Prefix',
            to: '/prefix/newPrefix'
          },
          {
            name: 'View Prefix',
            to: '/prefix/prefixList'
          }
        ]
      },

      {
        _name: 'CSidebarNavDropdown',
        name: 'Postfix',
        route: '/postfix',
        icon: 'cil-people',
        items: [
          {
            name: 'New Postfix',
            to: '/postfix/newPostfix'
          },
          {
            name: 'View Postfix',
            to: '/postfix/postfixList'
          }
        ]
      },

      {
        _name: 'CSidebarNavDropdown',
        name: 'Category',
        route: '/category',
        icon: 'cil-people',
        items: [
          {
            name: 'New Category',
            to: '/category/newCategory'
          },
          {
            name: 'View Category',
            to: '/category/categoryList'
          }
        ]
      },

      {
        _name: 'CSidebarNavDropdown',
        name: 'Priority',
        route: '/priority',
        icon: 'cil-people',
        items: [
          {
            name: 'New Priority',
            to: '/priority/newPriority'
          },
          {
            name: 'View Priority',
            to: '/priority/priorityList'
          }
        ]
      },

    ]
  }
]