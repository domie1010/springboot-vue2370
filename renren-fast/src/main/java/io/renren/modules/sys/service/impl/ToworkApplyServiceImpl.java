/**
 * .
 *
 * ToworkApply
 *
 * ！
 */

package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.ToworkApplyDao;
import io.renren.modules.sys.entity.ToworkApply;
import io.renren.modules.sys.service.ToworkApplyService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("toworkApplyService")
public class ToworkApplyServiceImpl extends ServiceImpl<ToworkApplyDao, ToworkApply> implements ToworkApplyService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String applyUser = (String)params.get("applyUser");
		String stu = (String)params.get("stu");
		IPage<ToworkApply> page = this.page(
			new Query<ToworkApply>().getPage(params),
			new QueryWrapper<ToworkApply>()
				.like(StringUtils.isNotBlank(applyUser),"apply_user", applyUser)
					.eq(StringUtils.isNotBlank(stu),"stu", stu)
		);

		return new PageUtils(page);
	}


}