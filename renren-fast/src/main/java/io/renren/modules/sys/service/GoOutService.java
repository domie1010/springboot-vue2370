/**
 * .
 *
 * Thm
 *
 * ！
 */

package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.GoOut;
import io.renren.modules.sys.entity.Info;

import java.util.Map;


public interface GoOutService extends IService<GoOut> {

	PageUtils queryPage(Map<String, Object> params);

}
