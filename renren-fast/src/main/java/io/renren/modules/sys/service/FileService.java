package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.File;

import java.util.Map;

public interface FileService extends IService<File> {
    PageUtils queryPage(Map<String, Object> params);
}