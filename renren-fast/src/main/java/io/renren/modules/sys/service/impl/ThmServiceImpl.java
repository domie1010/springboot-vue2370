/**
 * .
 *
 * Thm
 *
 * ！
 */

package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.SysUserDao;
import io.renren.modules.sys.dao.ThmDao;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.entity.Thm;
import io.renren.modules.sys.service.SysRoleService;
import io.renren.modules.sys.service.SysUserRoleService;
import io.renren.modules.sys.service.SysUserService;
import io.renren.modules.sys.service.ThmService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("ThmService")
public class ThmServiceImpl extends ServiceImpl<ThmDao, Thm> implements ThmService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String username = (String)params.get("username");

		IPage<Thm> page = this.page(
			new Query<Thm>().getPage(params),
			new QueryWrapper<Thm>()
				.like(StringUtils.isNotBlank(username),"username", username)
		);

		return new PageUtils(page);
	}


}