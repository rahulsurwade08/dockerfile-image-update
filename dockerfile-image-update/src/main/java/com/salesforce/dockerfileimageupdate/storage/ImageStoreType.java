package com.salesforce.dockerfileimageupdate.storage;


import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.salesforce.dockerfileimageupdate.utils.DockerfileGitHubUtil;

/**
 * ImageStoreType is an enum that contains that different types of image tag stores that are supported
 * @author amukhopadhyay
 */
public enum ImageStoreType {
    S3{
        @Override
        public ImageTagStore getStore(DockerfileGitHubUtil dockerfileGitHubUtil, String store) {
            AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
            return new S3BackedImageTagStore(s3, store);
        }
    },
    GIT{
        @Override
        public ImageTagStore getStore(DockerfileGitHubUtil dockerfileGitHubUtil, String store) {
            return dockerfileGitHubUtil.getGitHubJsonStore(store);
        }
    };

    public ImageTagStore getStore(DockerfileGitHubUtil dockerfileGitHubUtil, String store) {
        return this.getStore(dockerfileGitHubUtil, store);
    }

}


