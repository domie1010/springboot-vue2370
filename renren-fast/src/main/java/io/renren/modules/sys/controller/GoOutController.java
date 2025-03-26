/**
 * .
 *
 * GoOut
 *
 * ！
 */

package io.renren.modules.sys.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.GoOut;
import io.renren.modules.sys.service.GoOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sys/goOut")
public class GoOutController extends AbstractController {
	@Autowired
	private GoOutService GoOutService;


	@GetMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = GoOutService.queryPage(params);
		return R.ok().put("page", page);
	}
	
	
	@GetMapping("/info/{id}")
	public R info(@PathVariable("id") Long id){
		GoOut goOut = GoOutService.getById(id);
		return R.ok().put("goOut", goOut);
	}
	
	@PostMapping("/save")
	public R save(@RequestBody GoOut goOut){
		goOut.setStu("未审核");
		GoOutService.saveOrUpdate(goOut);
		return R.ok();
	}
	
	@PostMapping("/update")
	public R update(@RequestBody GoOut goOut){
		GoOutService.updateById(goOut);
		return R.ok();
	}

	@PostMapping("/delete")
	public R update(@RequestBody  Long[] ids){
		for (int i = 0; i < ids.length; i++) {
			Long id=ids[i];
			GoOutService.removeById(id);
		}
		return R.ok();
	}
	
}
