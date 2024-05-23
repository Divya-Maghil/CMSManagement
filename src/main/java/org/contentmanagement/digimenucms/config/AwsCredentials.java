package org.contentmanagement.digimenucms.config;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component("credentials")
@Data
public class AwsCredentials {

    @Value("${aws.accessKey}")
    private String ACCESS_KEY;
    @Value("${aws.secretKey}")
    private String SECRET_KEY;
    @Value("${aws.s3.bucket}")
    private String BUCKET_NAME;

}
