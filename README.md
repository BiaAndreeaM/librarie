# SummerSchool @IBM Library Project


## HOW TO ACCESS SWAGGER INTERFACE
Follow URL with the project **running** on the local machine: http://localhost:8080/swagger-ui/index.html

## How to clone GIT Repo

### Option 1

- Click on the "Code" button
- Copy the HTTPS link
- On your PC, go to the desired location and right click -> Show more options -> Git Bash Here
- In the command line that opens enter the following command: `$ git clone <Shift + Insert to paste the URL copied on stape 2> `

I've made a short GIF highlighting the steps needed to clone the repo.
Once the cloning steps are done, you can open the project in Intellij.

![git clone](https://github.com/user-attachments/assets/1094b53e-b42a-41a0-a86c-50120125939e)

### Option 2 

- Click on the `Code` button
- Click `Download ZIP`
- Project will be downloaded and you need to un-ZIP it

In this way, the project will no longer be "linked" to the initial project's GIT repo, and you can add it to any of your own repositories.

## Keycloak config

Keycloak is an open-source identity and access management (IAM) tool. It manages users, login, roles, permissions, and security in applications. It as a ready-made login and user management system that can be connected to any apps.

Keycloak can be downloaded as a packaged distribution: download latest keycloak zip from the Keycloak official downloads and then extract it
- https://www.keycloak.org/downloads (download Distribution powered by Quarkus zip)


### Keycloak config

- to enable keycloak:
  - uncomment spring security and oauth2 resource server dependencies in `build.gradle`
  - uncomment security classes in package `security`
  - uncomment config in `application.properties`
  - follow the steps below to configure keycloak server

- after extracting, go to folder `/bin` and open a `bash` terminal to start auth server
  - `KC_BOOTSTRAP_ADMIN_USERNAME=admin KC_BOOTSTRAP_ADMIN_PASSWORD=admin kc.sh start-dev`
  - after running this command once, it can be run without giving the user credentials, since the user has been created & persisted in keycloak
    - `kc.sh start-dev`

- open another `bash` terminal in the same location to configure KC server (this configuration can also be done from the Keycloak web console, running on localhost:8080 -> admin/admin)
  - connect to KC server
    - `kcadm.sh config credentials --server http://localhost:8080 --user admin --password admin --realm master`
  - create a realm
    - `kcadm.sh create realms -s realm="$REALM_NAME" -s enabled=true`
  - create a client
    - `kcadm.sh create clients -r $REALM_NAME -s clientId="myclient" -s enabled=true -s name="My Client" -s protocol=openid-connect -s publicClient=true -s standardFlowEnabled=true -s 'redirectUris=["https://www.keycloak.org/app/*"]' -s baseUrl="https://www.keycloak.org/app/" -s 'webOrigins=["*"]'`
  - create a user
    - `kcadm.sh create users -r $REALM_NAME -s username="myuser" -s enabled=true -s firstName="My" -s lastName="User" -s email="my.user@email.com"`
  - set a password for user
    - `kcadm.sh set-password -r $REALM_NAME --username "myuser" --new-password "myuser"`
  - create a role
    - `kcadm.sh create roles -r=$MY_REALM -s name=$MY_ROLE`
  - add role to user
    - `kcadm.sh add-roles -r=$MY_REALM --uusername $USER_ID --rolename $MY_ROLE`
    - 
- Valid redirect uris 
  - in order for the redirects to keycloak to work, open Keycloak admin web console:
    - select realm 
    - go to client 
    - go to settings
    - for field "Valid Redirect URIs" set:
      - http://localhost:8081/login/oauth2/code/keycloak
      - http://localhost:8081/*

- Roles configuration 
  - in order for spring to read the roles correctly from the token, open Keycloak admin web console:
    - select realm 
    - go to client scopes
    - go to roles scope
    - go to mapper
    - go to realm/client roles:
      - check boxes: Add to ID token, Add to access token, Add to user info, Add to token introspecton, Multivalued, Add to lightweight access token

