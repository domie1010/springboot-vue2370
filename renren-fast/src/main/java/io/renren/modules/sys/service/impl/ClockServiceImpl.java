/**
 * .
 *
 * Clock
 *
 * ！
 */

package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.ClockDao;
import io.renren.modules.sys.entity.Clock;
import io.renren.modules.sys.service.ClockService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("clockService")
public class ClockServiceImpl extends ServiceImpl<ClockDao, Clock> implements ClockService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String username = (String)params.get("username");

		IPage<Clock> page = this.page(
			new Query<Clock>().getPage(params),
			new QueryWrapper<Clock>()
				.like(StringUtils.isNotBlank(username),"username", username)
		);

		return new PageUtils(page);
	}


}