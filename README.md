# AWS S3 Client

## Setup
1. Ensure that AWS Localstack S3 is running at http://localhost:4572 
2. Upload a file by name "FB_IMG_1456275709103_2.jpg" into S3 bucket "enablehr-s3-poc-2"
3. Fork the repository
4. Open the project in Intellij IDEA
5. Edit the run configuration to specify the system properties - aws.access.key & aws.secret.key
6. Trigger the run configuration
7. Observe the log statements generated 
    ```
       S3 endpoint is http://localhost:4572 \n
       Downloading file FB_IMG_1456275709103_2.jpg from S3
   ``` 
8. Notice that the file is downloaded at the location "~/Downloads"
