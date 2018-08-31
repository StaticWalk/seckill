package com.xxy.web;

import com.xxy.dto.Exposer;
import com.xxy.dto.SeckillExecution;
import com.xxy.po.Seckill;
import com.xxy.service.SeckillService;
import com.xxy.vo.SeckillResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by xiongxiaoyu
 * Data:2018/8/13
 * Time:22:41
 */

@Controller
public class SeckillController {

	@Resource
	private SeckillService seckillService;

	/**
	 * 秒杀列表
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Seckill> list = seckillService.getAll();
		model.addAttribute("list", list);
		return "list";
	}

	/**
	 * 秒杀详情
	 * @param seckillId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
	public String detial(@PathVariable("seckillId") Long seckillId, Model model) {
		if (seckillId == null) {
			return "redirect:/list";
		}
		Seckill seckill = seckillService.getById(seckillId);
		if (seckill == null) {
			return "forward:/list";
		}
		model.addAttribute("seckill", seckill);
		return "detail";
	}

	/**
	 * 秒杀地址暴露
	 *
	 * @param seckillId
	 * @return
	 */
	@RequestMapping(value = "/{seckillId}/exposer",
			method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
		SeckillResult<Exposer> result;
		try {
			Exposer exposer = seckillService.exoportSeckillUrl(seckillId);
			result = new SeckillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			result = new SeckillResult<Exposer>(false, e.getMessage());
		}
		return result;
	}

	/**
	 * 执行秒杀
	 *
	 * @param seckillId
	 * @param md5
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/{seckillId}/{md5}/execution",
			method = RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
												   @PathVariable("md5") String md5,
												   @CookieValue(value = "killPhone", required = false) Long phone) {
		if (phone == null) {
			return new SeckillResult<SeckillExecution>(false, "未注册");
		}
		SeckillResult<SeckillExecution> result;
		SeckillExecution execution = seckillService.executeSeckillProcedure(seckillId, phone, md5);
		return new SeckillResult<SeckillExecution>(true, execution);
	}

	@RequestMapping(value = "/time/now", method = RequestMethod.GET)
	@ResponseBody
	public SeckillResult<Long> now() {
		Date date = new Date();
		return new SeckillResult<Long>(true, date.getTime());
	}

}
