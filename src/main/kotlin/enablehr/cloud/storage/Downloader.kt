package enablehr.cloud.storage

private const val AWS_S3_ENDPOINT = "http://localhost:4572"
private const val BUCKET_NAME = "enablehr-s3-poc-2"
private val S3_OBJECT_KEY = System.getProperty("user.home") + "/Downloads/MicrosoftTeams-image.png"

fun main() {
    val fileManager = FileManager()
    val s3Client = fileManager.getClient(
        AWS_S3_ENDPOINT,
        System.getProperty("aws.access.key") ?: "XXXXX",
        System.getProperty("aws.secret.key") ?: "XXXXX"
    )
    fileManager.download(
        s3Client,
        BUCKET_NAME,
        S3_OBJECT_KEY
    )

}
