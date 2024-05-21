# it-claster-autonetics-api


## DEPLOYMENT
___  
> **Note:** The **sudo** command can be required to run the commands.

### 1. Change version of build in `pom.xml` and `Dockerfile`

### 2. Build project
```shell  
docker buildx build --platform linux/amd64 -t autonetics-rest-api-v{your_version} .
```  
> docker buildx build --platform linux/amd64 -t autonetics-rest-api-v1.0.0 .

### 3. Export image to `.tar`
```shell  
docker save -o {your_path_to_file}/autonetics-rest-api-v{your_version}.tar {image_id}
```  
> docker save -o /Users/pe_maksutka/Desktop/autonetics-rest-api-v1.0.0.tar 1f3e4d5e4d5e

### 4. Copy `.tar` file to server
```shell  
scp -i {your_pem_key} {your_path_to_file}/autonetics-rest-api-v{your_version}.tar autonetics@23.100.50.204:~/containers/
```  
> scp -i ~/.ssh/AutoneticsVM_key.pem /Users/pe_maksutka/Desktop/autonetics-rest-api-v1.0.0.tar autonetics@23.100.50.204:~/containers/

### 5. Connect to the server
```shell  
ssh -i {your_pem_key} autonetics@23.100.50.204
```  
> ssh -i ~/.ssh/AutoneticsVM_key.pem autonetics@23.100.50.204

### 6. Load image from `.tar` on the server
```shell  
docker load -i ~/containers/autonetics-rest-api-v{your_version}.tar
```  
> docker load -i ~/containers/autonetics-rest-api-v1.0.0.tar

### 7. Tag image
```shell  
docker tag {image_id} netrunners/autonetics-rest-api-v{your_version}
```  
> docker tag adf70c594df2 netrunners/autonetics-rest-api-v1.0.0

### 8. Run container
```shell  
docker run -it -p 8080:8080 netrunners/autonetics-rest-api-v{your_version}
```  
> docker run -it -p 8080:8080 netrunners/autonetics-rest-api-v1.0.0

### 9. Restart container
Press `CTRL + C` to exit from container and run it again using:
```shell  
docker start {your_container_name_or_id}
```  
> docker start hardcore_perlman