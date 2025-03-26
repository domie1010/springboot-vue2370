/**
 * .
 *
 * Thm
 *
 * ！
 */

package io.renren.modules.sys.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.Thm;
import io.renren.modules.sys.service.ThmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/thm")
public class ThmController extends AbstractController {
	@Autowired
	private ThmService ThmService;


//获取历史行程列表数据
	@GetMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = ThmService.queryPage(params);
		return R.ok().put("page", page);
	}
	
	
	@GetMapping("/info/{id}")
	public R info(@PathVariable("id") Long id){
		Thm thm = ThmService.getById(id);
		return R.ok().put("thm", thm);
	}
	
	@PostMapping("/save")
	public R save(@RequestBody Thm thm){
		ThmService.saveOrUpdate(thm);
		return R.ok();
	}
	
	@PostMapping("/update")
	public R update(@RequestBody Thm thm){
		ThmService.updateById(thm);
		return R.ok();
	}

	@PostMapping("/delete")
	public R update(@RequestBody  Long[] ids){
		for (int i = 0; i < ids.length; i++) {
			Long id=ids[i];
			ThmService.removeById(id);
		}
		return R.ok();
	}
	
}
