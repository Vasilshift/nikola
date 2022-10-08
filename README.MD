APP REQUIREMENTS

Create one spring boot app with necessary dependencies that can manipulate postgres database.


-spring boot app
-postgreSQL base
-build two docker images, one for the app, one for the database
-table should be auto created with correct fields (jpa-hibernate)


table containing:
unique id (auto increment/serial)
name of the user (text)
state of the user (text)

API
- POST create user
  input (name)
  output  id, name and status 'CREATED'
- PUT update user
  input (id)
  output  id, name and status 'UPDATED' (change the status of the user to 'UPDATED')
- DEL remove user
  input (id)
  output  id, name and status 'REMOVED' (remove user from the table)
- GET get user
  input (id)
  output  id, name and current status
- GET get_all_users
  input (none)
  output  all users with id, name and current status


example call
create_user {"name":"Petar"} -> {"id":11, "name":"Petar", "status":"CREATED"}