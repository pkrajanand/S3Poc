package enablehr.cloud.storage

private const val AWS_S3_ENDPOINT = "http://localhost:4572"
private const val BUCKET_NAME = "enablehr-s3-poc-2"
private const val FILE_NAME = "FB_IMG_1456275709103_2.jpg"
private val DESTINATION = System.getProperty("user.home") + "/Downloads"

fun main() {

    val fileManager = FileManager()

    val accessKey: String = System.getProperty("aws.access.key") ?: "XXXXX"
    val secretKey: String = System.getProperty("aws.secret.key") ?: "XXXXX"

    val s3Client = fileManager.getClient(
        AWS_S3_ENDPOINT,
        accessKey,
        secretKey
    )
    fileManager.download(s3Client,
        BUCKET_NAME,
        FILE_NAME,
        DESTINATION
    )

}
