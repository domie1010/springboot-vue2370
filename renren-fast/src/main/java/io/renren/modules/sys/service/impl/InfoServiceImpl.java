/**
 * .
 *
 * Info
 *
 * ！
 */

package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.InfoDao;
import io.renren.modules.sys.entity.Info;
import io.renren.modules.sys.service.InfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("infoService")
public class InfoServiceImpl extends ServiceImpl<InfoDao, Info> implements InfoService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String title = (String)params.get("title");

		IPage<Info> page = this.page(
			new Query<Info>().getPage(params),
			new QueryWrapper<Info>()
				.like(StringUtils.isNotBlank(title),"title", title)
		);

		return new PageUtils(page);
	}


}