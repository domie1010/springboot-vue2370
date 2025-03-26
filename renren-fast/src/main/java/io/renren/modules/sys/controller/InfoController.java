/**
 * .
 *
 * Info
 *
 * ！
 */

package io.renren.modules.sys.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.Info;
import io.renren.modules.sys.service.InfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/sys/info")
public class InfoController extends AbstractController {
	@Autowired
	private InfoService InfoService;


	@GetMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = InfoService.queryPage(params);
		return R.ok().put("page", page);
	}
	
	
	@GetMapping("/info/{id}")
	public R info(@PathVariable("id") Long id){
		Info info = InfoService.getById(id);
		return R.ok().put("info", info);
	}
	
	@PostMapping("/save")
	@RequiresPermissions("sys:info:save")
	public R save(@RequestBody Info info){
		info.setCreateBy(getUser().getUsername());
		info.setCreateTime(new Date());
		InfoService.saveOrUpdate(info);
		return R.ok();
	}
	
	@PostMapping("/update")
	@RequiresPermissions("sys:info:update")
	public R update(@RequestBody Info info){
		InfoService.updateById(info);
		return R.ok();
	}

	@PostMapping("/delete")
	@RequiresPermissions("sys:info:delete")
	public R update(@RequestBody  Long[] ids){
		for (int i = 0; i < ids.length; i++) {
			Long id=ids[i];
			InfoService.removeById(id);
		}
		return R.ok();
	}


}
