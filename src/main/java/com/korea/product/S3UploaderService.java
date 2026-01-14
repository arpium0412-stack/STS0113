package com.korea.product;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;


@Service
public class S3UploaderService {

	@Autowired  // AwsS3Config 에서 s3Client() 주입 
	private  S3Client s3Client;
	
	@Value("${spring.cloud.aws.s3.bucket}")
	private  String  bucketName;
	
	public String uploadFile(MultipartFile  file){
	   String fileName ="";
	   try {
		   fileName = UUID.randomUUID()+"_"+file.getOriginalFilename();
		   PutObjectRequest  putObjectRequest = PutObjectRequest.builder()
											   .bucket(bucketName)
											   .key(fileName)
											   .contentType(file.getContentType())
											   .build();
		  s3Client.putObject(putObjectRequest, 
				             RequestBody.fromInputStream(
				            		 file.getInputStream(), 
				            		 file.getSize()
				            		 )
				             );
	}  catch (Exception e) {
		// e.printStackTrace();
		throw new RuntimeException("===> S3 파일 업로드 실패", e);
	}
	   return fileName;
	}
	
	public void deleteFile(String  fileName) {
		   DeleteObjectRequest  deleteRequest = DeleteObjectRequest.builder()
				   .bucket(bucketName)
				   .key(fileName)
				   .build();
        s3Client.deleteObject(deleteRequest);
	}
	
}
