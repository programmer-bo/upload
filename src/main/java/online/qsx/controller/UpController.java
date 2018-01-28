package online.qsx.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

@Controller
@RequestMapping("upload")
public class UpController {
    //http://localhost:8080/upload/to_upload
    @GetMapping("to_upload")
    public String to_upload(){
        return "upload";
    }

    //http://localhost:80/upload/do_upload
    @PostMapping("do_upload")
    @ResponseBody
    public String do_upload(MultipartFile file, HttpServletRequest request) throws Exception{
        //文件夹路径
        String url=request.getSession().getServletContext().getRealPath("/upload");
        if(!new File(url).exists()){
            new File(url).mkdir();
        }
        System.out.println(url);

        //文件名
        String fileName= UUID.randomUUID().toString().replaceAll("-","")+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),file.getOriginalFilename().length());
        System.out.println(fileName);
        FileUtils.copyInputStreamToFile(file.getInputStream(),new File(url+File.separator+fileName));

        return "{'url':'"+fileName+"'}";
    }
}
