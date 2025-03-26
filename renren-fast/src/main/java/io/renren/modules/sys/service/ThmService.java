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
import io.renren.modules.sys.entity.Thm;

import java.util.Map;


public interface ThmService extends IService<Thm> {

	PageUtils queryPage(Map<String, Object> params);

}
