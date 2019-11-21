package enablehr.cloud.storage

private const val AWS_S3_ENDPOINT = "http://localhost:4572"
private const val BUCKET_NAME = "enablehr-s3-poc-2"
private val SOURCE_FILE_LOCATION = System.getProperty("user.home") + "/Downloads/MicrosoftTeams-image.png"

fun main() {

    val fileManager = FileManager()

    val accessKey: String = System.getProperty("aws.access.key") ?: "XXXXX"
    val secretKey: String = System.getProperty("aws.secret.key") ?: "XXXXX"

    val s3Client = fileManager.getClient(
        AWS_S3_ENDPOINT,
        accessKey,
        secretKey
    )
    fileManager.upload(
        s3Client,
        BUCKET_NAME,
        SOURCE_FILE_LOCATION,
        SOURCE_FILE_LOCATION
    )

}
