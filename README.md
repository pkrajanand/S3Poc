# Using LocalStack to work with AWS S3 service

## Setup LocalStack
1. Installation 
   ```
      brew install python@2
      python --version
      pip --version
      pip install "localstack[full]" --user localstack
   ```
   Note: Open issue with pip3: https://github.com/localstack/localstack/issues/829
2. Configure Services
    ```
       export PORT_WEB_UI=9090
       localstack web
       export SERVICES=s3
       export DATA_DIR=~/Works/LocalStack/DataDir
       localstack start --host // will run `aws endpoint` at 4572, while `web` at 9090
   ```   
   Note: Another better free 'web' alternative is https://getcommandeer.com/docs/download-app

## Working with LocalStack
### Using AWS CLI
#### Setup AWS CLI
   ```
      pip install awscli-local --user localstack
      aws configure //needed to run cli commands and require a proper AWS account     
   ```
#### Working with S3
1. Create bucket
   ```
        aws --endpoint http://localhost:4572 s3 mb s3://enablehr-s3-poc-2
        aws --endpoint http://localhost:4572 s3 ls --recursive
   ```
2. Upload file 
   ```
        aws --endpoint-url=http://localhost:4572 s3 cp Pictures/FB_IMG_1456275709103_2.jpg s3://enablehr-s3-poc-2
   ```
3. Remove file
   ```
        aws --endpoint-url=http://localhost:4572 s3 rm s3://enablehr-s3-poc-2/FB_IMG_1456275709103_2.jpg
   ```   

### Using AWS Java SDK 
#### Setup Dev Environment
1. Fork this repository
2. Open the project in Intellij IDEA
3. Make sure the S3 bucket is created through AWS CLI using the steps mentioned above.
4. Location of the file is hard-coded to `/Users/rxp/Downloads/MicrosoftTeams-image.png`. Edit the variable to suit to your needs.

#### Upload file
1. Remove file from S3, if it already exists
   ```
        aws --endpoint-url=http://localhost:4572 s3 rm s3://enablehr-s3-poc-2 --recursive
   ```
2. Trigger the run configuration for "enablehr.cloud.storage.UploaderKt" 
3. Observe the log statements generated 
    ```
       S3 endpoint is http://localhost:4572 
       Uploading file /Users/rxp/Downloads/MicrosoftTeams-image.png to S3
   ``` 
4. Verify that the file is uploaded to s3://enablehr-s3-poc-2
   ```
       aws --endpoint http://localhost:4572 s3 ls s3://enablehr-s3-poc-2 --recursive
   ```

#### Download file
1. Remove the file from /Users/rxp/Downloads/MicrosoftTeams-image.png
2. Trigger the run configuration for "enablehr.cloud.storage.DownloaderKt" 
3. Observe the log statements generated 
    ```
       S3 endpoint is http://localhost:4572 
       Downloading file /Users/rxp/Downloads/MicrosoftTeams-image.png from S3
   ``` 
4. Notice that the file is downloaded at the location "/Users/rxp/Downloads/MicrosoftTeams-image.png"

## References
2. https://github.com/localstack/localstack
8. https://localstack.cloud/#pricing
1. https://lobster1234.github.io/2017/04/05/working-with-localstack-command-line/
3. https://docs.aws.amazon.com/cli/latest/userguide/cli-services-s3-commands.html
5. https://www.baeldung.com/aws-s3-java
1. https://medium.com/@shrikantjagtap99/beginner-tutorial-for-amazon-s3-service-with-kotlin-7bd2ef953d99
7. https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html
