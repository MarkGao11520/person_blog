package com.gwf.family.common.util;

import com.gwf.family.business.core.exception.ServiceException;
import com.gwf.family.business.core.results.ResultEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by gaowenfeng on 2017/11/7.
 */

public class UploadUtil {

    /**
     * @param uploadPath   上传路径   /upload/lanch/logo
     * @param multipartFile   上传的文件
     * @return
     */
    public static String picImport(String uploadPath, MultipartFile multipartFile) {
        try {
            UUID uuid = UUID.randomUUID();
            Calendar calendar = Calendar.getInstance();
            String userPath =
                    calendar.get(Calendar.YEAR)+
                    File.separator+calendar.get(Calendar.MONTH)+
                    File.separator+calendar.get(Calendar.DATE)+
                    File.separator;
            String realPath =uploadPath+File.separator+userPath;
            File dir = new File(realPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            //split  函数是用于按指定字符（串）或正则去分割某个字符串，结果以字符串数组形式返回；
            String fileName = multipartFile.getOriginalFilename();
            String suffix =  fileName.substring(fileName.lastIndexOf("."),
                    fileName.length()) ;
            String filename = uuid.toString() +  suffix;
            String fileurl = realPath + filename;
            multipartFile.transferTo(new File(fileurl));
            Runtime.getRuntime().exec("chmod -R 755 "+uploadPath);
            return userPath+filename;
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException(ResultEnum.UPLOAD_ERROR);
        }
    }
}
