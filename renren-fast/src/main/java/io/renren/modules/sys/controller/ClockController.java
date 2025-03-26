/**
 * .
 *
 * Clock
 *
 * ！
 */

package io.renren.modules.sys.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.Clock;
import io.renren.modules.sys.service.ClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sys/clock")
public class ClockController extends AbstractController {
	@Autowired
	private ClockService ClockService;


	@GetMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = ClockService.queryPage(params);
		return R.ok().put("page", page);
	}
	
	
	@GetMapping("/info/{id}")
	public R info(@PathVariable("id") Long id){
		Clock clock = ClockService.getById(id);
		return R.ok().put("clock", clock);
	}

	//保存检查打卡信息
	@PostMapping("/save")
	public R save(@RequestBody Clock clock){
		ClockService.saveOrUpdate(clock);
		return R.ok();
	}
	
	@PostMapping("/update")
	public R update(@RequestBody Clock clock){
		ClockService.updateById(clock);
		return R.ok();
	}
	@PostMapping("/delete")
	public R update(@RequestBody  Long[] ids){
		for (int i = 0; i < ids.length; i++) {
			Long id=ids[i];
			ClockService.removeById(id);
		}
		return R.ok();
	}
}
