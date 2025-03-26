/**
 * .
 *
 * Thm
 *
 * ！
 */

package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("thm")
public class Thm implements Serializable {
	private static final long serialVersionUID = 1L;
	@TableId
	private Long id;
	private String username;
	private String place;
	private String bz;
	private String mobile;
	private Date cxTime;

}
