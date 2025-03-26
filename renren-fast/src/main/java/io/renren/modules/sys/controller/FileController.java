package io.renren.modules.sys.controller;


import io.renren.common.utils.FileUtil;
import io.renren.common.utils.MyFileConfig;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.File;
import io.renren.modules.sys.service.FileService;
import io.renren.modules.sys.service.impl.FileServiceImpl;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Api(description = "文件接口")
@RestController
public class FileController {


    @Autowired
    public FileService fileService;

    @Autowired
    public FileServiceImpl fileServiceImpl;


    /**
     * PC_列表
     */
    @RequiresPermissions("sys:file:list")
    @RequestMapping(value = "file/list",method = {RequestMethod.POST,RequestMethod.GET})
    public R list_PC(@RequestParam Map<String, Object> params) {
        PageUtils page = fileServiceImpl.queryPage(params);
        return R.ok().put("page", page);
    }


    //查看附件信息
//    @RequiresPermissions("sys:file:info")
    @RequestMapping("file/getFileDetail")
    public R getFileDetail(HttpServletRequest request) {
        File file = fileServiceImpl.getById(request.getParameter("id"));
        return R.ok().put("url",file.getPath());
    }



    /**
     * 保存  PC-后台上传文件
     */
    @RequestMapping(value = "uFile/uploadFile", method = {RequestMethod.POST, RequestMethod.GET})
    @Transactional
    public R uploadFile(HttpServletRequest request) {
        String classify = request.getParameter("classify");
        MultipartHttpServletRequest multipartRequest = WebUtils
                .getNativeRequest(request, MultipartHttpServletRequest.class);
        String fileName = "";
        if (multipartRequest != null) {
            Iterator<String> names = multipartRequest.getFileNames();
            while (names.hasNext()) {
                List<MultipartFile> files = multipartRequest.getFiles(names.next());
                if (files != null && files.size() > 0) {
                    for (MultipartFile file : files) {
                        fileName = file.getOriginalFilename();
                        String SUFFIX = FileUtil.getFileExt(fileName);
                        File uFile = new File();
                        uFile.setFileName(fileName);
                        uFile.setClassify(classify);
                        uFile.setCreateTime(new Date());
                        uFile.setFileType(SUFFIX);
                        String uuid = FileUtil.uuid();
                        try {
                            uFile.setPath(uploadFile(uuid,file) +uuid+"."+SUFFIX);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                         fileService.save(uFile);
                    }
                }
            }
        }
        return R.ok();
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    public static String uploadFile(String uuid, MultipartFile file) throws IllegalStateException, IOException {
        String defaultUrl = MyFileConfig.getFilePath();
        String Directory = java.io.File.separator ;
        String realUrl = defaultUrl + Directory;
        java.io.File realFile = new  java.io.File(realUrl);
        if (!realFile.exists() && !realFile.isDirectory()) {
            realFile.mkdirs();
        }
        String fullFile = realUrl + uuid + "."+FileUtil.getFileExt(file.getOriginalFilename());
        file.transferTo(new java.io. File(fullFile));
        String relativePlanUrl = Directory;
//        return relativePlanUrl.replaceAll("\\\\", "/");
        return "";
    }


    @PostMapping("file/delete")
    @RequiresPermissions("sys:file:delete")
    public R delete(@RequestBody  Long[] ids){
        for (int i = 0; i < ids.length; i++) {
            Long id=ids[i];
            fileService.removeById(id);
        }
        return R.ok();
    }

}
