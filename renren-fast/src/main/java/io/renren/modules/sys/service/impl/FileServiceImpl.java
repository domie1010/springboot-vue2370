package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.FileDao;
import io.renren.modules.sys.entity.File;
import io.renren.modules.sys.service.FileService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("fileService")
public class FileServiceImpl extends ServiceImpl<FileDao, File> implements FileService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String fileName = (String)params.get("fileName");
        IPage<File> page = this.page(
                new Query<File>().getPage(params),
                new QueryWrapper<File>()
                        .like(StringUtils.isNotBlank(fileName),"file_name", fileName)
        );
        return new PageUtils(page);
    }
}