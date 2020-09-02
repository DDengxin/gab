package com.tengzhi.base.editormd;

import com.alibaba.fastjson.JSONObject;
import com.tengzhi.base.tools.log.Log;
import com.tengzhi.base.ueditor.UEditorController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 鱼游浅水
 * @create 2020-08-17
 */


@RequestMapping("/editormd/")
@RestController
public class EditormdController {

    @Autowired
    UEditorController uEditorController;

    @RequestMapping("getFlie/{fileName}")
    public Map<String, Object> download(@PathVariable String fileName, HttpServletRequest request,HttpServletResponse response) {
        Map<String, Object> rmap = new HashMap();
        if (!uEditorController.getDownloadResponse(request, response, fileName)) {
            rmap.put("status", false);
            rmap.put("message", "获取下载链接失败");
            return rmap;
        }
        return rmap;
    }


    @PostMapping("upload")
    public JSONObject  upload(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file, HttpServletRequest request,HttpServletResponse response) throws IOException {
        FileOutputStream outputStream = null;
        String fileName = null;
        try {
            fileName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            fileName = UUID.randomUUID().toString() + fileName;
            File fileDir = uEditorController.getImgDirFile();
            outputStream = new FileOutputStream(fileDir.getAbsoluteFile() + File.separator + fileName);
            try {
                outputStream.write(file.getBytes());
            } catch (IOException e) {
                Log.error(e.getMessage(), e);
            }
        } catch (FileNotFoundException e) {
            Log.error(e.getMessage(), e);
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                Log.error(e.getMessage(), e);
            }
        }
        JSONObject jsobject = new JSONObject();
        jsobject.put("url","/editormd/getFlie/"+fileName);
        jsobject.put("success", 1);
        jsobject.put("message", "upload success!");
        return jsobject;

    }


}
