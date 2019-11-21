package enablehr.cloud.storage

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.GetObjectRequest
import java.io.File

class FileManager {
    fun getClient(
        endpoint: String,
        accessKey: String,
        secretKey: String
    ) =
        AmazonS3ClientBuilder
            .standard()
            .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(endpoint, Regions.DEFAULT_REGION.toString()))
            .withPathStyleAccessEnabled(true)
            .withCredentials((
                    AWSStaticCredentialsProvider(
                        BasicAWSCredentials(
                            accessKey,
                            secretKey
                        ))))
            .build()
        .apply {
            println("S3 endpoint is ${endpoint}")
        }

    fun upload(
        s3Client: AmazonS3,
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
        s3Client: AmazonS3,
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
