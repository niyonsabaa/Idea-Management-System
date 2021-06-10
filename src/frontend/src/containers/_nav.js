export default [
  {
    
    _name: 'CSidebarNav',
    _children: [
      {
        _name: 'CSidebarNavItem',
        name: 'Dashboard',
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
      }   

    ]
  }
]