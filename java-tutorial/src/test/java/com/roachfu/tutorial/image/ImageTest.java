package com.roachfu.tutorial.image;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifDirectoryBase;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 图片处理测试
 *
 * @author roach
 * @date 2018/12/14
 */
public class ImageTest {


    /**
     * 导入标签，使用metadata-extractor
     *
     * @param args
     */
    public static void main(String[] args) {
        readPic();
    }

    /**
     * 处理 单张 图片
     *
     * @return void
     * @date 2015-7-25 下午7:30:47
     */
    private static void readPic() {
//        File jpegFile = new File("C:\\Users\\1\\Pictures\\roach\\IMG_20181218_111200.jpg");
            File jpegFile = new File("C:\\Users\\1\\Pictures\\roach\\123.jpg");
        Metadata metadata;
        try {
            metadata = JpegMetadataReader.readMetadata(jpegFile);
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    System.out.println(tag);
                }
            }

            // obtain the Exif directory
            ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

            // query the tag's value
            Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
            System.out.println(DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));

            date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_DIGITIZED);
            System.out.println(DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));

            ExifDirectoryBase exifDirectory = metadata.getFirstDirectoryOfType(ExifDirectoryBase.class);
            date = exifDirectory.getDate(ExifDirectoryBase.TAG_DATETIME);
            System.out.println(DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));


        } catch (JpegProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
