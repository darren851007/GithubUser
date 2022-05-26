GitHub User App (K)
===
User List
---
  Use Retrofit to get whole user from github api and bind into the recyclerView.

    Each of list contains 
    1. 'login' which is get from 'login' of response body
    2. Users picture which is use Glide function to load from 'avatar_url' of response body
    3. Get 'site_admin' from response body, and check if it is true then there have "staff" text under the 'login'
    4. Set onItemClick to watch user's detail info
Deatail User
---
  When click the chosen user, it would get received 'login' message from User List then to get detail user infomation from github Get a User api.
  
    In the detail information page, it would bind the related infromation into the detail view
    1. User name
    2. User's picture
    3. User bio
    4. User login
    5. User's country
    6. User's blog url
    
