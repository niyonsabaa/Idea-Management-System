export default [
    {
      
      _name: 'CSidebarNav',
      _children: [
        {
          _name: 'CSidebarNavItem',
          name: 'User Dashboard',
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
        ]
    }
  ]