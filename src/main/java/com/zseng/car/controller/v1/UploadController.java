package com.zseng.car.controller.v1;

import com.fasterxml.jackson.annotation.JsonView;
import com.zseng.car.common.DataResponse;
import com.zseng.car.common.OutputEntityJsonView;
import com.zseng.car.exception.BaseException;
import com.zseng.car.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by cc on 2017/4/12.
 */
@RestController("V1.UploadController")
@RequestMapping("/api/v1/upload")
@Controller
public class UploadController {

    @Autowired
    UploadService uploadService;

    @RequestMapping("/img")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse handleFileUpload(@RequestParam("file") MultipartFile file) {
        uploadService.validateFile(file);

        DataResponse response = DataResponse.create();
        try {
            uploadService.ensureImageRoot();
            String fileName = uploadService.copy(file);

            HashMap<String, Object> fileDataMap = new HashMap<>();
            fileDataMap.put("name", fileName);
            response.put("file", fileDataMap);

        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
            throw new BaseException(BaseException.ERROR, "文件上传失败 ");
        }

        return response;
    }

}
