package enablehr.cloud.storage

private const val AWS_S3_ENDPOINT = "http://localhost:4572"
private const val BUCKET_NAME = "enablehr-s3-poc-2"
private const val FILE_NAME = "FB_IMG_1456275709103_2.jpg"
private const val DESTINATION = "/Users/rxp/Downloads"

fun main() {

    val storageManager = StorageManager()

    val accessKey: String = System.getenv("ACCESS_KEY") ?: "XXXXX"
    val secretKey: String = System.getenv("SECRET_KEY") ?: "XXXXX"

    val s3Client = storageManager.init(
        AWS_S3_ENDPOINT,
        accessKey,
        secretKey
    )
    storageManager.download(s3Client,
        BUCKET_NAME,
        FILE_NAME,
        DESTINATION
    )

}
