package enablehr.cloud.storage

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.S3ClientOptions
import com.amazonaws.services.s3.model.GetObjectRequest
import java.io.File

class FileManager {
    fun getClient(
        endpoint: String,
        accessKey: String,
        secretKey: String
    ) =
        AmazonS3Client(
            BasicAWSCredentials(accessKey, secretKey)
        ).apply {
            setEndpoint(endpoint).apply {
                println("S3 endpoint is ${endpoint}")
            }
            setS3ClientOptions(
                S3ClientOptions.builder()
                    .setPathStyleAccess(true).build()
            )
        }

    fun upload(
        s3Client: AmazonS3Client,
        bucketName: String,
        key: String,
        sourceFileLocation: String
    ) {
        val putObject = s3Client.putObject(
            bucketName,
            key,
            File(sourceFileLocation)
        ).also {
            println("Uploading file ${sourceFileLocation} to S3")
        }
    }

    fun download(
        s3Client: AmazonS3Client,
        bucketName: String,
        s3ObjectKey: String
    ) =
        s3Client
            .getObject(GetObjectRequest(bucketName, s3ObjectKey))
            .objectContent
            .use { inStream ->
                File("$s3ObjectKey")
                    .outputStream()
                    .buffered()
                    .use { outStream ->
                        inStream
                            .buffered()
                            .copyTo(outStream)
                    }
            }.also {
                println("Downloading file ${s3ObjectKey} from S3")
            }
}
