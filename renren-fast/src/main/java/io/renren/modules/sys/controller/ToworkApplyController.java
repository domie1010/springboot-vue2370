/**
 * .
 *
 * ToworkApply
 *
 * ！
 */

package io.renren.modules.sys.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.ToworkApply;
import io.renren.modules.sys.service.ToworkApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sys/toworkApply")
public class ToworkApplyController extends AbstractController {
	@Autowired
	private ToworkApplyService ToworkApplyService;


	@GetMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = ToworkApplyService.queryPage(params);
		return R.ok().put("page", page);
	}
	
	
	@GetMapping("/ToworkApply/{id}")
	public R ToworkApply(@PathVariable("id") Long id){
		ToworkApply toworkApply = ToworkApplyService.getById(id);
		return R.ok().put("toworkApply", toworkApply);
	}
	
	@PostMapping("/save")
	public R save(@RequestBody ToworkApply toworkApply){
		toworkApply.setStu("未审核");
		ToworkApplyService.saveOrUpdate(toworkApply);
		return R.ok();
	}
	
	@PostMapping("/update")
	public R update(@RequestBody ToworkApply toworkApply){
		ToworkApplyService.updateById(toworkApply);
		return R.ok();
	}

	@PostMapping("/delete")
	public R update(@RequestBody  Long[] ids){
		for (int i = 0; i < ids.length; i++) {
			Long id=ids[i];
			ToworkApplyService.removeById(id);
		}
		return R.ok();
	}
	
}
