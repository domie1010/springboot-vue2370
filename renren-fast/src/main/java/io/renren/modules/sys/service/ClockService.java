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
import io.renren.modules.sys.entity.Clock;

import java.util.Map;


public interface ClockService extends IService<Clock> {

	PageUtils queryPage(Map<String, Object> params);

}
