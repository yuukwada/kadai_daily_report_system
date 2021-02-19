package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;

public class S3Connect {

    static final String S3_ACCESS_KEY           = "AKIAU6VATB3KH655VZ45";
    static final String S3_SECRET_KEY           = "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";
    static final String S3_SERVICE_END_POINT    = "s3://yw-imagebucket/test/";
    static final String S3_REGION               = "ap-northeast-1";
    static final String S3_BUCKET_NAME          = "yw_image_bucket";


    private static AmazonS3 auth(){
        System.out.println("auth start");

        AWSCredentials credentials = new BasicAWSCredentials(S3_ACCESS_KEY, S3_SECRET_KEY);

        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProxyHost("[proxyHost]");
        clientConfig.setProxyPort(8080);


        EndpointConfiguration endpointConfiguration = new EndpointConfiguration(S3_SERVICE_END_POINT,  S3_REGION);

        AmazonS3 client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withClientConfiguration(clientConfig)
                .withEndpointConfiguration(endpointConfiguration).build();

        System.out.println("auth end");
        return client;


    }

    private static void upload() throws Exception{
        System.out.println("upload start");

        AmazonS3 client = auth();

        File file = new File("/uploaded");
        FileInputStream fis = new FileInputStream(file);


        ObjectMetadata om = new ObjectMetadata();
        om.setContentLength(file.length());

        final PutObjectRequest putRequest = new PutObjectRequest(S3_BUCKET_NAME, file.getName(), fis, om);

        putRequest.setCannedAcl(CannedAccessControlList.PublicRead);

        client.putObject(putRequest);

        fis.close();

        System.out.println("upload end");
    }

    private static void download() throws Exception{
        System.out.println("download start");

        // 認証処理
        AmazonS3 client = auth();

        final GetObjectRequest getRequest = new GetObjectRequest(S3_BUCKET_NAME, "${employee.image}");

        S3Object object = client.getObject(getRequest);

        FileOutputStream fos = new FileOutputStream(new File("/employees/show?id=${employee.id}'"));
        IOUtils.copy(object.getObjectContent(), fos);

        fos.close();

        System.out.println("download end");
    }

}
