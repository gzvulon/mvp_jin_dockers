# Artifactory installation notes
See [official documentation](https://www.jfrog.com/confluence/display/RTF/Installing+with+Docker)
look for `art-compose`


This file based on
```
curl -# https://jfrog.bintray.com/run/art-compose/5.1.0/art-compose | tee ./art-compose | sudo bash
```
### Changes
* removed postgre db
* use embeded mysql (mariadb)

### Usage
Spin up artifactory instance with nginx reverse proxy
```
# in artifactory dir:
docker compose up -d
```

### Configuration
To spin up artifactory with custom volumes mapping
```
# in artifactory dir:
ARTIFACTORY_COMPOSE_BASE=/opt/my-data docker compose up -d
```

### MIME types
To able to (pre)view certain file types
on artifactory or naive browser(html folders)
you should add the extention to $ARTIFACTORY_HOME/etc/mimetypes.xml
for more info look:
https://www.jfrog.com/confluence/display/RTF/Configuration+Files#ConfigurationFiles-MIMETypeAttributes

# Developing
See [Artifactory Scripts](https://github.com/JFrogDev/artifactory-scripts)
