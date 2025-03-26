/**
 * .
 *
 * goOutk
 *
 * ！
 */

package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.GoOutDao;
import io.renren.modules.sys.entity.GoOut;
import io.renren.modules.sys.service.GoOutService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("goOutService")
public class GoOutServiceImpl extends ServiceImpl<GoOutDao, GoOut> implements GoOutService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String username = (String)params.get("username");
		String stu = (String)params.get("stu");
		IPage<GoOut> page = this.page(
			new Query<GoOut>().getPage(params),
			new QueryWrapper<GoOut>()
				.like(StringUtils.isNotBlank(username),"username", username)
					.eq(StringUtils.isNotBlank(stu),"stu", stu)
		);

		return new PageUtils(page);
	}


}