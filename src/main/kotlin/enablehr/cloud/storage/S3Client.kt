package enablehr.cloud.storage

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.S3ClientOptions
import com.amazonaws.services.s3.model.GetObjectRequest
import java.io.File

class StorageManager {
    fun init(
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

    fun download(
        s3Client: AmazonS3Client,
        bucketName: String,
        targetFilename: String,
        pathToDownload: String
    ) =
        s3Client
            .getObject(GetObjectRequest(bucketName, targetFilename))
            .objectContent
            .use { inStream ->
                File("$pathToDownload/$targetFilename")
                    .outputStream()
                    .buffered()
                    .use { outStream ->
                        inStream
                            .buffered()
                            .copyTo(outStream)
                    }
            }.also {
                println("Downloading file ${targetFilename} from S3")
            }
}
