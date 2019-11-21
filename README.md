# AWS S3 Client for AWS LocalStack

## Setup
1. Ensure that AWS Localstack S3 is running at http://localhost:4572 
2. Fork the repository
3. Open the project in Intellij IDEA

## Run Upload
1. Open the IDE run configuration for "enablehr.cloud.storage.UploaderKt" 
2. Trigger the run configuration
3. Observe the log statements generated 
    ```
       S3 endpoint is http://localhost:4572 
       Uploading file /Users/rxp/Downloads/FB_IMG_1456275709103_2.jpg to S3
   ``` 
4. Verify that the file is uploaded to s3://enablehr-s3-poc-2


## Run Download
1. Ensure that the file, that is uploaded in the previous step, is deleted from its location. That would help to verify the successful download to the same location
2. Open the IDE run configuration for "enablehr.cloud.storage.DownloaderKt" 
3. Trigger the run configuration
4. Observe the log statements generated 
    ```
       S3 endpoint is http://localhost:4572 
       Downloading file FB_IMG_1456275709103_2.jpg from S3
   ``` 
5. Notice that the file is downloaded at the location "~/Downloads"

## References
1. https://medium.com/@shrikantjagtap99/beginner-tutorial-for-amazon-s3-service-with-kotlin-7bd2ef953d99
2. https://lobster1234.github.io/2017/04/05/working-with-localstack-command-line/
3. https://github.com/localstack/localstack
4. https://docs.aws.amazon.com/cli/latest/userguide/cli-services-s3-commands.html